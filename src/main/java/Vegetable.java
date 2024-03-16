public class Vegetable extends Topping{
    private static Integer timeToCook = 8;
    private static final String category = "Vegetable";

        public Vegetable(String name, Double price){
            super(name, price, timeToCook, category);
        }
}
