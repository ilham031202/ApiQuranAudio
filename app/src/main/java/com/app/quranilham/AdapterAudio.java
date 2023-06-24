package com.app.quranilham;


import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.quranilham.ModelAudio.AudioFilesItem;

import java.io.IOException;
import java.util.List;

public class AdapterAudio extends RecyclerView.Adapter<AdapterAudio.ViewHolder> {
    private static List<AudioFilesItem> list;
    private MediaPlayer mediaPlayer;

    public AdapterAudio(List<AudioFilesItem> list){
        this.list = list;
        mediaPlayer = new MediaPlayer();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_audio, parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAudio.ViewHolder holder, int position) {
        AudioFilesItem audio = list.get(position);

        holder.btAudio.setOnClickListener(view -> {

            if (mediaPlayer.isPlaying()){
                pauseAudio();
            } else {
                playAudio(audio.getAudioUrl());
            }
        });
    }
    private void playAudio(String audioUrl) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(audioUrl);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void pauseAudio() {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getData(List<AudioFilesItem> result) {
        list.clear();
        list.addAll(result);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Button btAudio;
        public ViewHolder(View v){
            super(v);

            btAudio = v.findViewById(R.id.btAudio);
        }
    }
}
