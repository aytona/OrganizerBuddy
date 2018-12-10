package ca.organizerbuddy.aytona_fahmy_project.db;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

public abstract class AccountRoomDb extends RoomDatabase {
    private static AccountRoomDb INSTANCE;

    public abstract AccountDao empoyeeDao();

    public static AccountRoomDb getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context,AccountRoomDb.class,"employee_db").build();
        }
        return INSTANCE;
    }

}