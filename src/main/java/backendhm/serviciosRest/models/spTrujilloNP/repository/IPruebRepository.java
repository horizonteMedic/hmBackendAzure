package backendhm.serviciosRest.models.spTrujilloNP.repository;

import backendhm.serviciosRest.models.spTrujilloNP.entity.Prueba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPruebRepository extends JpaRepository<Prueba,Long> {
}
