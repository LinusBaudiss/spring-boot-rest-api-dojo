package th.dojo.springbootdojo.service;

import th.dojo.springbootdojo.model.Employee;
import th.dojo.springbootdojo.model.EmployeeDto;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Optional<Employee> findEmployeeById(BigInteger id);

    Employee createEmployee(EmployeeDto employee);

    boolean removeEmployeeById(BigInteger id);

    List<Employee> findEmployees();

    Employee findLastEmployee();
}
