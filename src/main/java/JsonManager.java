import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;


public class JsonManager implements IFileCreate {
    // region Variables
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private JsonParser parser = new JsonParser();
    private Type listOfMyClassObject = new TypeToken<List<Task>>() {
    }.getType();

    private LocalDateTime localDateTimeNow;
    private String[] formattedDateTime;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:a");
    private final String FILES_PATH = "src/main/resources/";
    private final String JSON_EXTENSION = ".json";
    private List<Task> tasks;
    //endregion

    public JsonManager() {
        this.localDateTimeNow = LocalDateTime.now();
        this.formattedDateTime = dateTimeFormatter.format(localDateTimeNow).split(" ");
        this.loadToVar();
    }

    public String getFormattedDateTime() {
        return formattedDateTime[0] + JSON_EXTENSION;
    }

    public void writeToFiles(Task task) {

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(
                Paths.get(FILES_PATH + getFormattedDateTime()))) {

            if (this.tasks != null) {
                this.tasks.add(task);
                gson.toJson(this.tasks, bufferedWriter);
            } else {
                List<Task> newTask = Arrays.asList(task);
                gson.toJson(newTask, bufferedWriter);
            }
        } catch (IOException e) {
            e.getStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readAll(String name) {

        try {
            this.loadToVar();

            for (Task obj : this.tasks) {
                System.out.println(obj);
            }
        } catch (NullPointerException e) {
            System.out.println("File is empty.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadToVar() {
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(
                    Paths.get(FILES_PATH + getFormattedDateTime()));
            JsonElement rootElement = parser.parse(bufferedReader);
            this.tasks = gson.fromJson(rootElement, listOfMyClassObject);

        } catch (IOException e) {
            e.getStackTrace();
        }
    }
    @Override
    public boolean checkFileAlreadyCreated() throws IOException {
        File file = new File(FILES_PATH + getFormattedDateTime());

        if (file.createNewFile()) {
            System.out.println("creating file");
            return false;
        }
        return true;
    }
}