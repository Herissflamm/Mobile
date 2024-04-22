package fr.mds.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import fr.mds.helloworld.data.database.AppDatabase;
import fr.mds.helloworld.data.models.Champion;
import fr.mds.helloworld.data.ui.champion.ChampionActivity;
import fr.mds.helloworld.data.ui.champion.ChampionViewModel;
import fr.mds.helloworld.utils.DatabaseInitializer;

public class MainActivity extends AppCompatActivity {
    private ChampionViewModel mViewModel;

    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseInitializer.populateDatabaseAsync(AppDatabase.getInstance(getApplicationContext()));

        btnNext = findViewById(R.id.champion_to_liste);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChampionActivity.class);
                startActivity(intent);
            }
        });
        mViewModel = new ViewModelProvider(this).get(ChampionViewModel.class);
        mViewModel.setDao(AppDatabase.getInstance(getApplicationContext()).getChampionDao());
        listChampions();
    }

    public void listChampions() {
        LiveData<List<Champion>> champions = mViewModel.getAllChampions();
        champions.observe(this, champions1 -> {
            ((TextView)findViewById(R.id.tv)).setText("");
            for (Champion champion : champions1) {
                CharSequence oldText = ((TextView)findViewById(R.id.tv)).getText() + " Champion : " + champion.getName() + " Role : " + champion.getLane() + "\n";
                ((TextView)findViewById(R.id.tv)).setText(oldText);
            }
        });
    }
}