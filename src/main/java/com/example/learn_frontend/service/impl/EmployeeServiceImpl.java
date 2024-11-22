package com.example.learn_frontend.service.impl;

import com.example.learn_frontend.dto.EmployeeDto;
import com.example.learn_frontend.entity.Employee;
import com.example.learn_frontend.exception.ResourceNotFoundException;
import com.example.learn_frontend.repository.EmployeeRepository;
import com.example.learn_frontend.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto){
        Employee employee = new Employee(employeeDto);
        employee.setStatus(1);
        Employee savedEmployee = employeeRepository.saveAndFlush(employee);
        return new EmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exist with given id : " + id));
        return new EmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee(){
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream().map(x -> new EmployeeDto(x)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto){
        Employee employee = employeeRepository.findById(employeeDto.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exist with given id : " + employeeDto.getId()));

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

        Employee savedEmployee = employeeRepository.saveAndFlush(employee);
        return new EmployeeDto(savedEmployee);
    }

    @Override
    public void updateEmployeeStatus(List<Long> employeeIdList, Integer status){
        List<Employee> employeeList = new ArrayList<>();
        for(Long employeeId : employeeIdList){
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(()-> new ResourceNotFoundException("Employee not exist with given id : " + employeeId));
            employee.setStatus(status);
            employeeList.add(employee);
        }
    }

    @Override
    public void deleteEmployee(Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exist with given id : " + id));

        employeeRepository.deleteById(id);
    }
}
