package backendhm.serviciosRest.models.spTrujilloSD.repository;

import backendhm.serviciosRest.models.spTrujilloSD.entity.RespuestaBackendTSD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRespuestaBackendSDRepository extends JpaRepository<RespuestaBackendTSD,Long> {

    @Query(value = "SELECT COD_PA as id_resp, Cast('valor encontrado' as text) as mensaje FROM N_ORDEN_OCUPACIONAL WHERE N_ORDEN=?", nativeQuery=true)
    Optional<RespuestaBackendTSD> busquedaDniPorNorden(long nOrden);

    @Query(value = "select *from registro_automatico_servidorSantoDomingo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", nativeQuery=true)
    Optional<RespuestaBackendTSD> registroDatosPacienteServidorSD(long codPa, String nombresPa, String fechaNaciminetoPa, String sexoPa, String emailPa, String lugarNacPa, String nivelEstPa, String ocupacionPa, String estadoCivilPa, String direccionPa, String departamentoPa, String provinciaPa, String distritoPa, String caserioPA, String fotoPa, long codAleatorioPa, String telCasaPa, String telTrabajoPa, String celPa, String fechaRegistroPa, String apellidosPa, String horaRegistroPa, long tipoDoc);


    @Query(value = "select row_number() over() as id_resp, razon_empresa as mensaje from empresas where length(ruc_empresa)>=11;", nativeQuery=true)
    Optional<List<RespuestaBackendTSD>> listadoEmpresasNP();

    @Query(value = "select row_number() over() as id_resp, razon_contrata as mensaje from contratas where length(ruc_contrata)>=11;", nativeQuery=true)
    Optional<List<RespuestaBackendTSD>> listadoContratasNP();

    @Query(value = "select row_number() over() as id_resp,CONCAT(NOMBRE_USER,' ',APELLIDO_USER) AS mensaje from  usuarios;", nativeQuery=true)
    Optional<List<RespuestaBackendTSD>> listadoMedicos();

    @Query(value = "select row_number() over() as id_resp,descripcion as mensaje from tipoPruebas;", nativeQuery=true)
    Optional<List<RespuestaBackendTSD>> listadoTipoPrueba();

    @Query(value = "select row_number() over() as id_resp,ocupacion_pa as mensaje from ocupaciones_pa;", nativeQuery=true)
    Optional<List<RespuestaBackendTSD>> listadoCargo();

    @Query(value = "select row_number() over() as id_resp,area_o as mensaje from area_ocupacional;", nativeQuery=true)
    Optional<List<RespuestaBackendTSD>> listadoArea();

    @Query(value = "select row_number() over() as id_resp, nom_examen as mensaje from examen_medico_ocupacional;", nativeQuery=true)
    Optional<List<RespuestaBackendTSD>> listadoTipoExamen();

    @Query(value = "select row_number() over() as id_resp, nom_ex as mensaje from explotacion_en_po;", nativeQuery=true)
    Optional<List<RespuestaBackendTSD>> listadoExplotacion();

    @Query(value = "select row_number() over() as id_resp, mineral_po as mensaje from mineral_ex_po;", nativeQuery=true)
    Optional<List<RespuestaBackendTSD>> listadoMineral();

    @Query(value = "select row_number() over() as id_resp, altura_po as mensaje from altura_po;", nativeQuery=true)
    Optional<List<RespuestaBackendTSD>> listadoAltura();

    @Query(value = "select row_number() over() as id_resp, precio_examen  AS mensaje from examen_medico_ocupacional where nom_examen =?;", nativeQuery=true)
    Optional<RespuestaBackendTSD> precioExamen(String name_examen);


}
