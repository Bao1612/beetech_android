package com.example.login_form.java;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login_form.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context context;
    List<ProductList> product_list;

    public ProductAdapter(Context context, List<ProductList> product_list) {
        this.context = context;
        this.product_list = product_list;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        if(product_list != null && product_list.size() > 0) {
            ProductList productList = product_list.get(position);
            holder.id.setText(productList.getId());
            holder.name.setText(productList.getName());
            holder.barcode.setText(productList.getBarCode());
            holder.rfid.setText(productList.getRFID());
        } else {
            return;
        }
    }

    @Override
    public int getItemCount() {
        return product_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, barcode, rfid;
        public ViewHolder(@NonNull View intenView) {
            super(intenView);
            id = intenView.findViewById(R.id.id);
            name = intenView.findViewById(R.id.name);
            barcode = intenView.findViewById(R.id.barCode);
            rfid = intenView.findViewById(R.id.rfid);
        }
    }

}
