package th.dojo.springbootdojo.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import th.dojo.springbootdojo.model.Employee;
import th.dojo.springbootdojo.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    public List<Employee> all() {
        return repository.getEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "employee not found"));
    }

    @PutMapping("/employees/{id}")
    public Employee changeEmployee(@RequestBody Employee newEmployee,
                                   @PathVariable Long id) {
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

    @PostMapping("/employees")
    public Employee newEmployee(@RequestBody Employee newEmployee) {
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

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        if (!repository.removeById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "employee not found");
        }
    }
}
