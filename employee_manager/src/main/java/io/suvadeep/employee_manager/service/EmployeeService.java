package io.suvadeep.employee_manager.service;

import io.suvadeep.employee_manager.exception.EmployeeAlreadyExists;
import io.suvadeep.employee_manager.exception.EmployeeNotFoundException;
import io.suvadeep.employee_manager.model.Employee;
import io.suvadeep.employee_manager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    private static final String EMPLOYEE_ID = "Employee with id: ";
    private static final String DOES_NOT_EXISTS = " does not exists.";

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee registerEmployee(Employee employee) throws EmployeeAlreadyExists {
        Optional<Employee> optionalEmployee = employeeRepository.findEmployeeByEmployeeEmail(employee.getEmployeeEmail());
        if (optionalEmployee.isPresent()) {
            throw new EmployeeAlreadyExists("Employee with email: " + employee.getEmployeeEmail() + " already exists.");
        }
        employee.setEmployeeRandomCode(UUID.randomUUID().toString());
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long employeeId) throws EmployeeNotFoundException {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_ID + employeeId + DOES_NOT_EXISTS));
    }

    public void deleteEmployee(Long employeeId) throws EmployeeNotFoundException {
        if (!employeeRepository.existsById(employeeId)) {
            throw new EmployeeNotFoundException(EMPLOYEE_ID + employeeId + DOES_NOT_EXISTS);
        }
        employeeRepository.deleteById(employeeId);
    }

    @Transactional
    public Employee updateEmployee(Employee employee) throws EmployeeNotFoundException {
        Employee updateEmployee = employeeRepository.findEmployeeByEmployeeEmail(employee.getEmployeeEmail()).orElseThrow(() -> new EmployeeNotFoundException("No employee with " + employee.getEmployeeEmail() + DOES_NOT_EXISTS));
        /*To update name of the employee*/
        if (employee.getEmployeeName() != null && employee.getEmployeeName().length() > 0 && !Objects.equals(employee.getEmployeeName(), updateEmployee.getEmployeeName())) {
            updateEmployee.setEmployeeName(employee.getEmployeeName());
        }
        /*To update job title of the employee*/
        if (employee.getEmployeeJobTitle() != null && employee.getEmployeeJobTitle().length() > 0 && !Objects.equals(employee.getEmployeeJobTitle(), updateEmployee.getEmployeeJobTitle())) {
            updateEmployee.setEmployeeJobTitle(employee.getEmployeeJobTitle());
        }
        /*To update the phone number of the employee*/
        if (employee.getEmployeePhoneNumber() != null && employee.getEmployeePhoneNumber().length() > 0 && !Objects.equals(employee.getEmployeePhoneNumber(), updateEmployee.getEmployeePhoneNumber())) {
            updateEmployee.setEmployeePhoneNumber(employee.getEmployeePhoneNumber());
        }
        /*To update the profile picture of the employee*/
        if (employee.getEmployeeProfilePictureUrl() != null && employee.getEmployeeProfilePictureUrl().length() > 0 && !Objects.equals(employee.getEmployeeProfilePictureUrl(), updateEmployee.getEmployeeProfilePictureUrl())) {
            updateEmployee.setEmployeeProfilePictureUrl(employee.getEmployeeProfilePictureUrl());
        }
        return updateEmployee;
    }

    @Transactional
    public Employee updateEmployeeEmail(Long employeeId, String employeeEmail) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_ID + employeeId + DOES_NOT_EXISTS));
        if (employeeEmail != null && employeeEmail.length() > 0 && !Objects.equals(employee.getEmployeeEmail(), employeeEmail)) {
            employee.setEmployeeEmail(employeeEmail);
        }
        return employee;
    }
}
