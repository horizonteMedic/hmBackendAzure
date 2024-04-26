package backendhm.serviciosRest.models.azure.repository.parametros;

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
}
