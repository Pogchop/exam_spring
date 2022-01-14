package com.example.spring_practice.controller;


import com.example.spring_practice.entity.Employee;
import com.example.spring_practice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/employees")
public class EmployeeController {
    @Autowired
    EmployeeRepository repository;

    //create
    @RequestMapping(method = RequestMethod.POST)
    public Employee create(@RequestBody Employee employee){

        return repository.save(employee);
    }

    //getList
    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getList(){

        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    //getDetail
    public Employee getDetail(@PathVariable int id){

        return repository.getById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    //update
    public Employee update(@PathVariable int id, @RequestBody Employee updatedEmployee){
        Employee employee = repository.getById(id);
        if (employee != null){
            employee.setName(updatedEmployee.getName());
            employee.setAge(updatedEmployee.getAge());
            employee.setSalary(updatedEmployee.getSalary());
        }
        repository.save(employee);
        return employee;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    //delete
    public boolean delete(@PathVariable int id){
        Employee employee = repository.getById(id);
        repository.delete(employee);
        return true;
    }

}
