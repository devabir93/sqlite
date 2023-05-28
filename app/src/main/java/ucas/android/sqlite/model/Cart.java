package ucas.android.sqlite.model;

/**
 * Created by abeer on 20,May,2023
 */
public class Cart {
    int productId; int count;
    String size;
    public Cart(int productId,String size, int count) {
        this.productId=productId;
        this.count=count;
        this.size=size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
