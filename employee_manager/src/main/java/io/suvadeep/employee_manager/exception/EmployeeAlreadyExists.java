package io.suvadeep.employee_manager.exception;

public class EmployeeAlreadyExists extends Throwable {
    public EmployeeAlreadyExists(String message) {
        super(message);
    }
}
