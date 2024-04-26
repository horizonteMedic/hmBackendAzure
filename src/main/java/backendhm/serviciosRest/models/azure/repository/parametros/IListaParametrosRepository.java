package backendhm.serviciosRest.models.azure.repository.parametros;

import backendhm.serviciosRest.models.azure.entity.parametros.ListaParametros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IListaParametrosRepository extends JpaRepository<ListaParametros,Long> {


}
