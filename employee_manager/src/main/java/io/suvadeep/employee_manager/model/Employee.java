package io.suvadeep.employee_manager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Employee")
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    @Id
    @SequenceGenerator(name = "employee_sequence", sequenceName = "employee_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence")
    @Column(name = "id", nullable = false, updatable = false)
    private Long employeeId;

    @Column(name = "name", nullable = false, updatable = true)
    private String employeeName;

    @Column(name = "email", nullable = false, updatable = true)
    private String employeeEmail;

    @Column(name = "job_title", nullable = false, updatable = true)
    private String employeeJobTitle;

    @Column(name = "phone_number", nullable = false, updatable = true)
    private String employeePhoneNumber;

    @Column(name = "picture_url", nullable = true, updatable = true)
    private String employeeProfilePictureUrl;

    @Column(name = "secret_code", nullable = false, updatable = false)
    private String employeeRandomCode;
}
