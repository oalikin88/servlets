package ru.ibs.aos.test_servlets.servlets;
import com.google.gson.Gson;
import ru.ibs.aos.test_servlets.Person;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = {"/greet"})
public class FirstServlet extends HttpServlet {
    private final Gson gson = new Gson();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Person person = new Person();
        person.setFirstName("Tom");
        person.setLastName("Cruse");
        String jsPersonToString = this.gson.toJson(person);
        try {
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");
            out.println(jsPersonToString);
            out.flush();
        } catch (IOException e) {
            e.getMessage();
        }


    }

}
