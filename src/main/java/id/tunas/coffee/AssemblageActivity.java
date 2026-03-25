package id.tunas.coffee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.common.base.Strings;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import id.tunas.coffee.adapter.OnBoardingAdapter;
import id.tunas.coffee.dto.OnBoardingItem;

public class AssemblageActivity extends AppCompatActivity {

    private String baristaName;
    private String baristaId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assemblage);

        findViewById(R.id.arrow_left).setOnClickListener(view -> finish());

        ActivityResultLauncher<Intent> selectBaristaLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    String baristaId = result.getData().getStringExtra("barista_id");
                    String baristaName = result.getData().getStringExtra("barista_name");
                    if(!Strings.isNullOrEmpty(baristaId) && !Strings.isNullOrEmpty(baristaName)){
                        this.baristaId = baristaId;
                        this.baristaName = baristaName;
                        TextView selectBaristaTitle = findViewById(R.id.select_barista_title);
                        StringBuilder sb = new StringBuilder(getString(R.string.select_barista));
                        sb.append(" - ").append(this.baristaName);
                        selectBaristaTitle.setText(sb.toString());
                    }
                }
        );

        findViewById(R.id.select_barista).setOnClickListener(view -> {
            Intent intent = new Intent(this, SelectBaristaActivity.class);
            selectBaristaLauncher.launch(intent);
        });

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.CustomBottomSheetDialog);
        View v = getLayoutInflater().inflate(R.layout.milk_layout, null);
        bottomSheetDialog.setContentView(v);

        v.findViewById(R.id.cancel_select_milk).setOnClickListener(view -> bottomSheetDialog.dismiss());

        findViewById(R.id.select_milk).setOnClickListener(view -> {
            bottomSheetDialog.show();
        });

        OnBoardingAdapter onBoardingAdapter = new OnBoardingAdapter(OnBoardingItem.createDummy());
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        TabLayout tabIndicator = findViewById(R.id.tabIndicator);
        viewPager.setAdapter(onBoardingAdapter);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabIndicator, viewPager, (tab, i) -> {
        });
        tabLayoutMediator.attach();

    }
}
