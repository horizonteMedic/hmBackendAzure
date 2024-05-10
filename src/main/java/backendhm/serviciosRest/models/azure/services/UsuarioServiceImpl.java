package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.auth.user.Role;
import backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO;
import backendhm.serviciosRest.models.azure.entity.Empleado;
import backendhm.serviciosRest.models.azure.entity.RespuestaBackend;
import backendhm.serviciosRest.models.azure.entity.Usuario;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.IEmpleadoRepository;
import backendhm.serviciosRest.models.azure.repository.UserRepository;
import backendhm.serviciosRest.models.azure.dtos.UsuarioDTO;
import backendhm.serviciosRest.models.azure.repository.parametros.IRespuestaBackendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional("azureTransactionManagerFactory")
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IRespuestaBackendRepository respuestaBackendRepository;

    @Autowired
    private IEmpleadoRepository empleadoRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UsuarioDTO> listadoUsuario() {
        List<Usuario> listaUsuarios=userRepository.findAll();
        return listaUsuarios.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public List<UsuarioDTO> listadoUsuarioPorIDEmpleado(long idEmpleado) {
        List<Usuario> listaUsuarios = userRepository.listadoUsuarioPorIDEmpleado(idEmpleado).orElseThrow();
        return listaUsuarios.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorID(long id) {
        Usuario usuario=userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario","id user",id));
        return mapearDTO(usuario);
    }

    @Override
    public RespuestaBackendDTO actualizarParteUsuario(UsuarioDTO usuarioDTO, long id) {

        System.out.println("El objeto usuarioDTO ES: "+usuarioDTO+", el id_user es:"+id);
        Usuario usuario=userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario","id user",id));
        RespuestaBackend respuestaBackend=respuestaBackendRepository.actualizarUserParcial(usuarioDTO.getEstado(), usuario.getUsername(), usuarioDTO.getId_empleado(), id).orElseThrow();
        RespuestaBackendDTO respuestaBackendDTO=new RespuestaBackendDTO();
        respuestaBackendDTO.setId(respuestaBackend.getId());
        respuestaBackendDTO.setMensaje(respuestaBackend.getMensaje());
        return respuestaBackendDTO;
    }

    @Override
    public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO, long id) {
        Usuario usuario=userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario","id user",id));
        Usuario usuarioActual=userRepository.save(actualizarUsuarioEntidad(usuarioDTO,usuario));

        return mapearDTO(usuarioActual);
    }

    @Override
    public void eliminarUsuario(long id) {
        Usuario usuario=userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario","id user",id));
        userRepository.delete(usuario);
    }

    private UsuarioDTO mapearDTO(Usuario usuario){
        UsuarioDTO usuarioDTO=new UsuarioDTO();

        usuarioDTO.setIdUser(usuario.getIdUser());

        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setEstado(usuario.getEstado());
        usuarioDTO.setId_empleado(usuario.getEmpleado().getId());

        return usuarioDTO;
    }

    private Usuario mapearEntidad(UsuarioDTO usuarioDTO){
        Usuario usuario=new Usuario();
        Empleado empleado=empleadoRepository.findById(usuarioDTO.getId_empleado()).orElseThrow();

        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setEstado(usuarioDTO.getEstado());
        usuario.setEmpleado(empleado);

        return usuario;
    }

    private Usuario actualizarUsuarioEntidad(UsuarioDTO usuarioDTO,Usuario usuario){

        Empleado empleado=empleadoRepository.findById(usuarioDTO.getId_empleado()).orElseThrow();

        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setEstado(usuarioDTO.getEstado());
        usuario.setRole(Role.USER);
        usuario.setEmpleado(empleado);
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        return usuario;
    }


}
