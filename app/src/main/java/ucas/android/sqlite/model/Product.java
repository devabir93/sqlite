package ucas.android.sqlite.model;

/**
 * Created by abeer on 20,May,2023
 */
public class Product {
    int id;
    String name;
    String description;
    float price;
    int categoryId;
    int quantity;

    String categoryName;

    public Product() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Product(String name, String description, float price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(int id, String name, String description, float price, String categoryName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryName = categoryName;
    }

    public Product(String name, String description, float price, int categoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
    }
    public Product(int id, String name, String description, float price,int categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", categoryId=" + categoryId +
                ", quantity=" + quantity +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
