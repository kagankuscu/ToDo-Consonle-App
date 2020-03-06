
import com.google.gson.JsonElement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ToDoManager extends JsonManager{
    private final String FILES_PATH = "src/main/resources/";
    private final Path path = Paths.get(FILES_PATH);
    private final String JSON_EXTENSION = ".json";
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:a");
    private LocalDateTime localDateTimeNow;
    private String[] formattedDateTime;
    private Map<String, Object> map = new HashMap<>();

    public ToDoManager() {
        this.localDateTimeNow = LocalDateTime.now();
        this.formattedDateTime = dateTimeFormatter.format(localDateTimeNow).split(" ");
    }

    public void loadAllItem() {

    }

    public void addNewItem(int id,String content) {
        AddTask addTask = new AddTask();

        try {
            checkFileAlreadyCreated();
        } catch (IOException e) {
            e.printStackTrace();
        }

        addTask.setId(id);
        addTask.setContent(content);
        addTask.setCompleted(false);

        writeToFiles(addTask, Paths.get(path + "/" + formattedDateTime[0] + JSON_EXTENSION));
    }



    private void checkFileAlreadyCreated() throws IOException {
        File file = new File(FILES_PATH + "/" + formattedDateTime[0] + JSON_EXTENSION);

        if (file.createNewFile()) {
            System.out.println("creating");
        }
    }
}