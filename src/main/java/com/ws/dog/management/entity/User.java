package com.ws.dog.management.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "user")
public class User {
	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;
    
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private Set<RoleEntity> roles = new HashSet<RoleEntity>();

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = PASSWORD_ENCODER.encode(passwordHash);
    }

	public Set<RoleEntity> getRoles() {
		return roles;
	}
	
	public String[] getRoleStrs() {
		Object[] roleArray = this.getRoles().toArray();
		String[] roleList = new String[roleArray.length];
		for (int i = 0; i < roleArray.length; i++) {
			roleList[i] = roleArray[i].toString();
		}
		return roleList;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", email='" + email.replaceFirst("@.*", "@***") +
//                ", passwordHash='" + passwordHash.substring(0, 10) +
//                ", role=" + role +
//                '}';
//    }
}
