package cn.chen.model;

import java.io.Serializable;

/**
 * @Author Chen
 * @Date 2020/3/9 20:05
 **/
public class User implements Serializable {
    private static final long serialVersionUID = 4764784728907988083L;

    private long id;
    private String userName;
    private String note;

    public User() {
    }

    public User(long id, String userName, String note) {
        this.id = id;
        this.userName = userName;
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
