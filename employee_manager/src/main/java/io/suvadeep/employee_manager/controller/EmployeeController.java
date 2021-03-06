package io.suvadeep.employee_manager.controller;

import io.suvadeep.employee_manager.exception.EmployeeAlreadyExists;
import io.suvadeep.employee_manager.exception.EmployeeNotFoundException;
import io.suvadeep.employee_manager.model.Employee;
import io.suvadeep.employee_manager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping(path = "/find/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "employeeId") Long employeeId) throws EmployeeNotFoundException {
        return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<Employee> registerEmployee(@RequestBody Employee employee) throws EmployeeAlreadyExists {
        try {
            return new ResponseEntity<>(employeeService.registerEmployee(employee), HttpStatus.CREATED);
        } catch (EmployeeAlreadyExists e) {
            throw new IllegalStateException("Employee with email: " + employee.getEmployeeEmail() + " already exists.");
        }

    }

    @PutMapping(path = "/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) throws EmployeeNotFoundException {
        return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatus.OK);
    }

    @PutMapping(path = "/update/email/{employeeId}")
    public ResponseEntity<Employee> updateEmployeeEmailAddress(@PathVariable Long employeeId, @RequestBody String employeeEmail) throws EmployeeNotFoundException {
        return new ResponseEntity<>(employeeService.updateEmployeeEmail(employeeId, employeeEmail), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId) throws EmployeeNotFoundException {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
