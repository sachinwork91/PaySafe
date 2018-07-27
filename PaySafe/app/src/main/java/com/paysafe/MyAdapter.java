package com.paysafe;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Sachin on 2018-07-26.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private Customer[] customersDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public View layout;
        public TextView customerName;
        public TextView customerContactNumber;
        public MyViewHolder(View v) {
            super(v);
            layout = v;
            customerName = (TextView) layout.findViewById(R.id.customer_name);
            customerContactNumber = (TextView) layout.findViewById(R.id.customer_contact_number);
        }
    }

    public MyAdapter(Customer[] myDataset) {
        customersDataset = myDataset;
    }

    public void loadData(Customer[] myDataset){
        this.customersDataset = myDataset;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        view= mInflater.inflate(R.layout.customer_list_item , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.customerName.setText(customersDataset[position].getName());
        holder.customerContactNumber.setText(customersDataset[position].getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return customersDataset.length;
    }
}
