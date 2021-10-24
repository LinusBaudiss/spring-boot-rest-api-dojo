package th.dojo.springbootdojo.service;

import th.dojo.springbootdojo.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Optional<Employee> findEmployeeById(Long id);

    Employee createEmployee(Employee employee);

    boolean removeEmployeeById(Long id);

    List<Employee> findEmployees();

    Long getNextId();

}
