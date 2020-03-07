import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class JsonManager implements IFileCreate {
    // region Variables
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private final String FILES_PATH = "src/main/resources/";
    private final String JSON_EXTENSION = ".json";
    //endregion

    public JsonManager() {
    }

    public void writeToFile(List<Task> tasks, String fileName) {

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(
                Paths.get(FILES_PATH + fileName + JSON_EXTENSION))) {

            // clean file
            bufferedWriter.write("");

            // write all list
            gson.toJson(tasks, bufferedWriter);
        } catch (IOException e) {
            e.getStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Task> readAll(String fileName) {
        try {
            checkFileAlreadyCreated(fileName);

            Type listOfMyClassObject = new TypeToken<List<Task>>() {
            }.getType();
            BufferedReader bufferedReader = Files.newBufferedReader(
                    Paths.get(FILES_PATH + fileName + JSON_EXTENSION));
            JsonElement rootElement = new JsonParser().parse(bufferedReader);
            List<Task> result = gson.fromJson(rootElement, listOfMyClassObject);

            return result;
        } catch (NullPointerException e) {
            List<Task> items = Collections.emptyList();
            return items;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean checkFileAlreadyCreated(String fileName) throws IOException {
        File file = new File(FILES_PATH + fileName + JSON_EXTENSION);

        if (file.createNewFile()) {
            return false;
        }
        return true;
    }
}