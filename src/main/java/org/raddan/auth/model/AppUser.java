package org.raddan.auth.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * @author Alexander Dudkin
 */
@Entity
@Table(name = "users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    List<AppUserRole> appUserRoles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @Size(min = 4, max = 255, message = "Minimum username length: 4 characters") String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 4, max = 255, message = "Minimum username length: 4 characters") String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public @Size(min = 8, message = "Minimum password length: 8 characters") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 8, message = "Minimum password length: 8 characters") String password) {
        this.password = password;
    }

    public List<AppUserRole> getAppUserRoles() {
        return appUserRoles;
    }

    public void setAppUserRoles(List<AppUserRole> appUserRoles) {
        this.appUserRoles = appUserRoles;
    }

}
