package backendhm.serviciosRest.controller.azure;

import backendhm.serviciosRest.models.azure.dtos.TipoArchivoAsignadoDTO;
import backendhm.serviciosRest.models.azure.services.ITipoArchivoAsignadoService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/ct/tipoArchivoAsignado")
@CrossOrigin
public class TipoArchivoAsignadoController {

    @Autowired
    private ITipoArchivoAsignadoService tipoArchivoAsignadoService;

    private static JSONObject json=null;

    @GetMapping()
    public ResponseEntity<List<TipoArchivoAsignadoDTO>> obtenerListadoTipoArchivoAsignado(){

        return  ResponseEntity.ok(tipoArchivoAsignadoService.listadoTipoArchivoAsignado());
    }


    @GetMapping("/busquedaTipoArchivoAsignadoPorIdRol/{idRol}")
    public ResponseEntity<List<TipoArchivoAsignadoDTO>> obtenerRolPorIDRo(@PathVariable(name = "idRol") long idRol) {

        return ResponseEntity.ok(tipoArchivoAsignadoService.listadoTipoArchivoAsignadoPorIDROL(idRol));
    }


    @GetMapping("/{id}")
    public ResponseEntity<TipoArchivoAsignadoDTO> obtenerTipoArchivoAsignadorPorIdRol(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(tipoArchivoAsignadoService.obtenerTipoArchivoAsignadoPorIDTipoAsignado(id));
    }

    @PostMapping
    public ResponseEntity<TipoArchivoAsignadoDTO> guardarTipoArchivoAsignado(@Valid @RequestBody TipoArchivoAsignadoDTO tipoArchivoAsignadoDTO) {

        return new ResponseEntity<>(tipoArchivoAsignadoService.crearTipoArchivoAsignado(tipoArchivoAsignadoDTO), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoArchivoAsignadoDTO> actualizarTipoArchivoAsignado(@Valid @RequestBody TipoArchivoAsignadoDTO tipoArchivoAsignadoDTO,
                                                        @PathVariable(name = "id") long id) {

        TipoArchivoAsignadoDTO tipoArchivoAsignadoDTOActualizado = tipoArchivoAsignadoService.actualizarTipoArchivoAsignado(tipoArchivoAsignadoDTO, id);
        return new ResponseEntity<>(tipoArchivoAsignadoDTOActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRol(@PathVariable(name = "id") long id) {
        tipoArchivoAsignadoService.eliminarTipoArchivoAsignado(id);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }
}
