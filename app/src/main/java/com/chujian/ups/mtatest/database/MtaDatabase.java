package com.chujian.ups.mtatest.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;


@Database(entities = {MtaEntity.class}, version = 2, exportSchema = false)
public abstract class MtaDatabase extends RoomDatabase {
    public abstract MtaDao dataDao();

    private static MtaDatabase INSTANCE;

    // TODO 数据库升级操作 后续测试
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //此处对于数据库中的所有更新都需要写下面的代码
            database.execSQL("ALTER TABLE data "
                    + " ADD COLUMN type TEXT");
        }
    };

    public synchronized static MtaDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context,
                    MtaDatabase.class, "juhe-mta-data.db")
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return INSTANCE;
    }
}