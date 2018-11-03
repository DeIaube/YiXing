package arouter.dawn.zju.edu.lib_db.bean;

import io.realm.RealmObject;

public class Notice extends RealmObject {

    private String title;
    private String content;
    private String time;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public Notice(String title, String content, String time) {
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public Notice() {

    }
}
