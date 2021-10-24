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
        Employee employee = data
                .stream()
                .filter(e -> id.equals(e.getId()))
                .findFirst()
                .orElse(null);

        if (employee == null) {
            return Optional.empty();
        }
        return Optional.of(employee);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        employee.setId(getNextId());
        data.add(employee);
        return employee;
    }

    @Override
    public boolean removeEmployeeById(Long id) {
        return data.removeIf(e -> id.equals(e.getId()));
    }

    @Override
    public List<Employee> findEmployees() {
        return data;
    }

    @Override
    public Long getNextId() {
        //empty list -> 1
        int size = data.size();
        if (size == 0) {
            return 1L;
        }
        //single employee in list -> id++
        if (size == 1) {
            return data.get(0).getId() + 1;
        }
        //check for "id" gaps in list
        for (int i = 0; i < size - 1; i++) {
            Employee e = data.get(i);
            Long id = e.getId();
            Employee nextE = data.get(i + 1);
            if (id + 1 != nextE.getId()) {
                return id + 1;
            }
        }
        //no gaps? -> lastId++
        return data.get(size - 1).getId() + 1;
    }
}
