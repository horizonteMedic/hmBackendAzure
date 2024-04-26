package backendhm.serviciosRest.controller.azure.parametros;

import backendhm.serviciosRest.models.azure.dtos.parametros.DetalleParametrosDTO;
import backendhm.serviciosRest.models.azure.services.parametros.IDetalleParametrosService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/ct/detalleParametros")
@CrossOrigin
public class DetalleParametrosController {

    @Autowired
    private IDetalleParametrosService detalleParametrosService;

    private static JSONObject json=null;


    @GetMapping()
    public ResponseEntity<List<DetalleParametrosDTO>> obtenerdetalleParametros(){

        return ResponseEntity.ok(detalleParametrosService.listadoDetalleParametros());
    }


    @GetMapping("/detalleParametrizable/{detalleParametrizable}")
    public ResponseEntity<List<DetalleParametrosDTO>> obtenerdetalleParametrizable(@PathVariable(name = "detalleParametrizable") String detalleParametrizable){

        return ResponseEntity.ok(detalleParametrosService.listadoDetalleParametrizablePorListaParametros(detalleParametrizable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleParametrosDTO> obtenerDetalleParametrosPorID(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(detalleParametrosService.obtenerDetalleParametrosPorID(id));
    }

    @PostMapping
    public ResponseEntity<DetalleParametrosDTO> guardarDetalleParametros(@Valid @RequestBody DetalleParametrosDTO detalleParametrosDTO) {

        return new ResponseEntity<>(detalleParametrosService.crearDetalleParametros(detalleParametrosDTO), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleParametrosDTO> actualizarDetalleParametros(@Valid @RequestBody DetalleParametrosDTO detalleParametrosDTO,
                                                                        @PathVariable(name = "id") long id) {

        DetalleParametrosDTO detalleParametrosDTOActualizado = detalleParametrosService.actualizarDetalleParametros(detalleParametrosDTO, id);
        return new ResponseEntity<>(detalleParametrosDTOActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDetalleParametros(@PathVariable(name = "id") long id) {
        detalleParametrosService.eliminarDetalleParametro(id);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }




}
