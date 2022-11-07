package id.co.nds.catalogue.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ProductModel extends RecordModel{
    // main
    private Integer id;

    @NotEmpty(message = "Product name is required")
    private String name;

    @NotEmpty(message = "Product quantity is required")
    @Min(value = 1, message = "Minimum quantity is 1")
    private Integer quantity;

    @NotEmpty(message = "Product category is required")
    private String categoryId;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public String getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }  
}
