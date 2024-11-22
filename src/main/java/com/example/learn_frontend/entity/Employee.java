package com.example.learn_frontend.entity;

import com.example.learn_frontend.dto.EmployeeDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "status")
    private Integer status;

    public Employee(EmployeeDto employeeDto){
        this.id = employeeDto.getId();
        this.firstName = employeeDto.getFirstName();
        this.lastName = employeeDto.getLastName();
        this.email = employeeDto.getEmail();
    }
}
