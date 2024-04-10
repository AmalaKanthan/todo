package com.example.todoapp.dto;

import com.example.todoapp.entity.User;
import lombok.*;

import javax.validation.constraints.*;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Name is required")
    private String name;

    @Pattern(regexp="(^$|[0-9]{10})", message = "Telephone number should be 10 digits")
    private String telephoneNumber;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age must be greater than or equal to 18")
    private Integer age;
    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;

    public static UserDTO mapToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setTelephoneNumber(user.getTelephoneNumber());
        userDTO.setAge(user.getAge());
        userDTO.setActive(user.isActive());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        return userDTO;
    }

    public User mapToEntity() {
        User user = new User();
        user.setId(this.id);
        user.setEmail(this.email);
        user.setName(this.name);
        user.setTelephoneNumber(this.telephoneNumber);
        user.setAge(this.age);
        user.setActive(this.isActive);
        user.setCreatedAt(this.createdAt);
        user.setUpdatedAt(this.updatedAt);
        return user;
    }
}
