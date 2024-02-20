package com.app.bdt.service.serviceImpl;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import com.app.bdt.service.ITechnicalSkillService;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TechnicalSkillService implements ITechnicalSkillService {
    private EntityManager entityManager;

    public TechnicalSkillService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createTechnicalSkill(Long talentId, String skill, Integer years) {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("SP_INSERT_TECHNICALSKILL")
                .registerStoredProcedureParameter("p_ID_TALENTO", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_NO_HABILIDAD", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_NU_ANIOS", Integer.class, ParameterMode.IN)
                .setParameter("p_ID_TALENTO", talentId)
                .setParameter("p_NO_HABILIDAD", skill)
                .setParameter("p_NU_ANIOS", years);
        storedProcedure.execute();
    }
}
