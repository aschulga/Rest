package server;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/references")
public class ServiceHandler {

    private Controller controller = new Controller();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllBooks() {
        return controller.getAllBooks();
    }

    @POST
    @Path("add/book")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void addBook(@FormParam("book") String book) {
        controller.addBook(book);
    }

    @DELETE
    @Path("delete/book/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteBook(@PathParam("id") String id) {
        controller.deleteBook(Integer.parseInt(id));
    }

    @PUT
    @Path("change/book/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void changeBook(@PathParam("id") String id, @FormParam("newBook")String newBook) {
        controller.changeBook(Integer.parseInt(id), newBook);
    }

    @GET
    @Path("getAllThemes/in/book/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllThemesInBock(@PathParam("id") String id) {
        return controller.getAllThemes(Integer.parseInt(id));
    }

    @POST
    @Path("add/theme/in/book/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void addTheme(@PathParam("id") String id, @FormParam("theme") String theme) {
        controller.addTheme(Integer.parseInt(id), theme);
    }

    @DELETE
    @Path("delete/theme/{idTheme}/in/book/{idBook}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteBook(@PathParam("idBook") String idBooks, @PathParam("idTheme") String idTheme) {
        controller.deleteTheme(Integer.parseInt(idBooks), Integer.parseInt(idTheme));
    }

    @PUT
    @Path("change/theme/{idTheme}/in/book/{idBook}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void changeTheme(@PathParam("idTheme") String idTheme,
                            @PathParam("idBook") String idBook,
                            @FormParam("newTheme")String newTheme) {
        controller.changeTheme(Integer.parseInt(idBook), Integer.parseInt(idTheme), newTheme);
    }
}