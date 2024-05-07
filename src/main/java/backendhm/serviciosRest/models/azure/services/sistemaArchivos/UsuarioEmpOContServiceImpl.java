package backendhm.serviciosRest.models.azure.services.sistemaArchivos;

import backendhm.serviciosRest.models.azure.dtos.sistemaArchivos.EmpContDTO;
import backendhm.serviciosRest.models.azure.dtos.sistemaArchivos.UsuarioEmpresaOContraTaDTO;
import backendhm.serviciosRest.models.azure.entity.asistencial.Contrata;
import backendhm.serviciosRest.models.azure.entity.asistencial.Empresa;
import backendhm.serviciosRest.models.azure.entity.sistemaArchivos.UsuarioEmpresaOContrata;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.asistencial.IContrataRepository;
import backendhm.serviciosRest.models.azure.repository.asistencial.IEmpresaRepository;
import backendhm.serviciosRest.models.azure.repository.sistemasArchivos.UserEmpresaContrataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioEmpOContServiceImpl implements IUsuarioEmpresaOContrataService{

    @Autowired
    private UserEmpresaContrataRepository userEmpresaContrataRepository;

    @Autowired
    private IEmpresaRepository empresaRepository;

    @Autowired
    private IContrataRepository contrataRepository;

    @Override
    public List<EmpContDTO> listadoEmpOCont(String descripcion) {
        if(descripcion.contains("EMPRESA")){
            List<Empresa> listadoEmpresas=empresaRepository.findAll();
            return listadoEmpresas.stream().map(this::mapearDTOEmpresa).collect(Collectors.toList());
        }
        else {
            List<Contrata> listadoContratas=contrataRepository.findAll();
            return listadoContratas.stream().map(this::mapearDTOContrata).collect(Collectors.toList());

        }
    }

    @Override
    public UsuarioEmpresaOContraTaDTO Crear(UsuarioEmpresaOContraTaDTO usuarioEOCDto) {
        UsuarioEmpresaOContrata user=mapearEntidad(usuarioEOCDto);

        UsuarioEmpresaOContrata nuevoUser=userEmpresaContrataRepository.save(user);
        UsuarioEmpresaOContraTaDTO userRespuesta=mapearDTO(nuevoUser);

        return userRespuesta;
    }

    @Override
    public List<UsuarioEmpresaOContraTaDTO> listadoUserEOC() {
        List<UsuarioEmpresaOContrata> listaUserEOC=userEmpresaContrataRepository.findAll();
        return listaUserEOC.stream().map(this::mapearDTO).collect(Collectors.toList());    }

    @Override
    public List<UsuarioEmpresaOContraTaDTO> listadoUEOCPorIdUser(long idUser) {
        List<UsuarioEmpresaOContrata> listadoUserEOCPorIdUser= userEmpresaContrataRepository.listadoEmpresasContratasPorIdUser(idUser).orElseThrow();
        return listadoUserEOCPorIdUser.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public UsuarioEmpresaOContraTaDTO obtenerUserEOCPorID(long id) {
        UsuarioEmpresaOContrata user=userEmpresaContrataRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("userEOC","id",id));
        return mapearDTO(user);
    }

    @Override
    public UsuarioEmpresaOContraTaDTO actualizarUserEOC(UsuarioEmpresaOContraTaDTO usuarioEOCDto, long id) {
        UsuarioEmpresaOContrata user=userEmpresaContrataRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("userEOC","id",id));

        UsuarioEmpresaOContrata userActual=userEmpresaContrataRepository.save(actualizaruserEOCEntidad(usuarioEOCDto,user));
        return mapearDTO(userActual);
    }

    @Override
    public void eliminarUserEOC(long id) {
        UsuarioEmpresaOContrata user=userEmpresaContrataRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("userEOC","id",id));
        userEmpresaContrataRepository.delete(user);
    }

    private UsuarioEmpresaOContraTaDTO mapearDTO(UsuarioEmpresaOContrata user){
        UsuarioEmpresaOContraTaDTO userDTO=new UsuarioEmpresaOContraTaDTO();
        if(user.getTipo().contains("EMPRESA")) {
            Empresa empresa = empresaRepository.findById(user.getRuc()).orElseThrow();
            userDTO.setRazonSocial(empresa.getRazonEmpresa());
        }
        if(user.getTipo().contains("CONTRATA")) {
            Contrata contrata = contrataRepository.findById(user.getRuc()).orElseThrow();
            userDTO.setRazonSocial(contrata.getRazonContrata());
        }

        userDTO.setId(user.getId());
        userDTO.setRuc(user.getRuc());
        userDTO.setIdUser(user.getIdUser());
        userDTO.setTipo(user.getTipo().toUpperCase());
        userDTO.setEstado(user.getEstado());
        userDTO.setFechaRegistro(user.getFechaRegistro());
        userDTO.setUserRegistro(user.getUserRegistro());
        userDTO.setFechaActualizacion(user.getFechaActualizacion());
        userDTO.setUserActualizacion(user.getUserActualizacion());

        return userDTO;
    }

    private UsuarioEmpresaOContrata mapearEntidad(UsuarioEmpresaOContraTaDTO userDTO){
        UsuarioEmpresaOContrata user=new UsuarioEmpresaOContrata();

        user.setRuc(userDTO.getRuc());
        user.setTipo(userDTO.getTipo().toUpperCase());
        user.setIdUser(userDTO.getIdUser());
        user.setEstado(userDTO.getEstado());
        user.setFechaRegistro(userDTO.getFechaRegistro());
        user.setUserRegistro(userDTO.getUserRegistro());
        user.setFechaActualizacion(userDTO.getFechaActualizacion());
        user.setUserActualizacion(userDTO.getUserActualizacion());

        return user;
    }

    private UsuarioEmpresaOContrata actualizaruserEOCEntidad(UsuarioEmpresaOContraTaDTO userDTO,UsuarioEmpresaOContrata user){
        user.setRuc(userDTO.getRuc());
        user.setTipo(userDTO.getTipo().toUpperCase());
        user.setIdUser(userDTO.getIdUser());
        user.setEstado(userDTO.getEstado());
        user.setFechaRegistro(userDTO.getFechaRegistro());
        user.setUserRegistro(userDTO.getUserRegistro());
        user.setFechaActualizacion(userDTO.getFechaActualizacion());
        user.setUserActualizacion(userDTO.getUserActualizacion());

        return user;
    }

    private EmpContDTO mapearDTOEmpresa(Empresa emp){
        EmpContDTO empDTO= new EmpContDTO();
        empDTO.setRuc(emp.getId());
        empDTO.setRazonSocial(emp.getRazonEmpresa());
        return empDTO;
    }

    private EmpContDTO mapearDTOContrata(Contrata cont){
        EmpContDTO empDTO= new EmpContDTO();
        empDTO.setRuc(cont.getId());
        empDTO.setRazonSocial(cont.getRazonContrata());
        return empDTO;
    }
}
