import java.util.List;

public class Pizza <T extends Topping> {
    private Size size;
    private List<T> toppings;

   public Pizza (Size size, List<T> toppings) {
        this.size = size;
        this.toppings = toppings;
    }

    public Double getPrice(){
        Double sizePrice = size.getPrice();
        Double toppingPrice = 0.0;

        for (T topping : toppings) {
            toppingPrice += topping.getPrice();
        }

        return toppingPrice + sizePrice;
    }
}
