package backendhm.serviciosRest.models.spTrujilloNP.repository;

import backendhm.serviciosRest.models.spTrujilloNP.entity.MatrizAdministrativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMatrizAdminRepository extends JpaRepository<MatrizAdministrativa,Long> {

    @Query(value = "SELECT row_number() over() as id_matriz_adm,cast (noo.fecha_apertura_po as text) as fecha_solicitud, CONCAT(dp.apellidos_pa,' ',dp.nombres_pa) as apellidos_nombres, dp.cod_pa as dni, CAST (dp.fecha_nacimiento_pa AS TEXT) as fecha_nacimiento, CAST(date_part('year', CURRENT_DATE) - date_part('year', dp.fecha_nacimiento_pa) AS integer ) as edad,\n" +
            "CAST(age(CURRENT_DATE,dp.fecha_nacimiento_pa) AS Text) as edad_texto, noo.razon_contrata, noo.cargo_de as cargo, CAST('APTO' AS TEXT) as Aptitud_emo, CAST( noo.fecha_apertura_po AS TEXT ) as fecha_examen, cast(' ' as text) as observacion\n" +
            "  FROM datos_paciente as dp inner join n_orden_ocupacional as noo on dp.cod_pa=noo.cod_pa inner join contratas as ct on noo.razon_contrata=ct.razon_contrata\n" +
            " where ct.ruc_contrata=? AND noo.fecha_apertura_po BETWEEN CAST(? AS DATE) AND CAST(? AS DATE);", nativeQuery=true)
    Optional<List<MatrizAdministrativa>> listadoMatrizAdmin(String rucContrata, String fechaInicio, String fechaFinal);
}
