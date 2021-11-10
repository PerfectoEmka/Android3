package com.example.android3.ui.film_list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android3.App;
import com.example.android3.R;
import com.example.android3.data.models.Films;
import com.example.android3.databinding.FragmentFilmsBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmsFragment extends Fragment implements FilmsAdapter.OnItemClickListener {

    FilmsAdapter adapter;
    FragmentFilmsBinding binding;

    public FilmsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new FilmsAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFilmsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        App.api.getFilms().enqueue(new Callback<List<Films>>() {
            @Override
            public void onResponse(Call<List<Films>> call, Response<List<Films>> response) {
                if (response.isSuccessful() && response.body() != null){
                    adapter.setList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Films>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void OnItemClick(int pos) {Log.e("TAG", "onItemSelected");
        String id = adapter.getItem(pos).getId();
        Bundle bundle = new Bundle();
        bundle.putString("key", id);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.filmDetailFragment, bundle);
    }
}