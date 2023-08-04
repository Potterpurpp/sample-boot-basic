package th.mfu;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    
    //create HashMap for employee
    private HashMap<Long,Employee> employeesDB = new HashMap <Long, Employee>();

    //Select all Employee
    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees(){
        return employeeRepository.findAll(); 
    }
    //Select employee by ID
    @GetMapping("/employees/{id}")
    public ResponseEntity getEmployeeByID(@PathVariable long id){
        Optional <Employee> optemployee = employeeRepository.findById(id);

        //Check if Id exist
        if(!optemployee.isPresent()){
            //Show Error
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not Found");
        }

        Employee emp = optemployee.get();
        return ResponseEntity.ok(emp);
    }
    //select employee by firstname
    @GetMapping("/employees/firstname/{firstname}")
    public ResponseEntity getEmployeeByFirstname(@PathVariable String firstname){
        //get employee from db
        List<Employee> employees = employeeRepository.findByfirstname(firstname);
        //check if employee is empty
        if(employees.isEmpty()){
            //return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
        return ResponseEntity.ok(employees);
    }

   //create new employee
    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee){
        //TODO: check if id exists

        // add employee to repository
        employeeRepository.save(employee);
       

        //return created success message
        return ResponseEntity.ok("Employee created");
    }
   

    //Update All field in Employee (PUT)
    @PutMapping("/employees/{id}")
    public ResponseEntity<String> updateEmployee (@RequestBody Employee employee){

        if(!employeeRepository.existsById(employee.getId())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee NOT FOUND!");
        }
        employeeRepository.save(employee);
        return ResponseEntity.ok(" Employee Has been updated");
    }

    //Update some field (PATCH)
    @PatchMapping("/employees/{id}")
    public ResponseEntity<String> patchEmployee(@PathVariable long id, @RequestBody HashMap<String, Object> filedupdate){
        //Check exist
        if(!employeesDB.containsKey(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee NOT FOUND!");
        }

        //Get employee from db
        Employee emp = employeesDB.get(id);
        //loop throught fields to update
        filedupdate.forEach((key,value) ->{
            if(key.equals("first_name")){
                emp.setFirstname((String)value);
            }
           //check if field is lastname
            if(key.equals("last_name")){
                //update lastname
               emp.setLastname((String)value);
            }

            //check if field is salary
            if(key.equals("salary")){
                //update salary
                emp.setSalary(Long.valueOf(""+value));
            }
            if(key.equals("birthday")){
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    emp.setBirthday(formatter.parse((String)value));
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }

        });

        //update employee
        employeesDB.put(id, emp);


        //return success message
        return ResponseEntity.ok("Employee updated");
      
    }

    //Delete Employee
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id){

        //Check ID
        if(!employeeRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee NOT FOUND!");
        }
        employeeRepository.deleteById(id);
        return ResponseEntity.ok("Employee has been deleted");
    }
}
