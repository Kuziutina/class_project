public class Student {
    private String last_name;
    private int id = -1;
    private int group_id = -1;
    private Group group;

    public Student(String last_name, int id, int group_id) {
        this.last_name = last_name;
        this.id = id;
        this.group_id = group_id;
        if (this.group_id != -1) {
            GroupRepo gp = new GroupRepo();
            this.group = gp.getGroupById(group_id);
        }
    }

    public Student(String last_name) {
        this.last_name = last_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
