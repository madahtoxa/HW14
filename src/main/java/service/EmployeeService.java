package service;

import exception.EmployeeAlreadyAddedException;
import exception.EmployeeNotFoundException;
import exception.EmployeeStorageIsFullException;
import model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private final Map<String, Employee> employees = new HashMap(MAX_SIZE);
    final static private int MAX_SIZE = 5;
    public EmployeeService() {
        new Employee("Anton", "Machul", 1, 20000);
                new Employee("Sasha", "Timofeeva", 1, 30000);
                new Employee("Anton", "Timofeev", 3, 40000);
                new Employee("Natalia", "Aleksandrovna", 4, 50000);

    }
    public Collection<Employee> getAll() {
        return employees.values();
    }
    public Employee add(Employee employee) {
        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException();
        }
        if (employees.containsKey(createKey(employee))) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(createKey(employee),employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        return employees.remove(createKey(firstName, lastName));
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = employees.get(createKey(firstName, lastName));
        if (employee==null){
            throw new EmployeeNotFoundException();
        }
        return employee;
    }



    private static String createKey(String firstName,String lastName) {
        return (firstName + lastName).toLowerCase();
    }
    private static String createKey(Employee employee) {
        return createKey(employee.getFirstName(),employee.getLastName());
    }
}
