package model.pojo;

/**
 * Created by admin on 20.04.2017.
 */
public class User {

    private Integer id;
    private String login;
    private String password;

    public User(Integer id, String login, String password){

        this.id = id;
        this.login = login;
        this.password = password;
    }
    public User(String login, String password){

        this.id = null;
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
