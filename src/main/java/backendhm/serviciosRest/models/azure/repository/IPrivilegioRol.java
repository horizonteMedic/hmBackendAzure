package backendhm.serviciosRest.models.azure.repository;

import backendhm.serviciosRest.models.azure.entity.PrivilegioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPrivilegioRol extends JpaRepository<PrivilegioRol,Long> {

    @Query(value = "select pr.* from privilegio_rol as pr where pr.id_rol=?;", nativeQuery=true)
    Optional<List<PrivilegioRol>> listadoPrivilegiosRolesPorIdRol(long idRol);

}
