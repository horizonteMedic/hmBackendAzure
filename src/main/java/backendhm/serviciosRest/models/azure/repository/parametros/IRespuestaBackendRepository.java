package backendhm.serviciosRest.models.azure.repository.parametros;

import backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO;
import backendhm.serviciosRest.models.azure.entity.RespuestaBackend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRespuestaBackendRepository extends JpaRepository<RespuestaBackend,Long> {

    @Query(value = "select *from generar_codigo_validar_email(:emailUser);", nativeQuery=true)
    Optional<RespuestaBackend> generarCodigo(String emailUser);


    @Query(value = "select *from usar_codigo_generado(:emailUser,:codigoGenerado);", nativeQuery=true)
    Optional<RespuestaBackend> usarCodigoGenerado(String emailUser, String codigoGenerado);

    @Query(value = "select *from tipo_usuario(:user_name)", nativeQuery=true)
    Optional<RespuestaBackend> obtenerTipoUsuario(String user_name);

    @Query(value = "select *from obtener_ruc_por_username(:user_name)", nativeQuery=true)
    Optional<RespuestaBackend> obtenerRucUsuario(String user_name);

    @Query(value = "select count(orden) as id_resp, 'validar existencia' as mensaje from archivos_servidores where historia_clinica= ? and id_tipo_archivo=?;", nativeQuery=true)
    Optional<RespuestaBackend> existenciaDelArchivo(String hc, long ta);

}
