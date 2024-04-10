package com.example.todoapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "age")
    private int age;

    @Column(name="is_active")
    private boolean isActive = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Todo> todos;
    @Column(name="crated_at")
    private Date createdAt;

    @Column(name="update_at")
    private Date updatedAt;


    @PrePersist
    public void onSave(){

        Date currentDateTime = new Date();

        if(createdAt == null){
            this.createdAt = currentDateTime;
        }

        this.updatedAt = currentDateTime;
    }

    @PostPersist
    public void onUpdate(){
        Date curentDateTime = new Date();
        this.updatedAt = curentDateTime;
    }
}