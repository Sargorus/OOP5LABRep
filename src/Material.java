import java.util.Scanner;

public enum Material {
    WOOL("Шерсть"),
    COTTON("Хлопок"),
    LEATHER("Кожа"),
    SYNTHETIC("Синтетика"),
    FELT("Фетр"),
    STRAW("Солома"),
    KNIT("Трикотаж"),
    DENIM("Джинсовая ткань"),
    SILK("Шелк");

    private final String russianName;

    Material(String russianName) {
        this.russianName = russianName;
    }

    public String getRussianName() {
        return russianName;
    }

    public static void displayMaterials() {
        System.out.println("\n=== Доступные материалы ===");
        for (int i = 0; i < values().length; i++) {
            System.out.println(i + ". " + values()[i].getRussianName());
        }
        System.out.println("===========================");
    }

    public static Material selectMaterial(Scanner scanner) {
        displayMaterials();
        System.out.print("Выберите материал (номер): ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 0 && index < values().length) {
            return values()[index];
        } else {
            System.out.println("Неверный выбор, установлен 'Хлопок' по умолчанию.");
            return Material.COTTON;
        }
    }
}
