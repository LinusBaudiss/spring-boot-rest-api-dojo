package th.dojo.springbootdojo.repository;

import th.dojo.springbootdojo.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Optional<Employee> findEmployeeById(Long id);

    Employee saveEmployee(Employee employee);

    boolean removeEmployeeById(Long id);

    List<Employee> findEmployees();

}
