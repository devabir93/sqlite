package ucas.android.sqlite;

/**
 * Created by abeer on 20,May,2023
 */
public interface ProductListener {
    void onAddToCart(int productId, String size, int count);
    void deleteFromCart(int productId, int pos);
}
