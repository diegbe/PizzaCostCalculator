public class Fish extends Topping {
    private static Integer timeToCook = 10;
    private static final String category = "Fish";

    public Fish(String name, Double price) {
        super(name, price, timeToCook, category);
    }
}
