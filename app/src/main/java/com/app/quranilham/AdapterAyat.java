package com.app.quranilham;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.quranilham.ModelAyat.VersesItem;

import java.util.List;

public class AdapterAyat extends RecyclerView.Adapter<AdapterAyat.AyatViewHolder> {

    private static List<VersesItem> results;

    public AdapterAyat(List<VersesItem> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public AyatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AyatViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ayat, parent, false)
        );
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterAyat.AyatViewHolder holder, int position) {
        VersesItem result = results.get(position);

        holder.textViewAyat.setText(result.getTextUthmani());
    }
    @Override
    public int getItemCount() {
        return results.size()  ;
    }
    public class AyatViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAyat;
        public AyatViewHolder(View itemView){
            super(itemView);
            textViewAyat = itemView.findViewById(R.id.tvAyat);
        }
    }
    public void setData(List<VersesItem> data){
        results.clear();
        results.addAll(data);
        notifyDataSetChanged();
    }

}
