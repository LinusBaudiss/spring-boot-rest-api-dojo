package th.dojo.springbootdojo.repository;

import org.springframework.stereotype.Repository;
import th.dojo.springbootdojo.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository {

    private List<Employee> employees;

    public EmployeeRepository() {
        employees = new ArrayList<>();
        employees.add(new Employee(1L, "Hans Müller", "Chef"));
        employees.add(new Employee(2L, "Max Mustermann", "Mitarbeiter"));
    }

    public Optional<Employee> findById(Long id) {
        Employee employee = employees
                .stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);

        if (employee == null) {
            return Optional.empty();
        }
        return Optional.of(employee);
    }

    public Employee save(Employee employee){
        employees.add(employee);
        return  employee;
    }

    public boolean removeById(Long id){
        return employees.removeIf(e -> e.getId() == id);
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
