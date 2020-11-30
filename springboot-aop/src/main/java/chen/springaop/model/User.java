package chen.springaop.model;

/**
 * @Author Chen
 * @Date 2020/3/18 11:41
 * 一个实体类
 **/

public class User {
    private int id ;
    private String userName;
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
