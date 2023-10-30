package com.example.depo.ui.add_new_material;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.depo.R;
import com.example.depo.databinding.FragmentAddNewTeaMaterialBinding;
import com.example.depo.ui.MainActivity;
import com.example.depo.ui.inventory_categories_page.tea_materials_page.TeaMaterialFragment;
import com.example.depo.ui.inventory_categories_page.tea_materials_page.TeaMaterialsViewModel;
import com.example.depo.util.FragmentHelper;

public class AddNewTeaMaterialFragment extends Fragment {
    private FragmentAddNewTeaMaterialBinding binding;
    private FragmentHelper helper;
    private TeaMaterialsViewModel teaMaterialsViewModel;

    public AddNewTeaMaterialFragment(TeaMaterialsViewModel teaMaterialsViewModel) {
        this.teaMaterialsViewModel = teaMaterialsViewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddNewTeaMaterialBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        helper = new FragmentHelper(getActivity());
        ((MainActivity) getActivity()).updateStatusBarColor(R.color.tea_material_tertiary);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCreamMaterialsFragment();
            }
        });
    }

    private void goToCreamMaterialsFragment(){
        TeaMaterialFragment fragment = new TeaMaterialFragment();
        helper.changeFragment(R.id.body_container,fragment,"CreamMaterialsFragment");
    }
}
