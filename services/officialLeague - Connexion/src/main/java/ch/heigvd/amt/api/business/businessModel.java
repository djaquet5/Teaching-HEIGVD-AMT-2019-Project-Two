package ch.heigvd.amt.api.business;

@Getter
public class businessModel {

    private String email;
    private int ID

    public AuthInfo(int ID,String email) {
        this.ID = ID;
        this.email = email;
    }

}