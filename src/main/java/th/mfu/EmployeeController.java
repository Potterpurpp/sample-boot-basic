package th.mfu;

import java.util.Collection;
import java.util.HashMap;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    
    //create HashMap for employee
    private HashMap<Long,Employee> employeesDB = new HashMap <Long, Employee>();

    //Select all Employee
    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees(){
        return employeesDB.values(); // จะได้ employee ทุกคนที่อยู่ใน hashMap
    }
    //Select employee by ID
    @GetMapping("/employees/{id}")
    public ResponseEntity getEmployeeByID(@PathVariable long id){

        //Check if Id exist
        if(!employeesDB.containsKey(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not Found");
        }
        return ResponseEntity.ok(employeesDB.get(id));
    }

    //Create Employee
    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee (@RequestBody Employee employee){

        //Check if ID exist
        if(employeesDB.containsKey(employee.getId())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Employee id already exists");
        }

        //add employee
        employeesDB.put(employee.getId(), employee);
        return ResponseEntity.ok("Employee had created");
    }
   

    //Update Employee
    @PutMapping("/employees/{id}")
    public ResponseEntity<String> updateEmployee (@PathVariable long id , @RequestBody Employee employee){

        employeesDB.put(id, employee);
        return ResponseEntity.ok(" Employee Has been updated");
    }

    //Delete Employee
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id){
        employeesDB.remove(id);
        return ResponseEntity.ok("Employee has been deleted");
    }
}
