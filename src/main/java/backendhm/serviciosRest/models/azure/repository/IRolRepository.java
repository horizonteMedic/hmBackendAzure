package backendhm.serviciosRest.models.azure.repository;

import backendhm.serviciosRest.models.azure.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRolRepository extends JpaRepository<Rol,Long> {

    @Query(value = "select r.* from rol as r inner join  usuario_rol as ur on r.id_rol=ur.id_rol where ur.estado=true and ur.id_user=?;", nativeQuery=true)
    Optional<List<Rol>> listadoRolesPorIdUser(long idUser);

    @Query(value = "select * from rol where estado = true;", nativeQuery=true)
    Optional<List<Rol>> listadoRolesHabilitados();
    @Query(value = "select *from listado_roles_asignados_por_username(?);", nativeQuery=true)
    Optional<List<Rol>> listadoRolesPorUsername(String Username);
}
