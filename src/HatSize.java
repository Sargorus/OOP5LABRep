import java.util.Scanner;

public enum HatSize {
    SIZE_S(54, "S"),
    SIZE_M(56, "M"),
    SIZE_L(58, "L"),
    SIZE_XL(60, "XL"),
    SIZE_XXL(62, "XXL");

    private final int circumference;
    private final String sizeName;

    HatSize(int circumference, String sizeName) {
        this.circumference = circumference;
        this.sizeName = sizeName;
    }

    public int getCircumference() {
        return circumference;
    }

    public String getSizeName() {
        return sizeName;
    }

    public static void displayHatSizes() {
        System.out.println("\n=== Размеры шляп ===");
        for (int i = 0; i < values().length; i++) {
            System.out.println(i + ". Размер " + values()[i].getSizeName() +
                    " (окружность " + values()[i].getCircumference() + " см)");
        }
        System.out.println("====================");
    }

    public static HatSize selectHatSize(Scanner scanner) {
        displayHatSizes();
        System.out.print("Выберите размер шляпы (номер): ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 0 && index < values().length) {
            return values()[index];
        } else {
            System.out.println("Неверный выбор, установлен размер M по умолчанию.");
            return HatSize.SIZE_M;
        }
    }
}