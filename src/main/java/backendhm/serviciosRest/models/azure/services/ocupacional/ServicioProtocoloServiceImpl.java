package backendhm.serviciosRest.models.azure.services.ocupacional;

import backendhm.serviciosRest.models.azure.dtos.Ocupacional.ServicioProtocoloDTO;
import backendhm.serviciosRest.models.azure.entity.ocupacional.Protocolo;
import backendhm.serviciosRest.models.azure.entity.ocupacional.Servicio;
import backendhm.serviciosRest.models.azure.entity.ocupacional.ServicioProtocolo;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.ocupacional.IProtocoloRepository;
import backendhm.serviciosRest.models.azure.repository.ocupacional.IServicioProtocoloRepository;
import backendhm.serviciosRest.models.azure.repository.ocupacional.IServiciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioProtocoloServiceImpl implements IServicioProtocoloService {

    @Autowired
    private IServicioProtocoloRepository servicioProtocoloRepository;

    @Autowired
    private IProtocoloRepository protocoloRepository;

    @Autowired
    private IServiciosRepository serviciosRepository;


    @Override
    public ServicioProtocoloDTO crearServiciosProtocolo(ServicioProtocoloDTO servicioProtocoloDTO) {
        ServicioProtocolo servicioProtocolo=maapearENTIDAD(servicioProtocoloDTO);

        ServicioProtocolo nuevoServicioProtocolo=servicioProtocoloRepository.save(servicioProtocolo);
        ServicioProtocoloDTO servicioProtocoloRespuesta=mapearDTO(nuevoServicioProtocolo);

        return servicioProtocoloRespuesta;
    }

    @Override
    public List<ServicioProtocoloDTO> listadoServiciosProtocolos() {
        List<ServicioProtocolo> listarServicioProtocolo=servicioProtocoloRepository.findAll();
        return listarServicioProtocolo.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public List<ServicioProtocoloDTO> busquedaServicioProtocoloPorIDProtocolo(long idProtocolo) {
        List<ServicioProtocolo> listarServicioProtocolo=servicioProtocoloRepository.buscarServicioProtocoloPorIdProtocolo(idProtocolo).orElseThrow();

        return listarServicioProtocolo.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public ServicioProtocoloDTO obtenerServicioProtocoloPorID(long id) {
        ServicioProtocolo servicioProtocolo=servicioProtocoloRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Servicio Protocolo","id",id));
        return mapearDTO(servicioProtocolo);
    }

    @Override
    public ServicioProtocoloDTO actualizarServiciProtocolo(ServicioProtocoloDTO servicioProtocoloDTO, long id) {
        ServicioProtocolo servicioProtocolo=servicioProtocoloRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("servicio protocolo","id",id));

        ServicioProtocolo servicioProtocoloActual=servicioProtocoloRepository.save(actualizarServiciosPrtocolos(servicioProtocoloDTO,servicioProtocolo));
        return mapearDTO(servicioProtocoloActual);
    }

    @Override
    public void eliminarServicioProtocolo(long id) {
        ServicioProtocolo servicioProtocolo=servicioProtocoloRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Servicio protocolo","id",id));
        servicioProtocoloRepository.delete(servicioProtocolo);
    }

    private ServicioProtocoloDTO mapearDTO(ServicioProtocolo servicioProtocolo){
        ServicioProtocoloDTO servicioProtocoloDTO=new ServicioProtocoloDTO();
        Servicio servicio=serviciosRepository.busquedaServicioPorIdServicio(servicioProtocolo.getServicios_hm().getIdServicio());

        servicioProtocoloDTO.setNombreServicio(servicio.getNombreServicio());
        servicioProtocoloDTO.setIdServicioProtocolo(servicioProtocolo.getIdServicioProtocolo());
        servicioProtocoloDTO.setId_protocolo(servicioProtocolo.getProtocolo_hm().getIdProtocolo());
        servicioProtocoloDTO.setId_servicio(servicioProtocolo.getServicios_hm().getIdServicio());
        servicioProtocoloDTO.setPrecio(Float.parseFloat(servicioProtocolo.getPrecio()));
        servicioProtocoloDTO.setFechaRegistro(servicioProtocolo.getFechaRegistro());
        servicioProtocoloDTO.setUserRegistro(servicioProtocolo.getUserRegistro());
        servicioProtocoloDTO.setFechaActualizacion(servicioProtocolo.getFechaActualizacion());
        servicioProtocoloDTO.setUserActualizacion(servicioProtocolo.getUserActualizacion());

        return  servicioProtocoloDTO;

    }

    private ServicioProtocolo maapearENTIDAD(ServicioProtocoloDTO servicioProtocoloDTO){
        ServicioProtocolo servicioProtocolo=new ServicioProtocolo();
        Protocolo protocolo=protocoloRepository.findById(servicioProtocoloDTO.getId_protocolo()).orElseThrow();
        Servicio servicio=serviciosRepository.findById(servicioProtocoloDTO.getId_servicio()).orElseThrow();

        servicioProtocolo.setProtocolo_hm(protocolo);
        servicioProtocolo.setServicios_hm(servicio);
        servicioProtocolo.setPrecio(String.valueOf(servicioProtocoloDTO.getPrecio()));
        servicioProtocolo.setFechaRegistro(servicioProtocoloDTO.getFechaRegistro());
        servicioProtocolo.setUserRegistro(servicioProtocoloDTO.getUserRegistro());
        servicioProtocolo.setFechaActualizacion(servicioProtocoloDTO.getFechaActualizacion());
        servicioProtocolo.setUserActualizacion(servicioProtocoloDTO.getUserActualizacion());
        return servicioProtocolo;
    }


    private ServicioProtocolo actualizarServiciosPrtocolos(ServicioProtocoloDTO servicioProtocoloDTO, ServicioProtocolo servicioProtocolo){
        Protocolo protocolo=protocoloRepository.findById(servicioProtocoloDTO.getId_protocolo()).orElseThrow();
        Servicio servicio=serviciosRepository.findById(servicioProtocoloDTO.getId_servicio()).orElseThrow();

        servicioProtocolo.setProtocolo_hm(protocolo);
        servicioProtocolo.setServicios_hm(servicio);
        servicioProtocolo.setPrecio(String.valueOf(servicioProtocoloDTO.getPrecio()));
        servicioProtocolo.setFechaRegistro(servicioProtocoloDTO.getFechaRegistro());
        servicioProtocolo.setUserRegistro(servicioProtocoloDTO.getUserRegistro());
        servicioProtocolo.setFechaActualizacion(servicioProtocoloDTO.getFechaActualizacion());
        servicioProtocolo.setUserActualizacion(servicioProtocoloDTO.getUserActualizacion());
        return servicioProtocolo;
    }

}
