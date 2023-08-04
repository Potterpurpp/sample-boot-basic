package th.mfu;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    private long id;
    @JsonProperty("product_name")
    private String name;
    @JsonProperty("product_desc")
    private String description;
    private long price;
    public Product(){

    }
    
    public Product(long id, String name, String description, long price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public long getPrice() {
        return price;
    }
    public void setPrice(long price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    

    
}
