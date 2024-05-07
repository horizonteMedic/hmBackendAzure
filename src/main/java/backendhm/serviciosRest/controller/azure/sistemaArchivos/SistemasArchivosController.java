package backendhm.serviciosRest.controller.azure.sistemaArchivos;

import backendhm.serviciosRest.models.azure.dtos.SedePorUserDTO;
import backendhm.serviciosRest.models.azure.dtos.sistemaArchivos.EmpContDTO;
import backendhm.serviciosRest.models.azure.dtos.sistemaArchivos.UsuarioEmpresaOContraTaDTO;
import backendhm.serviciosRest.models.azure.services.asistencial.IContrataService;
import backendhm.serviciosRest.models.azure.services.asistencial.IEmpresaService;
import backendhm.serviciosRest.models.azure.services.sistemaArchivos.IHistorialPacienteSPNPService;
import backendhm.serviciosRest.models.azure.services.sistemaArchivos.IUsuarioEmpresaOContrataService;
import backendhm.serviciosRest.models.spTrujilloNP.dto.DetalleHistorialPacienteMultiservidorDTO;
import backendhm.serviciosRest.models.spTrujilloNP.dto.HistorialPacienteMultiservidorDto;
import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestDetalleHistorialPacienteMultiservidorDTO;
import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestHistorialPacienteMultiservidor;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v01/ct/sistemaArchivos")
@CrossOrigin
public class SistemasArchivosController {

    @Autowired
    private IHistorialPacienteSPNPService historialPacienteSPNPService;

    @Autowired
    IUsuarioEmpresaOContrataService usuarioEmpresaOContrataService;

    @Autowired
    IEmpresaService empresaService;

    IContrataService contrataService;

    private static JSONObject json=null;


    @GetMapping("/busquedaEmpresaContrata/{userName}/{tipo}")
    public ResponseEntity<List<EmpContDTO>> listadoEmpresaContrataDeUsername(@PathVariable String userName, @PathVariable String tipo)
    {
            System.out.println("el valor del username es: "+userName+" , el valor del tipo: "+tipo);
            if (tipo.toUpperCase().contains("EMPRESA")) {
                return ResponseEntity.ok(empresaService.listadoEmpresaPorUsername(userName, tipo));
            }
        if (tipo.toUpperCase().contains("CONTRATA"))
            return ResponseEntity.ok(empresaService.listadocONTPorUsername(userName, tipo));

        else return null;


    }

    @GetMapping("/listadoEmpContIdUser/{id}")
    public ResponseEntity<List<UsuarioEmpresaOContraTaDTO>> obtenerListadoEmpContIDUSer(@PathVariable(name = "id") long id){
        return  ResponseEntity.ok(usuarioEmpresaOContrataService.listadoUEOCPorIdUser(id));
    }


    @GetMapping("/listadoUsuarioAsingacionEmpCont")
    public ResponseEntity<List<UsuarioEmpresaOContraTaDTO>> obtenerListado(){
        return  ResponseEntity.ok(usuarioEmpresaOContrataService.listadoUserEOC());
    }

    @GetMapping("/UsuarioAsingacionEmpCont/{id}")
    public ResponseEntity<UsuarioEmpresaOContraTaDTO> obteneruserEOCPorID(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(usuarioEmpresaOContrataService.obtenerUserEOCPorID(id));
    }

    @PostMapping("/UsuarioAsingacionEmpCont")
    public ResponseEntity<UsuarioEmpresaOContraTaDTO> guardar(@Valid @RequestBody UsuarioEmpresaOContraTaDTO userDTO) {

        return new ResponseEntity<>(usuarioEmpresaOContrataService.Crear(userDTO), HttpStatus.CREATED);

    }

    @PutMapping("/UsuarioAsingacionEmpCont/{id}")
    public ResponseEntity<UsuarioEmpresaOContraTaDTO> actualizarUserEOC(@Valid @RequestBody UsuarioEmpresaOContraTaDTO userDTO,
                                                @PathVariable(name = "id") long id) {

        UsuarioEmpresaOContraTaDTO userAct = usuarioEmpresaOContrataService.actualizarUserEOC(userDTO, id);
        return new ResponseEntity<>(userAct, HttpStatus.OK);
    }

    @DeleteMapping("/UsuarioAsingacionEmpCont/{id}")
    public ResponseEntity<String> eliminarUserEOC(@PathVariable(name = "id") long id) {
        usuarioEmpresaOContrataService.eliminarUserEOC(id);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }


    @GetMapping("/listadoEmpresasOcontratas/{descripcion}")
    public ResponseEntity<List<EmpContDTO>> obteneruserEOCPorID(@PathVariable(name = "descripcion") String descripcion) {

        return ResponseEntity.ok(usuarioEmpresaOContrataService.listadoEmpOCont(descripcion));
    }



    @PostMapping("/listadoHistorialPacientesConFiltros")
    public ResponseEntity<List<HistorialPacienteMultiservidorDto>> multiservidores(@Valid @RequestBody RequestHistorialPacienteMultiservidor requestHistorialPacienteSPTNP) {

        return new ResponseEntity<>(historialPacienteSPNPService.listadoHistorialPaciente(requestHistorialPacienteSPTNP), HttpStatus.OK);

    }


    @PostMapping("/detalleListadoHistorialPacientesConFiltros")
    public ResponseEntity<List<DetalleHistorialPacienteMultiservidorDTO>> detalleMultiservidores(@Valid @RequestBody RequestDetalleHistorialPacienteMultiservidorDTO requestDetalleHistorialPacienteMultiservidorDTO) {

        return new ResponseEntity<>(historialPacienteSPNPService.detalleHistoruialUsuario(requestDetalleHistorialPacienteMultiservidorDTO), HttpStatus.OK);

    }


    @GetMapping("/sedePorUsuario/{username}")
    public ResponseEntity<List<SedePorUserDTO>> obtenerSedeOorUsuario(@PathVariable(name = "username") String username) {

        return ResponseEntity.ok(historialPacienteSPNPService.listadoSedesPorUsuario(username));
    }
}
