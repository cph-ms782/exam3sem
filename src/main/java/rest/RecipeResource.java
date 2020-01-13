package rest;

import dto.RecipeDTO;
import facades.RecipeFacade;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.List;
import java.util.concurrent.ExecutionException;
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
    public String getAllRecipes() throws IOException, ProtocolException, ExecutionException, InterruptedException {
        return FACADE.getAllRecipeTitles();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("recipe/{id}")
    public List<RecipeDTO> getRecipe(@PathParam("id") String id) throws IOException, ProtocolException, ExecutionException, InterruptedException {
        
        return FACADE.getRecipe("Cheese and bacon stuffed pasta shells");
    }

}
