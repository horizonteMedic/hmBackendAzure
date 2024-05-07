package backendhm.serviciosRest.models.azure.repository;

import backendhm.serviciosRest.models.azure.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario,Long> {
   // @Query(value = "SELECT * FROM users WHERE username=:nombre;", nativeQuery=true)
    Optional<Usuario> findByUsername(String username);

    @Query(value = "select *from obtener_datos_actualizar(:email);", nativeQuery=true)
    Optional<Usuario> detalleUsuario(String email);

    @Query(value = "select *from usuario where id_empleado=?;", nativeQuery=true)
    Optional<List<Usuario>> listadoUsuarioPorIDEmpleado(long idEmpleado);




}
