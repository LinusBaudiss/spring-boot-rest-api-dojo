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
        return repo.findById(id);
    }

    @Override
    public Employee createEmployee(EmployeeDto employeeDto) {
        Employee employee = Employee
                .builder()
                .name(employeeDto.getName())
                .role(employeeDto.getRole())
                .build();
        if (BigInteger.ZERO.equals(repo.countAllDocuments())) {
            employee.setId(BigInteger.ONE);
        } else {
            employee.setId(findLastEmployee().getId().add(BigInteger.ONE));
        }
        return repo.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee, EmployeeDto employeeDto) {
        employee.setName(employeeDto.getName());
        employee.setRole(employeeDto.getRole());
        return repo.save(employee);
    }

    @Override
    public boolean removeEmployeeById(BigInteger id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Employee> findEmployees() {
        return repo.findAll();
    }

    @Override
    public Employee findLastEmployee() {
        return repo.findFirstByOrderByIdDesc();
    }
}
