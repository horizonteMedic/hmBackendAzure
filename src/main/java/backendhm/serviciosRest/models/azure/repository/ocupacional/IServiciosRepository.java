package backendhm.serviciosRest.models.azure.repository.ocupacional;

import backendhm.serviciosRest.models.azure.entity.ocupacional.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IServiciosRepository extends JpaRepository<Servicio,Long> {
    @Query(value = "select *from servicios_hm where id_servicio=?", nativeQuery=true)
    Servicio busquedaServicioPorIdServicio(long idServicio);
}
