package ucas.android.sqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ucas.android.sqlite.model.Product;
import ucas.android.sqlite.ui.CartActivity;

public class ProductsAdapterReccylerview extends RecyclerView.Adapter<ProductsAdapterReccylerview.ProductViewholder> {
    ArrayList<Product> productArrayList;
    ProductListener productListener;
    String src;

    public ProductsAdapterReccylerview(String src, ArrayList<Product> productArrayList, ProductListener productListener) {
        this.productArrayList = productArrayList;
        this.productListener = productListener;
        this.src = src;
    }

    @NonNull
    @Override
    public ProductsAdapterReccylerview.ProductViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int resource = R.layout.list_item_product;
        if(src.equals(CartActivity.class.getSimpleName())){
            resource= R.layout.list_item_cart;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new ProductViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapterReccylerview.ProductViewholder holder, int position) {

        Product product = productArrayList.get(holder.getAdapterPosition());
        if (product == null)
            return;
        holder.nameTv.setText(product.getName());
        holder.descTv.setText(product.getDescription());
        if(holder.quantityTv!=null)
            holder.quantityTv.setText(product.getQuantity()+"");
        if(holder.categoryTv!=null)
            holder.categoryTv.setText(product.getCategoryName());
        if(holder.sizeTv!=null)
            holder.sizeTv.setText(product.getSelectSize());
        if(holder.addToCartBtn!=null)
            holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productListener.onAddToCart(product.getId(),(String)holder.spinner.getSelectedItem(), position + 2);
            }
        });

        if(holder.deleteFromCartBtn!=null)
            holder.deleteFromCartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productListener.deleteFromCart(product.getCartId(), holder.getAdapterPosition());
                }
            });
    }

    @Override
    public int getItemCount() {
        if (productArrayList != null)
            return productArrayList.size();
        return 0;
    }

    static class ProductViewholder extends RecyclerView.ViewHolder {

        TextView nameTv, descTv, categoryTv,quantityTv,sizeTv;
        Button addToCartBtn,deleteFromCartBtn;
        Spinner spinner;

        public ProductViewholder(@NonNull View itemView) {
            super(itemView);
            spinner = itemView.findViewById(R.id.size_spinner);
            nameTv = itemView.findViewById(R.id.product_name_tv);
            descTv = itemView.findViewById(R.id.product_Desc_tv);
            categoryTv = itemView.findViewById(R.id.product_category_tv);
            quantityTv = itemView.findViewById(R.id.quantity_value_tv);
            sizeTv = itemView.findViewById(R.id.size_value_tv);
            addToCartBtn = itemView.findViewById(R.id.add_to_cart_btn);
            deleteFromCartBtn = itemView.findViewById(R.id.delete_from_cart_btn);
        }
    }
}
