package leetcode.hessian;


import java.io.Serializable;

public class UserInfo extends User implements Serializable {
    private static final long serialVersionUID = 4706244349039748166L;
    private String username;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }
}