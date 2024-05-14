package backendhm.serviciosRest.controller.azure;

import backendhm.serviciosRest.models.azure.dtos.RolAsignadoDTO;
import backendhm.serviciosRest.models.azure.services.IRolAsignadoService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/ct/rolAsignado")
@CrossOrigin
public class RolAsigandoController {

    @Autowired
    private IRolAsignadoService rolAsignadoService;

    private static JSONObject json=null;

    @GetMapping()
    public ResponseEntity<List<RolAsignadoDTO>> obtenerListadoRolesAsigandos(){

        return  ResponseEntity.ok(rolAsignadoService.listadoRol());
    }

    @GetMapping("/busquedaRolesPorIdRol/{idRol}")
    public ResponseEntity<List<RolAsignadoDTO>> obtenerRolPorIDRo(@PathVariable(name = "idRol") long idRol) {

        return ResponseEntity.ok(rolAsignadoService.listadoRolesPorIDROL(idRol));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolAsignadoDTO> obtenerRolPorIDRolAsignado(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(rolAsignadoService.obtenerRolPorID(id));
    }

    @PostMapping
    public ResponseEntity<RolAsignadoDTO> guardarRolAsignado(@Valid @RequestBody RolAsignadoDTO rolAsignadoDTO) {

        return new ResponseEntity<>(rolAsignadoService.crearRolAsignado(rolAsignadoDTO), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<RolAsignadoDTO> actualizarRol(@Valid @RequestBody RolAsignadoDTO rolAsignadoDTO,
                                                @PathVariable(name = "id") long id) {

        RolAsignadoDTO rolActualizado = rolAsignadoService.actualizarRol(rolAsignadoDTO, id);
        return new ResponseEntity<>(rolActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRol(@PathVariable(name = "id") long id) {
        rolAsignadoService.eliminarRolAsignado(id);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }

}
