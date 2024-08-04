package com.scm.entities;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="user")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails{

    @Id
    private String userId;
    
    @Column(name = "user_name", nullable = false)
    private String name;
    
    @Column(unique = true, nullable = false)
    private String email;
    @Getter(AccessLevel.NONE)
    private String password;
    
    @Column(columnDefinition = "TEXT")
    private String about;
    @Column(columnDefinition = "TEXT")
    private String profilePic;
    
    private String phoneNumber;

    //information
    @Getter(value = AccessLevel.NONE)
    private boolean enabled = false;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    
    // self, google, linkedin, github
    @Enumerated(value = EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String providerUserId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //list of [USER, ADMIN]
        //collection of SimpleGrantedAuthority[roles{ADMIN, USER}]
        Collection<SimpleGrantedAuthority> roles = roleList.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
    }

    //email id is the username for this project

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

}
