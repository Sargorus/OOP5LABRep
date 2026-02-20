import java.util.Scanner;

public enum SoleType {
    RUBBER("Резиновая"),
    LEATHER("Кожаная"),
    POLYURETHANE("Полиуретановая"),
    TPR("TPR"),
    EVA("EVA"),
    CREPE("Креповая");

    private final String russianName;

    SoleType(String russianName) {
        this.russianName = russianName;
    }

    public String getRussianName() {
        return russianName;
    }

    public static void displaySoleTypes() {
        System.out.println("\n=== Типы подошвы ===");
        for (int i = 0; i < values().length; i++) {
            System.out.println(i + ". " + values()[i].getRussianName());
        }
        System.out.println("====================");
    }

    public static SoleType selectSoleType(Scanner scanner) {
        displaySoleTypes();
        System.out.print("Выберите тип подошвы (номер): ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 0 && index < values().length) {
            return values()[index];
        } else {
            System.out.println("Неверный выбор, установлен 'Резиновая' по умолчанию.");
            return SoleType.RUBBER;
        }
    }
}