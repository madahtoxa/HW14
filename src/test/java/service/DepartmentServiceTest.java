package service;

import model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void init() {

        Collection<Employee> employees = Arrays.asList(
                new Employee("Anton", "Machul", 1, 20000),
                new Employee("Sasha", "Timofeeva", 1, 30000),
                new Employee("Anton", "Timofeev", 3, 40000),
                new Employee("Natalia", "Aleksandrovna", 4, 50000)

        );
        when(employeeService.getAll()).thenReturn(employees);
    }
    @Test
    void sum(){
        double actual = departmentService.getEmployeeSalarySum(1);
        assertEquals(10000.0,actual);
    }

    @Test
    void max(){
        double actual = departmentService.getEmployeeWithMaxSalary(2);
        assertEquals(50000,actual);
    }
    @Test
    void min(){
        double actual = departmentService.getEmployeeWithMinSalary(2);
        assertEquals(10000,actual);
    }
    @Test
    void getAllByDepartment(){
        List<Employee> actual = departmentService.getAll(1);
        Collection<Employee> excpected = Collections.singletonList(
                new Employee("ivan", "petrov", 1, 10000));
        assertIterableEquals(excpected,actual);

    }
    @Test
    void getAll(){

        Map<Integer, List<Employee>> actual = departmentService.getAll();
        Employee stepan = new Employee("stepan", "russkih", 2, 50000);
        assertTrue(actual.get(2).contains(stepan));
        assertFalse(actual.get(1).contains(stepan));
        assertEquals(3,actual.keySet().size());
    }

}
