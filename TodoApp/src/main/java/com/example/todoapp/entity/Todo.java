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
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "todo_list_id")
    private List<TodoItem> todoItems;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
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
