package client.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Book;
import models.Theme;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class Handler {

    private String endPoint = "http://localhost:8080/";

    private ClientConfig config = new ClientConfig();
    private Client client = ClientBuilder.newClient(config);
    private WebTarget target = client.target(endPoint);
    private Gson gson = new Gson();

    public List<Book> getAllBooks(){
        String response = target.path("rest").
                path("references").
                request().
                accept(MediaType.APPLICATION_JSON).
                get(String.class);

        List<Book> books = gson.fromJson(response, new TypeToken<List<Book>>(){}.getType());
        return books;
    }

    public void addBook(Book book) {
        Form form = new Form();
        form.param("book", gson.toJson(book));
        Response response = target.
                path("rest").
                path("references").
                path("add").
                path("book").
                request().
                post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);

    }

    public void deleteBook(String index){
        Response response = target.
                path("rest").
                path("references").
                path("delete").
                path("book").
                path(index).
                request().
                delete();
    }

    public void changeBook(String index, Book book){
        Form form = new Form();
        form.param("newBook", gson.toJson(book));
        Response response = target.
                path("rest").
                path("references").
                path("change").
                path("book").
                path(index).
                request().
                put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);
    }

    public List<Theme> getAllThemesInBock(String index){
        String response = target.path("rest").
                path("references").
                path("getAllThemes").
                path("in").
                path("book").
                path(index).
                request().
                accept(MediaType.APPLICATION_JSON).
                get(String.class);

        List<Theme> themes = gson.fromJson(response, new TypeToken<List<Theme>>(){}.getType());
        return themes;
    }

    public void addTheme(Theme theme, String index){
        Form form = new Form();
        form.param("theme", gson.toJson(theme));
        Response response = target.
                path("rest").
                path("references").
                path("add").
                path("theme").
                path("in").
                path("book").
                path(index).
                request().
                post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);
    }

    public void deleteTheme(String index, String indexInBook){
        Response response = target.
                path("rest").
                path("references").
                path("delete").
                path("theme").
                path(index).
                path("in").
                path("book").
                path(indexInBook).
                request().
                delete();
    }

    public void changeTheme(String index, Theme theme, String indexInBook){
        Form form = new Form();
        form.param("newTheme", gson.toJson(theme));
        Response response = target.
                path("rest").
                path("references").
                path("change").
                path("theme").
                path(index).
                path("in").
                path("book").
                path(indexInBook).
                request().
                put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);
    }
}
