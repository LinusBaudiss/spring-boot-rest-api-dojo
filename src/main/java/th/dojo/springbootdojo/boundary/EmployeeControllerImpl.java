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
        //TODO
        return null;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        //TODO
        return null;
    }

    @Override
    public Employee putEmployeeById(Employee newEmployee,
                                    Long id) {
        //TODO
        return null;
    }

    @Override
    public Employee postEmployee(Employee newEmployee) {
        //TODO
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {
        //TODO
    }
}
