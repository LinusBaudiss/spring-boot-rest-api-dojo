package th.dojo.springbootdojo.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import th.dojo.springbootdojo.model.Employee;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepositoryImpl repo;

    @BeforeEach
    void init() {
        repo.setEmployees(new ArrayList<>());
    }

    @Test
    void contextLoads() throws Exception {
        assertThat(repo).isNotNull();
    }

    @Test
    void findByIdExistsTest() {
        //arrange
        Employee employee1 = new Employee(1L, "test", "test");
        Employee employee2 = new Employee(2L, "test test", "test test");
        repo.findEmployees().add(employee1);
        repo.findEmployees().add(employee2);
        //act
        Optional<Employee> result = repo.findEmployeeById(1L);
        //assert
        assertThat(result).isEqualTo(Optional.of(employee1));
    }

    @Test
    void findByIdFailsTest() {
        //arrange
        //act
        Optional<Employee> result = repo.findEmployeeById(1L);
        //assert
        assertThat(result).isEmpty();
    }

    @Test
    void saveTest() {
        //arrange
        Employee employee = new Employee(1L, "test", "test");
        //act
        Employee result = repo.saveEmployee(employee);
        //assert
        assertThat(result).isEqualTo(employee);
        assertThat(repo.findEmployees().size()).isEqualTo(1);
        assertThat(repo.findEmployees().get(0)).isEqualTo(employee);
    }

    @Test
    void removeByIdTest() {
        //arrange
        Employee employee = new Employee(1L, "test", "test");
        repo.findEmployees().add(employee);
        //act
        boolean result = repo.removeEmployeeById(1L);
        //assert
        assertThat(result).isTrue();
        assertThat(repo.findEmployees().size()).isZero();
    }

    @Test
    void removeByIdFailsTest() {
        //arrange
        Employee employee = new Employee(1L, "test", "test");
        repo.findEmployees().add(employee);
        //act
        boolean result = repo.removeEmployeeById(2L);
        //assert
        assertThat(result).isFalse();
        assertThat(repo.findEmployees().size()).isEqualTo(1);
        assertThat(repo.findEmployees().get(0)).isEqualTo(employee);
    }

}
