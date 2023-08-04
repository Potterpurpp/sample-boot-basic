package th.mfu;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private HashMap<Long, Product>  productDB = new HashMap<Long, Product>();

    //Select All product
    @GetMapping("/products")
    public Collection<Product> getAllProducts(){
        return productDB.values();
    }

    //Create Product
    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        //Check exist
        if(productDB.containsKey(product.getId())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Product is alrealy exist");
        }
        
        productDB.put(product.getId(), product);
        return ResponseEntity.ok("Product has been created!");
    }
}
