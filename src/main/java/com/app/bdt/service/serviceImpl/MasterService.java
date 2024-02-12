package com.app.bdt.service.serviceImpl;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.app.bdt.model.response.Ciudad;
import com.app.bdt.model.response.Pais;
import com.app.bdt.repository.IMasterRepository;
import com.app.bdt.service.IMasterService;

@Service
@Transactional
public class MasterService implements IMasterService {

    Logger log = Logger.getLogger(this.getClass().getName());

    private final IMasterRepository masterRepository;
    private final EntityManager entityManager;

    public MasterService(IMasterRepository masterRepository, EntityManager entityManager) {
        this.masterRepository = masterRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<Pais> getPaises() {
        System.out.println(obtenerPaises());
        return null;
    }

    @Override
    public List<Ciudad> getCiudades() {
        return null;
    }

    public List<Object[]> obtenerPaises() {
        StoredProcedureQuery storedProcedure = entityManager
                .createStoredProcedureQuery("SP_OBTENER_PAISES");
        storedProcedure.execute();
        List<Object[]> paises = storedProcedure.getResultList();
        return paises;
    }
}
