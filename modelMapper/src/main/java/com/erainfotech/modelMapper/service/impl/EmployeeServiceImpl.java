package com.erainfotech.modelMapper.service.impl;

import com.erainfotech.modelMapper.dto.EmployeeDto;
import com.erainfotech.modelMapper.entity.Employee;
import com.erainfotech.modelMapper.exception.ResourceNotFoundException;
import com.erainfotech.modelMapper.repository.EmployeeRepository;
import com.erainfotech.modelMapper.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        //convert DTO to entity
        Employee employee=mapToEntity(employeeDto);
        Employee newEmployee= employeeRepository.save(employee);

        //convert entity into DTO
        EmployeeDto employeeResponse=mapToDTO(newEmployee);

        return employeeResponse;
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
       List<Employee> employees= employeeRepository.findAll();
       return employees.stream().map(employee -> mapToDTO(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("employee", "id",id));
        return mapToDTO(employee);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id) {
        //get post by id from the database
        Employee employee=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("employee", "id",id));
        employee.setEmpId(employeeDto.getEmpId());
        employee.setEmpName(employeeDto.getEmpName());
        employee.setEmpMobile(employeeDto.getEmpMobile());
        employee.setEmpSalary(employeeDto.getEmpSalary());

         Employee updateEmployee = employeeRepository.save(employee);

         return mapToDTO(updateEmployee);

    }

    @Override
    public void deleteEmployeeById(Long id) {
        Employee employee=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("employee", "id",id));
        employeeRepository.delete(employee);
    }

    // entity into dto
    private EmployeeDto mapToDTO(Employee employee){
        EmployeeDto employeeDto=new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setEmpId(employee.getEmpId());
        employeeDto.setEmpName(employee.getEmpName());
        employeeDto.setEmpMobile(employeeDto.getEmpMobile());
        employeeDto.setEmpSalary(employeeDto.getEmpSalary());

        return employeeDto;
    }

    //convert dto into entity
    private Employee mapToEntity(EmployeeDto employeeDto){
        Employee employee=new Employee();
        employee.setEmpId(employeeDto.getEmpId());
        employee.setEmpName(employeeDto.getEmpName());
        employee.setEmpMobile(employeeDto.getEmpMobile());
        employee.setEmpSalary(employeeDto.getEmpSalary());

        return employee;

    }
}
