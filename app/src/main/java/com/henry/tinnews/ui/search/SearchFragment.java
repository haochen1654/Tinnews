package com.henry.tinnews.ui.search;

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

import com.henry.tinnews.databinding.FragmentSearchBinding;
import com.henry.tinnews.repository.NewsRepository;
import com.henry.tinnews.repository.NewsViewModelFactory;

public class SearchFragment extends Fragment {

  private FragmentSearchBinding binding;
  private SearchViewModel viewModel;

  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    SearchViewModel SearchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);

    binding = FragmentSearchBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    SearchViewModel.searchNews()
        .observe(
            getViewLifecycleOwner(),
            newsResponse -> {
              if (newsResponse != null) {
                Log.d("SearchFragment", newsResponse.toString());
              }
            });
    return root;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    NewsRepository repository = new NewsRepository(getContext());
    viewModel =
        new ViewModelProvider(this, new NewsViewModelFactory(repository))
            .get(SearchViewModel.class);
    viewModel.setSearchInput("Covid-19");
    viewModel
        .searchNews()
        .observe(
            getViewLifecycleOwner(),
            newsResponse -> {
              if (newsResponse != null) {
                Log.d("SearchFragment", newsResponse.toString());
              }
            });
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
