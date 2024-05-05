package backendhm.serviciosRest.models.azure.services.asistencial;

import backendhm.serviciosRest.models.azure.dtos.asistencial.EmpresaDTO;
import backendhm.serviciosRest.models.azure.dtos.sistemaArchivos.EmpContDTO;
import backendhm.serviciosRest.models.azure.entity.asistencial.Empresa;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.asistencial.IEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaServiceImpl implements IEmpresaService {

    @Autowired
    private IEmpresaRepository empresaRepository;

    @Override
    public EmpresaDTO crearEmpresa(EmpresaDTO empresaDTO) {
        Empresa empresa=mapearEntidad(empresaDTO);

        Empresa nuevaEmpresa=empresaRepository.save(empresa);
        EmpresaDTO empresaDTORespuesta=mapearDTO(nuevaEmpresa);

        return empresaDTORespuesta;
    }

    @Override
    public List<EmpresaDTO> listadoEmpresas() {
        List<Empresa> listaEmpresa=empresaRepository.findAll();
        return listaEmpresa.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public List<EmpContDTO> listadoEmpresaPorUsername(String userName, String tipoEmpCont) {
        List<Empresa> listado=empresaRepository.busquedaEmpresaPorUsernameEmpresa(userName,tipoEmpCont);
        return listado.stream().map(this::mapearDTOEmpresaPorUsername).collect(Collectors.toList());
    }

    @Override
    public EmpresaDTO obtenerEmpresaPorRuc(String ruc) {

        Empresa empresa=empresaRepository.findById(ruc)
                .orElseThrow(()-> new ResourceNotFoundException("Empresa","ruc empresa",Long.parseLong(ruc)));
        return mapearDTO(empresa);
    }

    @Override
    public EmpresaDTO actualizarEmpresa(EmpresaDTO empresaDTO, String ruc) {
        Empresa empresa=empresaRepository.findById(ruc)
                .orElseThrow(()-> new ResourceNotFoundException("Empresa","ruc empresa",Long.parseLong(ruc)));

        Empresa empresaActual=empresaRepository.save(actualizarEmpresaEntidad(empresaDTO,empresa));
        return mapearDTO(empresaActual);
    }

    @Override
    public void eliminarEmpresa(String ruc) {
        Empresa empresa=empresaRepository.findById(ruc)
                .orElseThrow(()-> new ResourceNotFoundException("Empresa","ruc empresa",Long.parseLong(ruc)));

        empresaRepository.delete(empresa);
    }

    private EmpContDTO mapearDTOEmpresaPorUsername(Empresa empresa){

        EmpContDTO empContDTO=new EmpContDTO();

        empContDTO.setRuc(empresa.getId());
        empContDTO.setRazonSocial(empresa.getRazonEmpresa());

        return  empContDTO;
    }

    private EmpresaDTO mapearDTO(Empresa empresa){
        EmpresaDTO empresaDTO=new EmpresaDTO();

        empresaDTO.setRucEmpresa(empresa.getId());
        empresaDTO.setRazonEmpresa(empresa.getRazonEmpresa());
        empresaDTO.setDireccionEmpresa(empresa.getDireccionEmpresa());
        empresaDTO.setEmailEmpresa(empresa.getEmailEmpresa());
        empresaDTO.setTelefonoEmpresa(empresa.getTelefonoEmpresa());
        empresaDTO.setResponsableEmpresa(empresa.getResponsableEmpresa());
        empresaDTO.setApiToken(empresa.getApiToken());

        return empresaDTO;
    }

    private Empresa mapearEntidad(EmpresaDTO empresaDTO){
        Empresa empresa=new Empresa();

        empresa.setId(empresaDTO.getRucEmpresa());
        empresa.setRazonEmpresa(empresaDTO.getRazonEmpresa());
        empresa.setDireccionEmpresa(empresaDTO.getDireccionEmpresa());
        empresa.setEmailEmpresa(empresaDTO.getEmailEmpresa());
        empresa.setTelefonoEmpresa(empresaDTO.getTelefonoEmpresa());
        empresa.setResponsableEmpresa(empresaDTO.getResponsableEmpresa());
        empresa.setApiToken(empresaDTO.getApiToken());

        return empresa;
    }
    private Empresa actualizarEmpresaEntidad(EmpresaDTO empresaDTO,Empresa empresa){

        empresa.setRazonEmpresa(empresaDTO.getRazonEmpresa());
        empresa.setDireccionEmpresa(empresaDTO.getDireccionEmpresa());
        empresa.setEmailEmpresa(empresaDTO.getEmailEmpresa());
        empresa.setTelefonoEmpresa(empresaDTO.getTelefonoEmpresa());
        empresa.setResponsableEmpresa(empresaDTO.getResponsableEmpresa());
        empresa.setApiToken(empresaDTO.getApiToken());

        return empresa;
    }

}
