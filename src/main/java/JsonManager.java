import com.google.gson.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

public class JsonManager {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private JsonParser parser = new JsonParser();

    public void writeToFiles(AddTask task, Path path) {
    // here not single process

        ArrayList<Object> arrayList = getListFromFile();
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            if (arrayList != null) {
                arrayList.add(task);
                gson.toJson(arrayList, bufferedWriter);

                System.out.println(arrayList);
            } else {

                arrayList = new ArrayList<>();
                arrayList.add(task);
                gson.toJson(arrayList, bufferedWriter);
            }

        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public void readAll(String name) {
    // duplicated
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("src/main/resources/" + name + ".json"))) {
            JsonElement rootElement = parser.parse(bufferedReader);
            ArrayList<Object> rootObject = gson.fromJson(rootElement, ArrayList.class);
            for (Object obj:rootObject) {
                System.out.println(obj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Object> getListFromFile() {
        // duplicated
        ArrayList<Object> arrayList = null;
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("src/main/resources/06-03-2020.json"));
            JsonElement rootElement = parser.parse(bufferedReader);
            arrayList = gson.fromJson(rootElement, ArrayList.class);
            System.out.println(arrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}