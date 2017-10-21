import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ViewStudentServlet")
public class ViewStudentServlet extends HttpServlet {

    private Connection conn;
    private Student student;

    @Override
    public void init() throws ServletException {
        conn = DBConnection.getConnection();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("last_name");
        String group = request.getParameter("group");
        StudentRepo sr = new StudentRepo();
        GroupRepo gr = new GroupRepo();
        HttpSession session = request.getSession();
        Student lastSt = (Student) session.getAttribute("student");
        Student nSt = new Student(name, lastSt.getId(), (gr.getGroupByName(group)).getId());
        sr.updateStudent(lastSt, nSt);
        response.sendRedirect("/students/" + lastSt.getId());



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.getWriter().println("Student");

        String[] path = request.getPathInfo().split("/");

        String id = path[1];
//        PreparedStatement st = null;
        try {
            StudentRepo sr = new StudentRepo();
            Student student = sr.getStudentById(id);
            Map <String, Object> content = null;

            if (path.length == 2) {
                content = new HashMap<>();
                content.put("student", student);
                Render.render(response, content, "/viewStudent.ftl");


            }
            else if (path.length == 3 && path[2].equals("edit")) {
                content = new HashMap<>();
                HttpSession session = request.getSession();
                session.setAttribute("student", student);
                content.put("student", student);
                Render.render(response, content, "/createStudent.ftl");
//                student = new Student(last_name, rs.getInt("id"), rs.getInt("group_id"));

//                response.setContentType("text/html");
//                response.getWriter().println("<form method=\"POST\" action=\"/students/"+ id + "\">" +
//                        "<input type=\"text\" name=\"last_name\" value=\"" + last_name +"\">" +
//                        "<input type=\"text\" name=\"group\" value=\"" + group + "\">" +
//                        "<input type=\"submit\" value=\"ok\">" +
//                        "</form>" +
//                        "<a href=\"/students/" + id + "\">back</a>");

            }
            else if (path.length == 3 && path[2].equals("delete")) {
                StudentRepo sp = new StudentRepo();
                sp.deleteById(Integer.parseInt(id));
                response.sendRedirect("/students");
            }

        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }
}
