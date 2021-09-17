package com.example.projektukas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "AppUser",
        uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@AllArgsConstructor @NoArgsConstructor @Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false,nullable = false)
    private String username;
    @Column(unique = true,updatable = true,nullable = false)
    private String email;
    @Column(nullable = false)
    private String firstName;
    @Column(updatable = true,nullable = false)
    private String lastName;
    @Column(nullable = false,updatable = true)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //to create 3rd table between 2 tables -jointable
    @JoinTable(name = "appUser_roles",
            joinColumns = @JoinColumn(name = "id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private Collection<Role> roles;

    public AppUser(String username, String email, String firstName, String lastName, String password, Collection<Role> roles) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.roles = roles;
    }
}
