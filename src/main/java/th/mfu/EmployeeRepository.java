package th.mfu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{
    //CrudRepository<Employee, Long> อันแรก Enity อันสอง id
    public List<Employee> findAll();
    public List<Employee> findByfirstname(String firstname); //ต้อง match กับ get set ใน employee

}
