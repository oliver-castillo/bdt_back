package com.app.bdt.service.serviceImpl;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import com.app.bdt.service.IEducationalExperienceService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
public class EducationalExperienceService implements IEducationalExperienceService {

    private EntityManager entityManager;

    public EducationalExperienceService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createEducationalExperience(Long talentId, String educationalInstitute, String career, String degree, LocalDate startDate, LocalDate endDate) {

        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("SP_INSERT_EDUCATIONAL_EXPERIENCE")
                .registerStoredProcedureParameter("p_ID_TALENTO", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_NO_INSTITUCION_EDUCATIVA", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_CARRERA", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_GRADO", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_FECHA_INICIO", LocalDate.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_FECHA_FIN", LocalDate.class, ParameterMode.IN)
                .setParameter("p_ID_TALENTO", talentId)
                .setParameter("p_NO_INSTITUCION_EDUCATIVA", educationalInstitute)
                .setParameter("p_CARRERA", career)
                .setParameter("p_GRADO", degree)
                .setParameter("p_FECHA_INICIO", startDate)
                .setParameter("p_FECHA_FIN", endDate);
        storedProcedure.execute();
    }
}
