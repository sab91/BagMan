package fr.utt.if26.mytravel.Model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by sabri on 13/01/2018.
 */

public class Account extends BaseModel {
    private String email;
    private String password;

    public Account() {
        super();
        email = "aa";
        password = "pp";
    }

    public Account(String mail, String mdp) {
        super();
        email = mail;
        password = mdp;

    }

    public Account(int id, String mail, String mdp, long created_at, long updated_at) {
        super(created_at, updated_at);
        setId(id);
        setEmail(mail);
        setPassword(mdp);

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




    public String toString() {
        String sb = "- EmailId : " + getId() +
                "\n- Email : " + getEmail() +
                "\n- Password : " + getPassword() +
                "\n - CreatedAt : " + getCreatedAt() +
                "\n - UpdatedAt : " + getUpdatedAt();

        return sb;
    }


}
