package backendhm.serviciosRest.models.azure.services.parametros;

import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.parametros.IListaParametrosRepository;
import backendhm.serviciosRest.models.azure.dtos.parametros.ListaParametrosDTO;
import backendhm.serviciosRest.models.azure.entity.parametros.ListaParametros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListaParametrosServiceImpl implements IListaParametrosService{

    @Autowired
    private IListaParametrosRepository listaParametrosRepository;

    @Override
    public ListaParametrosDTO crearListadoParametros(ListaParametrosDTO listaParametrosDTO) {
        ListaParametros listaParametros=mapearEntidad(listaParametrosDTO);

        ListaParametros nuevaListaParametros=listaParametrosRepository.save(listaParametros);
        ListaParametrosDTO listaParametrosRespuesta=mapearDTO(nuevaListaParametros);

        return listaParametrosRespuesta;
    }

    @Override
    public List<ListaParametrosDTO> listadoParametros() {
        List<ListaParametros> listadoListaParametros=listaParametrosRepository.findAll();
        return listadoListaParametros.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public ListaParametrosDTO obtenerListadoParametrosPorID(Long id) {
        ListaParametros listaParametros=listaParametrosRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Lista de parametros","id",id));
        return mapearDTO(listaParametros);
    }

    @Override
    public ListaParametrosDTO actualizarListaParametros(ListaParametrosDTO listaParametrosDTO, Long id) {
        ListaParametros listaParametros=listaParametrosRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Lista de parametros","id",id));
        ListaParametros listaParametrosActual=
                listaParametrosRepository.save(actualizarListaParametrosEntidad(listaParametrosDTO,listaParametros));

        return mapearDTO(listaParametrosActual);
    }

    @Override
    public void eliminarListaParametros(Long id) {
        ListaParametros listaParametros=listaParametrosRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Lista de parametros","id",id));

        listaParametrosRepository.delete(listaParametros);
    }

    private ListaParametrosDTO mapearDTO(ListaParametros listaParametros){
        ListaParametrosDTO listaParametrosDTO=new ListaParametrosDTO();

        listaParametrosDTO.setId(listaParametros.getId());
        listaParametrosDTO.setDescripcion(listaParametros.getDescripcion());

        return listaParametrosDTO;
    }

    private ListaParametros mapearEntidad(ListaParametrosDTO listaParametrosDTO){
        ListaParametros listaParametros=new ListaParametros();

        listaParametros.setDescripcion(listaParametrosDTO.getDescripcion());

        return listaParametros;
    }

    private ListaParametros actualizarListaParametrosEntidad(ListaParametrosDTO  listaParametrosDTO,ListaParametros listaParametros){

        listaParametros.setDescripcion(listaParametrosDTO.getDescripcion());

        return listaParametros;
    }


}
