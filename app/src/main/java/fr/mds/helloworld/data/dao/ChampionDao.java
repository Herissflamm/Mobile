package fr.mds.helloworld.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.mds.helloworld.data.models.Champion;

@Dao
public interface ChampionDao {
    @Insert
    void insertChampion(Champion... champions);

    @Update
    void updateChampion(Champion champion);

    @Delete
    void deleteChampion(Champion champion);

    @Query("SELECT * FROM `champions` WHERE `champions`.`id` = :id")
    LiveData<Champion> getChampion(int id);

    @Query("SELECT * FROM `champions` ORDER BY `champions`.`name`")
    LiveData<List<Champion>> getAllChampions();

    @Query("SELECT Count(*) FROM `champions` ORDER BY `champions`.`name`")
    int getNumberChampions();

}
