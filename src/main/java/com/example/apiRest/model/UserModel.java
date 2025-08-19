package com.example.apiRest.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserModel {

    // criando id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // criando nome do user
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    // criando email
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    // criando senha
    @Column(nullable = false)
    private String password;

    public UserModel() {
    }

    public UserModel(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
