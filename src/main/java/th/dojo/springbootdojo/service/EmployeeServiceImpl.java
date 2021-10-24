package th.dojo.springbootdojo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.dojo.springbootdojo.model.Employee;
import th.dojo.springbootdojo.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private List<Employee> data;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository data) {
        this.data = data.getEmployees();
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        //TODO
        return null;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        //TODO
        return null;
    }

    @Override
    public boolean removeEmployeeById(Long id) {
        //TODO
        return false;
    }

    @Override
    public List<Employee> findEmployees() {
        //TODO
        return null;
    }

    @Override
    public Long getNextId() {
        //TODO
        //empty list -> 1
        //single employee in list -> id++
        //check for "id" gaps in list
        //no gaps? -> lastId++
        return null;
    }
}
