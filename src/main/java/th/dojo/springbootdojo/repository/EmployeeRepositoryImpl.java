package th.dojo.springbootdojo.repository;

import org.springframework.stereotype.Repository;
import th.dojo.springbootdojo.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private List<Employee> employees;

    public EmployeeRepositoryImpl() {
        employees = new ArrayList<>();
        employees.add(new Employee(1L, "Hans Müller", "Chef"));
        employees.add(new Employee(2L, "Max Mustermann", "Mitarbeiter"));
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        //TODO
        return null;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        //TODO
        return null;
    }

    @Override
    public boolean removeEmployeeById(Long id) {
        //TODO
        return false;
    }

    @Override
    public List<Employee> findEmployees() {
        //TODO
        return null;
    }

    //only for the tests
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
