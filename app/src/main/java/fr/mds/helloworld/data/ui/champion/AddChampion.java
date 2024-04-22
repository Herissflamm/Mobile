package fr.mds.helloworld.data.ui.champion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

import fr.mds.helloworld.MainActivity;
import fr.mds.helloworld.R;
import fr.mds.helloworld.data.database.AppDatabase;
import fr.mds.helloworld.data.models.Champion;
import fr.mds.helloworld.utils.AsyncRunner;

public class AddChampion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_champion);
        EditText name = (EditText) findViewById(R.id.name_champion);
        EditText role = (EditText) findViewById(R.id.lane_champion);
        Button submit = (Button) findViewById(R.id.submit_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncRunner runner = new AsyncRunner();
                runner.runTask(() -> {
                    AppDatabase.getInstance(getApplicationContext()).getChampionDao().insertChampion(new Champion(name.getText().toString(), role.getText().toString()));
                });
                Intent intent = new Intent(AddChampion.this, ChampionActivity.class);
                startActivity(intent);
            }
        });
    }
}