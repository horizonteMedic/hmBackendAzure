package backendhm.serviciosRest.auth;

import backendhm.serviciosRest.auth.user.Role;
import backendhm.serviciosRest.models.azure.repository.UserRepository;
import backendhm.serviciosRest.models.azure.dtos.EmpleadoDTO;
import backendhm.serviciosRest.models.azure.entity.Empleado;
import backendhm.serviciosRest.models.azure.entity.Usuario;
import backendhm.serviciosRest.models.azure.jwt.JwtService;
import backendhm.serviciosRest.models.azure.services.EmpleadoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private EmpleadoServiceImpl empleadoService;

    @Autowired
    private UserRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        Usuario userd=usuarioRepository.findByUsername(request.getNombre()).orElseThrow();

        UserDetails user=usuarioRepository.findByUsername(request.getNombre()).orElseThrow();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getNombre(), request.getPassword()));

        String token=jwtService.getToken(user,userd.getIdUser());
        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse register(RegisterRequest request) {
        Usuario usuario=new Usuario();
        EmpleadoDTO empleadoDTO=new EmpleadoDTO();
        usuario.setUsername(request.getUsername());
        usuario.setEstado(request.getEstado());
        usuario.setRole(Role.USER);
        usuario.setRuc(request.getRuc());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        empleadoDTO=empleadoService.obtenerEmpleadoPorID(request.getIdEmpleado());
        usuario.setEmpleado(mapearEntidad(empleadoDTO));
        usuarioRepository.save(usuario);
        return AuthResponse.builder()
                .token(jwtService.getToken(usuario,1))
                .build();
    }


    private Empleado mapearEntidad(EmpleadoDTO empleadoDTO){
        Empleado empleado=new Empleado();

        empleado.setId(empleadoDTO.getId_empleado());
        empleado.setNumDocumento(empleadoDTO.getNumDocumento());
        empleado.setTipoDoc(empleadoDTO.getTipoDoc());
        empleado.setNombres(empleadoDTO.getNombres());
        empleado.setApellidos(empleadoDTO.getApellidos());
        empleado.setCargo(empleadoDTO.getCargo());
        empleado.setUbigeo(empleadoDTO.getUbigeo());
        empleado.setCip(empleadoDTO.getCip());
        empleado.setCorreoElect(empleadoDTO.getCorreoElect());
        empleado.setCelular(empleadoDTO.getCelular());
        empleado.setTelFijo(empleadoDTO.getTelFijo());
        empleado.setDireccion(empleadoDTO.getDireccion());
        empleado.setEstado(empleadoDTO.getEstado());
        empleado.setFechaNacimiento(empleadoDTO.getFechaNacimiento());
        empleado.setFechaRegistro(empleadoDTO.getFechaRegistro());
        empleado.setUserRegistro(empleadoDTO.getUserRegistro());
        empleado.setFechaActualizacion(empleadoDTO.getFechaActualizacion());
        empleado.setUserActualizacion(empleadoDTO.getUserActualizacion());


        return empleado;
    }
}
