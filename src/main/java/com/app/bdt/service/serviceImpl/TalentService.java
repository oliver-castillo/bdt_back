package com.app.bdt.service.serviceImpl;


import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import com.app.bdt.model.dto.*;
import com.app.bdt.model.entity.SoftSkill;
import com.app.bdt.model.entity.TechnicalSkills;
import org.springframework.stereotype.Service;

import com.app.bdt.model.entity.Talent;
import com.app.bdt.model.mapper.ITalentMapper;
import com.app.bdt.service.ITalentService;

@Service
@Transactional
public class TalentService implements ITalentService {

    private static long nextId = 1;

    private final EntityManager entityManager;

    private final ITalentMapper talentMapper;
    private final TechnicalSkillService technicalSkillService;
    private final SoftSkillService softSkillService;

    private final WorkExperienceService workExperienceService;
    private final EducationalExperienceService educationalExperienceService;

    Logger log = Logger.getLogger(this.getClass().getName());

    public TalentService(EntityManager entityManager,
                         ITalentMapper talentMapper,
                         TechnicalSkillService technicalSkillService,
                         SoftSkillService softSkillService,
                         WorkExperienceService workExperienceService,
                         EducationalExperienceService educationalExperienceService
    ) {
        this.entityManager = entityManager;
        this.talentMapper = talentMapper;
        this.technicalSkillService = technicalSkillService;
        this.softSkillService = softSkillService;
        this.workExperienceService = workExperienceService;
        this.educationalExperienceService = educationalExperienceService;
    }

    @Override
    public List<TalentDto> getTalents() {
        try {
            StoredProcedureQuery storedProcedure = entityManager
                    .createStoredProcedureQuery("SP_GET_TALENTOS");

            storedProcedure.execute();

            List<Object[]> results = storedProcedure.getResultList();
            List<TalentDto> talents = new ArrayList<>();

            for (Object[] result : results) {
                Talent talent = new Talent();
                talent.setId(((Integer) result[0]).longValue());
                talent.setName((String) result[1]);
                talent.setPaternalSurname((String) result[2]);
                talent.setMaternalSurname((String) result[3]);
                talent.setImage((byte[]) result[4]);
                talent.setDescription((String) result[5]);
                talent.setInitialAmount(((Integer) result[6]).doubleValue());
                talent.setFinalAmount(((Integer) result[7]).doubleValue());
                talent.setCellPhoneNumber((String) result[8]);
                talent.setLinkedinLink((String) result[9]);
                talent.setGithubLink((String) result[10]);

                // Convierte el array de bytes de la imagen a una cadena en base64
                String base64Image = Base64.getEncoder().encodeToString(talent.getImage());

                // Crea un nuevo objeto TalentDto y establece sus propiedades
                TalentDto talentDto = talentMapper.toTalentDto(talent);
                talentDto.setImage(base64Image);

                talents.add(talentDto);
            }

            return talents;
        } catch (Exception e) {
            log.warning(e.getMessage());
            return null;
        }
    }

    @Override
    public TalentDto createTalent(TalentDto talentDto) {
        String base64Image = talentDto.getImage();
        if (base64Image.contains(",")) {
            base64Image = base64Image.split(",")[1];
        }
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);

        Talent talent = talentMapper.toTalent(talentDto);
        // Establece la imagen
        talent.setImage(imageBytes);
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("SP_INSERT_TALENT")
                    .registerStoredProcedureParameter("p_NO_NOMBRE", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("p_AP_APELLIDO_PATERNO", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("p_AP_APELLIDO_MATERNO", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("p_IM_IMAGEN", byte[].class, ParameterMode.IN)
                    .registerStoredProcedureParameter("p_DE_DESCRIPCION", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("p_NU_MONTO_INICIAL", Double.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("p_NU_MONTO_FINAL", Double.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("p_NU_CELULAR", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("p_DI_LINKDN", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("p_DI_GITHUB", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("ID_OUT", Long.class, ParameterMode.OUT)
                    .setParameter("p_NO_NOMBRE", talent.getName())
                    .setParameter("p_AP_APELLIDO_PATERNO", talent.getPaternalSurname())
                    .setParameter("p_AP_APELLIDO_MATERNO", talent.getMaternalSurname())
                    .setParameter("p_IM_IMAGEN", talent.getImage() != null ? talent.getImage() : new byte[0])
                    .setParameter("p_DE_DESCRIPCION", talent.getDescription())
                    .setParameter("p_NU_MONTO_INICIAL", talent.getInitialAmount())
                    .setParameter("p_NU_MONTO_FINAL", talent.getFinalAmount())
                    .setParameter("p_NU_CELULAR", talent.getCellPhoneNumber())
                    .setParameter("p_DI_LINKDN", talent.getLinkedinLink())
                    .setParameter("p_DI_GITHUB", talent.getGithubLink());

            storedProcedure.execute();

            Long generatedId = (Long) storedProcedure.getOutputParameterValue("ID_OUT");
            talent.setId(generatedId);

            // Inserta las habilidades blandas
            for (SoftSkillDto softSkillDto : talentDto.getSoftSkillList()) {
                softSkillService.createSoftSkill(talent.getId(), softSkillDto.getSkill());
            }

            // Inserta las habilidades técnicas
            for (TechnicallSkillDto technicalSkillDto : talentDto.getTechnicalSkillList()) {
                technicalSkillService.createTechnicalSkill(talent.getId(), technicalSkillDto.getSkill(), technicalSkillDto.getYears());
            }

            for (WorkExperienceDto workExperienceDto : talentDto.getWorkExperienceList()) {
                workExperienceService.createWorkExperience(talent.getId(), workExperienceDto.getCompanyName(), workExperienceDto.getPosition(), workExperienceDto.getStartDate(), workExperienceDto.getEndDate());
            }

            for (EducationalExperienceDto educationalExperienceDto : talentDto.getEducationalExperienceList()) {
                educationalExperienceService.createEducationalExperience(talent.getId(), educationalExperienceDto.getEducationalInstitute(), educationalExperienceDto.getCareer(), educationalExperienceDto.getDegree(), educationalExperienceDto.getStartDate(), educationalExperienceDto.getEndDate());
            }

            return talentMapper.toTalentDto(talent);
        } catch (Exception e) {
            log.warning(e.getMessage());
            return null;
        }
    }

}
