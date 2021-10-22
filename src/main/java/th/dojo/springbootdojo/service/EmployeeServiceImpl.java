package th.dojo.springbootdojo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.dojo.springbootdojo.model.Employee;
import th.dojo.springbootdojo.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private List<Employee> data;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository data){
        this.data = data.getEmployees();
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        Employee employee = data
                .stream()
                .filter(e -> id.equals(e.getId()))
                .findFirst()
                .orElse(null);

        if (employee == null) {
            return Optional.empty();
        }
        return Optional.of(employee);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        data.add(employee);
        return employee;
    }

    @Override
    public boolean removeEmployeeById(Long id) {
        return data.removeIf(e -> id.equals(e.getId()));
    }

    @Override
    public List<Employee> findEmployees() {
        return data;
    }
}
