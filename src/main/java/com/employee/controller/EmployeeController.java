package com.employee.controller;

import com.employee.dto.EmployeeRequest;
import com.employee.dto.EmployeeResponse;
import com.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public EmployeeResponse createEmployee(@RequestBody EmployeeRequest request) {
        log.info("Start::createEmployee()inside the EmployeeController with the Request, {} ", request);
        return employeeService.createEmployee(request);
    }

    @GetMapping("/get/{employeeId}")
    public EmployeeResponse findEmployeeById(@PathVariable int employeeId) {
        log.info("Strat::findEmployeeById()inside the EmployeeController with the id, {} ", employeeId);
        return employeeService.findEmployeeById(employeeId);


    }

    @GetMapping("/list")
    public List<EmployeeResponse> findAllEmployee(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy, @RequestParam String sortDir){
        log.info("Start: findAllEmployee inside the EmployeeController");
        return employeeService.findAllEmployee(page, size, sortBy, sortDir);
    }

    @DeleteMapping("/delete/{employeeId}")
    public String deleteEmployeeById(@PathVariable int employeeId){
        log.info("Start: DeleteEmployeeById()inside the EmployeeController with the id: " +employeeId);
        return employeeService.deleteEmployeeById(employeeId);
    }

    @PutMapping("/update/{employeeId}")
    public EmployeeResponse updateEmployeeById(@PathVariable int employeeId, @RequestBody EmployeeRequest request){
        log.info("Start: UpdateEmployeeById()inside the EmployeeController with the id: " +employeeId);
        return employeeService.updateEmployeeById(employeeId, request);
    }

}
