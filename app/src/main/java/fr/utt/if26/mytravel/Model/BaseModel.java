package fr.utt.if26.mytravel.Model;

/**
 * Created by paf on 12/11/17.
 */

import java.util.Calendar;
import java.util.Date;

public abstract class BaseModel {
    private int id;
    private Date createdAt;
    private Date updatedAt;

    public BaseModel() {
        Date currentTime = Calendar.getInstance().getTime();
        createdAt = currentTime;
        updatedAt = currentTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
