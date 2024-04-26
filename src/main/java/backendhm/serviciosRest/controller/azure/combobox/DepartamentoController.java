package backendhm.serviciosRest.controller.azure.combobox;

import backendhm.serviciosRest.models.azure.dtos.combobox.DepartamentoDTO;
import backendhm.serviciosRest.models.azure.services.combobox.IDepartamentoService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/ct/departamento")
@CrossOrigin
public class DepartamentoController {

    @Autowired
    IDepartamentoService departamentoService;

    private static JSONObject json=null;



    @GetMapping()
    public ResponseEntity<List<DepartamentoDTO>> obtenerListadoDepartamentos(){
        return  ResponseEntity.ok(departamentoService.listadoDepartamentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> obtenerDepartamentoPorID(@PathVariable(name = "id") String id) {

        return ResponseEntity.ok(departamentoService.obtenerDepartamentoPorID(id));
    }


    @PostMapping
    public ResponseEntity<DepartamentoDTO> guardarDepartamento(@Valid @RequestBody DepartamentoDTO departamentoDTO) {

        return new ResponseEntity<>(departamentoService.crearDepartamento(departamentoDTO), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> actualizarDepartamento(@Valid @RequestBody DepartamentoDTO departamentoDTO,
                                                @PathVariable(name = "id") String id) {

        DepartamentoDTO departamentoActualizado = departamentoService.actualizarDepartamento(departamentoDTO, id);
        return new ResponseEntity<>(departamentoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDepartamento(@PathVariable(name = "id") String id) {
        departamentoService.eliminarDepartamento(id);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }

}
