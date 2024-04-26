package backendhm.serviciosRest.controller.azure.combobox;

import backendhm.serviciosRest.models.azure.dtos.combobox.DistritoDTO;
import backendhm.serviciosRest.models.azure.services.combobox.IDistritoService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/ct/distrito")
@CrossOrigin
public class DistritoController {

    @Autowired
    private IDistritoService distritoService;

    private static JSONObject json=null;


    @GetMapping()
    public ResponseEntity<List<DistritoDTO>> obtenerListadoDistritos(){
        return  ResponseEntity.ok(distritoService.listadoDistritos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<DistritoDTO> obtenerDistritoPorID(@PathVariable(name = "id") String id) {

        return ResponseEntity.ok(distritoService.obtenerDistritoPorID(id));
    }


    @GetMapping("/busqueda-distrito-porProvincia/{id}")
    public ResponseEntity<List<DistritoDTO>> obtenerDistritoSegunProvincia(@PathVariable(name = "id") String id) {

        return ResponseEntity.ok(distritoService.listadoDistritoPorProvincia(id));
    }


    @PostMapping
    public ResponseEntity<DistritoDTO> guardarDistrito(@Valid @RequestBody DistritoDTO distritoDTO) {

        return new ResponseEntity<>(distritoService.crearDistrito(distritoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DistritoDTO> actualizarDistrito(@Valid @RequestBody DistritoDTO distritoDTO,
                                                            @PathVariable(name = "id") String id) {

        DistritoDTO distritoActualizado = distritoService.actualizarDistrito(distritoDTO, id);
        return new ResponseEntity<>(distritoActualizado, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDistrito(@PathVariable(name = "id") String id) {
        distritoService.eliminarDistrito(id);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }



}
