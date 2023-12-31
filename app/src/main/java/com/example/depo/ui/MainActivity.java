package com.example.depo.ui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.depo.util.CaptureAct;
import com.example.depo.R;
import com.example.depo.databinding.ActivityMainBinding;
import com.example.depo.model.Material;
import com.example.depo.model.ScanQRMaterial;
import com.example.depo.ui.detail_of_material_pages.cream_material_detail_page.CreamMaterialDetailFragment;
import com.example.depo.ui.detail_of_material_pages.paste_material_detail_page.PasteMaterialDetailFragment;
import com.example.depo.ui.detail_of_material_pages.syrup_material_detail_page.SyrupMaterialDetailFragment;
import com.example.depo.ui.detail_of_material_pages.tea_material_detail_page.TeaMaterialDetailFragment;
import com.example.depo.util.FragmentHelper;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainActivityViewModel mainActivityViewModel;
    private Fragment fragment;
    private boolean isFindAnyMaterialUsingQRCODE = false;
    private FragmentHelper fragmentHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        fragmentHelper=new FragmentHelper(this);

        binding.qrScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQRCodeScanner();
            }
        });


    }

    private void startQRCodeScanner(){
        ScanOptions options = new ScanOptions();
        options.setPrompt("Flaş İçin Ses Açma Tuşuna Basın !");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        launcher.launch(options);
    }
    ActivityResultLauncher<ScanOptions> launcher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null){
            showLoadingScreen();
            System.out.println(result.getContents());

            mainActivityViewModel.restartMaterialStringLiveData();

            mainActivityViewModel.queryForSpecificCode("CreamMaterials",result.getContents());
            mainActivityViewModel.queryForSpecificCode("TeaMaterials",result.getContents());
            mainActivityViewModel.queryForSpecificCode("PasteMaterials",result.getContents());
            mainActivityViewModel.queryForSpecificCode("SyrupMaterials",result.getContents());

            mainActivityViewModel.scanQRMaterialLiveData().observe(this, new Observer<ScanQRMaterial>() {
                @Override
                public void onChanged(ScanQRMaterial scanQRMaterial) {
                    if(scanQRMaterial.getMaterial() != null){
                        hideLoadingScreen();
                        Material material= scanQRMaterial.getMaterial();
                        String s = scanQRMaterial.getMaterialType();
                        System.out.println(s);
                        switch (s){
                            case "CreamMaterials":
                                fragment = new CreamMaterialDetailFragment(material);
                                mainActivityViewModel.scanQRMaterialLiveData().removeObserver(this);
                                break;
                            case "PasteMaterials":
                                fragment = new PasteMaterialDetailFragment(material);
                                mainActivityViewModel.scanQRMaterialLiveData().removeObserver(this);
                                break;
                            case "SyrupMaterials":
                                fragment = new SyrupMaterialDetailFragment(material);
                                mainActivityViewModel.scanQRMaterialLiveData().removeObserver(this);
                                break;
                            case "TeaMaterials":
                                fragment = new TeaMaterialDetailFragment(material);
                                mainActivityViewModel.scanQRMaterialLiveData().removeObserver(this);
                                break;
                            case "none":
                                Toast.makeText(MainActivity.this, "bulunamadı", Toast.LENGTH_SHORT).show();
                                mainActivityViewModel.scanQRMaterialLiveData().removeObserver(this);
                                break;
                        }

                        if (!(s.matches("none") || s.matches(""))){
                            fragmentHelper.changeFragment(R.id.body_container,fragment,s);
                        }
                    }
                }
            });



            /*
            mainActivityViewModel.getMaterialHash().observe(this, new Observer<HashMap<String, Object>>() {
                @Override
                public void onChanged(HashMap<String, Object> stringObjectHashMap) {
                    if(stringObjectHashMap.get("material") != null && stringObjectHashMap.get("collection") != null && !stringObjectHashMap.get("material").toString().matches("")){
                        hideLoadingScreen();
                        //Material material= (Material) stringObjectHashMap.get("material");
                        String s = (String) stringObjectHashMap.get("collection");
                        System.out.println(s);
                        switch (s){
                            case "CreamMaterials":
                                fragment = new CreamMaterialDetailFragment((Material) stringObjectHashMap.get("material"));
                                mainActivityViewModel.getMaterialHash().removeObserver(this);
                                break;
                            case "PasteMaterials":
                                fragment = new PasteMaterialDetailFragment();
                                mainActivityViewModel.getMaterialHash().removeObserver(this);
                                break;
                            case "SyrupMaterials":
                                fragment = new SyrupMaterialDetailFragment();
                                mainActivityViewModel.getMaterialHash().removeObserver(this);
                                break;
                            case "TeaMaterials":
                                fragment = new TeaMaterialDetailFragment();
                                mainActivityViewModel.getMaterialHash().removeObserver(this);
                                break;
                            case "none":
                                Toast.makeText(MainActivity.this, "bulunamadı", Toast.LENGTH_SHORT).show();
                                mainActivityViewModel.getMaterialHash().removeObserver(this);
                                break;
                        }

                        if (!(s.matches("none") || s.matches(""))){
                            fragmentHelper.changeFragment(R.id.body_container,fragment,s);
                        }
                    }
                }
            });

             */



            /*
            mainActivityViewModel.getMaterialStringLiveData().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    hideLoadingScreen();
                    //System.out.println(s);
                    switch (s){
                        case "CreamMaterials":
                            fragment = new CreamMaterialDetailFragment();
                            mainActivityViewModel.getMaterialStringLiveData().removeObserver(this);
                            break;
                        case "PasteMaterials":
                            fragment = new PasteMaterialDetailFragment();
                            mainActivityViewModel.getMaterialStringLiveData().removeObserver(this);
                            break;
                        case "SyrupMaterials":
                            fragment = new SyrupMaterialDetailFragment();
                            mainActivityViewModel.getMaterialStringLiveData().removeObserver(this);
                            break;
                        case "TeaMaterials":
                            fragment = new TeaMaterialDetailFragment();
                            mainActivityViewModel.getMaterialStringLiveData().removeObserver(this);
                            break;
                        case "none":
                            Toast.makeText(MainActivity.this, "bulunamadı", Toast.LENGTH_SHORT).show();
                            mainActivityViewModel.getMaterialStringLiveData().removeObserver(this);
                            break;
                    }

                        if (!(s.matches("none") || s.matches(""))){
                            fragmentHelper.changeFragment(R.id.body_container,fragment,s);
                        }

                        //alternatif
                        /*
                        if (!(s.matches("none") || s.matches(""))) {
                        switch (s) {
                            case "CreamMaterials":
                                fragment = new CreamMaterialDetailFragment();
                                mainActivityViewModel.getMaterialStringLiveData().removeObserver(this);
                                break;
                            case "PasteMaterials":
                                fragment = new PasteMaterialDetailFragment();
                                mainActivityViewModel.getMaterialStringLiveData().removeObserver(this);
                                break;
                            case "SyrupMaterials":
                                fragment = new SyrupMaterialDetailFragment();
                                mainActivityViewModel.getMaterialStringLiveData().removeObserver(this);
                                break;
                            case "TeaMaterials":
                                fragment = new TeaMaterialDetailFragment();
                                mainActivityViewModel.getMaterialStringLiveData().removeObserver(this);
                                break;
                        }
                        fragmentHelper.changeFragment(R.id.body_container, fragment, s);
                    } else {
                        Toast.makeText(MainActivity.this, "bulunamadı", Toast.LENGTH_SHORT).show();
                    }



                }
            });

*/




        }



    });

    private void showLoadingScreen() {
        binding.loadingBar.setVisibility(View.VISIBLE);
        binding.loadingTextView.setVisibility(View.VISIBLE);

        binding.loadingBar.setAlpha(1.0f);
        binding.loadingTextView.setAlpha(1.0f);
    }
    private void hideLoadingScreen() {


        binding.loadingBar.animate().alpha(0.0f).setDuration(500).withEndAction(new Runnable() {
            @Override
            public void run() {
                binding.loadingBar.setVisibility(View.GONE);
            }
        });

        binding.loadingTextView.animate().alpha(0.0f).setDuration(500).withEndAction(new Runnable() {
            @Override
            public void run() {
                binding.loadingTextView.setVisibility(View.GONE);
            }
        });
    }

    public void updateStatusBarColor(int color){// Color must be in hexadecimal format
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),color));
        }
    }


}