package com.example.employeedb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping("/")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "list";
    }

    @RequestMapping("/departments")
    public String listDepartments(Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        return "listdepartments";
    }

    @GetMapping("/add")
    public String employeeForm(Model model) {
        model.addAttribute("departments", departmentRepository);
        model.addAttribute("employee", new Employee());
        return "employeeform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentRepository.findAll());
            return "employeeform";
        }
        employeeRepository.save(employee);
        return "redirect:/";
    }
    @GetMapping("/addDepartment")
    public String departmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "departmentform";
    }

    @PostMapping("/processDepartment")
    public String processDepartment(@Valid Department department, BindingResult result) {
        if (result.hasErrors()) {
            return "departmentform";
        }
        departmentRepository.save(department);
        return "redirect:/";
    }


    @RequestMapping("/updateDepartment/{id}")
    public String updateDepartment(@PathVariable("id") long id, Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("department", departmentRepository.findById(id));
        return "departmentform";
    }

    @RequestMapping("/deleteDepartment/{id}")
    public String delDepartment(@PathVariable("id") long id) {
        departmentRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/detailDepartment/{id}")
    public String showDepartment(@PathVariable("id") long id, Model model) {
        model.addAttribute("department", departmentRepository.findById(id).get());
        return "showdepartment";
    }

    @RequestMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable("id") long id, Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("employee", employeeRepository.findById(id));
        return "employeeform";
    }

    @RequestMapping("/deleteEmployee/{id}")
    public String delEmployee(@PathVariable("id") long id) {
        employeeRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/detailEmployee/{id}")
    public String showEmployee(@PathVariable("id") long id, Model model) {
        model.addAttribute("employee", employeeRepository.findById(id).get());
        return "show";
    }

}
