package ucas.android.sqlite;

/**
 * Created by abeer on 20,May,2023
 */
public interface ProductListener {
    void onAddToCart(int productId, int count);
}
