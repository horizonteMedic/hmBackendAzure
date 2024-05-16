package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.sistemaArchivos.TipoArchivoDTO;

import java.util.List;

public interface ITipoArchivoService {
    TipoArchivoDTO crearTipoArchivo(TipoArchivoDTO tipoArchivoDTO);
    List<TipoArchivoDTO> listadoTipoArchivo();
    List<TipoArchivoDTO> listadoTiposArchivosIdUser(long idUser);
    TipoArchivoDTO obtenerTipoArchivoPorID(long id);
    TipoArchivoDTO actualizarTipoArchivo(TipoArchivoDTO tipoArchivoDTO, long id);
    void eliminarTipoArchivo(long id);
}
