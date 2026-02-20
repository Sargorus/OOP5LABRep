import java.util.Scanner;

public enum ShoeSize {
    SIZE_38(38),
    SIZE_39(39),
    SIZE_40(40),
    SIZE_41(41),
    SIZE_42(42),
    SIZE_43(43),
    SIZE_44(44),
    SIZE_45(45),
    SIZE_46(46);

    private final int size;

    ShoeSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public static void displaySizes() {
        System.out.println("\n=== Доступные размеры обуви ===");
        for (int i = 0; i < values().length; i++) {
            System.out.println(i + ". " + values()[i].getSize());
        }
        System.out.println("=============================");
    }

    public static ShoeSize selectSize(Scanner scanner) {
        displaySizes();
        System.out.print("Выберите размер (номер): ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 0 && index < values().length) {
            return values()[index];
        } else {
            System.out.println("Неверный выбор, установлен 42 размер по умолчанию.");
            return ShoeSize.SIZE_42;
        }
    }
}