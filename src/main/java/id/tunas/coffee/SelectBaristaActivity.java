package id.tunas.coffee;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import id.tunas.coffee.adapter.BaristaListAdapter;
import id.tunas.coffee.dto.Barista;

public class SelectBaristaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_barista);

        findViewById(R.id.arrow_left).setOnClickListener(view -> {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        });
        RecyclerView baristaRc = findViewById(R.id.barista_rc);
        BaristaListAdapter baristaListAdapter = new BaristaListAdapter(Barista.createDummy(), barista -> {
            Intent intent = new Intent();
            intent.putExtra("barista_id", barista.getId());
            intent.putExtra("barista_name", barista.getName());
            setResult(RESULT_OK, intent);
            finish();
        });
        baristaRc.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this));
        baristaRc.setAdapter(baristaListAdapter);
    }
}
