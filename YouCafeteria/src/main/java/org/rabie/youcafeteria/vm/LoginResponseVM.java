package org.rabie.youcafeteria.vm;

public class LoginResponseVM {
    private String token;

    public LoginResponseVM(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
