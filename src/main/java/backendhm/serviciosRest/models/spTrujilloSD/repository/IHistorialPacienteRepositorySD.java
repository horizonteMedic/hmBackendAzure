package backendhm.serviciosRest.models.spTrujilloSD.repository;

import backendhm.serviciosRest.models.spTrujilloSD.entity.HistorialPacienteSD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IHistorialPacienteRepositorySD extends JpaRepository<HistorialPacienteSD,Long> {
    @Query(value = "select *from listadoHistorialUsuarios(?,?,?,?,?,?,?)", nativeQuery=true)
    Optional<List<HistorialPacienteSD>> obtenerHistorialPacienteUsuariosSD(String userName, String fechaInicio, String fechaFin, String tipo, String rucContrataUser, String rucEmpresaUser, String sedeUser );


}
