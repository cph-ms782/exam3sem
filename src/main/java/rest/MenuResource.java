package rest;

import dto.MenuPlanOutDTO;
import facades.MenuFacade;
import utils.EMF_Creator;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.SetupTestUsers;

@OpenAPIDefinition(
        info = @Info(
                title = "Menu API",
                version = "0.1",
                description = "API to get info about menues.",
                contact = @Contact(name = "Martin", email = "cph-ms782@cphbusiness.dk")
        ),
        tags = {
            @Tag(name = "menu", description = "API related to MenuPlan Info")

        },
        servers = {
            @Server(
                    description = "For Local host testing",
                    url = "http://localhost:8080/exam3sem"
            ),
            @Server(
                    description = "Server API",
                    url = "https://sandersolutions.com/exam3sem/"
            )

        }
)

@Path("menu")
public class MenuResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);

    private static final MenuFacade FACADE = MenuFacade.getMenuFacade(EMF);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello Hobby\"}";
    }

//    @GET
//    @Path("id/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Operation(summary = "Get Menuplan info by ID",
//            tags = {"menuplan"},
//            responses = {
//                @ApiResponse(
//                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonOutDTO.class))),
//                @ApiResponse(responseCode = "200", description = "The Requested hobby"),
//                @ApiResponse(responseCode = "403", description = "Not authenticated - do login"),
//                @ApiResponse(responseCode = "404", description = "Person not found")})
//    public MenuPlanOutDTO getHobbyInfo(@PathParam("id") int menuPlanID) {
//        if (menuPlanID > 0 && menuPlanID == 99999) {
//            // for test
//            return new MenuPlanOutDTO();
//        } else {
//            // here should be something real :-)
//            return new MenuPlanOutDTO("info@simonskodebiks.dk", "Gũnther", "Steiner");
//        }
//    }
//    @PUT
//    @Path("edit")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Operation(summary = "Edit existing hobby", tags = {"hobby"},
//            responses = {
//                @ApiResponse(responseCode = "200", description = "The edited person"),
//                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
//            })
//    public HobbyOutDTO editPerson(HobbyInDTO hobbyWithChanges) {
//        if (hobbyWithChanges.getHobbyID()== 0 ) {
//            throw new WebApplicationException("Not all required arguments included", 400);
//        }
//        return FACADE.editHobby(hobbyWithChanges);
//    }
//    @POST
//    @Path("add")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Operation(summary = "Add a new hobby to a person", tags = {"hobby"},
//            responses = {
//                @ApiResponse(responseCode = "200", description = "Person with added hobby"),
//                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
//            })
//    public HobbyOutDTO addHobby(HobbyInDTO newHobby) {
//        if (newHobby.getName()== null || newHobby.getDescription()== null) {
//            throw new WebApplicationException("Not all required arguments included", 400);
//        }
//        
//        return FACADE.addHobby(newHobby);
//    }
//    @DELETE
//    @Path("delete/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Operation(summary = "Delete Hobby ",
//            tags = {"hobby"},
//            responses = {
//                @ApiResponse(
//                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonOutDTO.class))),
//                @ApiResponse(responseCode = "200", description = "The Requested hobby"),
//                @ApiResponse(responseCode = "403", description = "Not authenticated - do login"),
//                @ApiResponse(responseCode = "404", description = "Person not found")})
//    public String deleteHobby(@PathParam("id") int hobbyID) {
//            return FACADE.deleteHobby(hobbyID);
//    }
//    private ExecutorService executorService = java.util.concurrent.Executors.newCachedThreadPool();
//    Get all hobbies
//    @GET
//    @Path("hobbies")
////    @RolesAllowed({"user", "admin"})
//    @Produces(MediaType.APPLICATION_JSON)
//    @Operation(summary = "Get all hobbies",
//            tags = {"person"},
//            responses = {
//                @ApiResponse(
//                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonOutDTO.class))),
//                @ApiResponse(responseCode = "200", description = "The Requested hobbies"),
//                @ApiResponse(responseCode = "403", description = "Not authenticated - do login"),
//                @ApiResponse(responseCode = "404", description = "Hobbies not found")})
//    public void getAllHobbies(@Suspended
//    final AsyncResponse asyncResponse) {
//        executorService.submit(new Runnable() {
//            public void run() {
//                asyncResponse.resume(doGetAllHobbies());
//            }
//        });
//    }
//    private List<Hobby> doGetAllHobbies() {
//        List<HobbyOutDTO> p = new ArrayList();
//        return FACADE.getHobbies();
//    }
    

    @GET
    @Path("fill")
    @Operation(summary = "Fill database with start data",
            tags = {"menu"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = MenuPlanOutDTO.class))),
                @ApiResponse(responseCode = "200", description = "Database filled"),
                @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @Produces(MediaType.APPLICATION_JSON)
    public String fillDB() {
        FACADE.fillUp();
        return "{\"msg\": \"DB filled\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("empty")
    @Operation(summary = "Empty database",
            tags = {"menu"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = MenuPlanOutDTO.class))),
                @ApiResponse(responseCode = "200", description = "Database emptied"),
                @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    public String emptyDB() {
        FACADE.emptyDB();
        return "{\"msg\": \"DB emptied\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("filluser")
    public String fillUsersDB() {
        new SetupTestUsers().fill();
        return "{\"msg\": \"Users filled\"}";
    }

}
