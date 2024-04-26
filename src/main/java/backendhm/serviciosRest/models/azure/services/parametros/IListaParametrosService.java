package backendhm.serviciosRest.models.azure.services.parametros;

import backendhm.serviciosRest.models.azure.dtos.parametros.ListaParametrosDTO;

import java.util.List;

public interface IListaParametrosService {

    ListaParametrosDTO crearListadoParametros(ListaParametrosDTO listaParametrosDTO);
    List<ListaParametrosDTO> listadoParametros();
    ListaParametrosDTO obtenerListadoParametrosPorID(Long id);
    ListaParametrosDTO actualizarListaParametros(ListaParametrosDTO listaParametrosDTO, Long id);
    void eliminarListaParametros(Long id);
}
