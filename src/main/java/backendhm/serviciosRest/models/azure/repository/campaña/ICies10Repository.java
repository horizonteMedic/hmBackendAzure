package backendhm.serviciosRest.models.azure.repository.campaña;

import backendhm.serviciosRest.models.azure.entity.campaña.CIE10;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICies10Repository extends JpaRepository<CIE10,String> {
}
