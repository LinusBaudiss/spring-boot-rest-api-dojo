package th.dojo.springbootdojo.boundary;

import org.springframework.web.bind.annotation.*;
import th.dojo.springbootdojo.model.Employee;
import th.dojo.springbootdojo.model.EmployeeDto;

import java.math.BigInteger;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public interface EmployeeController {

    @GetMapping("/employees")
    List<Employee> getAllEmployees();

    @GetMapping("/employees/{id}")
    Employee getEmployeeById(@PathVariable BigInteger id);

    @PutMapping("/employees/{id}")
    Employee putEmployeeById(@RequestBody EmployeeDto newEmployee,
                             @PathVariable BigInteger id);

    @PostMapping("/employees")
    Employee postEmployee(@RequestBody EmployeeDto newEmployee);

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable BigInteger id);

}
