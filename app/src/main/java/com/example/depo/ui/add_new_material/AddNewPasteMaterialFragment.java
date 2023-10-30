package com.example.depo.ui.add_new_material;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.depo.R;
import com.example.depo.databinding.FragmentAddNewPasteMaterialBinding;
import com.example.depo.ui.MainActivity;
import com.example.depo.ui.inventory_categories_page.paste_materials_page.PasteMaterialsFragment;
import com.example.depo.ui.inventory_categories_page.paste_materials_page.PasteMaterialsViewModel;
import com.example.depo.util.FragmentHelper;

public class AddNewPasteMaterialFragment extends Fragment {
    private FragmentAddNewPasteMaterialBinding binding;
    private FragmentHelper helper;
    private PasteMaterialsViewModel pasteMaterialsViewModel;

    public AddNewPasteMaterialFragment(PasteMaterialsViewModel pasteMaterialsViewModel) {
        this.pasteMaterialsViewModel = pasteMaterialsViewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddNewPasteMaterialBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        helper = new FragmentHelper(getActivity());
        ((MainActivity) getActivity()).updateStatusBarColor(R.color.paste_material_tertiary);

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
        PasteMaterialsFragment fragment = new PasteMaterialsFragment();
        helper.changeFragment(R.id.body_container,fragment,"CreamMaterialsFragment");
    }
}
