package ca.organizerbuddy.aytona_fahmy_project.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "account_table")
public class Account implements Parcelable {
    private String username;
    private String password;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int id;

    protected Account(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
        this.id = in.readInt();
    }

    @Ignore
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(String username, String password, int id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public int getId() {
        return this.id;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeInt(this.id);
    }
}
