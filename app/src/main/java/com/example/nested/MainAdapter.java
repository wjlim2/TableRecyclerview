package com.example.nested;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nested.databinding.ItemAttendTimeBinding;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{
    private ArrayList<EmpAttend> mAttendTimeList;

    public MainAdapter(ArrayList<EmpAttend> attendTimeList) {
        this.mAttendTimeList = attendTimeList;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAttendTimeBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_attend_time, parent, false);
        return new MainViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.onBind(mAttendTimeList.get(position));
    }

    @Override
    public int getItemCount() {
        return mAttendTimeList.size();
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {
        ItemAttendTimeBinding mBinding;

        MainViewHolder(@NonNull ItemAttendTimeBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        void onBind(EmpAttend empAttend) {
            mBinding.setModel(empAttend);
        }
    }
}
