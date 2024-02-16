package com.app.bdt.service.serviceImpl;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.entity.Talent;
import com.app.bdt.model.mapper.ITalentMapper;
import com.app.bdt.service.ITalentService;

@Service
@Transactional
public class TalentService implements ITalentService {

    private static long nextId = 1;

    private final EntityManager entityManager;

    private final ITalentMapper talentMapper;

    Logger log = Logger.getLogger(this.getClass().getName());

    public TalentService(EntityManager entityManager, ITalentMapper talentMapper) {
        this.entityManager = entityManager;
        this.talentMapper = talentMapper;
    }

    @Override
    public List<Talent> getTalents() {
        try {
            StoredProcedureQuery storedProcedure = entityManager
                    .createStoredProcedureQuery("GET_TALENTOS", Talent.class)
                    .registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);

            storedProcedure.execute();

            List<Talent> talents = storedProcedure.getResultList();
            return talents;
        } catch (Exception e) {
            log.warning(e.getMessage());
            return null;
        }
    }

    @Override
    public synchronized TalentDto createTalent(TalentDto talentDto) {
        Talent talent = talentMapper.toTalent(talentDto);
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("INSERT_TALENTO")
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

            // Get the generated ID from the stored procedure
            Long generatedId = (Long) storedProcedure.getOutputParameterValue("ID_OUT");

            // Set the generated ID to the talent object
            talent.setId(generatedId);

            return talentMapper.toTalentDto(talent);
        } catch (Exception e) {
            log.warning(e.getMessage());
            return null;
        }
    }

}
