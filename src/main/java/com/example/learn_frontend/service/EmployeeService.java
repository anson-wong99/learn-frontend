package com.example.learn_frontend.service;

import com.example.learn_frontend.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto addEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long id);

    List<EmployeeDto> getAllEmployee();

    EmployeeDto updateEmployee(EmployeeDto employeeDto);

    void updateEmployeeStatus(List<Long> employeeIdList, Integer status);

    void deleteEmployee(Long id);
}
