package com.chujian.ups.mtatest.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MtaDao {

    @Transaction
    @Query("SELECT * FROM mtadata")
    List<MtaEntity> getDataList();

    @Transaction
    @Query("SELECT * FROM mtadata LIMIT 1")
    MtaEntity getFirstData();

    @Transaction
    @Query("SELECT * FROM mtadata ORDER BY _id DESC LIMIT 1")
    MtaEntity getLastData();

    @Transaction
    @Insert
    void insertData(MtaEntity... data);

    @Transaction
    @Delete
    void deleteData(MtaEntity... data);

    @Transaction
    @Update
    void updateData(MtaEntity... data);

}


