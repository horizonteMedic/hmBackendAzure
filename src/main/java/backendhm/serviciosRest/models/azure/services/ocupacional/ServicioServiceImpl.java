package backendhm.serviciosRest.models.azure.services.ocupacional;

import backendhm.serviciosRest.models.azure.dtos.Ocupacional.ServicioDTO;
import backendhm.serviciosRest.models.azure.entity.ocupacional.Servicio;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.ocupacional.IServiciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioServiceImpl implements IServicioService{

    @Autowired
    private IServiciosRepository serviciosRepository;

    @Override
    public ServicioDTO crearServicio(ServicioDTO servicioDTO) {
        Servicio servicio=mapearEntidad(servicioDTO);

        Servicio nuevoServicio=serviciosRepository.save(servicio);
        ServicioDTO servicioRespuesta=mapearDTO(nuevoServicio);

        return servicioRespuesta;
    }

    @Override
    public List<ServicioDTO> listadoServicios() {
        List<Servicio> listarServicios=serviciosRepository.findAll();
        return listarServicios.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public ServicioDTO obtenerServicioPorID(long id) {
        Servicio servicio=serviciosRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Servicio","id",id));
        return mapearDTO(servicio);
    }

    @Override
    public ServicioDTO actualizarServicio(ServicioDTO servicioDTO, long id) {
        Servicio servicio=serviciosRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Servicio","id",id));

        Servicio servicioActual=serviciosRepository.save(actualizarServicioEntidad(servicioDTO,servicio));
        return mapearDTO(servicioActual);
    }

    @Override
    public void eliminarServicio(long id) {
        Servicio servicio=serviciosRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Servicio","id",id));
        serviciosRepository.delete(servicio);
    }

    private ServicioDTO mapearDTO(Servicio servicio){
        ServicioDTO servicioDTO=new ServicioDTO();

        servicioDTO.setIdServicio(servicio.getIdServicio());
        servicioDTO.setNombreServicio(servicio.getNombreServicio());
        servicioDTO.setTablaServicio(servicio.getTablaServicio());
        servicioDTO.setMoney(servicio.getPrecio());
        servicioDTO.setEstado(servicio.getEstado());
        servicioDTO.setFechaRegistro(servicio.getFechaRegistro());
        servicioDTO.setUserRegistro(servicio.getUserRegistro());
        servicioDTO.setFechaActualizacion(servicio.getFechaActualizacion());
        servicioDTO.setUserActualizacion(servicio.getUserActualizacion());

        return servicioDTO;
    }

    private Servicio mapearEntidad(ServicioDTO servicioDTO){
        Servicio servicio=new Servicio();

        servicio.setNombreServicio(servicioDTO.getNombreServicio());
        servicio.setEstado(servicioDTO.getEstado());
        servicio.setTablaServicio(servicioDTO.getTablaServicio());
        servicio.setPrecio(servicioDTO.getMoney());
        servicio.setFechaRegistro(servicioDTO.getFechaRegistro());
        servicio.setUserRegistro(servicioDTO.getUserRegistro());
        servicio.setFechaActualizacion(servicioDTO.getFechaActualizacion());
        servicio.setUserActualizacion(servicioDTO.getUserActualizacion());

        return servicio;
    }
    private Servicio actualizarServicioEntidad(ServicioDTO servicioDTO,Servicio servicio){
        servicio.setNombreServicio(servicioDTO.getNombreServicio());
        servicio.setEstado(servicioDTO.getEstado());
        servicio.setTablaServicio(servicioDTO.getTablaServicio());
        servicio.setPrecio(servicioDTO.getMoney());
        servicio.setFechaRegistro(servicioDTO.getFechaRegistro());
        servicio.setUserRegistro(servicioDTO.getUserRegistro());
        servicio.setFechaActualizacion(servicioDTO.getFechaActualizacion());
        servicio.setUserActualizacion(servicioDTO.getUserActualizacion());

        return servicio;
    }
}
