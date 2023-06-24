package com.app.quranilham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.app.quranilham.ModelAudio.Audio;
import com.app.quranilham.ModelAudio.AudioFilesItem;
import com.app.quranilham.ModelSurah.ChaptersItem;
import com.app.quranilham.ModelSurah.Surah;
import com.app.quranilham.Retrofit.ApiQuran;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AdapterSurah adapterSurah;
    private AdapterAudio adapterAudio;
    private RecyclerView rv1, rv2;
    RecyclerView.LayoutManager lm1,lm2;
    private List<ChaptersItem> results = new ArrayList<>();
    private List<AudioFilesItem> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataFromApi();
        getAudioFromApi();
        setUpView();
        setUpRecyclerView();
    }



    private void setUpRecyclerView() {
        adapterSurah = new AdapterSurah(results);
        lm1 = new LinearLayoutManager(this);
        rv1.setLayoutManager(lm1);
        rv1.setAdapter(adapterSurah);

        adapterAudio = new AdapterAudio(list);
        lm2 = new LinearLayoutManager(this);
        rv2.setLayoutManager(lm2);
        rv2.setAdapter(adapterAudio);
    }

    private void setUpView() {
        rv1 = findViewById(R.id.rvSurah);
        rv2 = findViewById(R.id.rvAudio);
    }

    private void getAudioFromApi() {
        ApiQuran.endpoint().getAudio().enqueue(new Callback<Audio>() {
            @Override
            public void onResponse(Call<Audio> call, Response<Audio> response) {
                List<AudioFilesItem> result = response.body().getAudioFiles();
                adapterAudio.getData(result);
            }

            @Override
            public void onFailure(Call<Audio> call, Throwable t) {

            }
        });
    }

    private void getDataFromApi() {
        ApiQuran.endpoint().getSurah().enqueue(new Callback<Surah>() {
            @Override
            public void onResponse(Call<Surah> call, Response<Surah> response) {
                if (response.isSuccessful()){
                    List<ChaptersItem> result = response.body().getChapters();
                    Log.d( "Surah", result.toString());
                    adapterSurah.setData(result);
                }
            }

            @Override
            public void onFailure(Call<Surah> call, Throwable t) {
                Log.d("ErrorMain", t.toString());
            }
        });
    }
}