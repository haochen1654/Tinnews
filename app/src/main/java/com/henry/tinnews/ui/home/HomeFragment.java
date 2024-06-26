package com.henry.tinnews.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.henry.tinnews.databinding.FragmentHomeBinding;
import com.henry.tinnews.repository.NewsRepository;
import com.henry.tinnews.repository.NewsViewModelFactory;

public class HomeFragment extends Fragment {

  private FragmentHomeBinding binding;
  private HomeViewModel viewModel;

  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    NewsRepository repository = new NewsRepository(getContext());
    HomeViewModel homeViewModel =
        new ViewModelProvider(this, new NewsViewModelFactory(repository)).get(HomeViewModel.class);

    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    homeViewModel
        .getTopHeadlines()
        .observe(
            getViewLifecycleOwner(),
            newsResponse -> {
              if (newsResponse != null) {
                Log.d("HomeFragment", newsResponse.toString());
              }
            });
    return root;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    NewsRepository repository = new NewsRepository(getContext());
    viewModel =
        new ViewModelProvider(this, new NewsViewModelFactory(repository)).get(HomeViewModel.class);
    viewModel.setCountryInput("us");
    viewModel
        .getTopHeadlines()
        .observe(
            getViewLifecycleOwner(),
            newsResponse -> {
              if (newsResponse != null) {
                Log.d("HomeFragment", newsResponse.toString());
              }
            });
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
