package com.example.learn_frontend.controller;

import com.example.learn_frontend.dto.EmployeeDto;
import com.example.learn_frontend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employee/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "addEmployee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.addEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping(value = "get")
    public ResponseEntity<EmployeeDto> getEmployeeById(@RequestParam(name = "id") Long id){
        EmployeeDto employeeDto = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping(value = "getAll")
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        List<EmployeeDto> employeeDtoList = employeeService.getAllEmployee();
        return ResponseEntity.ok(employeeDtoList);
    }

    @PostMapping(value = "edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestParam(name = "id") Long id, @RequestBody EmployeeDto employeeDto){
        employeeDto.setId(id);
        EmployeeDto updatedEmployee = employeeService.updateEmployee(employeeDto);
        return ResponseEntity.ok(updatedEmployee);
    }

    @PostMapping(value = "updateStatus/{status}")
    public ResponseEntity<String> updateEmployeeStatus(@PathVariable(name = "status") Integer status,
                                                       @RequestBody List<Long> employeeIdList){
        employeeService.updateEmployeeStatus(employeeIdList, status);
        return ResponseEntity.ok("Employee status updated successfully");
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(name = "id") Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully!");
    }
}
