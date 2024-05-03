package backendhm.serviciosRest.models.azure.repository.asistencial;

import backendhm.serviciosRest.models.azure.entity.asistencial.Contrata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IContrataRepository extends JpaRepository<Contrata,String> {
    @Query(value = "select *from contratas where ruc_contrata=?;", nativeQuery=true)
    Optional<Contrata> busquedaContrataPorRuc(String rucContrata);
}
