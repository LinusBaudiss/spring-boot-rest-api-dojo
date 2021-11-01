package th.dojo.springbootdojo.boundary;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import th.dojo.springbootdojo.model.Employee;
import th.dojo.springbootdojo.model.EmployeeDto;
import th.dojo.springbootdojo.service.EmployeeService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    public static final String BASE_URL = "/api/v1/employees";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeControllerImpl controller;

    @MockBean
    private EmployeeService service;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void getAllEmployeesTest() throws Exception {
        //arrange
        List<Employee> list = new ArrayList<>();
        Employee employee1 = new Employee(BigInteger.ONE, "test", "test");
        Employee employee2 = new Employee(BigInteger.valueOf(2L), "test test", "test test");
        list.add(employee1);
        list.add(employee2);
        when(service.findEmployees()).thenReturn(list);

        //act
        mockMvc.perform(get(BASE_URL)).andDo(print())

                //assert
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(mapper.writeValueAsString(employee1))))
                .andExpect(content().string(containsString(mapper.writeValueAsString(employee2))));
    }

    @Test
    void getEmployeeByIdTest() throws Exception {
        //arrange
        Employee testEmployee = new Employee(BigInteger.ONE, "test", "test");
        when(service.findEmployeeById(BigInteger.ONE)).thenReturn(Optional.of(testEmployee));

        //act
        mockMvc.perform(get(BASE_URL + "/1")).andDo(print())

                //assert
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(mapper.writeValueAsString(testEmployee))));
    }

    @Test
    void getEmployeeByIdFailsTest() throws Exception {
        //arrange
        when(service.findEmployeeById(BigInteger.ONE)).thenReturn(Optional.empty());

        //act
        mockMvc.perform(get(BASE_URL + "/1")).andDo(print())

                //assert
                .andExpect(status().isNotFound());
    }

    @Test
    void putAlreadyExisitingEmployeeTest() throws Exception {
        //arrange
        Employee employee = new Employee(BigInteger.ONE, "test", "test");
        EmployeeDto employeeDto = new EmployeeDto("test test", "test test");
        Employee updatedEmployee = new Employee(BigInteger.ONE, "test test", "test test");
        when(service.findEmployeeById(BigInteger.ONE)).thenReturn(Optional.of(employee));
        when(service.updateEmployee(employee, employeeDto)).thenReturn(updatedEmployee);

        String inputEmployeeDto = mapper.writeValueAsString(employeeDto);
        String outputEmployee = mapper.writeValueAsString(updatedEmployee);

        //act
        mockMvc.perform(
                        put(BASE_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputEmployeeDto)
                ).andDo(print())

                //assert
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(outputEmployee)));
    }

    @Test
    void putNewEmployeeTest() throws Exception {
        //arrange
        EmployeeDto employeeDto = new EmployeeDto("test", "test");
        Employee employee = new Employee(BigInteger.ONE, "test", "test");
        when(service.findEmployeeById(BigInteger.ONE)).thenReturn(Optional.empty());
        when(service.createEmployee(employeeDto)).thenReturn(employee);

        String inputEmployeeDto = mapper.writeValueAsString(employeeDto);
        String outputEmployee = mapper.writeValueAsString(employee);

        //act
        mockMvc.perform(
                        put(BASE_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputEmployeeDto)
                ).andDo(print())

                //assert
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(outputEmployee)));
    }

    @Test
    void postNewEmployeeTest() throws Exception {
        //arrange
        EmployeeDto employeeDto = new EmployeeDto("test", "test");
        Employee employee = new Employee(BigInteger.ONE, "test", "test");
        String newEmployee = mapper.writeValueAsString(employee);
        when(service.createEmployee(employeeDto)).thenReturn(employee);

        //act
        mockMvc.perform(
                        post(BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(newEmployee)
                ).andDo(print())

                //assert
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(newEmployee)));
    }

    @Test
    void deleteEmployeeTest() throws Exception {
        //arrange
        when(service.removeEmployeeById(BigInteger.ONE)).thenReturn(true);

        //act
        mockMvc.perform(delete(BASE_URL + "/1")).andDo(print())

                //assert
                .andExpect(status().isOk());
    }

    @Test
    void deleteNotExisitingEmployeeTest() throws Exception {
        //arrange
        when(service.removeEmployeeById(BigInteger.ONE)).thenReturn(false);

        //act
        mockMvc.perform(delete(BASE_URL + "/1")).andDo(print())

                //assert
                .andExpect(status().isNotFound());
    }
}
