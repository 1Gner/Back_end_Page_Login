package LoginPage.Entities;


import jakarta.persistence.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tab_user")
public class User {

    ;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    @Column(length = 500)
    private String Nome;
    @Column(length = 500)
    private String Username;
    @Column(length = 500)
    private String Password;
    @Column(length = 500)
    private String Email;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tab_user_role",joinColumns = @JoinColumn(name = "user_id"))
    @Column(name="role_id",length = 20 )
    private List<String> roles = new ArrayList<String>();



    public User(Integer id, String nome, String username, String password, String email) {
        this.id = id;
        Nome = nome;
        Username = username;
        Password = password;
        Email = email;
    }





    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
