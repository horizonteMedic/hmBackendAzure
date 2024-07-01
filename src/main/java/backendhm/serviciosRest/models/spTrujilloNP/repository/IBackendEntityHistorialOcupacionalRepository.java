package backendhm.serviciosRest.models.spTrujilloNP.repository;

import backendhm.serviciosRest.models.spTrujilloNP.entity.BackendEntityHistoriaOcupacional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBackendEntityHistorialOcupacionalRepository extends JpaRepository <BackendEntityHistoriaOcupacional,Long> {

    @Query(value = "select n_orden, cod_pa, razon_empresa, razon_contrata, nom_ex, altura_po, mineral_po, fecha_apertura_po, precio_po, estado_ex,\n" +
            "       nom_examen, cargo_de, area_o, n_medico, n_hora, tipo_pago, n_fisttest, n_psicosen, n_testaltura, color, gruposan, grupofactorsan, \n" +
            "       cod_clinica, visual_compl, trab_calientes, chkcovid1, chkcovid2, manip_alimentos, txtobserv1, txtobserv2, CAST (sm.codigo_sucursal AS text) as cod_sede,\n" +
            "       tipo_prueba_covid, tipoprueba, nombrehotel, protocolo, precio_adic, autoriza, n_operacion, herra_manuales,rxc_dorso_lumbar,\n" +
            "       rxc_lumbar, rxc_lumbosacra, rxc_plomos, mercurioo  \n" +
            " from n_orden_ocupacional as n inner join sede_multisucursal as sm on n.cod_sede=sm.id where sm.codigo_sucursal=? ORDER BY n.n_orden desc limit 150\n", nativeQuery=true)
    Optional<List<BackendEntityHistoriaOcupacional>> listadoHistoriaOcupacionalSede(String codSede);

    @Query(value = "select n_orden, cod_pa, razon_empresa, razon_contrata, nom_ex, altura_po, mineral_po, fecha_apertura_po, precio_po, estado_ex,\n" +
            "       nom_examen, cargo_de, area_o, n_medico, n_hora, tipo_pago, n_fisttest, n_psicosen, n_testaltura, color, gruposan, grupofactorsan, \n" +
            "       cod_clinica, visual_compl, trab_calientes, chkcovid1, chkcovid2, manip_alimentos, txtobserv1, txtobserv2, CAST (sm.codigo_sucursal AS text) as cod_sede,\n" +
            "       tipo_prueba_covid, tipoprueba, nombrehotel, protocolo, precio_adic, autoriza, n_operacion, herra_manuales,rxc_dorso_lumbar,\n" +
            "       rxc_lumbar, rxc_lumbosacra, rxc_plomos, mercurioo  \n" +
            " from n_orden_ocupacional as n inner join sede_multisucursal as sm on n.cod_sede=sm.id where n.n_orden=?", nativeQuery=true)
    Optional<BackendEntityHistoriaOcupacional> historiaOcupacionalNOrDEN(long norden);



}
