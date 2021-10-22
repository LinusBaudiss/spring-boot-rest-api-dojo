package th.dojo.springbootdojo.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import th.dojo.springbootdojo.model.Employee;
import th.dojo.springbootdojo.repository.EmployeeRepositoryImpl;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeRepositoryImpl repository;

    @Autowired
    public EmployeeControllerImpl(EmployeeRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> all() {
        return repository.getEmployees();
    }

    @Override
    public Employee one(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "employee not found"));
    }

    @Override
    public Employee changeEmployee(Employee newEmployee,
                                   Long id) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return employee;
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    @Override
    public Employee newEmployee(Employee newEmployee) {
        Long id = newEmployee.getId();
        if (id != null) {
            Optional<Employee> employee = repository.findById(id);
            if (employee.isPresent()) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "employee with id: " + id + "already in list");
            } else {
                return repository.save(newEmployee);
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "employee needs a id");
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!repository.removeById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "employee not found");
        }
    }
}
