package ucas.android.sqlite.model;

/**
 * Created by abeer on 20,May,2023
 */
public class Cart {
    int productId; int count;
    public Cart(int productId, int count) {
        this.productId=productId;
        this.count=count;
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
