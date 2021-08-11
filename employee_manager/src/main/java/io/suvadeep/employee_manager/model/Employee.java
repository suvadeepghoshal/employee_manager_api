package io.suvadeep.employee_manager.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Employee")
@Table(name = "employee", uniqueConstraints = {@UniqueConstraint(name = "unique_email", columnNames = "email"), @UniqueConstraint(name = "unique_code", columnNames = "secret_code")})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Employee implements Serializable {
    @Id
    @SequenceGenerator(name = "employee_sequence", sequenceName = "employee_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "employee_sequence")
    @Column(name = "id", nullable = false, updatable = false)
    private Long employeeId;

    @Column(name = "name", nullable = false, updatable = true, columnDefinition = "TEXT")
    private String employeeName;

    @Column(name = "email", nullable = false, updatable = true, columnDefinition = "TEXT")
    private String employeeEmail;

    @Column(name = "job_title", nullable = false, updatable = true, columnDefinition = "TEXT")
    private String employeeJobTitle;

    @Column(name = "phone_number", nullable = false, updatable = true, length = 10)
    private String employeePhoneNumber;

    @Column(name = "picture_url", nullable = true, updatable = true, columnDefinition = "TEXT")
    private String employeeProfilePictureUrl;

    @Column(name = "secret_code", nullable = false, updatable = false)
    private String employeeRandomCode;
}
