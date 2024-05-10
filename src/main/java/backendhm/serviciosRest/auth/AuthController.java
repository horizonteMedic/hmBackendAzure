package backendhm.serviciosRest.auth;

import backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v01/st/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {


    @Autowired
    private  AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<RespuestaBackendDTO> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }
}
