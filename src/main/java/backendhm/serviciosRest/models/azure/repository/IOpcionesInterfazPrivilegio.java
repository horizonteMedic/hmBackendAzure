package backendhm.serviciosRest.models.azure.repository;

import backendhm.serviciosRest.models.azure.entity.OpcionesInterfazPrivilegios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOpcionesInterfazPrivilegio extends JpaRepository<OpcionesInterfazPrivilegios,Long> {
    @Query(value = "select oip.* from opciones_interfaz_privilegios as oip inner join privilegio_rol as pr on oip.id_opcion_interfaz=pr.id_opcion_interfaz\n" +
            "\t where pr.id_rol=?;", nativeQuery=true)
    Optional<List<OpcionesInterfazPrivilegios>> listadoVistasPorIdRol(long idRol);
}
