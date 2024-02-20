package com.app.bdt.service.serviceImpl;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import com.app.bdt.model.entity.SoftSkill;
import com.app.bdt.service.ISoftSkillService;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SoftSkillService implements ISoftSkillService {

    private EntityManager entityManager;

    public SoftSkillService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createSoftSkill(Long talentId, String skill) {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("SP_INSERT_SOFTSKILL")
                .registerStoredProcedureParameter("p_ID_TALENTO", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_NO_HABILIDAD", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("ID_OUT", Long.class, ParameterMode.OUT)
                .setParameter("p_ID_TALENTO", talentId)
                .setParameter("p_NO_HABILIDAD", skill);
        storedProcedure.execute();
    }
}
