import java.util.Scanner;

public enum Color {
    RED("Красный"),
    BLUE("Синий"),
    BLACK("Черный"),
    WHITE("Белый"),
    GREEN("Зеленый"),
    BROWN("Коричневый"),
    GRAY("Серый"),
    BEIGE("Бежевый"),
    MULTICOLOR("Разноцветный");

    private final String russianName;

    Color(String russianName) {
        this.russianName = russianName;
    }

    public String getRussianName() {
        return russianName;
    }

    public static void displayColors() {
        System.out.println("\n=== Доступные цвета ===");
        for (int i = 0; i < values().length; i++) {
            System.out.println(i + ". " + values()[i].getRussianName());
        }
        System.out.println("=======================");
    }

    public static Color selectColor(Scanner scanner) {
        displayColors();
        System.out.print("Выберите цвет (номер): ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 0 && index < values().length) {
            return values()[index];
        } else {
            System.out.println("Неверный выбор, установлен черный цвет по умолчанию.");
            return Color.BLACK;
        }
    }
}