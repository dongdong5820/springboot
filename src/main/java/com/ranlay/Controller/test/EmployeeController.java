package com.ranlay.Controller.test;

import com.ranlay.core.utils.DateUtil;
import com.ranlay.mysql.mapper.EmployeeMapper;
import com.ranlay.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Ranlay.su
 * @date: 2021-07-06 17:06
 * @version: 1.0
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping("/list")
    public List<Employee> List() {
        List<Employee> employees = employeeMapper.queryEmployeeList();
        String format = "yyyy-MM-dd";
        employees.forEach(k -> k.setBirthdayStr(DateUtil.timestampToDate(String.valueOf(k.getBirthday()), format)));

        return employees;
    }

    @GetMapping("/info/{id}")
    public Employee info(@PathVariable("id") Integer id) {
        Employee employee = employeeMapper.queryEmployeeById(id);
        String format = "yyyy-MM-dd";
        employee.setBirthdayStr(DateUtil.timestampToDate(String.valueOf(employee.getBirthday()), format));

        return employee;
    }

    @PostMapping("/add")
    public String add(Employee employee) {
        System.out.println("=====>" + employee);
        String format = "yyyy-MM-dd";
        employee.setBirthday(DateUtil.dateToTimestamp(employee.getBirthdayStr(), format));
        employeeMapper.addEmployee(employee);

        return "add employee success";
    }

    @PostMapping("/update")
    public String update(Employee employee) {
        System.out.println("====>" + employee);
        String format = "yyyy-MM-dd";
        employee.setBirthday(DateUtil.dateToTimestamp(employee.getBirthdayStr(), format));
        employeeMapper.updateEmployee(employee);

        return "update employee success";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        System.out.println("===>" + id);
        employeeMapper.deleteEmployee(id);

        return "delete employee success";
    }
}
