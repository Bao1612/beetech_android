package com.example.login_form.java;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.login_form.R;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private final List<ProductList> getProduct;
    private final Context context;
    private final int layout;

    public ProductAdapter(List<ProductList> getProduct, Context context, int layout) {
        this.getProduct = getProduct;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return getProduct.size();
    }

    @Override
    public Object getItem(int position) {
        return getProduct.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        TextView productID, productName, productBarCode, productRFID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(layout, null);
        viewHolder.productID = convertView.findViewById(R.id.productID);
        viewHolder.productName = convertView.findViewById(R.id.productName);
        viewHolder.productBarCode = convertView.findViewById(R.id.productBarCode);
        viewHolder.productRFID = convertView.findViewById(R.id.productRFID);
        ProductList productList = getProduct.get(position);

        viewHolder.productID.setText("ID: " + productList.getInternalId());
        viewHolder.productName.setText("Products name: " + productList.getAliasName());
        viewHolder.productBarCode.setText("Prodcts BarCode: " + productList.getBarcodeId());
        viewHolder.productRFID.setText("Products RFID: " + productList.getRfid());

        return convertView;
    }
}
