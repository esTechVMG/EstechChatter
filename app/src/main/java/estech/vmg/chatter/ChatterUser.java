package estech.vmg.chatter;

public class ChatterUser {
    private String id,token;
    ChatterUser(String id, String token){
        this.id=id;
        this.token=token;
    }

    public String getId() {
        return id;
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
}