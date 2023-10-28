package com.example.depo.ui.inventory_categories_page.tea_materials_page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.depo.R;
import com.example.depo.databinding.FragmentTeaMaterialsBinding;
import com.example.depo.model.CreamMaterial;
import com.example.depo.model.SyrupMaterial;
import com.example.depo.model.TeaMaterial;
import com.example.depo.ui.inventory_categories_page.InventoryCategoryPage;
import com.example.depo.util.FragmentHelper;

import java.util.List;

public class TeaMaterialFragment extends Fragment {
    private FragmentTeaMaterialsBinding binding;
    private TeaMaterialsViewModel teaMaterialsViewModel;
    FragmentHelper helper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTeaMaterialsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        teaMaterialsViewModel = new ViewModelProvider(this).get(TeaMaterialsViewModel.class);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*
        teaMaterialsViewModel.addData(new TeaMaterial(
                "kekreyemiş",
                "1234",
                "11.10.2023",
                "gönderilmeye hazır",
                1500));



         */

        teaMaterialsViewModel.getData();
        teaMaterialsViewModel.getTeaMaterials().observe(getViewLifecycleOwner(), new Observer<List<TeaMaterial>>() {
            @Override
            public void onChanged(List<TeaMaterial> teaMaterials) {

                /*
                    Bundle bundle = new Bundle();
                    bundle.putString("veriAnahtari", teaMaterials.get(0).getMaterialName());

                    
                InventoryCategoryPage fragment = new InventoryCategoryPage();

                fragment.setArguments(bundle);
                    /*
                    getParentFragmentManager().setFragmentResult("datafromtea",bundle);




                 helper = new FragmentHelper(getActivity());
                helper.changeFragment(R.id.body_container,fragment,"InventoryCategoryPage");

                 */




            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        teaMaterialsViewModel=null;
        helper=null;
    }
}
