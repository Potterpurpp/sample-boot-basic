package th.mfu;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public class ProductController {

    //Pathvariable use for http://localhost///////.....
    //Requestvariable use for send data from json
    @Autowired
    private ProductReposity productReposity;
    private HashMap<Long, Product>  productDB = new HashMap<Long, Product>();

    //Select All product
    @GetMapping("/products")
    public Collection<Product> getAllProducts(){
        return productReposity.findAll();
    }

    //Create Product
    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        //Check exist
        productReposity.save(product);
        
        productDB.put(product.getId(), product);
        return ResponseEntity.ok("Product has been created!");
    }

    //Select product_name
    @GetMapping("/products/{name}")
    public ResponseEntity getProductName(@PathVariable String name){
        //Check 
        List <Product> products = productReposity.findByName(name);
        if(products.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Didn't have this id");
        }
       
        return ResponseEntity.ok(products);
    }
    //Update product
    @PutMapping("/products/")
    public ResponseEntity<String> updateProduct(@RequestBody Product product){
        //CHECK EXIST
        if(!productReposity.existsById(product.getId())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
        }
        productReposity.save(product);
        return ResponseEntity.ok(" Employee Has been updated");
    }

    //Delete product
    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable long id){
        //CHECK EXIST
        if(!productReposity.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product NOT FOUND");
        }
        productReposity.deleteById(id);
        return ResponseEntity.ok("Product had been deleted");
    }
}
