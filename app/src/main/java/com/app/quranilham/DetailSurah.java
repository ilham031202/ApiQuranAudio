package com.app.quranilham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.app.quranilham.ModelAyat.Ayat;
import com.app.quranilham.ModelAyat.VersesItem;
import com.app.quranilham.ModelSurah.Surah;
import com.app.quranilham.Retrofit.ApiQuran;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSurah extends AppCompatActivity {

    private RecyclerView recyclerView;

    private AdapterAyat adapterAyat;

    private List<VersesItem> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_surah);

        int id = getIntent().getIntExtra("id", 1);

        setUpView();
        setUpRecyclerView();
        getDataFromApi(id);
    }
    private void getDataFromApi(int id) {
        ApiQuran.endpoint().getAyat(id).enqueue(new Callback<Ayat>() {
            @Override
            public void onResponse(Call<Ayat> call, Response<Ayat> response) {
                if (response.isSuccessful()){
                    List<VersesItem> results = response.body().getVerses();
                    Log.d("Main ", results.toString());
                    adapterAyat.setData(results);
                }
            }
            @Override
            public void onFailure(Call<Ayat> call, Throwable t) {
            }
        });
    }
    private void setUpRecyclerView() {
        adapterAyat = new AdapterAyat(results);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterAyat);
    }

    private void setUpView() {
        recyclerView = findViewById(R.id.rvAyat);
    }


}