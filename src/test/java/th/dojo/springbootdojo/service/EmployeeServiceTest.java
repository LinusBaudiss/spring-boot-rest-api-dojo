package th.dojo.springbootdojo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import th.dojo.springbootdojo.model.Employee;
import th.dojo.springbootdojo.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmployeeServiceTest {

    private EmployeeServiceImpl service;
    private EmployeeRepository repo;

    @Test
    void contextLoads() throws Exception {
        assertThat(service).isNotNull();
    }

    @BeforeEach
    void init() {
        repo = new EmployeeRepository(new ArrayList<>());
        service = new EmployeeServiceImpl(repo);
    }

    @Test
    void findByIdExistsTest() {
        //arrange
        Employee employee = new Employee(1L, "test", "test");
        repo.getEmployees().add(employee);
        //act
        Optional<Employee> result = service.findEmployeeById(1L);
        //assert
        assertThat(result).isEqualTo(Optional.of(employee));
    }

    @Test
    void findByIdFailsTest() {
        //arrange
        //act
        Optional<Employee> result = service.findEmployeeById(1L);
        //assert
        assertThat(result).isEmpty();
    }

    @Test
    void saveTest() {
        //arrange
        Employee employee = new Employee(1L, "test", "test");
        //act
        Employee result = service.createEmployee(employee);
        //assert
        assertThat(result).isEqualTo(employee);
        assertThat(service.findEmployees().size()).isEqualTo(1);
        assertThat(service.findEmployees().get(0)).isEqualTo(employee);
    }

    @Test
    void removeByIdTest() {
        //arrange
        Employee employee = new Employee(1L, "test", "test");
        repo.getEmployees().add(employee);
        //act
        boolean result = service.removeEmployeeById(1L);
        //assert
        assertThat(result).isTrue();
        assertThat(service.findEmployees().size()).isZero();
    }

    @Test
    void removeByIdFailsTest() {
        //arrange
        Employee employee = new Employee(1L, "test", "test");
        repo.getEmployees().add(employee);
        //act
        boolean result = service.removeEmployeeById(2L);
        //assert
        assertThat(result).isFalse();
        assertThat(service.findEmployees().size()).isEqualTo(1);
        assertThat(service.findEmployees().get(0)).isEqualTo(employee);
    }

    @Test
    void getNextIdEmptyListTest() {
        //arrange
        //act
        Long result = service.getNextId();
        //assert
        assertThat(result).isEqualTo(1L);
    }

    @Test
    void getNextIdTest() {
        //arrange
        Employee employee = new Employee(4L, "test", "test");
        repo.getEmployees().add(employee);
        //act
        Long result = service.getNextId();
        //assert
        assertThat(result).isEqualTo(5L);
    }

    @Test
    void getNextIdTwoEmployeesTest() {
        //arrange
        Employee employee1 = new Employee(1L, "test", "test");
        Employee employee2 = new Employee(2L, "test", "test");
        repo.getEmployees().add(employee1);
        repo.getEmployees().add(employee2);
        //act
        Long result = service.getNextId();
        //assert
        assertThat(result).isEqualTo(3L);
    }

    @Test
    void getNextIdMultipleEmployeesTest() {
        //arrange
        Employee employee1 = new Employee(1L, "test", "test");
        Employee employee2 = new Employee(2L, "test", "test");
        Employee employee3 = new Employee(5L, "test", "test");
        repo.getEmployees().add(employee1);
        repo.getEmployees().add(employee2);
        repo.getEmployees().add(employee3);
        //act
        Long result = service.getNextId();
        //assert
        assertThat(result).isEqualTo(3L);
    }

}
