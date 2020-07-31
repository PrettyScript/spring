package com.tekcamp.ulysse.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.security.SecureRandom;

@Entity
public class User {

    @Id
    private Long id;
    private String userId;

    private String firstName;
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;
    private String encryptedPassword;

    public User() {
    }

    public User(String firstName, String lastName, String email, String encryptedPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.encryptedPassword = encryptedPassword;

    }

    public void setId(Long id) {
        SecureRandom random = new SecureRandom();
        id = random.nextLong();
        if(id <= 0) {
            id = id * -1;
        }
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }
}
