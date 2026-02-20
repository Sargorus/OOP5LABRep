

import java.io.*;
import java.util.List;

public class FileManager {

    public static void saveAtelierToFile(Atelier atelier, String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {

            writer.println("EARNINGS:" + String.format("%.2f", atelier.getTotalEarnings()).replace(',', '.'));
            writer.println("# Формат: TYPE|NAME|PRICE|COLOR|ДОПОЛНИТЕЛЬНЫЕ_ПАРАМЕТРЫ");

            List<Clothing> items = atelier.getAllClothing();
            for (Clothing item : items) {
                String line;

                if (item instanceof Coat) {
                    Coat coat = (Coat) item;
                    line = String.format("COAT|%s|%s|%s|%s|%s",
                            coat.getName(),
                            String.format("%.2f", coat.getPrice()).replace(',', '.'),
                            coat.getColor().name(),
                            coat.getSeason().name(),
                            coat.hasHood());
                } else if (item instanceof Boots) {
                    Boots boots = (Boots) item;
                    line = String.format("BOOTS|%s|%s|%s|%s|%s",
                            boots.getName(),
                            String.format("%.2f", boots.getPrice()).replace(',', '.'),
                            boots.getColor().name(),
                            boots.getSize().name(),
                            boots.getSoleType().name());
                } else if (item instanceof Hat) {
                    Hat hat = (Hat) item;
                    line = String.format("HAT|%s|%s|%s|%s|%s",
                            hat.getName(),
                            String.format("%.2f", hat.getPrice()).replace(',', '.'),
                            hat.getColor().name(),
                            hat.getSize().name(),
                            hat.getMaterial().name());
                } else {
                    continue;
                }

                writer.println(line);
            }
        }
    }

    public static Atelier loadAtelierFromFile(String filename) throws IOException, IllegalArgumentException {
        Atelier atelier = new Atelier();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean earningsLoaded = false;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();


                if (line.isEmpty()) {
                    continue;
                }


                if (line.startsWith("#")) {
                    continue;
                }


                if (!earningsLoaded && line.startsWith("EARNINGS:")) {
                    try {
                        String earningsStr = line.substring("EARNINGS:".length()).trim();
                        double earnings = Double.parseDouble(earningsStr);
                        if (earnings < 0) {
                            throw new IllegalArgumentException("Заработок не может быть отрицательным (строка " + lineNumber + ")");
                        }
                        atelier.setTotalEarnings(earnings);
                        earningsLoaded = true;
                        continue;
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Неверный формат заработка (строка " + lineNumber + "): " + line);
                    }
                }

                String[] parts = line.split("\\|");

                if (parts.length < 5) {
                    throw new IllegalArgumentException("Неверный формат строки " + lineNumber +
                            ": недостаточно параметров. Строка: " + line);
                }

                String type = parts[0];
                String name = parts[1];
                double price;
                Color color;


                try {
                    price = Double.parseDouble(parts[2]);
                    if (price <= 0) {
                        throw new IllegalArgumentException("Цена должна быть положительной (строка " + lineNumber + ")");
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Неверный формат цены (строка " + lineNumber + "): " + parts[2]);
                }


                try {
                    color = Color.valueOf(parts[3]);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Неверный цвет (строка " + lineNumber + "): " + parts[3]);
                }

                // Создаем объект в зависимости от типа
                switch (type) {
                    case "COAT":
                        if (parts.length < 6) {
                            throw new IllegalArgumentException("Недостаточно параметров для пальто (строка " + lineNumber + ")");
                        }
                        Season season;
                        boolean hasHood;

                        try {
                            season = Season.valueOf(parts[4]);
                        } catch (IllegalArgumentException e) {
                            throw new IllegalArgumentException("Неверный сезон (строка " + lineNumber + "): " + parts[4]);
                        }

                        try {
                            hasHood = Boolean.parseBoolean(parts[5]);
                        } catch (Exception e) {
                            throw new IllegalArgumentException("Неверное значение для капюшона (строка " + lineNumber + "): " + parts[5]);
                        }

                        Coat coat = new Coat(name, price, color, season, hasHood);
                        atelier.sewClothing(coat);
                        break;

                    case "BOOTS":
                        if (parts.length < 6) {
                            throw new IllegalArgumentException("Недостаточно параметров для ботинок (строка " + lineNumber + ")");
                        }
                        ShoeSize shoeSize;
                        SoleType soleType;

                        try {
                            shoeSize = ShoeSize.valueOf(parts[4]);
                        } catch (IllegalArgumentException e) {
                            throw new IllegalArgumentException("Неверный размер обуви (строка " + lineNumber + "): " + parts[4]);
                        }

                        try {
                            soleType = SoleType.valueOf(parts[5]);
                        } catch (IllegalArgumentException e) {
                            throw new IllegalArgumentException("Неверный тип подошвы (строка " + lineNumber + "): " + parts[5]);
                        }

                        Boots boots = new Boots(name, price, color, shoeSize, soleType);
                        atelier.sewClothing(boots);
                        break;

                    case "HAT":
                        if (parts.length < 6) {
                            throw new IllegalArgumentException("Недостаточно параметров для шляпы (строка " + lineNumber + ")");
                        }
                        HatSize hatSize;
                        Material material;

                        try {
                            hatSize = HatSize.valueOf(parts[4]);
                        } catch (IllegalArgumentException e) {
                            throw new IllegalArgumentException("Неверный размер шляпы (строка " + lineNumber + "): " + parts[4]);
                        }

                        try {
                            material = Material.valueOf(parts[5]);
                        } catch (IllegalArgumentException e) {
                            throw new IllegalArgumentException("Неверный материал (строка " + lineNumber + "): " + parts[5]);
                        }

                        Hat hat = new Hat(name, price, color, hatSize, material);
                        atelier.sewClothing(hat);
                        break;

                    default:
                        throw new IllegalArgumentException("Неизвестный тип одежды (строка " + lineNumber + "): " + type);
                }
            }

            if (!earningsLoaded) {
                System.out.println("В файле не найден заработок. Установлен 0.");
            }
        }

        return atelier;
    }
}