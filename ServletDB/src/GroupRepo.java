import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class GroupRepo {

    private Connection conn;

    public Group getGroupByName(String name) {
        Statement st = null;
        try {
            conn = DBConnection.getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from groups where name='" + name + "'");
            if (rs.next()) {
                return new Group(name, rs.getInt("id"));
            }
            else {

                addGroup(new Group(name));
                return getGroupByName(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Group getGroupById(Integer id) {
        Statement st = null;
        try {
            conn = DBConnection.getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from groups where id = " + id);
            if (rs.next() ) {
                return new Group(rs.getString("name"), id);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean addGroup(Group group) {
        conn = DBConnection.getConnection();
        Statement st = null;

        try {
            st = conn.createStatement();
            st.executeUpdate("insert into groups(name) values(" +
                    "'" + group.getName() + "')");

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



}
