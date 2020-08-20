package com.chujian.ups.mtatest.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

// UPS Log 数据表
@Entity(tableName = "mtadata")
public class MtaEntity {

    //ID
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    public int _id;

    // category
    @ColumnInfo(name = "category")
    public String category;

    //uuid
    @ColumnInfo(name = "uuid")
    public String uuid;

    // data
    @ColumnInfo(name = "data")
    public String data;

    // data
    @ColumnInfo(name = "type")
    public String type;


    public int get_id() {
        return _id;
    }

    public void set_id( int _id) {
        this._id = _id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toJson() {
        return data;
    }

    @Override
    public String toString() {
        return "category:" + category + "; type:" + type + "\ndata:" + data;
    }
}
