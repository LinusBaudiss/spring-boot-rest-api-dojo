package th.dojo.springbootdojo.repository;

import th.dojo.springbootdojo.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Optional<Employee> findById(Long id);

    Employee save(Employee employee);

    boolean removeById(Long id);

    List<Employee> getEmployees();

}
