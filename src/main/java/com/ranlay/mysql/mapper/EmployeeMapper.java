package com.ranlay.mysql.mapper;

import com.ranlay.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Ranlay.su
 * @date: 2021-07-06 15:59
 * @version: 1.0
 */
@Mapper
@Repository
public interface EmployeeMapper {
    /**
     * 查询员工列表
     * @return
     */
    List<Employee> queryEmployeeList();

    /**
     * 获取当个员工
     * @param id
     * @return
     */
    Employee queryEmployeeById(Integer id);

    /**
     * 新增员工
     * @param employee
     * @return
     */
    int addEmployee(Employee employee);

    /**
     * 修改员工
     * @param employee
     * @return
     */
    int updateEmployee(Employee employee);

    /**
     * 删除员工
     * @param id
     * @return
     */
    int deleteEmployee(Integer id);
}
