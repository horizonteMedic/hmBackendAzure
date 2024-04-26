package backendhm.serviciosRest.models.azure.repository;

import backendhm.serviciosRest.models.azure.entity.HistorialUsuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IHistorialUsuarioRepository extends JpaRepository<HistorialUsuarios,Long> {

    @Query(value = "select row_number() over() as id_historial_usuario,emp.tipo_doc as tipo_documento, num_documento, apellidos, nombres, usu.username as usuario, rr.rol, ur.estado as vigente,usu.id_user,ur.id_user_rol from empleado as emp inner join usuario as usu on emp.id_empleado=usu.id_empleado inner join\n" +
            "            usuario_rol as ur on usu.id_user=ur.id_user inner join rol as rr on ur.id_rol=rr.id_rol;", nativeQuery=true)
    Optional<List<HistorialUsuarios>> listadoHistorialUsuarios();
}
