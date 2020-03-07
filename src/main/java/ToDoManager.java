import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

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
        var item = items.stream().filter(a -> a.getId() == id).findFirst();
        int count = items.size();

        if (item == null){
            return false;
        }

        items.remove(item);

        return items.size() < count;
    }

    public int getLastId() {
        int id = 1;
        if (items.size() > 0) {
            Collections.sort(items, (a, b) -> a.getId() - b.getId());
            id = (items.get(0).getId() + 1);
        }

        return id;
    }
}