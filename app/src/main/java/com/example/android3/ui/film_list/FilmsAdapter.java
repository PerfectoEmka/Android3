package com.example.android3.ui.film_list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3.data.models.Films;
import com.example.android3.databinding.ItemFilmsBinding;

import java.util.ArrayList;
import java.util.List;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.ViewHolder> {

    private OnItemClickListener onItemClickListener;
    private List<Films> list = new ArrayList<>();

    public void setList(List<Films> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Films getItem(int pos){
        return list.get(pos);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFilmsBinding binding = ItemFilmsBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemFilmsBinding binding;

        public ViewHolder(@NonNull ItemFilmsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind(Films films) {
            binding.getRoot().setOnClickListener(v -> {
                onItemClickListener.OnItemClick(getAdapterPosition());
            });
            binding.filmName.setText(films.getTitle());
            binding.filmDirector.setText(films.getDirector());
        }
    }

    public interface OnItemClickListener{
        void OnItemClick(int pos);
    }
}
