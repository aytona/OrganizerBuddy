package ca.organizerbuddy.aytona_fahmy_project.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ca.organizerbuddy.aytona_fahmy_project.model.Account;

@Dao
public interface AccountDao {
    @Insert
    long insert(Account account);

    @Query("SELECT * FROM account_table")
    List<Account> getAllAccounts();

    @Delete
    int delete(Account account);
}
