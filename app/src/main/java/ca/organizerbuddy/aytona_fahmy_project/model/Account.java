package ca.organizerbuddy.aytona_fahmy_project.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "account_table")
public class Account implements Parcelable {
    private String m_username;
    private String m_password;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int m_id;

    protected Account(Parcel in) {
        m_username = in.readString();
        m_password = in.readString();
        m_id = in.readInt();
    }

    @Ignore
    public Account(String name, String pass) {
        m_username = name;
        m_password = pass;
    }

    public Account(String name, String pass, int id) {
        m_username = name;
        m_password = pass;
        m_id = id;
    }

    public String getUsername() {
        return m_username;
    }

    public String getPassword() {
        return m_password;
    }

    public int getId() {
        return m_id;
    }

    public void setUsername(String name) {
        m_username = name;
    }

    public void setPassword(String pass) {
        m_password = pass;
    }

    public void setId(int id) {
        m_id = id;
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
        dest.writeString(m_username);
        dest.writeString(m_password);
        dest.writeInt(m_id);
    }
}
