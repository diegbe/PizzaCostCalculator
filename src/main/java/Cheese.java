public class Cheese extends Topping{
    private static Integer timeToCook = 5;
    private static final String category = "Cheese";
    public Cheese (String name, Double price){
       super(name, price, timeToCook, category);
    }
}
