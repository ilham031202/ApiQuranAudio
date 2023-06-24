package com.app.quranilham;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.quranilham.ModelSurah.ChaptersItem;

import java.util.List;

public class AdapterSurah extends RecyclerView.Adapter<AdapterSurah.ViewHolder> {

    private List<ChaptersItem> results;

    public AdapterSurah(List<ChaptersItem> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public AdapterSurah.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_surah, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSurah.ViewHolder holder, int position) {
        ChaptersItem result = results.get(position);

        holder.textViewSurahArab.setText(result.getNameArabic());
        holder.textViewSurahLatin.setText(result.getNameSimple());
        holder.textViewSurahNomor.setText(String.valueOf(result.getId()));
        holder.textViewTerjemahanSurah.setText(result.getTranslatedName().getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailSurah.class);

                intent.putExtra("id", result.getId());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSurahLatin, textViewSurahArab, textViewTerjemahanSurah , textViewSurahNomor;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            textViewSurahNomor = itemView.findViewById(R.id.tvSurahNomor);
            textViewSurahArab = itemView.findViewById(R.id.tvSurahArab);
            textViewSurahLatin = itemView.findViewById(R.id.tvSurahLatin);
            textViewTerjemahanSurah = itemView.findViewById(R.id.tvTerjemahanSurah);
        }
    }
    public void setData(List<ChaptersItem> data) {
        results.clear();
        results.addAll(data);
        notifyDataSetChanged();
    }
}
