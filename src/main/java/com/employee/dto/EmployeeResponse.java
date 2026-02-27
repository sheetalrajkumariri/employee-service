package com.employee.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeResponse implements Serializable {
    private String id;
    private int age;
    private String name;
    private String address;
}
