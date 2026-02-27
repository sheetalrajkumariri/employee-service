package com.employee.service.impl;

import com.employee.dto.EmployeeRequest;
import com.employee.dto.EmployeeResponse;
import com.employee.entity.Employee;
import com.employee.exception.NotFoundException;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @CachePut(value = "employeeCache", key = "#result.id")
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        log.info("Start:: createEmployee()inside the EmployeeServiceImpl with the request, {} ", request);
        Employee employee = modelMapper.map(request, Employee.class);
        employee = employeeRepository.save(employee);
        log.info("End::createEmployee()inside the EmployeeServiceImpl with the request, {} ", request);
        return modelMapper.map(employee, EmployeeResponse.class);
    }

    @Override
    @Cacheable(value = "employeeCache", key = "#employeeId")
    public EmployeeResponse findEmployeeById(int employeeId) {
        log.info("Start::findEmployeeById() inside EmployeeServiceImpl with id: {}", employeeId);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + employeeId));
        log.info("End::findEmployeeById() inside EmployeeServiceImpl with id: {}", employeeId);
        return modelMapper.map(employee, EmployeeResponse.class);
    }

    @Override
    @Cacheable(value = "allEmployees")
    public List<EmployeeResponse> findAllEmployee(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Employee> employeePage = employeeRepository.findAll(pageable);

        List<EmployeeResponse> responseList = employeePage.getContent()
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeResponse.class))
                .toList();

        log.info("End:: findAllEmployee() inside EmployeeServiceImpl");

        return responseList;
    }

    @Override
    @CacheEvict(value = "employeeCache", key = "#employeeId")
    public String deleteEmployeeById(int employeeId) {
        log.info("Star: deleteEmployeeBYId()inside the EmployeeServiceImpl with the id: " + employeeId);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + employeeId));
        if (employee.isDeleted()) {
            throw new NotFoundException("employee already deleted with id: " + employeeId);
        }
        employee.setDeleted(true);
        Employee update = employeeRepository.save(employee);
        modelMapper.map(update, EmployeeResponse.class);
        log.info("End: deleteEmployeeBYId()inside the EmployeeServiceImpl with the id: " + employeeId);

        return "Employee deleted successfully with id: " + employeeId;
    }

    @Override
    @CachePut(value = "employeeCache", key = "#employeeId")
    public EmployeeResponse updateEmployeeById(int employeeId, EmployeeRequest request) {
        log.info("Start:: UpdateEmployeeById()inside the EmployeeServiceImpl");
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + employeeId));
        modelMapper.map(request, employee);
        Employee update = employeeRepository.save(employee);
        return modelMapper.map(update, EmployeeResponse.class);
    }
}
