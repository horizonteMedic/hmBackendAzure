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
            "  CAST( EXTRACT ('YEAR' FROM AGE(CURRENT_DATE,dp.fecha_nacimiento_pa)) AS Text) as edad_texto, noo.razon_contrata, noo.cargo_de as cargo, \n" +
            "  CAST(CASE WHEN ama.chkapto = 'TRUE' THEN 'Apto'\n" +
            "            WHEN ama.chkapto_restriccion = 'TRUE' THEN 'Apto con Restriccion'\n" +
            "            WHEN ama.chkno_apto = 'TRUE' THEN 'No Apto'\n" +
            "            WHEN fi.n_orden IS NOT NULL THEN 'INTERCONSULTA PENDIENTE'||':'||string_agg (fi.especialidad,'-')\n" +
            "            WHEN ama.n_orden IS NULL THEN 'APTITUD PENDIENTE'\n" +
            "             END AS TEXT) as Aptitud_emo, CAST( noo.fecha_apertura_po AS TEXT ) as fecha_examen, cast(' ' as text) as observacion\n" +
            "  FROM datos_paciente as dp inner join n_orden_ocupacional as noo on dp.cod_pa=noo.cod_pa inner join contratas as ct on noo.razon_contrata=ct.razon_contrata\n" +
            "LEFT join ficha_interconsulta as fi ON (noo.n_orden=fi.n_orden)\n" +
            "LEFT join aptitud_medico_ocupacional_agro as ama ON (noo.n_orden=ama.n_orden)\n" +
            "    where ct.ruc_contrata=? AND noo.fecha_apertura_po BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)\n" +
            "\n" +
            "   GROUP BY fecha_solicitud,apellidos_nombres,dni,fecha_nacimiento,edad,edad_texto,noo.razon_contrata,cargo,fecha_examen,observacion,ama.chkapto,\n" +
            "\t    ama.chkapto_restriccion,ama.chkno_apto,fi.n_orden,ama.n_orden;", nativeQuery=true)
    Optional<List<MatrizAdministrativa>> listadoMatrizAdmin(String rucContrata, String fechaInicio, String fechaFinal);
}
