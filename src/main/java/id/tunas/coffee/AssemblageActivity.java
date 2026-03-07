package id.tunas.coffee;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
                    this.baristaId = result.getData().getStringExtra("barista_id");
                    this.baristaName = result.getData().getStringExtra("barista_name");
                    TextView selectBaristaTitle = findViewById(R.id.select_barista_title);
                    StringBuilder sb = new StringBuilder(getString(R.string.select_barista));
                    sb.append(" - ").append(this.baristaName);
                    selectBaristaTitle.setText(sb.toString());
                }
        );

        Intent intent = new Intent(this, SelectBaristaActivity.class);
        selectBaristaLauncher.launch(intent);
    }
}
