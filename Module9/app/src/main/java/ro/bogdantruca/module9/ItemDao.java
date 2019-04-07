package ro.bogdantruca.module9;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM item")
    List<Item> getAllItems();

    @Insert
    void insertItem(Item item);

    @Query("DELETE FROM item")
    void deleteAll();
}
