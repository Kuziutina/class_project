//
//import freemarker.template.TemplateException;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//
//public class AjaxStudentSearchServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            Render.render(response, new HashMap<>(), "/search.ftl");
//        } catch (TemplateException e) {
//            e.printStackTrace();
//        }
//        String q = request.getParameter("q").toLowerCase();
//
//        String lastnames [] = {"Vlad", "Sasha", "Mark"};
//
//        JSONObject jo = new JSONObject();
//        JSONArray ja = new JSONArray();
//        for (String name: lastnames) {
//            if (name.toLowerCase().contains(q)) {
//                ja.put(name);
//            }
//        }
//        try {
//            jo.put("lastnames", ja);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        response.setContentType("text/json");
//        response.getWriter().print(jo.toString());
//        response.getWriter().close();
//    }
//}

import freemarker.template.TemplateException;
import org.json.*;
import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AjaxStudentSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String q = request.getParameter("q").toLowerCase();
        // узнать у БД фамилии студентов, в которых есть q
        StudentRepo sr = new StudentRepo();
        List<String> names = new ArrayList<>();
        for(Student student: sr.getStudentByLetter(q)) {
            names.add(student.getLast_name());
        }
        // построить json по ResultSet
        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();
        for (Student student: sr.getStudentByLetter(q)) {
            ja.put(student.getLast_name());
        }
        try {
            jo.put("lastnames", ja);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // передать json в response
        response.setContentType("text/json");
        response.getWriter().print(jo.toString());
        response.getWriter().close();
    }
}