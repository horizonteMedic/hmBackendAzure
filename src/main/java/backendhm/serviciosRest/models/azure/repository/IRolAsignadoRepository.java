package backendhm.serviciosRest.models.azure.repository;

import backendhm.serviciosRest.models.azure.entity.RolAsignado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRolAsignadoRepository extends JpaRepository<RolAsignado,Long> {

    @Query(value = "select *from rol_asignado where id_rol=?;", nativeQuery=true)
    Optional<List<RolAsignado>> listadoRolesPoridRol(long idRol);
}
