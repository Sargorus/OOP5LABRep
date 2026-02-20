

import java.util.ArrayList;
import java.util.List;

public class Atelier {
    private List<Clothing> clothingItems;
    private double totalEarnings;

    public Atelier() {
        clothingItems = new ArrayList<>();
        totalEarnings = 0.0;
    }


    public void sewClothing(Clothing clothing) {
        clothingItems.add(clothing);
        System.out.println("Сшита новая вещь: " + clothing.getName());
    }


    public boolean sellClothingByIndex(int index) {
        try {
            if (index < 0 || index >= clothingItems.size()) {
                System.out.println("Неверный индекс. Доступные индексы: 1-" + clothingItems.size());
                return false;
            }

            Clothing sold = clothingItems.remove(index);
            totalEarnings += sold.getPrice();
            System.out.println("Продана вещь: " + sold.getName() +
                    " за " + sold.getPrice() + " руб.");
            System.out.println("Общий заработок: " + totalEarnings + " руб.");
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка при продаже: " + e.getMessage());
            return false;
        }
    }

    // Поиск по любому критерию
    public List<Clothing> searchClothing(String searchType, Object value) {
        List<Clothing> results = new ArrayList<>();

        try {
            for (Clothing item : clothingItems) {
                switch (searchType.toLowerCase()) {
                    case "name":
                        if (item.getName().equalsIgnoreCase((String) value)) {
                            results.add(item);
                        }
                        break;
                    case "color":
                        if (item.getColor() == value) {
                            results.add(item);
                        }
                        break;
                    case "price":
                        if (item.getPrice() <= (Double) value) {
                            results.add(item);
                        }
                        break;
                    case "type":
                        if (item.getClass().getSimpleName().equalsIgnoreCase((String) value)) {
                            results.add(item);
                        }
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка при поиске: " + e.getMessage());
        }

        return results;
    }

    public void displayAllClothing() {
        if (clothingItems.isEmpty()) {
            System.out.println("В ателье пока нет вещей.");
            return;
        }

        System.out.println("\n=== Вещи в ателье (" + clothingItems.size() + " шт.) ===");
        for (int i = 0; i < clothingItems.size(); i++) {
            System.out.print((i + 1) + ". ");
            clothingItems.get(i).displayInfo();
            System.out.println("   Тип: " + clothingItems.get(i).getClass().getSimpleName());
        }
        System.out.println("=================================");
    }

    public Clothing getClothingByIndex(int index) {
        try {
            if (index >= 0 && index < clothingItems.size()) {
                return clothingItems.get(index);
            }
        } catch (Exception e) {
            System.out.println("Ошибка при получении вещи: " + e.getMessage());
        }
        return null;
    }

    public List<Clothing> getAllClothing() {
        return new ArrayList<>(clothingItems);
    }

    // Геттеры
    public int getClothingCount() {
        return clothingItems.size();
    }

    public double getTotalEarnings() {
        return totalEarnings;
    }

    // Сеттер для заработка (для загрузки из файла)
    public void setTotalEarnings(double totalEarnings) {
        if (totalEarnings < 0) {
            throw new IllegalArgumentException("Заработок не может быть отрицательным");
        }
        this.totalEarnings = totalEarnings;
    }
}