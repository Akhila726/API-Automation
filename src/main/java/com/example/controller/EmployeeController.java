package com.example.controller;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeebyID(@PathVariable(value = "id")Long employeeID){
        Employee employee = employeeRepository.findById(employeeID).orElseThrow(()->new RuntimeException("Employee not found"+employeeID));
        return ResponseEntity.ok().body(employee);
    }
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){

        return employeeRepository.save(employee);
    }
    @PutMapping ("/employees/{id}")
    public ResponseEntity<Employee> updatedEmployee(@PathVariable(value = "id")Long employeeID,@RequestBody Employee employeedetails){
        Employee employee = employeeRepository.findById(employeeID).orElseThrow(()->new RuntimeException("Employee not found"+employeeID));
        employee.setEmail(employeedetails.getEmail());
        employee.setFirst_name(employeedetails.getFirst_name());
        employee.setLast_name(employeedetails.getLast_name());
        final  Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
    @DeleteMapping("/employees/{id}")
    public Map<String,Boolean>deleteEmployee(@PathVariable(value = "id")Long employeeID){
        Employee employee=employeeRepository.findById(employeeID).orElseThrow(()->new RuntimeException("Employee not found"+employeeID));
        employeeRepository.deleteById(employeeID);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted",Boolean.TRUE);
        return response;

    }
}
