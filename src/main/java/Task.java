import java.time.LocalDate;

public class Task {
    private int id;
    private LocalDate date;
    private String content;
    private boolean isCompleted;

    public Task() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "AddTask{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
