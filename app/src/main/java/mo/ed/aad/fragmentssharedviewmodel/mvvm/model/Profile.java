package mo.ed.aad.fragmentssharedviewmodel.mvvm.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Profiles")
public class Profile implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ProfileID")
    public int ProfileID;

    @NonNull
    @ColumnInfo(name = "UserName")
    public String UserName;

    @NonNull
    @ColumnInfo(name = "JobTitle")
    public String JobTitle;

    @NonNull
    @ColumnInfo(name = "Time")
    public String Time;

    @NonNull
    @ColumnInfo(name = "Distance")
    public String Distance;

    @NonNull
    @ColumnInfo(name = "Address")
    public String Address;



    public Profile() {
    }

    @NonNull
    public String getAddress() {
        return Address;
    }

    public void setAddress(@NonNull String address) {
        Address = address;
    }

    @Ignore
    public Profile(String userName, String jobTitle, String address, String distance, String time) {
        UserName=userName;
        JobTitle=jobTitle;
        Address=address;
        Distance=distance;
        Time=time;
    }

    public int getProfileID() {
        return ProfileID;
    }

    public void setProfileID(int profileID) {
        ProfileID = profileID;
    }

    @NonNull
    public String getUserName() {
        return UserName;
    }

    public void setUserName(@NonNull String userName) {
        UserName = userName;
    }

    @NonNull
    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(@NonNull String jobTitle) {
        JobTitle = jobTitle;
    }

    @NonNull
    public String getTime() {
        return Time;
    }

    public void setTime(@NonNull String time) {
        Time = time;
    }

    @NonNull
    public String getDistance() {
        return Distance;
    }

    public void setDistance(@NonNull String distance) {
        Distance = distance;
    }
}