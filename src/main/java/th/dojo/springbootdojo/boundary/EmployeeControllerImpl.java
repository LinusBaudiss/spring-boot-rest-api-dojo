package th.dojo.springbootdojo.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import th.dojo.springbootdojo.model.Employee;
import th.dojo.springbootdojo.service.EmployeeServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeServiceImpl repository;

    @Autowired
    public EmployeeControllerImpl(EmployeeServiceImpl repository) {
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
                    return repository.saveEmployee(newEmployee);
                });
    }

    @Override
    public Employee postEmployee(Employee newEmployee) {
        Long id = newEmployee.getId();
        if (id != null) {
            Optional<Employee> employee = repository.findEmployeeById(id);
            if (employee.isPresent()) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return repository.saveEmployee(newEmployee);
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!repository.removeEmployeeById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
