package controller;

import com.pro.sky.DZ14_Kurs2.model.Employee;
import com.pro.sky.DZ14_Kurs2.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController (DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public double getEmployeeWithMaxSalary (@RequestParam("departmentId")int department){
        return departmentService.getEmployeeWithMaxSalary(department);
    }
    @GetMapping("/min-salary")
    public double getEmployeeWithMinSalary (@RequestParam("departmentId")int department){
        return departmentService.getEmployeeWithMinSalary(department);
    }
    @GetMapping(value="/all",params="departmentID")
    public List<Employee> getAll(@RequestParam("departmentId")int department){
        return departmentService.getAll(department);
    }

    @GetMapping("/all")
    private Map<Integer,List<Employee>> getAll() {
        return departmentService.getAll();
    }

    @GetMapping("/depatment-salary")
    public double getEmployeeDepartmentSalary  (@RequestParam("departmentId")int department){
        return  departmentService.getEmployeeSalarySum(department);
    }
}
