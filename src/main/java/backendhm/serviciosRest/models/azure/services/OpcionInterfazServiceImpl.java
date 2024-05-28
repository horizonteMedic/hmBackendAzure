package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.ListadoDTO;
import backendhm.serviciosRest.models.azure.dtos.OpcionesInterfazDTO;
import backendhm.serviciosRest.models.azure.entity.OpcionesInterfazPrivilegios;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.IOpcionesInterfazPrivilegio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpcionInterfazServiceImpl implements IOpcionInterfazService{

    @Autowired
    private IOpcionesInterfazPrivilegio opcionesInterfazPrivilegio;

    @Override
    public OpcionesInterfazDTO crearOpcionInterfaz(OpcionesInterfazDTO opcionesInterfazDTO) {
        OpcionesInterfazPrivilegios opcionesInterfazPrivilegios=mapearEntidad(opcionesInterfazDTO);

        OpcionesInterfazPrivilegios nuevoOpcionesInterfaz=opcionesInterfazPrivilegio.save(opcionesInterfazPrivilegios);
        OpcionesInterfazDTO opcionesInterfazRespuesta=mapearDTO(nuevoOpcionesInterfaz);

        return opcionesInterfazRespuesta;
    }

    @Override
    public List<OpcionesInterfazDTO> listadoOpcionesInterfaz() {
        List<OpcionesInterfazPrivilegios> listaOpcionesInterfaz=opcionesInterfazPrivilegio.findAll();
        return listaOpcionesInterfaz.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public List<OpcionesInterfazDTO> listadoVistasPorIdRol(long idRol) {
        List<OpcionesInterfazPrivilegios> listadoVistas=opcionesInterfazPrivilegio.listadoVistasPorIdRol(idRol).orElseThrow();
        return listadoVistas.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public List<ListadoDTO> listadoVistasPorIdUser(long idUser) {
        List<OpcionesInterfazPrivilegios> listadoVistas=opcionesInterfazPrivilegio.listadoVistasPorIdUser(idUser).orElseThrow();

        return listadoVistas.stream().map(this::mapearDTOVistas).collect(Collectors.toList());
    }

    @Override
    public OpcionesInterfazDTO obtenerOpcionInterdazPorID(long id) {
        OpcionesInterfazPrivilegios opcionesInterfazPrivilegios=opcionesInterfazPrivilegio.
                findById(id).orElseThrow(()-> new ResourceNotFoundException("Opciones Interfaz","id-opciones-interfaz",id));
        return mapearDTO(opcionesInterfazPrivilegios);
    }

    @Override
    public OpcionesInterfazDTO actualizarOpcionInterfaz(OpcionesInterfazDTO opcionesInterfazDTO, long id) {
        OpcionesInterfazPrivilegios opcionesInterfazPrivilegios=opcionesInterfazPrivilegio.
        findById(id).orElseThrow(()-> new ResourceNotFoundException("Opciones Interfaz","id-opciones-interfaz",id));

        OpcionesInterfazPrivilegios opcionesInterfazPrivilegiosActual=opcionesInterfazPrivilegio
                .save(actualizarOpcionesInterfaz(opcionesInterfazDTO,opcionesInterfazPrivilegios));
        return mapearDTO(opcionesInterfazPrivilegiosActual);
    }

    @Override
    public void eliminarOpcionInterfaz(long id) {
        OpcionesInterfazPrivilegios opcionesInterfazPrivilegios=opcionesInterfazPrivilegio.
                findById(id).orElseThrow(()-> new ResourceNotFoundException("Opciones Interfaz","id-opciones-interfaz",id));
        opcionesInterfazPrivilegio.delete(opcionesInterfazPrivilegios);
    }
    private ListadoDTO mapearDTOVistas(OpcionesInterfazPrivilegios opcionesInterfazPrivilegios){
        ListadoDTO listadoDTO=new ListadoDTO();

        listadoDTO.setId(opcionesInterfazPrivilegios.getId());
        listadoDTO.setDescripcion(opcionesInterfazPrivilegios.getDescripcion());
        listadoDTO.setRuta(opcionesInterfazPrivilegios.getRutaVista());
        listadoDTO.setNombre(opcionesInterfazPrivilegios.getNombre());
        return listadoDTO;
    }
    private OpcionesInterfazDTO mapearDTO(OpcionesInterfazPrivilegios opcionesInterfazPrivilegios){
        OpcionesInterfazDTO opcionesInterfazDTO=new OpcionesInterfazDTO();

        opcionesInterfazDTO.setId(opcionesInterfazPrivilegios.getId());
        opcionesInterfazDTO.setNombre(opcionesInterfazPrivilegios.getNombre());
        opcionesInterfazDTO.setRutaVista(opcionesInterfazPrivilegios.getRutaVista());
        opcionesInterfazDTO.setDescripcion(opcionesInterfazPrivilegios.getDescripcion());
        opcionesInterfazDTO.setEstado(opcionesInterfazPrivilegios.getEstado());
        opcionesInterfazDTO.setNivel(opcionesInterfazPrivilegios.getNivel());
        opcionesInterfazDTO.setIdPadre(opcionesInterfazPrivilegios.getIdPadre());
        opcionesInterfazDTO.setFechaRegistro(opcionesInterfazPrivilegios.getFechaRegistro());
        opcionesInterfazDTO.setUserRegistro(opcionesInterfazPrivilegios.getUserRegistro());
        opcionesInterfazDTO.setFechaActualizacion(opcionesInterfazPrivilegios.getFechaActualizacion());
        opcionesInterfazDTO.setUserActualizacion(opcionesInterfazPrivilegios.getUserActualizacion());

        return opcionesInterfazDTO;
    }

    private OpcionesInterfazPrivilegios mapearEntidad(OpcionesInterfazDTO opcionesInterfazDTO){
        OpcionesInterfazPrivilegios opcionesInterfazPrivilegios=new OpcionesInterfazPrivilegios();

        opcionesInterfazPrivilegios.setNombre(opcionesInterfazDTO.getNombre());
        opcionesInterfazPrivilegios.setDescripcion(opcionesInterfazDTO.getDescripcion());
        opcionesInterfazPrivilegios.setEstado(opcionesInterfazDTO.getEstado());
        opcionesInterfazPrivilegios.setRutaVista(opcionesInterfazDTO.getRutaVista());
        opcionesInterfazPrivilegios.setNivel(opcionesInterfazDTO.getNivel());
        opcionesInterfazPrivilegios.setIdPadre(opcionesInterfazDTO.getIdPadre());
        opcionesInterfazPrivilegios.setFechaRegistro(opcionesInterfazDTO.getFechaRegistro());
        opcionesInterfazPrivilegios.setUserRegistro(opcionesInterfazDTO.getUserRegistro());
        opcionesInterfazPrivilegios.setFechaActualizacion(opcionesInterfazDTO.getFechaActualizacion());
        opcionesInterfazPrivilegios.setUserActualizacion(opcionesInterfazDTO.getUserActualizacion());

        return opcionesInterfazPrivilegios;
    }

    private OpcionesInterfazPrivilegios actualizarOpcionesInterfaz(OpcionesInterfazDTO opcionesInterfazDTO,OpcionesInterfazPrivilegios opcionesInterfazPrivilegios){

        opcionesInterfazPrivilegios.setNombre(opcionesInterfazDTO.getNombre());
        opcionesInterfazPrivilegios.setDescripcion(opcionesInterfazDTO.getDescripcion());
        opcionesInterfazPrivilegios.setEstado(opcionesInterfazDTO.getEstado());
        opcionesInterfazPrivilegios.setRutaVista(opcionesInterfazDTO.getRutaVista());
        opcionesInterfazPrivilegios.setNivel(opcionesInterfazDTO.getNivel());
        opcionesInterfazPrivilegios.setIdPadre(opcionesInterfazDTO.getIdPadre());
        opcionesInterfazPrivilegios.setFechaRegistro(opcionesInterfazDTO.getFechaRegistro());
        opcionesInterfazPrivilegios.setUserRegistro(opcionesInterfazDTO.getUserRegistro());
        opcionesInterfazPrivilegios.setFechaActualizacion(opcionesInterfazDTO.getFechaActualizacion());
        opcionesInterfazPrivilegios.setUserActualizacion(opcionesInterfazDTO.getUserActualizacion());

        return opcionesInterfazPrivilegios;
    }


}
