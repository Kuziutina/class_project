import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StudentRepo {

    private Connection conn;

    public List<Student> getStudentByLetter(String buf) {
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("select * from students where last_name like ?");
            ps.setString(1, "%" + buf + "%");
            List<Student> students = new LinkedList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String last_name = rs.getString("last_name");
                int group_id = rs.getInt("group_id");
                students.add(new Student(last_name, rs.getInt("id"), group_id));
            }
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Student getStudentById(String id) {
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            int id_in = Integer.parseInt(id);
            ps = conn.prepareStatement("" +
                    "select * from students where students.id = ?");

            ps.setInt(1, id_in);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String last_name = rs.getString("last_name");
                int group_id = rs.getInt("group_id");
                return new Student(last_name, id_in, group_id);
            }
        } catch (SQLException e) {
         e.printStackTrace();
        }
        return null;
    }

    public List<Student> getAllStudent() {
        Statement st = null;
        List<Student> students = new LinkedList<>();
        try {
            conn = DBConnection.getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from students");
            Student student;
            while (rs.next()) {
                student = new Student(rs.getString("last_name"), rs.getInt("id"), rs.getInt("group_id"));
                students.add(student);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public List<Student> getStudentByName(String name) {
        Statement st = null;
        List<Student> students = new LinkedList<>();
        try {
            conn = DBConnection.getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from students where last_name = " + name);
            Student student;
            while (rs.next()) {
                student = new Student(rs.getString("last_name"), rs.getInt("id"), rs.getInt("group_id"));
                students.add(student);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public boolean addStudent(Student student) {
        conn = DBConnection.getConnection();
        Statement st = null;

        try {
            st = conn.createStatement();
            st.executeUpdate("insert into students(last_name, group_id) values (" +
                "'" + student.getLast_name() + "', " + student.getGroup_id()+ ")");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStudent(Student lastSt, Student newSt) {
        conn = DBConnection.getConnection();
        Statement st = null;

        try {
            st = conn.createStatement();
            st.executeUpdate("update students " +
                    "set last_name = '" + newSt.getLast_name() + "', group_id = " + newSt.getGroup_id() + "" +
                    "where id = " + lastSt.getId());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteById(int id) {
        conn = DBConnection.getConnection();
        Statement st = null;

        try {
            st = conn.createStatement();
            st.executeUpdate("delete from students where id = " + id);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
