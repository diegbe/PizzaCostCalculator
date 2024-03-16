abstract public class Topping {
    protected String name;
    protected Double price;
    protected Integer timeToCook;
    protected String category;

    public Topping(String name, Double price, Integer timeToCook, String category) {
        this.name = name;
        this.price = price;
        this.timeToCook = timeToCook;
        this.category = category;
    }

    public String getName() {

        return name;
    }

    public Double getPrice() {

        return price;
    }

    public String getCategory() {
        return category;
    }

    public Integer getTimeToCook() {

        return timeToCook;
    }

    @Override
    public String toString() {
        return getName();
    }

}
