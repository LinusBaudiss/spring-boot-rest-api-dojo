package th.dojo.springbootdojo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import th.dojo.springbootdojo.model.Employee;
import th.dojo.springbootdojo.model.EmployeeDto;
import th.dojo.springbootdojo.repository.EmployeeRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository repo;

    @Override
    public Optional<Employee> findEmployeeById(BigInteger id) {
        //TODO
        return Optional.empty();
    }

    @Override
    public Employee createEmployee(EmployeeDto employee) {
        //TODO
        return null;
    }

    @Override
    public Employee updateEmployee(Employee employee, EmployeeDto employeeDto) {
        //TODO
        return null;
    }

    @Override
    public boolean removeEmployeeById(BigInteger id) {
        //TODO
        return false;
    }

    @Override
    public List<Employee> findEmployees() {
        //TODO
        return null;
    }

    @Override
    public Employee findLastEmployee() {
        //TODO
        return null;
    }
}
