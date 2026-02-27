package com.employee.service;

import com.employee.dto.EmployeeRequest;
import com.employee.dto.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeRequest request);

    EmployeeResponse findEmployeeById(int employeeId);

    List<EmployeeResponse> findAllEmployee(int page, int size, String sortBy, String sortDir);

    String deleteEmployeeById(int employeeId);

    EmployeeResponse updateEmployeeById(int employeeId, EmployeeRequest request);
}
