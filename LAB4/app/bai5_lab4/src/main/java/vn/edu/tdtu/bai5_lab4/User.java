package vn.edu.tdtu.bai5_lab4;

public class User {

    private String user;
    private String email;
    public User(int user) {
        this.user = "User"+user;
        this.email = "user"+user+"@tdtu.edu.vn";
    }

    public String getUser() {
        return user;
    }

    public String getEmail() {
        return email;
    }

}
