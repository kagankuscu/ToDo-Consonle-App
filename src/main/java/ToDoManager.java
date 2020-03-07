import java.io.IOException;

public class ToDoManager extends JsonManager{

    public ToDoManager() {}

    public void loadAllItem() {

    }

    public void addNewItem(int id,String content) {
        Task task = new Task();

        try {

            if (checkFileAlreadyCreated()) {
                task.setId(id);
                task.setContent(content);
                task.setCompleted(false);

                writeToFiles(task);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}