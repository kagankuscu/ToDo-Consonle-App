import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ToDoManager {
    private List<Task> items;

    public ToDoManager(List<Task> _items) {
        this.items = _items;
    }

    public boolean add(String content, int dayOffSet) {
        Task task = new Task();

        try {
            if (content.isEmpty()) {
                return false;
            }

            task.setContent(content);
            LocalDate currentDate = LocalDate.now();
            currentDate = currentDate.plusDays(dayOffSet);
            task.setDate(currentDate);
            task.setId(getLastId());

            items.add(task);

            return true;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {

        items = (List<Task>) items.stream()
                .filter(a -> a.getId() != id)
                .map(x -> (Task) x)
                .collect(toList());;

        return true;
    }

    public List<String> getAsString() {
        List<String> result = new ArrayList<>();

        for (Task t: items) {
            result.add(t.toString());
        }

        return result;
    }

    public List<Task> get() {
        return items;
    }

    public int getLastId() {
        int id = 1;
        if (items.size() > 0) {
            Collections.sort(items, (a, b) -> b.getId() - a.getId());
            id = (items.get(0).getId() + 1);
        }

        return id;
    }
}