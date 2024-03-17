public class Size {
    private Integer diameter;
    private Double price;

    public Size (Integer diameter, Double price){
        this.diameter = diameter;
        this.price = price;
    }

    public Double getPrice() {

        return price;
    }

    @Override
    public String toString() {
        return "Size{" +
                ", price=" + price +
                '}';
    }
}
