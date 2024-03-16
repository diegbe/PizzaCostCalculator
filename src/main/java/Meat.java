public class Meat extends Topping {
    private static Integer timeToCook = 12;
    private static final String category = "Meat";


    public Meat(String name, Double price) {
        super(name, price, timeToCook, category);
    }
}
