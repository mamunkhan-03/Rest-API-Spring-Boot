package com.erainfotech.modelMapper.repository;

import com.erainfotech.modelMapper.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
