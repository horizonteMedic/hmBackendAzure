package backendhm.serviciosRest.models.spTrujilloNP.repository;


import backendhm.serviciosRest.models.spTrujilloNP.entity.RespuestaBackendNP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRespuestaBackendNPRepository extends JpaRepository<RespuestaBackendNP,Long> {


    @Query(value = "select *from registro_automatico_huamachuco(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", nativeQuery=true)
    Optional<RespuestaBackendNP> registroDatosPacienteHuamachuco(long codPa, String nombresPa, String fechaNaciminetoPa, String sexoPa, String emailPa, String lugarNacPa, String nivelEstPa, String ocupacionPa, String estadoCivilPa, String direccionPa, String departamentoPa, String provinciaPa, String distritoPa, String caserioPA, String fotoPa, long codAleatorioPa, String telCasaPa, String telTrabajoPa, String celPa, String fechaRegistroPa, String apellidosPa, String horaRegistroPa, long tipoDoc);


    @Query(value = "select *from registro_automatico_historiaClinica(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", nativeQuery=true)
    Optional<RespuestaBackendNP> registroHistoriaClinica(long n_orden_exp, long cod_pa_exp, String razon_empresa_exp,String razon_contrata_exp,
                                                         String nom_ex_exp,String altura_po_exp,String mineral_po_exp,String fecha_apertura_po_exp,
                                                         String precio_po_exp,String estado_ex_exp,String nom_examen_exp,String cargo_de_exp,String area_o_exp,
                                                         String n_medico_exp,String n_hora_exp,String tipo_pago_exp,boolean n_fisttest_exp,boolean n_psicosen_exp,boolean n_testaltura_exp,
                                                         long color_exp,String grupo_san_exp,String grupo_factor_san_exp,String cod_clinica_exp,boolean visual_compl_exp,boolean trab_calientes_exp,
                                                         boolean chk_covid1_exp,boolean chk_covid2_exp,boolean manip_alimentos_exp,String txtobserv1_exp,String txtobserv2_exp,long cod_sede_exp,
                                                         String tipo_prueba_covid_exp,String tipo_prueba_exp,String nombre_hotel_exp,String protocolo_exp,String precio_adic_exp,String autoriza_exp,
                                                         String n_operacion_exp,boolean herra_manuales_exp,boolean rxc_dorso_lumbar_exp,boolean rxc_lumbar_exp,boolean rxc_lumbosacra_exp,boolean rxc_plomos_exp,
                                                         boolean mercurioo_exp,String referencia_exp );
    @Query(value = "SELECT COD_PA as id_resp, Cast('valor encontrado' as text) as mensaje FROM N_ORDEN_OCUPACIONAL WHERE N_ORDEN=?", nativeQuery=true)
    Optional<RespuestaBackendNP> busquedaDniPorNorden(long nOrden);

    @Query(value = "SELECT COD_PA as id_resp, Cast('valor encontrado' as text) as mensaje FROM N_ORDEN_OCUPACIONAL WHERE referencia=?", nativeQuery=true)
    Optional<RespuestaBackendNP> busquedaDniPorReferencia(String referencia);



}
