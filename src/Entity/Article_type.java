package Entity;

import java.io.Serializable;

/**
 * @author LongYi
 * @date 2018/10/26 21:00
 * @log: 1.
 */
public class Article_type implements Serializable {
    private String type;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
