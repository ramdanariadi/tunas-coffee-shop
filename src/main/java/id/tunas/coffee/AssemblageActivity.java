package id.tunas.coffee;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.common.base.Strings;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.logging.Logger;

import id.tunas.coffee.adapter.OnBoardingAdapter;
import id.tunas.coffee.dto.OnBoardingItem;

public class AssemblageActivity extends AppCompatActivity {
    private static final Logger LOGGER = Logger.getLogger(AssemblageActivity.class.getName());
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
                    if(result.getResultCode() != RESULT_OK) return;
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
        findViewById(R.id.select_milk).setOnClickListener(view -> bottomSheetDialog.show());

        OnBoardingAdapter onBoardingAdapter = new OnBoardingAdapter(OnBoardingItem.createDummy());
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        TabLayout tabIndicator = findViewById(R.id.tabIndicator);
        viewPager.setAdapter(onBoardingAdapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                for(int i = 0; i < tabIndicator.getTabCount(); i++){
                    TabLayout.Tab tab = tabIndicator.getTabAt(i);
                    if(tab != null && tab.getCustomView() != null){
                        View customView = tab.getCustomView();
                        View dot = customView.findViewById(R.id.dot);
                        if(i == position){
                            dot.setAlpha(1.0F);
                        }else{
                            dot.setAlpha(0.2F);
                        }
                    }
                }
            }
        });
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabIndicator, viewPager, (tab, i) -> {
            tab.setCustomView(R.layout.custom_dot);
        });
        tabLayoutMediator.attach();
        viewPager.setCurrentItem(0, false);

        TextView previousSlider = findViewById(R.id.previous_slider);
        TextView nextSlider = findViewById(R.id.next_slider);
        LinearLayout sliderLayout = findViewById(R.id.slider);
        previousSlider.setOnClickListener(view -> {
            if(viewPager.getCurrentItem() == 0) return;
            if(viewPager.getCurrentItem() == 1){
                previousSlider.setTextColor(getColor(R.color.grey_border));
            };
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
        });

        nextSlider.setOnClickListener(view -> {
            if(viewPager.getCurrentItem() == onBoardingAdapter.getItemCount() - 1){
                sliderLayout.setVisibility(View.GONE);
                return;
            }
            if(viewPager.getCurrentItem() == (onBoardingAdapter.getItemCount() - 2)){
                nextSlider.setText("Done");
            }
            previousSlider.setTextColor(getColor(R.color.card_white));
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
        });

    }
}
