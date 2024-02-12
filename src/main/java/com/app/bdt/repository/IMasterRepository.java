package com.app.bdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.bdt.model.entity.Master;

@Repository
public interface IMasterRepository extends JpaRepository<Master, Long> {

    /*
     * @Query(value =
     * "SELECT ID_UNO AS id, STRING_UNO AS nombrePais FROM BT_TM_MASTER WHERE DE_DESCRIPCION = 'PAIS'"
     * , nativeQuery = true)
     * List<Pais> getPaises();
     *
     * @Query(value =
     * "SELECT ID_UNO AS id, STRING_UNO AS nombreCiudad, ID_DOS AS paisId FROM BT_TM_MASTER WHERE DE_DESCRIPCION = 'CIUDAD'"
     * , nativeQuery = true)
     * List<Ciudad> getCiudades();
     *
     * @Query(nativeQuery = true, value =
     * "SELECT PAISES.ID_UNO AS PAIS_ID, CIUDADES.ID_UNO AS CIUDAD_ID, PAISES.STRING_UNO AS nombrePais, CIUDADES.STRING_UNO AS ciudades "
     * +
     * "FROM BT_TM_MASTER PAISES " +
     * "LEFT JOIN BT_TM_MASTER CIUDADES ON PAISES.ID_UNO = CIUDADES.ID_DOS AND CIUDADES.DE_DESCRIPCION = 'CIUDAD' "
     * +
     * "WHERE PAISES.DE_DESCRIPCION = 'PAIS'")
     * List<Pais> getPaisesConCiudades();
     *
     * @Procedure(name = "SP_OBTENER_PAISES", outputParameterName = "C_RESULTADO")
     * List<Object> obtenerPaisesSP();
     */

}
