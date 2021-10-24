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
import th.dojo.springbootdojo.service.EmployeeServiceImpl;

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
    private EmployeeServiceImpl service;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    void getAllEmployeesTest() throws Exception {
        //arrange
        Employee employee1 = new Employee(1L, "test", "test");
        Employee employee2 = new Employee(2L, "test test", "test test");
        when(service.findEmployees()).thenReturn(List.of(employee1, employee2));

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
        Employee testEmployee = new Employee(1L, "test", "test");
        when(service.findEmployeeById(1L)).thenReturn(Optional.of(testEmployee));

        //act
        mockMvc.perform(get(BASE_URL + "/1")).andDo(print())

                //assert
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(mapper.writeValueAsString(testEmployee))));
    }

    @Test
    void getEmployeeByIdFailsTest() throws Exception {
        //arrange
        when(service.findEmployeeById(1L)).thenReturn(Optional.empty());

        //act
        mockMvc.perform(get(BASE_URL + "/1")).andDo(print())

                //assert
                .andExpect(status().isNotFound());
    }

    @Test
    void putAlreadyExisitingEmployeeTest() throws Exception {
        //arrange
        Employee testEmployee = new Employee(1L, "test", "test");
        when(service.findEmployeeById(1L)).thenReturn(Optional.of(testEmployee));

        String newEmployee = mapper.writeValueAsString(new Employee(1L, "test test", "test test"));

        //act
        mockMvc.perform(
                        put(BASE_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(newEmployee)
                ).andDo(print())

                //assert
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(newEmployee)));
    }

    @Test
    void putNewEmployeeTest() throws Exception {
        //arrange
        Employee employee = new Employee(1L, "test", "test");
        String newEmployee = mapper.writeValueAsString(employee);
        when(service.findEmployeeById(1L)).thenReturn(Optional.empty());
        when(service.createEmployee(employee)).thenReturn(employee);

        //act
        mockMvc.perform(
                        put(BASE_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(newEmployee)
                ).andDo(print())

                //assert
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(newEmployee)));
    }

    @Test
    void postNewEmployeeTest() throws Exception {
        //arrange
        Employee employee = new Employee("test", "test");
        Employee expectedEmployee = new Employee(1L, "test", "test");
        String newEmployee = mapper.writeValueAsString(employee);
        when(service.createEmployee(employee)).thenReturn(employee);

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
        when(service.removeEmployeeById(1L)).thenReturn(true);

        //act
        mockMvc.perform(delete(BASE_URL + "/1")).andDo(print())

                //assert
                .andExpect(status().isOk());
    }

    @Test
    void deleteNotExisitingEmployeeTest() throws Exception {
        //arrange
        when(service.removeEmployeeById(1L)).thenReturn(false);

        //act
        mockMvc.perform(delete(BASE_URL + "/1")).andDo(print())

                //assert
                .andExpect(status().isNotFound());
    }
}
