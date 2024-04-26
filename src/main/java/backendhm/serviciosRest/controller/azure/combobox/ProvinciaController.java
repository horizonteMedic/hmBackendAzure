package backendhm.serviciosRest.controller.azure.combobox;

import backendhm.serviciosRest.models.azure.dtos.combobox.ProvinciaDTO;
import backendhm.serviciosRest.models.azure.services.combobox.IProvinciaService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/ct/provincia")
@CrossOrigin
public class ProvinciaController {

    @Autowired
    private IProvinciaService provinciaService;

    private static JSONObject json=null;

    @GetMapping()
    public ResponseEntity<List<ProvinciaDTO>> obtenerListadoProvincias(){
        return  ResponseEntity.ok(provinciaService.listadoProvincia());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvinciaDTO> obtenerProvinciasPorID(@PathVariable(name = "id") String id) {

        return ResponseEntity.ok(provinciaService.obtenerProvinciaPorID(id));
    }


    @GetMapping("/busqueda-departamento/{id}")
    public ResponseEntity<List<ProvinciaDTO>> obtenerProvinciaSegunDepartamento(@PathVariable(name = "id") String id) {

        return ResponseEntity.ok(provinciaService.ListadoProvinciaPorDepartamento(id));
    }


    @PostMapping
    public ResponseEntity<ProvinciaDTO> guardarProvincia(@Valid @RequestBody ProvinciaDTO provinciaDTO) {

        return new ResponseEntity<>(provinciaService.crearProvincia(provinciaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProvinciaDTO> actualizarProvincia(@Valid @RequestBody ProvinciaDTO provinciaDTO,
                                                                  @PathVariable(name = "id") String id) {

        ProvinciaDTO provinciaActualizada = provinciaService.actualizarProvincia(provinciaDTO, id);
        return new ResponseEntity<>(provinciaActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProvincia(@PathVariable(name = "id") String id) {
        provinciaService.eliminarProvincia(id);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }


}
