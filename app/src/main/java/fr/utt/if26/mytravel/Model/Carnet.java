package fr.utt.if26.mytravel.Model;

/**
 * Created by paf on 08/11/17.
 */

public class Carnet extends BaseModel {
    private String name;
    private Story[] stories;

    public Carnet(String nom) {
        super();
        name = nom;
    }

    public Carnet(int id, String name, long created_at, long updated_at) {
        super(created_at, updated_at);
        setId(id);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Story[] getStories() {
        return stories;
    }

    public void setStories(Story[] stories) {
        this.stories = stories;
    }

    public String toString() {
        return "Carnet : " + getId() +
                "\n- Nom : " + getName() +
                "\n - CreatedAt : " + getCreatedAt() +
                "\n - UpdatedAt : " + getUpdatedAt() +
                "\n";
    }
}
