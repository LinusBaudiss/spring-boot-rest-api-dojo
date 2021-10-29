package th.dojo.springbootdojo.boundary;

import org.springframework.web.bind.annotation.*;
import th.dojo.springbootdojo.model.Employee;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "https://besser54.github.io"})
@RequestMapping("/api/v1")
public interface EmployeeController {

    @GetMapping("/employees")
    List<Employee> getAllEmployees();

    @GetMapping("/employees/{id}")
    Employee getEmployeeById(@PathVariable Long id);

    @PutMapping("/employees/{id}")
    Employee putEmployeeById(@RequestBody Employee newEmployee,
                             @PathVariable Long id);

    @PostMapping("/employees")
    Employee postEmployee(@RequestBody Employee newEmployee);

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id);

}
