package prueba.api.user.dto;


import prueba.api.user.entity.Role;

import java.util.Date;


public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String status;
    private boolean tokenRevoked;
    private Date createDate;
    private Role role;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String email, String password, String address, String status, boolean tokenRevoked, Date createDate, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.status = status;
        this.tokenRevoked = tokenRevoked;
        this.createDate = createDate;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isTokenRevoked() {
        return tokenRevoked;
    }

    public void setTokenRevoked(boolean tokenRevoked) {
        this.tokenRevoked = tokenRevoked;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
