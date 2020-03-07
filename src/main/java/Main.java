import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static int dayOffSet = 0;

    public static void main(String[] args) {
        JsonManager jsonManager = new JsonManager();
        ToDoManager todo = new ToDoManager();
        var items = jsonManager.readAll(getFileName());

        if (items == null) {
            System.out.println("error");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;
        while (isContinue) {
            printOptions();
            try {
                int input = scanner.nextInt();

                switch (input) {
                    case 1:
                        break;

                    case 9:
                        return; // save all files
                        break;
                    default:
                        throw new Exception();
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
        System.out.println("2: List all items");
        System.out.println("3: List all items");
        System.out.println("4: List all items");
        System.out.println("9: Close the app.");
    }
}
