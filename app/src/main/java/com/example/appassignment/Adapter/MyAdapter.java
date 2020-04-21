package com.example.appassignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appassignment.MainActivity;
import com.example.appassignment.Mvvm.MyListViewModel;
import com.example.appassignment.R;
import com.example.appassignment.databinding.MyListBinding;
import com.example.appassignment.model.RowModel;
import com.example.appassignment.model.modelist;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<RowModel> arrayList;
    private Context context;
    private LayoutInflater layoutInflater;

    public MyAdapter(List<RowModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        MyListBinding myListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.mylistitem, parent, false);
        return new ViewHolder(myListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        com.example.appassignment.model.RowModel myListViewModel = arrayList.get(position);
        holder.bind(myListViewModel);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private MyListBinding myListBinding;

        public ViewHolder(@NonNull MyListBinding myListBinding) {
            super(myListBinding.getRoot());
            this.myListBinding = myListBinding;
        }

        public void bind(com.example.appassignment.model.RowModel myli) {
            this.myListBinding.setMylistmodel(myli);
            myListBinding.executePendingBindings();
        }

        public MyListBinding getMyListBinding() {
            return myListBinding;
        }
    }
}