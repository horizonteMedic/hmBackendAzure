package backendhm.serviciosRest.models.spTrujilloSD.repository;

import backendhm.serviciosRest.models.spTrujilloSD.entity.DetalleHistorialPacienteSD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDetalleHistorialUsuarioRepositorySD extends JpaRepository<DetalleHistorialPacienteSD,Long> {
    @Query(value = "select *from listadodetalleHistorialUsuarios(?,?,?,?,?,?,?,?)", nativeQuery=true)
    Optional<List<DetalleHistorialPacienteSD>> obtenerdetalleHistorialPacienteUsuariosNP(String userName, String fechaInicio, String fechaFin, String tipo, String rucContrataUser, String rucEmpresaUser, String sedeUser, String dniUser );


}
