import java.util.Scanner;

public class Hat extends Clothing implements Washable {
    private HatSize size;
    private Material material;

    public Hat(String name, double price, Color color, HatSize size, Material material) {
        super(name, price, color);
        this.size = size;
        this.material = material;
    }

    @Override
    public void putOn() {
        System.out.println("Надеваем шляпу на голову и поправляем поля");
    }

    public void tiltHat() {
        System.out.println("Наклоняем шляпу набок для стильного вида");
    }

    @Override
    public String getCareInstructions() {
        return "Чистить щеткой, хранить в специальной коробке, избегать влаги";
    }

    // Геттеры
    public HatSize getSize() {
        return size;
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Размер: %s (%d см), Материал: %s%n",
                size.getSizeName(), size.getCircumference(),
                material.getRussianName());
    }

    public static Hat createFromInput(Scanner scanner) {
        System.out.println("\n=== Создание новой шляпы ===");

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
                if (price > 20000) {
                    System.out.println("Цена слишком высокая. Максимум 20000 руб.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Неверный формат цены. Введите число.");
                scanner.nextLine();
            }
        }

        Color color = Color.selectColor(scanner);
        HatSize size = HatSize.selectHatSize(scanner);
        Material material = Material.selectMaterial(scanner);

        return new Hat(name, price, color, size, material);
    }


    public void setSize(HatSize size) {
        if (size == null) {
            throw new IllegalArgumentException("Размер шляпы не может быть null");
        }
        this.size = size;
    }

    public void setMaterial(Material material) {
        if (material == null) {
            throw new IllegalArgumentException("Материал не может быть null");
        }
        this.material = material;
    }
}