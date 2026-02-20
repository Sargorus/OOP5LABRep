import java.util.Scanner;

public class Boots extends Clothing implements Washable {
    private ShoeSize size;
    private SoleType soleType;

    public Boots(String name, double price, Color color, ShoeSize size, SoleType soleType) {
        super(name, price, color);
        this.size = size;
        this.soleType = soleType;
    }

    @Override
    public void putOn() {
        System.out.println("Надеваем ботинки на ноги и зашнуровываем");
    }

    public void tieLaces() {
        System.out.println("Завязываем шнурки на ботинках");
    }

    @Override
    public String getCareInstructions() {
        return "Чистить специальным кремом для обуви, сушить при комнатной температуре";
    }

    // Геттеры
    public ShoeSize getSize() {
        return size;
    }

    public SoleType getSoleType() {
        return soleType;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Размер: %d, Подошва: %s%n",
                size.getSize(), soleType.getRussianName());
    }

    public static Boots createFromInput(Scanner scanner) {
        System.out.println("\n=== Создание новых ботинок ===");

        System.out.print("Введите название: ");
        String name = scanner.nextLine();

        double price = 0;
        while (true) {
            try {
                System.out.print("Введите цену (руб.): ");
                price = scanner.nextDouble();
                scanner.nextLine();

                if (price <= 0) {
                    System.out.println("Цена должна быть положительной. Попробуйте снова.");
                    continue;
                }
                if (price > 50000) {
                    System.out.println("Цена слишком высокая. Максимум 50000 руб.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Неверный формат цены. Введите число.");
                scanner.nextLine();
            }
        }

        Color color = Color.selectColor(scanner);
        ShoeSize size = ShoeSize.selectSize(scanner);
        SoleType soleType = SoleType.selectSoleType(scanner);

        return new Boots(name, price, color, size, soleType);
    }



    public void setSize(ShoeSize size) {
        if (size == null) {
            throw new IllegalArgumentException("Размер не может быть null");
        }
        this.size = size;
    }

    public void setSoleType(SoleType soleType) {
        if (soleType == null) {
            throw new IllegalArgumentException("Тип подошвы не может быть null");
        }
        this.soleType = soleType;
    }
}