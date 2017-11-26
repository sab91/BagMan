package fr.utt.if26.mytravel.Model;


/**
 * Created by paf on 08/11/17.
 */


public class Page extends BaseModel {
    private String title;
    private String content;
    private String summary;

    public Page(int id_pf, String title_pf, String content_pf, String summary_pf) {
        super();
        setId(id_pf);
        title = title_pf;
        content = content_pf;
        summary = summary_pf;
    }

    public Page(String title_pf, String content_pf, String summary_pf) {
        super();
        title = title_pf;
        content = content_pf;
        summary = summary_pf;
    }

    public Page(int id_pf, String title_pf, String content_pf, String summary_pf, long createdAt_pf, long updatedAt_pf) {
        setId(id_pf);
        title = title_pf;
        content = content_pf;
        summary = summary_pf;
        setCreatedAt(createdAt_pf);
        setUpdatedAt(updatedAt_pf);
    }

    public String toString() {
        return "Page : "+ getId() +
                "\n- Titre : " + getTitle() +
                "\n- Contenu : " + getContent() +
                "\n- Extrait : " + getSummary() +
                "\n- CreatedAt : " + getCreatedAt() +
                "\n- UpdatedAt : " + getUpdatedAt() +
                "\n";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
