package backendhm.serviciosRest.controller.azure;

import backendhm.serviciosRest.models.azure.dtos.UsuarioRolDTO;
import backendhm.serviciosRest.models.azure.services.IUsuarioRolService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/ct/usuarioRol")
@CrossOrigin
public class UsuarioRolController {

    @Autowired
    private IUsuarioRolService usuarioRolService;

    private static JSONObject json=null;


    @GetMapping()
    public ResponseEntity<List<UsuarioRolDTO>> obtenerListadoUsuarioRoles(){
        return  ResponseEntity.ok(usuarioRolService.listadoUsuarioRol());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioRolDTO> obtenerUsuarioRolesPorID(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(usuarioRolService.obtenerUsuarioRolPorID(id));
    }


    @PostMapping
    public ResponseEntity<UsuarioRolDTO> guardarUsuarioRol(@Valid @RequestBody UsuarioRolDTO usuarioRolDTO) {

        return new ResponseEntity<>(usuarioRolService.crearUsuarioRol(usuarioRolDTO), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioRolDTO> actualizarUsuarioRol(@Valid @RequestBody UsuarioRolDTO usuarioRolDTO,
                                                @PathVariable(name = "id") long id) {

        UsuarioRolDTO usuarioRolDTOActualizado = usuarioRolService.actualizarUsuarioRolDTO(usuarioRolDTO, id);
        return new ResponseEntity<>(usuarioRolDTOActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuarioRol(@PathVariable(name = "id") long id) {
        usuarioRolService.eliminarUsuarioRolDTO(id);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }

}
