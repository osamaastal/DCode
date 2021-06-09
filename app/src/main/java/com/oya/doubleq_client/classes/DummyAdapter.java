package com.oya.doubleq_client.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oya.doubleq_client.R;

import java.util.ArrayList;

public class DummyAdapter extends RecyclerView.Adapter<DummyAdapter.MyViewHolder> {

    private ArrayList<Object> mItems;
    private Context context;
    private int row;

    public DummyAdapter(Context context, int row, int count) {
        this.context = context;
        this.row = row;
        mItems = new ArrayList<>();
        for (int i=0; i<count; i++){
            mItems.add(new Object());
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (mItems == null)
            return 0;
        return mItems.size();
    }

    public void setList(ArrayList<Object> mItems) {
        this.mItems = mItems;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TextView titleTv = itemView.findViewById(R.id.titleTv);
        }
    }
}
