package backendhm.serviciosRest.controller.azure;


import backendhm.serviciosRest.models.azure.dtos.*;
import backendhm.serviciosRest.models.azure.services.IEmpresaWebService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/ct/empresasContratas")
@CrossOrigin
public class EmpresaUsuarioController {

    @Autowired
    private IEmpresaWebService empresaWebService;

    private static JSONObject json=null;

    @GetMapping("/listadoHistorialUsuarios")
    public ResponseEntity<List<HistorialUsuarioDTO>> obtenerListadoHistoriaUsuario(){
        return  ResponseEntity.ok(empresaWebService.listadoHistoriaUsuario());
    }

    @GetMapping("/listadoDetalleHistoriaClinica/{id}")
    public ResponseEntity<List<DetalleHistoriaClinicaDTO>> obtenerListadoDetalleHistorialClinico(@PathVariable(name = "id") long id){
        return  ResponseEntity.ok(empresaWebService.listaDetalleHistoriaClinica(id));
    }

    @GetMapping("/listadoHistorialPacientesUsuario/{id}")
    public ResponseEntity<List<HistorialPacienteDTO>> obtenerListadoHistorialPacientes(@PathVariable(name = "id") long id){
        return  ResponseEntity.ok(empresaWebService.listadoHistorialPaciente(id));
    }

    @GetMapping("/listadoEmpresasContratas")
    public ResponseEntity<List<ListadoEmpresaContrataDTO>> obtenerListadoEmpresasContratas(){
        return  ResponseEntity.ok(empresaWebService.listadoEmpresasContratas());
    }

    @GetMapping()
    public ResponseEntity<List<EmpresaWebDTO>> obtenerListadoEmpresaWeb(){
        return  ResponseEntity.ok(empresaWebService.listadoEmpresasWeb());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaWebDTO> obtenerEmpresaWebPorID(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(empresaWebService.obtenerEmpresaWebPorID(id));
    }


    @PostMapping("/registrar")
    public ResponseEntity<EmpresaWebDTO> guardarEmpresaWeb(@Valid @RequestBody EmpresaWebDTO empresaWebDTO) {

        return new ResponseEntity<>(empresaWebService.crearEmpresaWeb(empresaWebDTO), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaWebDTO> actualizarEmpresaWeb(@Valid @RequestBody EmpresaWebDTO empresaWebDTO,
                                                @PathVariable(name = "id") long id) {

        EmpresaWebDTO empresaWebDTOActualizado = empresaWebService.actualizarEmpresaWeb(empresaWebDTO, id);
        return new ResponseEntity<>(empresaWebDTOActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRol(@PathVariable(name = "id") long id) {
        empresaWebService.eliminarEmpresaWeb(id);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }

}
