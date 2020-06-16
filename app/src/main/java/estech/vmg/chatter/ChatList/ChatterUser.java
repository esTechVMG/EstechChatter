package estech.vmg.chatter.ChatList;

import android.os.Parcel;
import android.os.Parcelable;

public class ChatterUser implements Parcelable {
    private String id,token,username,email;
    public ChatterUser(String id, String token, String username, String email){
        this.username=username;
        this.email=email;
        this.id=id;
        this.token=token;
    }
    public ChatterUser(){//Dummy class for testing
        username="Test user";
        email="email@example.com";
        id="TestId1234567890";
        token="TestToken2749843648";
    }

    protected ChatterUser(Parcel in) {
        id = in.readString();
        token = in.readString();
        username = in.readString();
        email = in.readString();
    }

    public static final Creator<ChatterUser> CREATOR = new Creator<ChatterUser>() {
        @Override
        public ChatterUser createFromParcel(Parcel in) {
            return new ChatterUser(in);
        }

        @Override
        public ChatterUser[] newArray(int size) {
            return new ChatterUser[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(token);
        dest.writeString(username);
        dest.writeString(email);
    }
}
