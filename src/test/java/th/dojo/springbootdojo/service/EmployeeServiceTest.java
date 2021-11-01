package th.dojo.springbootdojo.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import th.dojo.springbootdojo.model.Employee;
import th.dojo.springbootdojo.model.EmployeeDto;
import th.dojo.springbootdojo.repository.EmployeeRepository;

import java.math.BigInteger;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    private EmployeeService service;
    @Autowired
    private EmployeeRepository repo;

    @Test
    void contextLoads() {
        assertThat(service).isNotNull();
    }

    @AfterEach
    void killData() {
        repo.deleteAll();
    }

    @Test
    void findByIdExistsTest() {
        //arrange
        Employee employee = new Employee(BigInteger.ONE, "test", "test");
        repo.save(employee);
        //act
        Optional<Employee> result = service.findEmployeeById(BigInteger.ONE);
        //assert
        assertThat(result).isEqualTo(Optional.of(employee));
    }

    @Test
    void findByIdFailsTest() {
        //arrange
        //act
        Optional<Employee> result = service.findEmployeeById(BigInteger.ONE);
        //assert
        assertThat(result).isEmpty();
    }

    @Test
    void createEmployeeTest() {
        //arrange
        EmployeeDto employeeDto = new EmployeeDto("test", "test");
        Employee employee = new Employee(BigInteger.ONE, "test", "test");
        //act
        Employee result = service.createEmployee(employeeDto);
        //assert
        assertThat(result).isEqualTo(employee);
        assertThat(service.findEmployees().size()).isEqualTo(1);
        assertThat(service.findEmployees().get(0)).isEqualTo(employee);
    }

    @Test
    void updateEmployeeTest(){
        //arrange
        Employee employee = new Employee(BigInteger.ONE, "test", "test");
        EmployeeDto employeeDto = new EmployeeDto("test test", "test test");
        Employee expectedEmployee = new Employee(BigInteger.ONE, "test test", "test test");
        //act
        Employee result = service.updateEmployee(employee, employeeDto);
        //assert
        assertThat(result).isEqualTo(expectedEmployee);
        assertThat(service.findEmployees().size()).isEqualTo(1);
        assertThat(service.findEmployees().get(0)).isEqualTo(expectedEmployee);
    }

    @Test
    void removeByIdTest() {
        //arrange
        Employee employee = new Employee(BigInteger.ONE, "test", "test");
        repo.save(employee);
        //act
        boolean result = service.removeEmployeeById(BigInteger.ONE);
        //assert
        assertThat(result).isTrue();
        assertThat(service.findEmployees().size()).isZero();
    }

    @Test
    void removeByIdFailsTest() {
        //arrange
        Employee employee = new Employee(BigInteger.ONE, "test", "test");
        repo.save(employee);
        //act
        boolean result = service.removeEmployeeById(BigInteger.valueOf(2L));
        //assert
        assertThat(result).isFalse();
        assertThat(service.findEmployees().size()).isEqualTo(1);
        assertThat(service.findEmployees().get(0)).isEqualTo(employee);
    }
}
