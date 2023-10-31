package com.example.depo.ui.detail_of_material_pages.tea_material_detail_page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.depo.R;
import com.example.depo.databinding.FragmentTeaMaterialDetailBinding;
import com.example.depo.ui.MainActivity;

public class TeaMaterialDetailFragment extends Fragment {

    FragmentTeaMaterialDetailBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTeaMaterialDetailBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        ((MainActivity) getActivity()).updateStatusBarColor(R.color.tea_material_secondary);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
