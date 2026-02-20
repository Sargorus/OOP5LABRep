public abstract class Clothing {
    protected String name;
    protected double price;
    protected Color color;

    public Clothing(String name, double price, Color color) {
        this.name = name;
        this.price = price;
        this.color = color;
    }

    // А
    public abstract void putOn();

    // О
    public void displayInfo() {
        System.out.printf("Название: %s, Цена: %.2f руб., Цвет: %s%n",
                name, price, color.getRussianName());
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}