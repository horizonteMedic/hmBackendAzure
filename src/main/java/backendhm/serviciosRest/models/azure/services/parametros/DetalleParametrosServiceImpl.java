package backendhm.serviciosRest.models.azure.services.parametros;

import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.dtos.parametros.DetalleParametrosDTO;
import backendhm.serviciosRest.models.azure.entity.parametros.DetalleParametros;
import backendhm.serviciosRest.models.azure.entity.parametros.ListaParametros;
import backendhm.serviciosRest.models.azure.repository.parametros.IDetalleParametrosRepository;
import backendhm.serviciosRest.models.azure.repository.parametros.IListaParametrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleParametrosServiceImpl implements IDetalleParametrosService{

    @Autowired
    private IDetalleParametrosRepository detalleParametrosRepository;

    @Autowired
    private IListaParametrosRepository listaParametrosRepository;

    @Override
    public DetalleParametrosDTO crearDetalleParametros(DetalleParametrosDTO detalleParametrosDTO) {
        DetalleParametros detalleParametros=mapearEntidad(detalleParametrosDTO);

        DetalleParametros nuevoDetalleParametros=detalleParametrosRepository.save(detalleParametros);
        DetalleParametrosDTO detalleParametrosDTORespuesta=mapearDTO(nuevoDetalleParametros);

        return detalleParametrosDTORespuesta;
    }

    @Override
    public List<DetalleParametrosDTO> listadoDetalleParametros() {
        List<DetalleParametros> detalleParametros=detalleParametrosRepository.findAll();
        return detalleParametros.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public List<DetalleParametrosDTO> listadoDetalleParametrizablePorListaParametros(String descripcionParametro) {
        List<DetalleParametros> detalleParametros=detalleParametrosRepository.listadoDetalleParametrizable(descripcionParametro).orElseThrow();
        return detalleParametros.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public DetalleParametrosDTO obtenerDetalleParametrosPorID(Long id) {
        DetalleParametros detalleParametros=detalleParametrosRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Detalle de parametros","id",id));
        return mapearDTO(detalleParametros);
    }

    @Override
    public DetalleParametrosDTO actualizarDetalleParametros(DetalleParametrosDTO detalleParametrosDTO, Long id) {
        DetalleParametros detalleParametros=detalleParametrosRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Detalle de parametros","id",id));
        DetalleParametros detalleParametrosActual=
                detalleParametrosRepository.save(actualizarDetalleParametrosEntidad(detalleParametrosDTO,detalleParametros));

        return mapearDTO(detalleParametrosActual);
    }

    @Override
    public void eliminarDetalleParametro(Long id) {
        DetalleParametros detalleParametros=detalleParametrosRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Detalle de parametros","id",id));

        detalleParametrosRepository.delete(detalleParametros);
    }

    private DetalleParametrosDTO mapearDTO(DetalleParametros detalleParametros){
        DetalleParametrosDTO detalleParametrosDTO=new DetalleParametrosDTO();
        ListaParametros listaParametros;
        listaParametros = listaParametrosRepository.findById(detalleParametros.getListaParametros().getId()).orElseThrow();
        detalleParametrosDTO.setId(detalleParametros.getId());
        detalleParametrosDTO.setDescripcion(detalleParametros.getDescripcion());
        detalleParametrosDTO.setIdListaParametros(listaParametros.getId());

        return detalleParametrosDTO;
    }

    private DetalleParametros mapearEntidad(DetalleParametrosDTO detalleParametrosDTO){
        DetalleParametros detalleParametros=new DetalleParametros();

        ListaParametros listaParametros=listaParametrosRepository.findById(detalleParametrosDTO.getIdListaParametros())
                .orElseThrow();

        detalleParametros.setDescripcion(detalleParametrosDTO.getDescripcion());
        detalleParametros.setListaParametros(listaParametros);

        return detalleParametros;
    }

    private DetalleParametros actualizarDetalleParametrosEntidad(DetalleParametrosDTO  detalleParametrosDTO,DetalleParametros detalleParametros){

        ListaParametros nuevaListaParametros=listaParametrosRepository
                .findById(detalleParametrosDTO.getIdListaParametros()).orElseThrow();

        detalleParametros.setDescripcion(detalleParametrosDTO.getDescripcion());
        detalleParametros.setListaParametros(nuevaListaParametros);

        return detalleParametros;
    }


}
