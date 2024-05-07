package backendhm.serviciosRest.models.azure.repository.asistencial;

import backendhm.serviciosRest.models.azure.entity.asistencial.Contrata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContrataRepository extends JpaRepository<Contrata,String> {
    @Query(value = "select *FROM listado_cont_por_busqueda(?,?)", nativeQuery=true)
    List<Contrata> porUsernameContrata(String userName, String tipoEmpCont);
}
