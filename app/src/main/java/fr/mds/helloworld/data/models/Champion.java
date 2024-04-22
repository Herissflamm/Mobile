package fr.mds.helloworld.data.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "champions")
public class Champion {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "lane")
    private String mLane;

    public Champion(final String name, final String lane) {
        mName = name;
        mLane = lane;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return mName;
    }

    public String getLane() {
        return mLane;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setLane(String mLane) {
        this.mLane = mLane;
    }




}
