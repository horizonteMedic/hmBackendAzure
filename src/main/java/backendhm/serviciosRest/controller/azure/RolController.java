package backendhm.serviciosRest.controller.azure;

import backendhm.serviciosRest.models.azure.dtos.RolDTO;
import backendhm.serviciosRest.models.azure.services.IRolService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/ct/rol")
@CrossOrigin
public class RolController {

    @Autowired
    private IRolService rolService;

    private static JSONObject json=null;

    @GetMapping()
    public ResponseEntity<List<RolDTO>> obtenerListadoRoles(){
        return  ResponseEntity.ok(rolService.listadoRol());
    }

    @GetMapping("/listadoRolesHabilitados")
    public ResponseEntity<List<RolDTO>> obtenerListadoRolesHabilitados(){
        return  ResponseEntity.ok(rolService.listadoRolesHabilitados());
    }

    @GetMapping("/busquedaRolesPorUserName/{userName}")
    public ResponseEntity<List<RolDTO>> obtenerRolPorIDUser(@PathVariable(name = "userName") String userName) {

        return ResponseEntity.ok(rolService.listadoRolesUsername(userName));
    }

    @GetMapping("/busquedaRolesPorIdUser/{id}")
    public ResponseEntity<List<RolDTO>> obtenerRolPorIDUser(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(rolService.listadoRolesIdUser(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> obtenerRolPorID(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(rolService.obtenerRolPorID(id));
    }


    @PostMapping
    public ResponseEntity<RolDTO> guardarRol(@Valid @RequestBody RolDTO rolDTO) {

        return new ResponseEntity<>(rolService.crearRol(rolDTO), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDTO> actualizarRol(@Valid @RequestBody RolDTO rolDTO,
                                                          @PathVariable(name = "id") long id) {

        RolDTO rolActualizado = rolService.actualizarRol(rolDTO, id);
        return new ResponseEntity<>(rolActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRol(@PathVariable(name = "id") long id) {
        rolService.eliminarRol(id);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }

}
