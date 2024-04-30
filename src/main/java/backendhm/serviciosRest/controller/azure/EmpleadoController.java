package backendhm.serviciosRest.controller.azure;


import backendhm.serviciosRest.models.azure.dtos.DatosEmpleadoDTO;
import backendhm.serviciosRest.models.azure.dtos.EmpleadoDTO;
import backendhm.serviciosRest.models.azure.services.IEmpleadoService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/st/empleado")
@CrossOrigin
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;

    private static JSONObject json=null;

    @GetMapping()
    public ResponseEntity<List<EmpleadoDTO>> obtenerListadoEmpleado(){

        return ResponseEntity.ok(empleadoService.listadoEmpleados());
    }

    @GetMapping("/busquedaPorNroDoc/{nroDoc}")
    public ResponseEntity<DatosEmpleadoDTO> obtenerListadoEmpleado(@PathVariable(name = "nroDoc") long nroDoc){

        return ResponseEntity.ok(empleadoService.obtenerDatosEmpleadoPorNroDoc(nroDoc));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> obtenerEmpleadoPorID(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(empleadoService.obtenerEmpleadoPorID(id));
    }

    @PostMapping
    public ResponseEntity<EmpleadoDTO> guardarEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDTO) {

        return new ResponseEntity<>(empleadoService.crearEmpleado(empleadoDTO), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> actualizarEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDTO,
                                                        @PathVariable(name = "id") long id) {

        EmpleadoDTO empleadoDTOActualizado = empleadoService.actualizarEmpleado(empleadoDTO, id);
        return new ResponseEntity<>(empleadoDTOActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable(name = "id") long id) {
        empleadoService.eliminarEmpleado(id);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }

}
