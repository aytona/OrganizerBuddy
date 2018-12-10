package ca.organizerbuddy.aytona_fahmy_project.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ca.organizerbuddy.aytona_fahmy_project.model.Account;

@Database(entities = {Account.class}, version = 1, exportSchema = false)
public abstract class AccountRoomDb extends RoomDatabase {
    private static AccountRoomDb INSTANCE;

    public abstract AccountDao accountDao();

    public static AccountRoomDb getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context,AccountRoomDb.class,"employee_db").build();
        }
        return INSTANCE;
    }

}