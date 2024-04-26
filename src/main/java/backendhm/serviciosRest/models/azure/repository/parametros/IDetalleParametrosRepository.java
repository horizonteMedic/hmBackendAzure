package backendhm.serviciosRest.models.azure.repository.parametros;

import backendhm.serviciosRest.models.azure.entity.parametros.DetalleParametros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDetalleParametrosRepository extends JpaRepository<DetalleParametros,Long> {

    @Query(value = "select dp.id_Detalle_parametro,dp.descripcion,dp.id_lista_parametros from detalle_parametro as dp inner join \n" +
            "\tlista_parametros as lp on dp.id_lista_parametros=lp.id_lista_parametros\n" +
            "\twhere lp.descripcion=?;", nativeQuery=true)
    Optional<List<DetalleParametros>> listadoDetalleParametrizable(String listaDetalle);
}
