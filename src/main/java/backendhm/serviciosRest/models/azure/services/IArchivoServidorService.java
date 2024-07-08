package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.sistemaArchivos.ArchivoServidorDTO;
import backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO;
import backendhm.serviciosRest.models.azure.dtos.sistemaArchivos.CargaMasivaDTO;
import backendhm.serviciosRest.models.azure.dtos.sistemaArchivos.EmpleadoTipoDocDTO;

import java.util.List;

public interface IArchivoServidorService {

    ArchivoServidorDTO detalleArchivoServidor(String hc,long ta);

    EmpleadoTipoDocDTO detalleArchivoEmpleado(long dni, String tipoArchivo);

    RespuestaBackendDTO registrarArchivoOActualizar(ArchivoServidorDTO archivoServidorDTO);

    RespuestaBackendDTO registrarActualizarArchivoEmpleado(EmpleadoTipoDocDTO empleadoTipoDocDTO);

    RespuestaBackendDTO registroCargaMasiva(CargaMasivaDTO cargaMasivaDTO);

    RespuestaBackendDTO cargaMasivaArchivos(String sede);

    ArchivoServidorDTO creararchivoServidor(ArchivoServidorDTO archivoServidorDTO);
    List<ArchivoServidorDTO> listadoArchivoServidor();

    List<ArchivoServidorDTO> listadoArchivoPorHC(String hc, long idUser);
    ArchivoServidorDTO obtenerArchivoServidorPorID(long id);
    ArchivoServidorDTO actualizarArchivoServidor(ArchivoServidorDTO archivoServidorDTO, long id);
    void eliminarArchivoServidor(long id);
}
