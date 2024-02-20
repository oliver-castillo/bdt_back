package com.app.bdt.service.serviceImpl;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import com.app.bdt.service.IWorkExperienceService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
public class WorkExperienceService implements IWorkExperienceService {

    private EntityManager entityManager;

    public WorkExperienceService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createWorkExperience(Long talentId, String companyName, String position, LocalDate startDate, LocalDate endDate) {

        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("SP_INSERT_WORK_EXPERIENCE")
                .registerStoredProcedureParameter("p_ID_TALENTO", Long.class, ParameterMode.IN )
                .registerStoredProcedureParameter("p_NO_EXPERIENCIA_LABORAL", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_NO_PUESTO", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_FECHA_INICIO", LocalDate.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_FECHA_FIN", LocalDate.class, ParameterMode.IN)
                .setParameter("p_ID_TALENTO", talentId)
                .setParameter("p_NO_EXPERIENCIA_LABORAL", companyName)
                .setParameter("p_NO_PUESTO", position)
                .setParameter("p_FECHA_INICIO", startDate)
                .setParameter("p_FECHA_FIN", endDate);
        storedProcedure.execute();
    }
}
