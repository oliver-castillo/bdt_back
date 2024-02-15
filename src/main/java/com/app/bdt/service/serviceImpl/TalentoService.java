package com.app.bdt.service.serviceImpl;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import com.app.bdt.model.entity.Talento;
import com.app.bdt.service.ITalentoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
public class TalentoService implements ITalentoService{

    private final EntityManager entityManager;

    private static long nextId = 1;

    Logger log = Logger.getLogger(this.getClass().getName());

    public TalentoService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Talento> getTalentos() {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_TALENTOS", Talento.class)
                    .registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);

            storedProcedure.execute();

            List<Talento> talentos = storedProcedure.getResultList();
            return talentos;
        } catch (Exception e) {
            log.warning(e.getMessage());
            return null;
        }
    }

    @Override
    public synchronized Talento createTalento(Talento talento) {
        //talento.setId(nextId++);
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
                    .setParameter("p_NO_NOMBRE", talento.getNombre())
                    .setParameter("p_AP_APELLIDO_PATERNO", talento.getApellidoPaterno())
                    .setParameter("p_AP_APELLIDO_MATERNO", talento.getApellidoMaterno())
                    .setParameter("p_IM_IMAGEN", talento.getImagen() != null ? talento.getImagen() : new byte[0])
                    .setParameter("p_DE_DESCRIPCION", talento.getDescripcion())
                    .setParameter("p_NU_MONTO_INICIAL", talento.getMontoInicial())
                    .setParameter("p_NU_MONTO_FINAL", talento.getMontoFinal())
                    .setParameter("p_NU_CELULAR", talento.getNroCelular())
                    .setParameter("p_DI_LINKDN", talento.getLinkLinkedin())
                    .setParameter("p_DI_GITHUB", talento.getLinkGithub());

            storedProcedure.execute();

            // Get the generated ID from the stored procedure
            Long generatedId = (Long) storedProcedure.getOutputParameterValue("ID_OUT");

            // Set the generated ID to the talento object
            talento.setId(generatedId);

            return talento;
        } catch (Exception e) {
            log.warning(e.getMessage());
            return null;
        }
    }

}
