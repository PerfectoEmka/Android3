package com.example.android3.ui.film_details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.android3.App;
import com.example.android3.R;
import com.example.android3.data.models.Films;
import com.example.android3.databinding.FragmentFilmDetailBinding;
import com.example.android3.databinding.FragmentFilmsBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmDetailFragment extends Fragment {

    private FragmentFilmDetailBinding binding;

    public FilmDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        String id = getArguments().getString("key");
        App.api.getFilm(id).enqueue(new Callback<Films>() {
            @Override
            public void onResponse(Call<Films> call, Response<Films> response) {
                setData(response.body());
            }

            @Override
            public void onFailure(Call<Films> call, Throwable t) {

            }
        });

    }

    private void setData(Films body) {
        binding.title.setText(body.getTitle());
        binding.description.setText(body.getDescription());
        binding.director.setText(body.getDirector());
        binding.originalTitle.setText(body.getOriginalTitle());
        binding.releaseDate.setText(body.getReleaseDate());
        Glide.with(requireContext())
                .load(body.getImage())
                .into(binding.imageV);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFilmDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}