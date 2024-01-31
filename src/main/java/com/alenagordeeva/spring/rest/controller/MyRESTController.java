package com.alenagordeeva.spring.rest.controller;

import com.alenagordeeva.spring.rest.exception_handling.EmployeeIncorrectData;
import com.alenagordeeva.spring.rest.exception_handling.NoSuchEmployeeException;
import com.alenagordeeva.spring.rest.service.EmployeeService;
import com.alenagordeeva.spring.rest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    private final EmployeeService employeeService;

    @Autowired
    public MyRESTController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);

        if(employee==null) {
            throw new NoSuchEmployeeException("Нет работника с ID = " +
                    id + " в Базе Данных");
        }
        return employee; //возвращается НЕ сам объект employee, а JSON, соответствующий этому объекту
    }

}
