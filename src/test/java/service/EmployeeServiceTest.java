package service;


import exception.EmployeeAlreadyAddedException;
import exception.EmployeeNotFoundException;
import exception.EmployeeStorageIsFullException;
import model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    EmployeeService employeeService = new EmployeeService();

    @Test
    void whenFullThenTrowException() {
        for (int i = 0; i < 5; i++) {
            char additionalChar = (char) ('a' + i);
            Employee employee = new Employee("name" + additionalChar, "sec_name", 1, 1);
            employeeService.add(employee);
        }

        assertThrows(EmployeeStorageIsFullException.class,
                () -> employeeService.add(new Employee("a", "b", 1, 1)));
    }

    @Test
    void whenNotUniqThenThrowException() {
        Employee employee = new Employee("name", "lastname", 1, 1);
        employeeService.add(employee);
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.add(employee));
    }

    @Test
    void addPositive() {
        Employee employee = new Employee("name", "last_name", 1, 1);
        employeeService.add(employee);
        assertTrue(employeeService.getAll().contains(employee));
    }

    @Test
    void findPositive() {
        Employee employee = new Employee("name", "last_name", 1, 1);
        employeeService.add(employee);
        Employee actual = employeeService.find("name", "last_name");
        assertNotNull(actual);
        assertEquals(employee, actual);
    }

    @Test
    void findNegative() {
        Employee employee = new Employee("name", "last_name", 1, 1);
        employeeService.add(employee);
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.find("not_name", "not_last_name"));
    }

    @Test
    void removePositive() {
        Employee employee = new Employee("name", "last_name", 1, 1);
        employeeService.add(employee);
        employeeService.remove("name", "last_name");
        assertFalse(employeeService.getAll().contains(employee));
    }

    @Test
    void removeNegative() {
        Employee employee = new Employee("name", "last_name", 1, 1);
        employeeService.add(employee);
        Employee actual = employeeService.remove("not_name", "not_last_name");
        assertNull(actual);
    }
}
