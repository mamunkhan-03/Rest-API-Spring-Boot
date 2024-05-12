package com.erainfotech.modelMapper.controller;

import com.erainfotech.modelMapper.dto.EmployeeDto;
import com.erainfotech.modelMapper.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    EmployeeService employeeService;


    //create employee api
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee (@RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.createEmployee(employeeDto), HttpStatus.CREATED);
    }

    //create getALl api

    @GetMapping
    public List<EmployeeDto> getAllEmployee (){
        return employeeService.getAllEmployee();
    }

    // create getById api
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById (@PathVariable (name="id")Long id){
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    // update by id api
    @PutMapping("/{id}")
    public ResponseEntity <EmployeeDto>  updateEmployee(@RequestBody EmployeeDto employeeDto,@PathVariable (name="id")Long id ){
      EmployeeDto employeeResponse=  employeeService.updateEmployee(employeeDto, id);
      return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    //delete post rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee (@PathVariable (name="id")Long id ){
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>("Employee successfully deleted", HttpStatus.OK);
    }

}
