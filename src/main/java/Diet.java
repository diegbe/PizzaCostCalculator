import java.util.List;

public class Diet {
    private String name;
    private List<Topping> toppingList;


    public Diet(String name, List<Topping> toppingList) {
        this.toppingList = toppingList;
        this.name = name;
    }

    public List<Topping> getToppingList() {
        return toppingList;
    }
}
