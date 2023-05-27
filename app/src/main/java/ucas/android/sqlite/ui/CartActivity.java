package ucas.android.sqlite.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import ucas.android.sqlite.ProductListener;
import ucas.android.sqlite.ProductsAdapterReccylerview;
import ucas.android.sqlite.databinding.ActivityCartBinding;
import ucas.android.sqlite.db.DBManager;
import ucas.android.sqlite.model.Product;

public class CartActivity extends AppCompatActivity {

    private DBManager db;
    ProductsAdapterReccylerview productsAdapterReccylerview;
    ActivityCartBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new DBManager(this).open();
        ArrayList<Product> productArrayList = db.fetchCart();
        if(productArrayList!=null && productArrayList.size()>0) {
            binding.productsRv.setVisibility(View.VISIBLE);
            binding.emptyLayout.container.setVisibility(View.GONE);
            initAdapter(productArrayList);
        }else{
            binding.productsRv.setVisibility(View.GONE);
            binding.emptyLayout.container.setVisibility(View.VISIBLE);
        }
        Log.d(MainActivity.class.getSimpleName(), productArrayList.toString());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(db!=null)
            db.close();
    }

    private void initAdapter(ArrayList<Product> productArrayList) {
        productsAdapterReccylerview = new ProductsAdapterReccylerview(CartActivity.class.getSimpleName(),productArrayList, new ProductListener() {
            @Override
            public void onAddToCart(int productId, int count) {

            }

            @Override
            public void deleteFromCart(int productId, int pos) {
                int rows =db.deleteFromCart(productId);
               Log.d("deleteFromCart",rows+"");
                if(rows > 0 ){
                    productArrayList.remove(pos);
                    productsAdapterReccylerview.notifyItemRemoved(pos);
                }
            }
        });
        binding.productsRv.setAdapter(productsAdapterReccylerview);
        binding.productsRv.setLayoutManager(new LinearLayoutManager(this));
    }

}