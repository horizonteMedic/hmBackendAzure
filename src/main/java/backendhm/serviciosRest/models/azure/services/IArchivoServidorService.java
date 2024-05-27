package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.sistemaArchivos.ArchivoServidorDTO;
import backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO;

import java.util.List;

public interface IArchivoServidorService {

    ArchivoServidorDTO detalleArchivoServidor(String hc,long ta);

    RespuestaBackendDTO registrarArchivoOActualizar(ArchivoServidorDTO archivoServidorDTO);

    RespuestaBackendDTO cargaMasivaArchivos(String sede);

    ArchivoServidorDTO creararchivoServidor(ArchivoServidorDTO archivoServidorDTO);
    List<ArchivoServidorDTO> listadoArchivoServidor();

    List<ArchivoServidorDTO> listadoArchivoPorHC(String hc, long idUser);
    ArchivoServidorDTO obtenerArchivoServidorPorID(long id);
    ArchivoServidorDTO actualizarArchivoServidor(ArchivoServidorDTO archivoServidorDTO, long id);
    void eliminarArchivoServidor(long id);
}
