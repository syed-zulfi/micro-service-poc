package com.royalfoods.department.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "DEPARTMENT")
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "DEPART_NAME")
    private String departName;
    @Column(name = "DEPART_CODE")
    private String departCode;

}
