import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentServlet extends javax.servlet.http.HttpServlet {


    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        Map<String, Object> content = new HashMap<>();
        StudentRepo sr = new StudentRepo();
        List<Student> students = sr.getAllStudent();
        content.put("students", students);
        try {
            Render.render(response, content, "/students.ftl");
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }
}
