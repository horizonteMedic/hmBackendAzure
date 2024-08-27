package backendhm.serviciosRest.models.azure.services.campaña;

import backendhm.serviciosRest.models.azure.dtos.campaña.CIE10CAMPAÑADTO;
import backendhm.serviciosRest.models.azure.entity.campaña.CIE10;
import backendhm.serviciosRest.models.azure.repository.campaña.ICies10Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Cie10ServiceImpl implements ICampañaService{

    @Autowired
    private ICies10Repository cies10Repository;

    @Override
    public CIE10CAMPAÑADTO crearCie10(CIE10CAMPAÑADTO cie10CAMPAÑADTO) {
        CIE10 cie10=mapearEntidad(cie10CAMPAÑADTO);

        CIE10 nuevoCie10= cies10Repository.save(cie10);

        CIE10CAMPAÑADTO cie10Respuesta=mapearDTO(nuevoCie10);

        return cie10Respuesta;
    }

    @Override
    public List<CIE10CAMPAÑADTO> listadoCie10() {
        List<CIE10> listadoCie10= cies10Repository.findAll();

        return listadoCie10.stream().map(this::mapearDTO).collect(Collectors.toList());
    }


    private CIE10CAMPAÑADTO mapearDTO(CIE10 cie10){
        CIE10CAMPAÑADTO cie10CAMPAÑADTO=new CIE10CAMPAÑADTO();

        cie10CAMPAÑADTO.setCodigo(cie10.getCodigo());
        cie10CAMPAÑADTO.setDescripcion(cie10.getDescripcion());

        return cie10CAMPAÑADTO;
    }

    //convierte DTO a Entidad
    private CIE10 mapearEntidad(CIE10CAMPAÑADTO cie10CAMPAÑADTO){
        CIE10 cie10=new CIE10();

        cie10.setCodigo(cie10CAMPAÑADTO.getCodigo());
        cie10.setDescripcion(cie10CAMPAÑADTO.getDescripcion());

        return cie10;
    }

}
