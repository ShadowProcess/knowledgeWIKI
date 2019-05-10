package com.example.thymeleafdemo.controller;

import com.example.thymeleafdemo.dao.DepartmentDao;
import com.example.thymeleafdemo.dao.EmployeeDao;
import com.example.thymeleafdemo.entities.Department;
import com.example.thymeleafdemo.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;

@Controller
public class EmployeeController {

    @Resource
    private DepartmentDao departmentDao;
    private final EmployeeDao employeeDao;
    public EmployeeController(@Autowired EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    //查询所有员工返回列表页面
    @GetMapping("emps")
    public String list(Model model){

        Collection<Employee> all = employeeDao.getAll();
        //放在请求域中
        model.addAttribute("emps",all);

        //thymeLeaf默认就会拼串
        //classpath:/templates/emp/list.html
        return "emp/list";
    }


    //来到员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //来到添加页面之前，查询所有部门，在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    //员工添加
    //springMvc自动将请求参数和入参对象的属性--绑定，请求的参数的名字和javabean里边的属性名是一样的
    @PostMapping("emp")
    public String addEmp(Employee employee){

        System.out.println(employee+"保存的员工信息");
        employeeDao.save(employee);
        //redirect表示重定向到一个地址  /代表当前项目路径
        //forward表示转发到一个地址
        return "redirect:/emps";
    }

    //来到修改页面，在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable Integer id,Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);

        //来到添加页面之前，查询所有部门，在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);

        //add是一个修改添加二合一的页面
        return "/emp/add";
    }

    @PutMapping("/emp")
    public String updateEmploy(Employee employee){

        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }

}
