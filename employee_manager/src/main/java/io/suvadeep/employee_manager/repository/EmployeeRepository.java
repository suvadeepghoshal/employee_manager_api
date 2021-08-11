package io.suvadeep.employee_manager.repository;

import io.suvadeep.employee_manager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select e from Employee e where e.employeeEmail = ?1")
    <E extends Employee> Optional<E> findEmployeeByEmployeeEmail(String employeeEmail);
}