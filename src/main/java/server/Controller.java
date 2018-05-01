package server;

import com.google.gson.GsonBuilder;
import models.BaseNotations;
import models.Book;

import java.io.*;

import com.google.gson.Gson;
import models.Theme;

public class Controller {

    private final static String FILE_NAME = "D:\\aipos\\Laba 2-3\\fileWithData\\data.json";
    private static BaseNotations base = new BaseNotations();
    private Gson gson = new Gson();

    public String getAllBooks(){
        base.getList().clear();

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(FILE_NAME));
            BaseNotations baseNotations = gson.fromJson(reader, BaseNotations.class);

            if(baseNotations != null){
                int i = 0;
                for(Book book: baseNotations.getList()){
                    book.setId(++i);
                    base.getList().add(book);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return gson.toJson(base.getList());
    }

    public void addBook(String bookJson){
        Book book = gson.fromJson(bookJson, Book.class);
        base.getList().add(book);
        saveChanging();
    }

    public void deleteBook(int index){
        base.getList().remove(index-1);
        saveChanging();
    }

    public void changeBook(int index, String bookJson){
        Book book = gson.fromJson(bookJson, Book.class);
        base.getList().remove(index-1);
        //book.setListThemes(new ArrayList<>());
        base.getList().add(index-1, book);
        saveChanging();
    }

    public String getAllThemes(int index){
        for(int i = 0; i < base.getList().get(index).getListThemes().size(); i++){
            base.getList().get(index).getListThemes().get(i).setId(i+1);
        }
        return gson.toJson(base.getList().get(index).getListThemes());
    }

    public void addTheme(int indexInListBooks, String themeJson){
        Theme theme = gson.fromJson(themeJson, Theme.class);
        base.getList().get(indexInListBooks).getListThemes().add(theme);
        saveChanging();
    }

    public void deleteTheme(int indexInListBooks, int index){
        base.getList().get(indexInListBooks).getListThemes().remove(index-1);
        saveChanging();
    }

    public void changeTheme(int indexInListBooks, int index, String themeJson){
        Theme theme = gson.fromJson(themeJson, Theme.class);
        base.getList().get(indexInListBooks).getListThemes().remove(index-1);
        base.getList().get(indexInListBooks).getListThemes().add(index-1, theme);
        saveChanging();
    }

    public void saveChanging(){

        BaseNotations baseNotations = new BaseNotations();
        baseNotations.setList(base.getList());

        Gson gs = new GsonBuilder().setPrettyPrinting().create();
        String json = gs.toJson(baseNotations);

        FileWriter writer = null;

        try {
            writer = new FileWriter(FILE_NAME);
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
