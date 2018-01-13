package fr.utt.if26.mytravel.Model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by paf on 08/11/17.
 */

public class Carnet extends BaseModel {
    private String name;
    private ArrayList<Page> pages;

    public Carnet(String nom) {
        super();
        name = nom;
        pages = new ArrayList<Page>();
    }

    public Carnet(int id, String name, long created_at, long updated_at) {
        super(created_at, updated_at);
        setId(id);
        setName(name);
        pages = new ArrayList<Page>();
    }

    public Carnet(int id, String name, long created_at, long updated_at, ArrayList<Page> pages_pf) {
        super(created_at, updated_at);
        setId(id);
        setName(name);
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
                "\n - CreatedAt : " + getCreatedAt() +
                "\n - UpdatedAt : " + getUpdatedAt() +
                "\n - Number of pages : " + getPages().size();

        return sb;
    }

    public String toStringWithPages() {
        String sb = "Carnet : " + getId() +
                "\n- Nom : " + getName() +
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

    public ArrayList<Page> getPages() {
        return pages;
    }

    public void setPages(ArrayList<Page> pages) {
        this.pages = pages;
    }
}