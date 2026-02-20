import java.util.Scanner;

public class Coat extends Clothing implements Washable {
    private Season season;
    private boolean hasHood;

    public Coat(String name, double price, Color color, Season season, boolean hasHood) {
        super(name, price, color);
        this.season = season;
        this.hasHood = hasHood;
    }

    @Override
    public void putOn() {
        System.out.println("Надеваем пальто на тело и застегиваем");
    }

    public void fastenButtons() {
        System.out.println("Застегиваем все пуговицы на пальто");
    }

    @Override
    public String getCareInstructions() {
        return "Стирать при 30°C, не отбеливать, гладить при средней температуре, с паром.";
    }

    // Геттеры
    public Season getSeason() {
        return season;
    }

    public boolean hasHood() {
        return hasHood;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Сезон: %s, Капюшон: %s%n",
                season.getRussianName(), hasHood ? "есть" : "нет");
    }

    public static Coat createFromInput(Scanner scanner) {
        System.out.println("\n=== Создание нового пальто ===");

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
                if (price > 100000) {
                    System.out.println("Цена слишком высокая. Максимум 100000 руб.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Неверный формат цены. Введите число.");
                scanner.nextLine();
            }
        }

        Color color = Color.selectColor(scanner);
        Season season = Season.selectSeason(scanner);

        boolean hasHood = false;
        while (true) {
            try {
                System.out.print("Есть ли капюшон? (да/нет): ");
                String hoodInput = scanner.nextLine().toLowerCase();

                if (hoodInput.equals("да")) {
                    hasHood = true;
                    break;
                } else if (hoodInput.equals("нет")) {
                    hasHood = false;
                    break;
                } else {
                    System.out.println("Пожалуйста, введите 'да' или 'нет'.");
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода.");
            }
        }

        return new Coat(name, price, color, season, hasHood);
    }

    public void setSeason(Season season) {
        if (season == null) {
            throw new IllegalArgumentException("Сезон не может быть null");
        }
        this.season = season;
    }

    public void setHasHood(boolean hasHood) {
        this.hasHood = hasHood;
    }
}