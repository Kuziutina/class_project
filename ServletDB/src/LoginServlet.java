import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {


    private Connection conn;

    @Override
    public void init() throws ServletException {
        conn = DBConnection.getConnection();
    }


    private boolean check(String username, String password) {
        if (username == null || password == null) return false;
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("" +
                    "select * from users where user_name = ?");
            st.setString(1, username);

            ResultSet rs = st.executeQuery();
            rs.next();
            return rs.getString("password").equals(password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (check(username, password)) {
            request.getSession().setAttribute("current_user", username);
            response.sendRedirect("/students");
        } else {
            response.setContentType("text/html");
            response.getWriter().println("<form method=\"POST\">" +
                    "<p> ERRor</p>" +
                    "<input type=\"text\" name=\"username\">" +
                    "<input type=\"password\" name=\"password\">" +
                    "<input type=\"submit\" value=\"ok\">" +
                    "</form>");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Render.render(response, new HashMap<>(), "/login.ftl");
        } catch (TemplateException e) {
            e.printStackTrace();
        }
//
    }
}
