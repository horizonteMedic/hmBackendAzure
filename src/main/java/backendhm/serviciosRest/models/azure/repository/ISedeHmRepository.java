package backendhm.serviciosRest.models.azure.repository;

import backendhm.serviciosRest.models.azure.dtos.SedePorUserDTO;
import backendhm.serviciosRest.models.azure.entity.SedeHmWeb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ISedeHmRepository extends JpaRepository<SedeHmWeb,Long> {

    @Query(value = "select *from obtener_sede_usuario(:user_name)", nativeQuery=true)
    Optional<List<SedeHmWeb>> listadoSedes(String user_name);
}
