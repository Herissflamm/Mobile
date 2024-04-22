package fr.mds.helloworld.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import fr.mds.helloworld.data.dao.ChampionDao;
import fr.mds.helloworld.data.models.Champion;

@Database(entities = {Champion.class}, version = 11)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase sInstance = null;
    private static final String DATABASE_NAME = "MaDatabase.db";

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return sInstance;
    }

    public abstract ChampionDao getChampionDao();

}
