package th.dojo.springbootdojo.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import th.dojo.springbootdojo.model.Employee;
import th.dojo.springbootdojo.model.EmployeeDto;
import th.dojo.springbootdojo.service.EmployeeService;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeService service;

    @Autowired
    public EmployeeControllerImpl(EmployeeService service) {
        this.service = service;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return service.findEmployees();
    }

    @Override
    public Employee getEmployeeById(BigInteger id) {
        return service.findEmployeeById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Employee putEmployeeById(EmployeeDto employeeDto,
                                    BigInteger id) {
        Optional<Employee> employee = service.findEmployeeById(id);
        if (employee.isPresent()) {
            return service.updateEmployee(employee.get(), employeeDto);
        } else {
            return service.createEmployee(employeeDto);
        }
    }

    @Override
    public Employee postEmployee(EmployeeDto employeeDto) {
        return service.createEmployee(employeeDto);
    }

    @Override
    public void deleteEmployee(BigInteger id) {
        if (!service.removeEmployeeById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
