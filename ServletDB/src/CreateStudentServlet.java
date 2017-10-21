import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "CreateStudentServlet")
public class CreateStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("last_name");
        String group = request.getParameter("group");
        GroupRepo gr = new GroupRepo();
        Student student = new Student(name);
        student.setGroup_id((gr.getGroupByName(group)).getId());
        StudentRepo sr = new StudentRepo();
        sr.addStudent(student);
        response.sendRedirect("/students");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Render.render(response, new HashMap<>(), "/createStudent.ftl");
        } catch (TemplateException e) {
            e.printStackTrace();
        }

//        response.setContentType("text/html");
//        response.getWriter().println("<form method=\"POST\" action=\"/students/create\">" +
//                "<input type=\"text\" name=\"last_name\">" +
//                "<input type=\"text\" name=\"group\">" +
//                "<input type=\"submit\" value=\"ok\">" +
//                "</form>" +
//                "<a href=\"/students\">back</a>");
    }
}
