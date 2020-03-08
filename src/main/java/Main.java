import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int dayOffSet = 0;

    public static void main(String[] args) {
        JsonManager jsonManager = new JsonManager();
        ToDoManager todo = loadData(jsonManager);
        if (todo == null) {
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;
        while (isContinue) {
            printOptions();
            try {
                String inputStr = scanner.nextLine();
                int input = Integer.parseInt(inputStr);

                switch (input) {
                    case 1:
                        List<String> items = todo.getAsString();
                        for (String s : items) {
                            System.out.println(s);
                        }
                        break;
                    case 2:
                        System.out.println("Enter the content.");
                        String content = scanner.nextLine();
                        if (todo.add(content, dayOffSet))
                        break;
                    case 3:
                        String idStr = scanner.nextLine();
                        int id = Integer.parseInt(idStr);

                        if (todo.delete(id)) {
                            System.out.printf("%d is deleted\n", id);
                        } else {
                            System.out.println("Record not deleted. Something happened.:D");
                        }
                        break;
                    case 4:
                        dayOffSet -= 1;
                        loadData(jsonManager);
                        break;
                    case 5:
                        dayOffSet += 1;
                        loadData(jsonManager);
                        break;
                    case 9:
                        List<Task> itemsToSave = todo.get();
                        jsonManager.writeToFile(itemsToSave, getFileName());
                        isContinue = false;
                        break;
                    default:
                        System.out.println("invalid input.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("invalid input.");
            }
        }
    }

    private static String getFileName() {
        LocalDateTime currentDate = LocalDateTime.now();
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        currentDate = currentDate.plusDays(dayOffSet);
        return dateTimeFormatter.format(currentDate);
    }

    private static void printOptions() {
        System.out.println("1: List all items");
        System.out.println("2: Add new item");
        System.out.println("3: Delete item");
        System.out.println("4: Switch to previous day");
        System.out.println("5: Switch to next day");
        System.out.println("9: Close the app.");
    }

    private static ToDoManager loadData(JsonManager jsonManager) {
        var items = jsonManager.readAll(getFileName());

        if (items == null) {
            System.out.println("items is null.");
            return null;
        }
        return new ToDoManager(items);
    }
}
