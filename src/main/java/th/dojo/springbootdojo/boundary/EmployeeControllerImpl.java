package th.dojo.springbootdojo.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import th.dojo.springbootdojo.model.Employee;
import th.dojo.springbootdojo.model.EmployeeDto;
import th.dojo.springbootdojo.service.EmployeeService;

import java.math.BigInteger;
import java.util.List;

@RestController
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeService service;

    @Autowired
    public EmployeeControllerImpl(EmployeeService service) {
        this.service = service;
    }

    @Override
    public List<Employee> getAllEmployees() {
        //TODO
        return null;
    }

    @Override
    public Employee getEmployeeById(BigInteger id) {
        //TODO
        return null;
    }

    @Override
    public Employee putEmployeeById(EmployeeDto newEmployee, BigInteger id) {
        //TODO
        return null;
    }

    @Override
    public Employee postEmployee(EmployeeDto newEmployee) {
        //TODO
        return null;
    }

    @Override
    public void deleteEmployee(BigInteger id) {
        //TODO
    }
}
