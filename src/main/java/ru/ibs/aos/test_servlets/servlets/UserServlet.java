package ru.ibs.aos.test_servlets.servlets;

import com.google.gson.Gson;
import ru.ibs.aos.test_servlets.Person;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(urlPatterns = "/second")
public class UserServlet extends HttpServlet {
    private Gson gson = new Gson();
    private String gsonPath = "src/main/resources/person.json";
    private String delFile = "src/main/resources/test.json";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //TODO: HTTP GET запрос
        try {
            com.google.gson.stream.JsonReader r = new com.google.gson.stream.JsonReader(new FileReader(gsonPath));
            Person person2 = gson.fromJson(r, Person.class);
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");
            String toJson = this.gson.toJson(person2);
            r.close();
            out.println(toJson);
            out.flush();
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //TODO: HTTP POST запрос
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        Person person = new Person();
        person.setFirstName("Sam");
        person.setLastName("Samsonov");
        Writer writer = null;
        try {

            writer = new FileWriter(gsonPath);
            if (writer != null) {
                new Gson().toJson(person, writer);
                System.getProperty("line.separator");
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        //TODO: HTTP PUT запрос
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        Person person = new Person();
        person.setFirstName("Denis");
        person.setLastName("Popov");
        try {
            Writer writer = new FileWriter(gsonPath);
            new Gson().toJson(person, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        //TODO: HTTP DELETE запрос
        try {
            File file = new File(delFile);
            file.delete();
        } catch (SecurityException e) {
            e.getMessage();
        }

    }
}
