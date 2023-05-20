package ucas.android.sqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ucas.android.sqlite.model.Product;

public  class ProductsAdapterReccylerview extends RecyclerView.Adapter<ProductsAdapterReccylerview.ProductViewholder> {
    ArrayList<Product> productArrayList;
    ProductListener productListener;
    public ProductsAdapterReccylerview(ArrayList<Product> productArrayList,ProductListener productListener) {
        this.productArrayList = productArrayList;
        this.productListener = productListener;
    }

    @NonNull
    @Override
    public ProductsAdapterReccylerview.ProductViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_product , parent , false);
        return new ProductViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapterReccylerview.ProductViewholder holder, int position) {

        Product product = productArrayList.get(holder.getAdapterPosition());
        if(product==null)
            return;
        holder.nameTv.setText(product.getName());
        holder.descTv.setText(product.getDescription());
        holder.categoryTv.setText(product.getCategoryName());
        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productListener.onAddToCart(product.getId(),position+2);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(productArrayList!=null)
            return productArrayList.size();
        return 0;
    }

    static class ProductViewholder extends RecyclerView.ViewHolder{

        TextView nameTv , descTv, categoryTv;
        Button addToCartBtn;
        public ProductViewholder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.product_name_tv);
            descTv = itemView.findViewById(R.id.product_Desc_tv);
            categoryTv = itemView.findViewById(R.id.product_category_tv);
            addToCartBtn = itemView.findViewById(R.id.add_to_cart_btn);
        }
    }
}
