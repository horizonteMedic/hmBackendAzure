package backendhm.serviciosRest.models.spTrujilloSD.repository;

import backendhm.serviciosRest.models.spTrujilloSD.entity.RespuestaBackendTSD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRespuestaBackendSDRepository extends JpaRepository<RespuestaBackendTSD,Long> {

    @Query(value = "SELECT COD_PA as id_resp, Cast('valor encontrado' as text) as mensaje FROM N_ORDEN_OCUPACIONAL WHERE N_ORDEN=?", nativeQuery=true)
    Optional<RespuestaBackendTSD> busquedaDniPorNorden(long nOrden);

    @Query(value = "select *from registro_automatico_servidorSantoDomingo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", nativeQuery=true)
    Optional<RespuestaBackendTSD> registroDatosPacienteServidorSD(long codPa, String nombresPa, String fechaNaciminetoPa, String sexoPa, String emailPa, String lugarNacPa, String nivelEstPa, String ocupacionPa, String estadoCivilPa, String direccionPa, String departamentoPa, String provinciaPa, String distritoPa, String caserioPA, String fotoPa, long codAleatorioPa, String telCasaPa, String telTrabajoPa, String celPa, String fechaRegistroPa, String apellidosPa, String horaRegistroPa, long tipoDoc);

}
