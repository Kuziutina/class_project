import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentServlet extends javax.servlet.http.HttpServlet {

    private Connection conn;

    @Override
    public void init() {
        try {

            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/firstDB",
                    "postgres",
                    "cjabqrf"
            );

        } catch (ClassNotFoundException e) {
            e.printStackTrace();}
         catch (SQLException e) {
            e.printStackTrace();
        }


    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
//        String username = (String) request.getSession().getAttribute("current_user");
//        if (username != null) {

//            response.setContentType("text/html");
            String id = request.getParameter("id");
            if (id == null) {
//                try {
//                    Statement st = conn.createStatement();
//                    ResultSet rs = st.executeQuery("select * from students");
//                    response.getWriter().println("<ul>");
//                    String user_id;
//                    while (rs.next()) {
//                        user_id = rs.getString("id");
//                        response.getWriter().println("<li><a href=\"/students/" + user_id + "\">" + rs.getString("last_name") + "  </a> " +
//                                "<a href=\"/students/" + user_id +"/edit\">edit    </a>" +
//                                "<a href=\"/students/" + user_id +"/delete\">delete    </a></li>");
//                    }
//                    response.getWriter().println("</ul>");
//                    response.getWriter().println("<a href=\"/students/create\">add new </a>");
//
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
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
            else {
                try {
                    PreparedStatement st = conn.prepareStatement("" +
                        "select * from students where id = ?");

                    st.setInt(1, Integer.parseInt(id));
                    ResultSet rs = st.executeQuery();
                    rs.next();
                    response.getWriter().println(rs.getString("last_name"));

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
//        }
//        else {
//            response.sendRedirect("/login");
//        }
    }
}
