package id.tunas.coffee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextClock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import id.tunas.coffee.dto.ConsumeOn;

public class OrderActivity extends AppCompatActivity {
    private int quantity = 1;
    private int ristretto = 1;
    private int volume = 350;
    private ConsumeOn consumeOn = ConsumeOn.ONSITE;
    private int previousSelectedVolumeLayoutId = R.id.volume_m_container;
    private Map<Integer, List<Integer>> volumeMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        findViewById(R.id.arrow_left).setOnClickListener(view -> finish());
        findViewById(R.id.qty_minus).setOnClickListener(this::qtyChange);
        findViewById(R.id.qty_plus).setOnClickListener(this::qtyChange);

        TextView oneTv = findViewById(R.id.ristretto_one);
        TextView twoTv = findViewById(R.id.ristretto_two);

        oneTv.setOnClickListener(view -> ristretto(view, twoTv));
        twoTv.setOnClickListener(view -> ristretto(view, oneTv));

        ImageView onsiteIv = findViewById(R.id.onsite);
        ImageView takeAwayIv = findViewById(R.id.take_away);

        onsiteIv.setOnClickListener(view -> consumeOn(view, takeAwayIv));
        takeAwayIv.setOnClickListener(view -> consumeOn(view, onsiteIv));

        initVolumeMap();

        findViewById(R.id.volume_s_container).setOnClickListener(view -> volumeChange(view.getId()));
        findViewById(R.id.volume_m_container).setOnClickListener(view -> volumeChange(view.getId()));
        findViewById(R.id.volume_l_container).setOnClickListener(view -> volumeChange(view.getId()));

        TextClock textClock = findViewById(R.id.textClock);
        textClock.setOnClickListener(view -> {
            MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setTitleText("Chose Time")
                    .build();

            timePicker.show(getSupportFragmentManager(), "TIME_PICKER");
        });

        findViewById(R.id.assemblage).setOnClickListener(view -> {
            Intent assemblageIntent = new Intent(this, AssemblageActivity.class);
            startActivity(assemblageIntent);
        });
    }

    private void qtyChange(View view){
        if(view.getId() == R.id.qty_minus) this.quantity--;
        if(view.getId() == R.id.qty_plus) this.quantity++;
        if(this.quantity < 1) this.quantity = 1;
        ((TextView)findViewById(R.id.quantity)).setText(String.valueOf(this.quantity));
    }

    private void ristretto(View view, View previousSelectedView){
        if(view.getId() == R.id.ristretto_one){
            this.ristretto = 1;
        }

        if(view.getId() == R.id.ristretto_two){
            this.ristretto = 2;
        }

        view.setBackground(getDrawable(R.drawable.bg_primary_all_rounded_50));
        ((TextView)view).setTextColor(getColor(R.color.card_white));
        previousSelectedView.setBackground(getDrawable(R.drawable.grey_rounded_border));
        ((TextView)previousSelectedView).setTextColor(getColor(R.color.dark_blue));
    }

    private void consumeOn(View imageView, View previousSelectedImageView){
        if(imageView.getId() == R.id.onsite){
            this.consumeOn = ConsumeOn.ONSITE;
        }

        if(imageView.getId() == R.id.take_away){
            this.consumeOn = ConsumeOn.TAKE_AWAY;
        }

        ((ImageView)imageView).setImageResource(R.drawable.takeaway_cup_active);
        ((ImageView)previousSelectedImageView).setImageResource(R.drawable.onsite_cup_disabled);
    }

    private void volumeChange(int volumeLayoutId){
        ImageView psc = findViewById(volumeMap.get(previousSelectedVolumeLayoutId).get(0));
        psc.setImageResource(volumeMap.get(previousSelectedVolumeLayoutId).get(3));
        TextView stv = findViewById(volumeMap.get(previousSelectedVolumeLayoutId).get(1));
        stv.setTextColor(getColor(R.color.grey_border));

        previousSelectedVolumeLayoutId = volumeLayoutId;
        this.volume = volumeMap.get(volumeLayoutId).get(4);

        ImageView sc = findViewById(volumeMap.get(volumeLayoutId).get(0));
        sc.setImageResource(volumeMap.get(volumeLayoutId).get(2));
        TextView tv = findViewById(volumeMap.get(volumeLayoutId).get(1));
        tv.setTextColor(getColor(R.color.black));
    }

    private void initVolumeMap(){
        volumeMap = new HashMap<>();
        volumeMap.put(R.id.volume_m_container, List.of(R.id.volume_m_cup, R.id.volume_m, R.drawable.cup_size_m_active, R.drawable.cup_size_m_disabled, 350));
        volumeMap.put(R.id.volume_s_container, List.of(R.id.volume_s_cup, R.id.volume_s, R.drawable.cup_size_s_active, R.drawable.cup_size_s_disabled, 250));
        volumeMap.put(R.id.volume_l_container, List.of(R.id.volume_l_cup, R.id.volume_l, R.drawable.cup_size_l_active, R.drawable.cup_size_l_disabled, 450));
    }

}
