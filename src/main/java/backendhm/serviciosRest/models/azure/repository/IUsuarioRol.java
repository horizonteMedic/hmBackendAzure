package backendhm.serviciosRest.models.azure.repository;

import backendhm.serviciosRest.models.azure.entity.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRol extends JpaRepository<UsuarioRol,Long> {
    @Query(value = "select *from usuario_rol where id_user=?;", nativeQuery=true)
    Optional<List<UsuarioRol>> listadoUsuariosRolPorIdUser(long idUser);
}
