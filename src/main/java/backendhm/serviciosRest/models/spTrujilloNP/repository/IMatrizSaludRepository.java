package backendhm.serviciosRest.models.spTrujilloNP.repository;

import backendhm.serviciosRest.models.spTrujilloNP.entity.ResponseMatrizSalud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMatrizSaludRepository extends JpaRepository<ResponseMatrizSalud,Long> {
    @Query(value = "SELECT n.n_orden AS n_orden, CAST(TO_CHAR(fecha_apertura_po,'DD/MM/YYYY') AS TEXT) as FECHASOLICITUD,d.apellidos_pa ||' '|| d.nombres_pa AS apellidos_nombres, d.cod_pa as dni,\n" +
            "      CAST(TO_CHAR(d.fecha_nacimiento_pa,'DD/MM/YYYY') AS TEXT) AS FECHANACIMIENTO,\n" +
            "      obtener_edad(d.fecha_nacimiento_pa,n.fecha_apertura_po) AS EDAD,\n" +
            "      n.razon_contrata,  n.cargo_de AS CARGO,\n" +
            "       CASE WHEN n.n_orden is null THEN '' END  as Tipotrabajo,\n" +
            "       v.txtdosis as carnet,\n" +
            "       CASE WHEN ama.chkapto = 'TRUE' THEN 'Apto'\n" +
            "            WHEN ama.chkapto_restriccion = 'TRUE' THEN 'Apto con Restriccion'\n" +
            "            WHEN ama.chkno_apto = 'TRUE' THEN 'No Apto'\n" +
            "            WHEN fi.n_orden IS NOT NULL THEN 'INTERCONSULTA PENDIENTE'||':'||string_agg (fi.especialidad,'-')\n" +
            "            WHEN ama.n_orden IS NULL THEN 'APTITUD PENDIENTE'\n" +
            "             END as APTITUD,CAST(TO_CHAR(ama.fecha,'DD/MM/YYYY') AS TEXT) AS FECHADEEVALUACION,\n" +
            "       CASE WHEN t.peso is null THEN 'INF. NO TOMADA' ELSE t.peso END,\n" +
            "       CASE WHEN t.talla is null THEN 'INF. NO TOMADA' ELSE t.talla END,\n" +
            "       CASE WHEN t.imc is null THEN 'INF. NO TOMADA' ELSE t.imc END,\n" +
            "       CASE WHEN t.imc < '18.5' THEN 'PESO BAJO'\n" +
            "\t    WHEN t.imc > '18.4' AND t.imc < '25'  THEN 'NORMAL'\n" +
            "\t    WHEN t.imc > '24.9' AND t.imc < '30'  THEN 'SOBREPESO'   \n" +
            "\t    WHEN t.imc > '29.9' AND t.imc < '35' THEN 'OBESIDAD I'\n" +
            "\t    WHEN t.imc > '34.9' AND t.imc < '40' THEN 'OBESIDAD II'\n" +
            "\t    WHEN t.imc > '39.9' THEN 'OBESIDAD'\n" +
            "\t    WHEN t.imc is null THEN 'SIN FICHA 7C'\n" +
            "       END  AS dxpeso,\n" +
            "       CASE WHEN lc.txtglucosabio IS NULL THEN 'INF. NO TOMADA' ELSE lc.txtglucosabio END AS Glucosa,    \n" +
            "       CASE WHEN ab.txtcolesterol IS NOT NULL THEN ab.txtcolesterol ELSE 'INF. NO TOMADA'\n" +
            "\t     END  AS Colesterol,        \n" +
            "       CASE WHEN ab.txttrigliseridos IS NOT NULL THEN ab.txttrigliseridos ELSE '...'\n" +
            "\t     END  AS trigliceridos, \t\n" +
            "       o.agudezaVisualLejor AS DXOFTALMO,\n" +
            "       CASE WHEN au.n_orden is not null THEN au.diagnostico \n" +
            "             WHEN au3.n_orden is not null  THEN au3.txtdiag_od ||' '|| au3.txtdiag_od \n" +
            "             WHEN au1.chkdnormal='TRUE' THEN 'NORMAL'\n" +
            "             WHEN au1.chkdtaleveod='TRUE' THEN 'Trauma Acústico Leve OD'\n" +
            "             WHEN au1.chkdtaavanzadood='TRUE' THEN 'Trauma Acústico Avanzado OD'\n" +
            "             WHEN au1.chkdhrleveod='TRUE' THEN 'Hipoacusia Inducida por Ruido, Leve OD'\n" +
            "             WHEN au1.chkdhrmoderadood='TRUE' THEN 'Hipoacusia Inducida por Ruido, Moderada OD'\n" +
            "             WHEN au1.chkdhravanzadaod='TRUE' THEN 'Hipoacusia Inducida por Ruido, Avanzada  OD'\n" +
            "             ELSE '.' END ||''|| \n" +
            "\tCASE WHEN au1.chkdtaleveoi='TRUE' THEN 'Trauma Acústico Leve OI'\n" +
            "             WHEN au1.chkdtaavanzadooi='TRUE' THEN 'Trauma Acústico Avanzado OI'\n" +
            "             WHEN au1.chkdhrleveoi='TRUE' THEN 'Hipoacusia Inducida por Ruido, Leve OI'\n" +
            "             WHEN au1.chkdhrmoderadooi='TRUE' THEN 'Hipoacusia Inducida por Ruido, Moderada OI'\n" +
            "             WHEN au1.chkdhravanzadaoi='TRUE' THEN 'Hipoacusia Inducida por Ruido, Avanzada  OI'\n" +
            "             else '.' END DXAUDIO,\n" +
            "        CASE WHEN ama.atxtrestricciones IS NOT NULL THEN ama.atxtrestricciones\n" +
            "             ELSE 'NO REGISTRO APTITUD'END AS RESTRICCIONESAPTITUD\n" +
            "FROM datos_paciente AS d\n" +
            "INNER JOIN n_orden_ocupacional AS n ON (d.cod_pa = n.cod_pa)\n" +
            "LEFT JOIN triaje AS t ON ( n.n_orden=t.n_orden)\n" +
            "LEFT JOIN oftalmologia AS o ON (n.n_orden = o.n_orden)\n" +
            "LEFT JOIN audiometria_po AS au ON (n.n_orden = au.n_orden)\n" +
            "LEFT JOIN audiometria_2021 AS au1 ON (n.n_orden = au1.n_orden)\n" +
            "LEFT JOIN audiometria_2023 AS au3 ON (n.n_orden = au3.n_orden)\n" +
            "LEFT JOIN ficha_audiologica AS fa ON(n.n_orden = fa.n_orden)\n" +
            "LEFT JOIN analisis_bioquimicos AS ab ON(n.n_orden = ab.n_orden)\n" +
            "LEFT JOIN lab_clinico AS lc ON (n.n_orden = lc.n_orden)  \n" +
            "LEFT join anexo7c as a ON (n.n_orden=a.n_orden)\n" +
            "LEFT join anexo_agroindustrial as aa ON (n.n_orden=aa.n_orden)\n" +
            "LEFT join certificado_aptitud_medico_ocupacional as ca ON (n.n_orden=ca.n_orden)\n" +
            "LEFT join aptitud_medico_ocupacional11 as ca1 ON (n.n_orden=ca1.n_orden)\n" +
            "LEFT join aptitud_medico_ocupacional_agro as ama ON (n.n_orden=ama.n_orden)\n" +
            "LEFT join observaciones as ob ON (n.n_orden=ob.n_orden)\n" +
            "left join antecedentes_patologicos as v ON(n.n_orden = v.n_orden)\n" +
            "LEFT join ficha_interconsulta as fi ON (n.n_orden=fi.n_orden)\n" +
            "inner join contratas as ct on n.razon_contrata=ct.razon_contrata\n" +
            "WHERE ct.ruc_contrata=?\n" +
            "AND n.fecha_apertura_po BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)\n" +
            "group by n.n_orden, ama.n_orden, apellidos_nombres, FECHANACIMIENTO,edad,ama.chkapto, ama.chkapto_restriccion, ama.chkno_apto, fi.n_orden,\n" +
            "t.peso,t.talla, t.imc, lc.txtglucosabio, ab.txtcolesterol, ab.txttrigliseridos, o.v_lejos_s_od, o.v_lejos_s_oi, o.e_oculares, o.e_oculares1,\n" +
            "au.n_orden, au.diagnostico, au3.n_orden, au3.txtdiag_od, DXAUDIO,d.cod_pa,v.txtdosis,o.agudezaVisualLejor;", nativeQuery=true)
    Optional<List<ResponseMatrizSalud>> listadoMatrizSalud(String rucContrata, String fechaInicio, String fechaFinal);
}
