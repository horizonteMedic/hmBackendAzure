package backendhm.serviciosRest.models.spTrujilloNP.repository;

import backendhm.serviciosRest.models.spTrujilloNP.entity.DetalleHistorialPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDetalleHistorialUsuarioRepository extends JpaRepository<DetalleHistorialPaciente,Long> {
    @Query(value = "select *from listadodetalleHistorialUsuarios(?,?,?,?,?,?,?,?)", nativeQuery=true)
    Optional<List<DetalleHistorialPaciente>> obtenerdetalleHistorialPacienteUsuariosNP(String userName, String fechaInicio, String fechaFin, String tipo, String rucContrataUser, String rucEmpresaUser, String sedeUser, String dniUser );

}
