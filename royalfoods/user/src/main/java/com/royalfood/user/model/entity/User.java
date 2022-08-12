package com.royalfood.user.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "USERS")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "MOBILE")
    private String mobile;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_department" , joinColumns = @JoinColumn(name = "ID"))
    @Column(name = "DEPARTMENT")
    private List<String> departments;
}
