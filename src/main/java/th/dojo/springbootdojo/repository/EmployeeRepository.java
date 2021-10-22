package th.dojo.springbootdojo.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Repository;
import th.dojo.springbootdojo.model.Employee;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Repository
public class EmployeeRepository {

    private List<Employee> employees;

    //in memory data
    public EmployeeRepository(){
        employees = new ArrayList<>();
        employees.add(new Employee(1L, "Hans Müller", "Chef"));
        employees.add(new Employee(2L, "Max Mustermann", "Mitarbeiter"));
    }

}
