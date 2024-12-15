package prueba.api.user.entity;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "users")
public class UserP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "status", nullable = false, length = 10)
    private String status;

    @Column(name = "token_revoked", nullable = false)
    private boolean tokenRevoked;

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate = new Date();

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public UserP() {
    }

    public UserP(Long id, String name, String email, String password, String address, String status, boolean tokenRevoked, Date createDate, Role role) {
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
