package fr.mds.helloworld.data.ui.champion;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.mds.helloworld.data.dao.ChampionDao;
import fr.mds.helloworld.data.database.AppDatabase;
import fr.mds.helloworld.data.models.Champion;
import fr.mds.helloworld.utils.AsyncRunner;

public class ChampionViewModel extends AndroidViewModel {
    private ChampionDao mDao;

    public void setDao(ChampionDao dao) {
        mDao = dao;
    }

    public LiveData<List<Champion>> getAll() {
        return mDao.getAllChampions();
    }

    public LiveData<Champion> getById(int id) {
        return mDao.getChampion(id);
    }

    public ChampionViewModel(@NonNull Application application) {
        super(application);
    }

    public void createChampion(String name, String role) {
        AsyncRunner runner = new AsyncRunner();
        runner.runTask(() -> {
            mDao.insertChampion(new Champion(name, role));
        });
    }

    public void updateChampionTask(Champion task) {
        AsyncRunner runner = new AsyncRunner();
        runner.runTask(() -> {
            mDao.updateChampion(task);
        });
    }

    public void deleteChampionTask(Champion task) {
        AsyncRunner runner = new AsyncRunner();
        runner.runTask(() -> {
            mDao.deleteChampion(task);
        });
    }

    public LiveData<List<Champion>> getAllChampions() {
        return mDao.getAllChampions();
    }
}
