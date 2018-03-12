package model.pojo;

/**
 * Created by admin on 20.04.2017.
 */
public class User {

    private long id;
    private String login;
    private String password;

    public User(long id, String login, String password){

        this.id = id;
        this.login = login;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}