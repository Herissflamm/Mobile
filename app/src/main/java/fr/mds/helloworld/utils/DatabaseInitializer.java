package fr.mds.helloworld.utils;

import androidx.annotation.NonNull;

import fr.mds.helloworld.data.database.AppDatabase;
import fr.mds.helloworld.data.models.Champion;

public class DatabaseInitializer {
    private static void populateWithChampions(@NonNull AppDatabase database) {

        database.getChampionDao().insertChampion(
                new Champion("Aatrox", "TOP"),
                new Champion("Lucian", "ADC"),
                new Champion("Orianna", "MID")
        );
    }

    private static void populateDatabaseSync(@NonNull AppDatabase database) {
        if(database.getChampionDao().getNumberChampions() == 0) {
            populateWithChampions(database);
        }
    }

    public static void populateDatabaseAsync(@NonNull AppDatabase database) {
        AsyncRunner runner = new AsyncRunner();
        runner.runTask(() -> {
            populateDatabaseSync(database);
        });
    }
}
