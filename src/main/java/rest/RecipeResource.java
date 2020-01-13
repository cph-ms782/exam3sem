package rest;

import facades.RecipeFacade;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.ExecutionException;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 */
@Path("recipe")
public class RecipeResource {

    private static final RecipeFacade FACADE = RecipeFacade.getRecipeFacade();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello recipe\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("allrecipes")
    @RolesAllowed({"user", "admin"})
    public String getAllRecipes() throws IOException, ProtocolException, ExecutionException, InterruptedException {
        return FACADE.getData("http://46.101.217.16:4000/allRecipes");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @RolesAllowed({"user", "admin"})
    public String getRecipe(@PathParam("id") String id) throws IOException, ProtocolException, ExecutionException, InterruptedException {

        return FACADE.getData("http://46.101.217.16:4000/recipe/" + id);
    }

}
