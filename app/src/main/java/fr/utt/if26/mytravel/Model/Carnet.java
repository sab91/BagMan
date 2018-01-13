package fr.utt.if26.mytravel.Model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by paf on 08/11/17.
 */

public class Carnet extends BaseModel {
    private String name;
    private String email_account;
    private ArrayList<Page> pages;

    public Carnet(String nom, String email) {
        super();
        name = nom;
        email_account = email;
        pages = new ArrayList<Page>();
    }


    public Carnet(int id, String name, String email, long created_at, long updated_at) {
        super(created_at, updated_at);
        setId(id);
        setName(name);
        setEmail_account(email);

        pages = new ArrayList<Page>();
    }

    public Carnet(int id, String name, String email, long created_at, long updated_at,
                  ArrayList<Page>
            pages_pf) {
        super(created_at, updated_at);
        setId(id);
        setName(name);
        setEmail_account(email);
        setPages(pages_pf);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        String sb = "Carnet : " + getId() +
                "\n- Nom : " + getName() +
                "\n- Email : " + getEmail_account() +
                "\n - CreatedAt : " + getCreatedAt() +
                "\n - UpdatedAt : " + getUpdatedAt() +
                "\n - Number of pages : " + getPages().size();

        return sb;
    }

    public String toStringWithPages() {
        String sb = "Carnet : " + getId() +
                "\n- Nom : " + getName() +
                "\n- Email : " + getEmail_account() +
                "\n - CreatedAt : " + getCreatedAt() +
                "\n - UpdatedAt : " + getUpdatedAt() +
                "\n";

        Iterator<Page> it = getPages().iterator();
        while(it.hasNext()) {
            Page p = it.next();
            sb += "     - " + p.toString() + "\n        ";
        }

        return sb;
    }

    public String getEmail_account() {
        return email_account;
    }

    public void setEmail_account(String email_account) {
        this.email_account = email_account;
    }

    public ArrayList<Page> getPages() {
        return pages;
    }

    public void setPages(ArrayList<Page> pages) {
        this.pages = pages;
    }
}