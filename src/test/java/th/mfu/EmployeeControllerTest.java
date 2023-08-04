package th.mfu;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

@SpringBootTest
public class EmployeeControllerTest {
    @Autowired
    EmployeeController controller;

    /// test query all
    @Test
    public void testGetAllEmployees(){
        //ACT
        Collection<Employee> response  = controller.getAllEmployees();
        //Assert 
        assertEquals(10, response.size());
    }

    // test create

    // test delete

    // test update
    
}
