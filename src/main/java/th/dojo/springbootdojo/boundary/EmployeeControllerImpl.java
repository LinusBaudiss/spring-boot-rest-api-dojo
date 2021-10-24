package th.dojo.springbootdojo.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import th.dojo.springbootdojo.model.Employee;
import th.dojo.springbootdojo.service.EmployeeService;

import java.util.List;

@RestController
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeService repository;

    @Autowired
    public EmployeeControllerImpl(EmployeeService repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findEmployees();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return repository.findEmployeeById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Employee putEmployeeById(Employee newEmployee,
                                    Long id) {
        return repository.findEmployeeById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return employee;
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.createEmployee(newEmployee);
                });
    }

    @Override
    public Employee postEmployee(Employee newEmployee) {
        return repository.createEmployee(newEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!repository.removeEmployeeById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
