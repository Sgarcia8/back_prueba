package prueba.api.user.responses;

import prueba.api.user.entity.UserP;

public class AuthResponse {
    private String token;
    private UserP user;

    // Constructor, getters y setters

    public AuthResponse(String token, UserP user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserP getUser() {
        return user;
    }

    public void setUser(UserP user) {
        this.user = user;
    }
}