

import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class Menu {
    private Scanner scanner;
    private Atelier atelier;
    private static final String DEFAULT_FILE = "atelier_data.txt";

    public Menu() {
        scanner = new Scanner(System.in);
        atelier = new Atelier();
    }


    public void showMainMenu() {
        boolean running = true;

        while (running) {
            try {
                System.out.println("\n" + "=".repeat(40));
                System.out.println("         МЕНЮ АТЕЛЬЕ");
                System.out.println("=".repeat(40));
                System.out.println("1. + Сшить новую вещь");
                System.out.println("2. - Продать вещь");
                System.out.println("3. -_- Показать все вещи");
                System.out.println("4. Поиск вещей");
                System.out.println("5. Работа с конкретной вещью");
                System.out.println("6. Показать текущее состояние");
                System.out.println("7. Сохранить данные в файл");
                System.out.println("8. Загрузить данные из файла");
                System.out.println("0. Выход");
                System.out.print("Выберите действие: ");

                String input = scanner.nextLine();
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        sewNewClothingMenu();
                        break;
                    case 2:
                        sellClothingMenu();
                        break;
                    case 3:
                        atelier.displayAllClothing();
                        break;
                    case 4:
                        searchClothingMenu();
                        break;
                    case 5:
                        clothingSubmenu();
                        break;
                    case 6:
                        showCurrentState();
                        break;
                    case 7:
                        saveToFileMenu();
                        break;
                    case 8:
                        loadFromFileMenu();
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Неверный выбор. Пожалуйста, выберите от 0 до 8.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: пожалуйста, введите число.");
            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private void showCurrentState() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("     ТЕКУЩЕЕ СОСТОЯНИЕ АТЕЛЬЕ");
        System.out.println("=".repeat(40));
        System.out.printf("Текущий заработок: %.2f руб.%n", atelier.getTotalEarnings());
        System.out.println("Количество вещей: " + atelier.getClothingCount() + " шт.");
    }

    private void saveToFileMenu() {
        try {
            System.out.println("\n" + "=".repeat(40));
            System.out.println("     СОХРАНЕНИЕ ДАННЫХ");
            System.out.println("=".repeat(40));

            System.out.print("Введите имя файла (по умолчанию '" + DEFAULT_FILE + "'): ");
            String filename = scanner.nextLine();
            if (filename.trim().isEmpty()) {
                filename = DEFAULT_FILE;
            }

            FileManager.saveAtelierToFile(atelier, filename);
            System.out.println("Данные успешно сохранены в файл: " + filename);
            System.out.println("Сохранено вещей: " + atelier.getClothingCount());
            System.out.println("Сохранен заработок: " + atelier.getTotalEarnings() + " руб.");

        } catch (IOException e) {
            System.out.println("Ошибка при сохранении в файл: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Неизвестная ошибка: " + e.getMessage());
        }
    }

    private void loadFromFileMenu() {
        try {
            System.out.println("\n" + "=".repeat(40));
            System.out.println("     ЗАГРУЗКА ДАННЫХ");
            System.out.println("=".repeat(40));

            System.out.println("ВНИМАНИЕ: Текущие данные будут потеряны!");
            System.out.print("Продолжить? (да/нет): ");
            String response = scanner.nextLine().toLowerCase();

            if (!response.equals("да") && !response.equals("yes") && !response.equals("y")) {
                System.out.println("Загрузка отменена.");
                return;
            }

            System.out.print("Введите имя файла (по умолчанию '" + DEFAULT_FILE + "'): ");
            String filename = scanner.nextLine();
            if (filename.trim().isEmpty()) {
                filename = DEFAULT_FILE;
            }

            Atelier loadedAtelier = FileManager.loadAtelierFromFile(filename);
            atelier = loadedAtelier;
            System.out.println("Данные успешно загружены из файла: " + filename);
            System.out.println("Загружено вещей: " + atelier.getClothingCount());
            System.out.println("Загружен заработок: " + atelier.getTotalEarnings() + " руб.");

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            System.out.println("Проверьте, существует ли файл.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка в формате файла: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Неизвестная ошибка: " + e.getMessage());
        }
    }


    private void sewNewClothingMenu() {
        try {
            System.out.println("\n" + "=".repeat(40));
            System.out.println("     СОЗДАНИЕ НОВОЙ ВЕЩИ");
            System.out.println("=".repeat(40));
            System.out.println("1. Пальто");
            System.out.println("2. Ботинки");
            System.out.println("3. Шляпа");
            System.out.println("0. Назад");
            System.out.print("Выберите тип одежды: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    Coat coat = Coat.createFromInput(scanner);
                    atelier.sewClothing(coat);
                    break;
                case 2:
                    Boots boots = Boots.createFromInput(scanner);
                    atelier.sewClothing(boots);
                    break;
                case 3:
                    Hat hat = Hat.createFromInput(scanner);
                    atelier.sewClothing(hat);
                    break;
                case 0:
                    System.out.println("Возврат в главное меню...");
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: пожалуйста, введите число.");
        } catch (Exception e) {
            System.out.println("Ошибка при создании вещи: " + e.getMessage());
        }
    }

    private void sellClothingMenu() {
        try {
            if (atelier.getClothingCount() == 0) {
                System.out.println("В ателье нет вещей для продажи.");
                return;
            }

            System.out.println("\n" + "=".repeat(40));
            System.out.println("        ПРОДАЖА ВЕЩИ");
            System.out.println("=".repeat(40));
            atelier.displayAllClothing();

            System.out.print("Введите номер вещи для продажи (или 0 для отмены): ");
            int index = Integer.parseInt(scanner.nextLine());

            if (index == 0) {
                System.out.println("Отмена продажи.");
                return;
            }

            boolean success = atelier.sellClothingByIndex(index - 1);

            if (!success) {
                System.out.println("Не удалось продать вещь.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: пожалуйста, введите число.");
        } catch (Exception e) {
            System.out.println("Ошибка при продаже: " + e.getMessage());
        }
    }

    private void searchClothingMenu() {
        try {
            System.out.println("\n" + "=".repeat(40));
            System.out.println("        ПОИСК ВЕЩЕЙ");
            System.out.println("=".repeat(40));
            System.out.println("1. По названию");
            System.out.println("2. По цвету");
            System.out.println("3. По максимальной цене");
            System.out.println("4. По типу одежды");
            System.out.println("0. Назад");
            System.out.print("Выберите критерий поиска: ");

            int searchType = Integer.parseInt(scanner.nextLine());

            List<Clothing> results = null;

            switch (searchType) {
                case 1:
                    System.out.print("Введите название для поиска: ");
                    String searchName = scanner.nextLine();
                    results = atelier.searchClothing("name", searchName);
                    break;
                case 2:
                    Color searchColor = Color.selectColor(scanner);
                    results = atelier.searchClothing("color", searchColor);
                    break;
                case 3:
                    System.out.print("Введите максимальную цену: ");
                    double maxPrice = Double.parseDouble(scanner.nextLine());
                    results = atelier.searchClothing("price", maxPrice);
                    break;
                case 4:
                    System.out.println("Выберите тип одежды:");
                    System.out.println("1. Пальто");
                    System.out.println("2. Ботинки");
                    System.out.println("3. Шляпа");
                    int typeChoice = Integer.parseInt(scanner.nextLine());

                    String typeName = "";
                    switch (typeChoice) {
                        case 1: typeName = "Coat"; break;
                        case 2: typeName = "Boots"; break;
                        case 3: typeName = "Hat"; break;
                        default:
                            System.out.println("Неверный выбор.");
                            return;
                    }

                    results = atelier.searchClothing("type", typeName);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор.");
                    return;
            }

            if (results != null && !results.isEmpty()) {
                System.out.println("\nНайдено " + results.size() + " вещей:");
                for (Clothing item : results) {
                    item.displayInfo();
                }
            } else {
                System.out.println("Ничего не найдено по вашему запросу.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: пожалуйста, введите число.");
        } catch (Exception e) {
            System.out.println("Ошибка при поиске: " + e.getMessage());
        }
    }

    private void clothingSubmenu() {
        try {
            if (atelier.getClothingCount() == 0) {
                System.out.println("В ателье нет вещей для работы.");
                return;
            }

            atelier.displayAllClothing();
            System.out.print("Введите номер вещи для работы (или 0 для отмены): ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) {
                return;
            }

            Clothing selected = atelier.getClothingByIndex(choice - 1);
            if (selected == null) {
                System.out.println("Неверный номер.");
                return;
            }

            boolean stayInSubmenu = true;
            while (stayInSubmenu) {
                try {
                    System.out.println("\n" + "=".repeat(50));
                    System.out.println("     РАБОТА С ВЕЩЬЮ: " + selected.getName());
                    System.out.println("=".repeat(50));
                    System.out.println("1. Надеть вещь");
                    System.out.println("2. Показать полную информацию");
                    System.out.println("3. Инструкция по уходу");
                    System.out.println("4. Изменить название");
                    System.out.println("5. Изменить цену");
                    System.out.println("6. Изменить цвет");

                    // Уникальные методы для каждого типа
                    if (selected instanceof Coat) {
                        System.out.println("7. Застегнуть пуговицы (только для пальто)");
                        System.out.println("8. Изменить сезон");
                        System.out.println("9. Изменить наличие капюшона");
                    } else if (selected instanceof Boots) {
                        System.out.println("7. Завязать шнурки (только для ботинок)");
                        System.out.println("8. Изменить размер обуви");
                        System.out.println("9. Изменить тип подошвы");
                    } else if (selected instanceof Hat) {
                        System.out.println("7. Наклонить шляпу (только для шляпы)");
                        System.out.println("8. Изменить размер шляпы");
                        System.out.println("9. Изменить материал");
                    }

                    System.out.println("0. Вернуться в главное меню");
                    System.out.print("Выберите действие: ");

                    int action = Integer.parseInt(scanner.nextLine());

                    switch (action) {
                        case 1:
                            selected.putOn();
                            break;
                        case 2:
                            selected.displayInfo();
                            break;
                        case 3:
                            if (selected instanceof Washable) {
                                Washable washable = (Washable) selected;
                                System.out.println("Инструкция по уходу: " + washable.getCareInstructions());
                            }
                            break;
                        case 4:
                            System.out.print("Введите новое название: ");
                            String newName = scanner.nextLine();
                            selected.setName(newName);
                            System.out.println("Название изменено на: " + newName);
                            break;
                        case 5:
                            try {
                                System.out.print("Введите новую цену: ");
                                double newPrice = Double.parseDouble(scanner.nextLine());
                                if (newPrice > 0) {
                                    selected.setPrice(newPrice);
                                    System.out.println("Цена изменена на: " + newPrice);
                                } else {
                                    System.out.println("Цена должна быть положительной");
                                }
                            } catch (Exception e) {
                                System.out.println("Неверный формат цены");
                            }
                            break;
                        case 6:
                            System.out.println("Выберите новый цвет:");
                            Color newColor = Color.selectColor(scanner);
                            selected.setColor(newColor);
                            System.out.println("Цвет изменен на: " + newColor.getRussianName());
                            break;
                        case 7:
                            if (selected instanceof Coat) {
                                ((Coat) selected).fastenButtons();
                            } else if (selected instanceof Boots) {
                                ((Boots) selected).tieLaces();
                            } else if (selected instanceof Hat) {
                                ((Hat) selected).tiltHat();
                            } else {
                                System.out.println("Это действие недоступно для данной вещи.");
                            }
                            break;
                        case 8:
                            if (selected instanceof Coat) {
                                Season newSeason = Season.selectSeason(scanner);
                                ((Coat) selected).setSeason(newSeason);
                                System.out.println("Сезон изменен на: " + newSeason.getRussianName());
                            } else if (selected instanceof Boots) {
                                ShoeSize newSize = ShoeSize.selectSize(scanner);
                                ((Boots) selected).setSize(newSize);
                                System.out.println("Размер обуви изменен на: " + newSize.getSize());
                            } else if (selected instanceof Hat) {
                                HatSize newHatSize = HatSize.selectHatSize(scanner);
                                ((Hat) selected).setSize(newHatSize);
                                System.out.println("Размер шляпы изменен на: " + newHatSize.getSizeName());
                            }
                            break;
                        case 9:
                            if (selected instanceof Coat) {
                                System.out.print("Есть ли капюшон? (да/нет): ");
                                String hoodInput = scanner.nextLine();
                                boolean newHasHood = hoodInput.equalsIgnoreCase("да");
                                ((Coat) selected).setHasHood(newHasHood);
                                System.out.println("Капюшон: " + (newHasHood ? "добавлен" : "удален"));
                            } else if (selected instanceof Boots) {
                                SoleType newSoleType = SoleType.selectSoleType(scanner);
                                ((Boots) selected).setSoleType(newSoleType);
                                System.out.println("Тип подошвы изменен на: " + newSoleType.getRussianName());
                            } else if (selected instanceof Hat) {
                                Material newMaterial = Material.selectMaterial(scanner);
                                ((Hat) selected).setMaterial(newMaterial);
                                System.out.println("Материал изменен на: " + newMaterial.getRussianName());
                            }
                            break;
                        case 0:
                            stayInSubmenu = false;
                            break;
                        default:
                            System.out.println("Неверный выбор.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: пожалуйста, введите число.");
                } catch (Exception e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: пожалуйста, введите число.");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}