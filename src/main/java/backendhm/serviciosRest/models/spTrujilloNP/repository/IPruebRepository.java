package backendhm.serviciosRest.models.spTrujilloNP.repository;

import backendhm.serviciosRest.models.spTrujilloNP.entity.HistorialPACIENTE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPruebRepository extends JpaRepository<HistorialPACIENTE,Long> {

    @Query(value = "select *from listadoHistorialUsuarios(?,?,?,?,?,?)", nativeQuery=true)
    Optional<List<HistorialPACIENTE>> obtenerHistorialPacienteUsuariosNP(String userName, String fechaInicio, String fechaFin, long tipoUsuario, String rucUser, String sedeUser );
}
