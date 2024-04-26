package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.TipoArchivoDTO;

import java.util.List;

public interface ITipoArchivoService {
    TipoArchivoDTO crearTipoArchivo(TipoArchivoDTO tipoArchivoDTO);
    List<TipoArchivoDTO> listadoTipoArchivo();
    TipoArchivoDTO obtenerTipoArchivoPorID(long id);
    TipoArchivoDTO actualizarTipoArchivo(TipoArchivoDTO tipoArchivoDTO, long id);
    void eliminarTipoArchivo(long id);
}
