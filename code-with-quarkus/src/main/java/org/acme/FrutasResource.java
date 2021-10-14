package org.acme;

import org.acme.dto.FrutasDTO;
import org.acme.model.Fruta;
import org.acme.service.FrutasService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/frutas")
public class FrutasResource {

    @Inject
    FrutasService frutasService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruta> list() {
        return frutasService.list();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void create(FrutasDTO frutasDTO) {
        frutasService.create(frutasDTO);
    }
}