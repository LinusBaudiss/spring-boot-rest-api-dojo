package th.dojo.springbootdojo.boundary;

import org.springframework.web.bind.annotation.*;
import th.dojo.springbootdojo.model.Employee;

import java.util.List;

@RequestMapping("/api/v1")
public interface EmployeeController {

    @GetMapping("/employees")
    List<Employee> all();

    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id);

    @PutMapping("/employees/{id}")
    Employee changeEmployee(@RequestBody Employee newEmployee,
                            @PathVariable Long id);

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee);

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id);

}
