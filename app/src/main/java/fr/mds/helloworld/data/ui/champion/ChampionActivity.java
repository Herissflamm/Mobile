package fr.mds.helloworld.data.ui.champion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import fr.mds.helloworld.MainActivity;
import fr.mds.helloworld.R;
import fr.mds.helloworld.data.database.AppDatabase;

public class ChampionActivity extends AppCompatActivity {

    private ChampionViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion);

        mViewModel = new ViewModelProvider(this).get(ChampionViewModel.class);
        mViewModel.setDao(AppDatabase.getInstance(getApplicationContext()).getChampionDao());

        final RecyclerView recyclerView = findViewById(R.id.champion_rv);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        mViewModel.getAllChampions().observe(this, championElements -> {
            ChampionListAdapter adapter = new ChampionListAdapter(championElements, getSupportFragmentManager());
            recyclerView.setAdapter(adapter);
        });

        Button addButton = findViewById(R.id.liste_to_create);
        addButton.setOnClickListener((view) -> {
            AddChampionDialogFragment.newInstance().show(getSupportFragmentManager(), "dialog");
        });

        Button addButton2 = findViewById(R.id.liste_to_champion);
        addButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChampionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
