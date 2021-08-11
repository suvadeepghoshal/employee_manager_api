package io.suvadeep.employee_manager.service;

import io.suvadeep.employee_manager.exception.EmployeeAlreadyExists;
import io.suvadeep.employee_manager.exception.EmployeeNotFoundException;
import io.suvadeep.employee_manager.model.Employee;
import io.suvadeep.employee_manager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee registerEmployee(Employee employee) throws EmployeeAlreadyExists {
        Optional<Employee> optionalEmployee = employeeRepository.findEmployeeByEmail(employee.getEmployeeEmail());
        if (optionalEmployee.isPresent()) {
            throw new EmployeeAlreadyExists("Employee with email: " + employee.getEmployeeEmail() + " already exists.");
        }
        employee.setEmployeeRandomCode(UUID.randomUUID().toString());
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployeeDetails(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long employeeId) throws EmployeeNotFoundException {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("Employee with id: " + employeeId + " does not exists."));
    }

    public void deleteEmployee(Long employeeId) throws EmployeeNotFoundException {
        if (!employeeRepository.existsById(employeeId)) {
            throw new EmployeeNotFoundException("Employee with id: " + employeeId + " does not exists.");
        }
        employeeRepository.deleteById(employeeId);
    }
}
