package th.dojo.springbootdojo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import th.dojo.springbootdojo.model.Employee;

import java.math.BigInteger;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, BigInteger> {

    @Query(value = "{}", count = true)
    BigInteger countAllDocuments();

    Employee findFirstByOrderByIdDesc();

}
