import java.util.Scanner;

public enum Season {
    WINTER("Зима"),
    SPRING("Весна"),
    SUMMER("Лето"),
    AUTUMN("Осень"),
    DEMISEASON("Демисезон");

    private final String russianName;

    Season(String russianName) {
        this.russianName = russianName;
    }

    public String getRussianName() {
        return russianName;
    }

    public static void displaySeasons() {
        System.out.println("\n=== Доступные сезоны ===");
        for (int i = 0; i < values().length; i++) {
            System.out.println(i + ". " + values()[i].getRussianName());
        }
        System.out.println("========================");
    }

    public static Season selectSeason(Scanner scanner) {
        displaySeasons();
        System.out.print("Выберите сезон (номер): ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 0 && index < values().length) {
            return values()[index];
        } else {
            System.out.println("Неверный выбор, установлен 'Демисезон' по умолчанию.");
            return Season.DEMISEASON;
        }
    }
}