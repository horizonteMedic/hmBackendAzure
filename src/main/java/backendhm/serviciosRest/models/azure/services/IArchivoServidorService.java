package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.ArchivoServidorDTO;

import java.util.List;

public interface IArchivoServidorService {

    ArchivoServidorDTO detalleArchivoServidor(long hc,long ta);

    ArchivoServidorDTO creararchivoServidor(ArchivoServidorDTO archivoServidorDTO);
    List<ArchivoServidorDTO> listadoArchivoServidor();
    ArchivoServidorDTO obtenerArchivoServidorPorID(long id);
    ArchivoServidorDTO actualizarArchivoServidor(ArchivoServidorDTO archivoServidorDTO, long id);
    void eliminarArchivoServidor(long id);
}
