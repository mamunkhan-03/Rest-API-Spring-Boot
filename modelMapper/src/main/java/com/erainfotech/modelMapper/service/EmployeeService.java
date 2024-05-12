package com.erainfotech.modelMapper.service;

import com.erainfotech.modelMapper.dto.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployee ();

    EmployeeDto getEmployeeById (Long id);

    EmployeeDto updateEmployee (EmployeeDto employeeDto, Long id);

    void deleteEmployeeById (Long id);

}
