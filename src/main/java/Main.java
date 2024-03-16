import java.util.*;

public class Main {
    static Map<String, Size> sizes = new HashMap<>();
    static ArrayList<Diet> diets = new ArrayList<>();

    public static void main(String[] args) {
        setUp();
        System.out.println("Welcome to the pizza shop!");
        System.out.println("Select the size: \n S   M   L");
        Scanner scanner = new Scanner(System.in);
        String size = scanner.next().toUpperCase();

        System.out.println("Your choice is size: " + size);

        System.out.println("How are you feeling today? \n 1.Vegan \n 2.Vegetarian \n 3.Pescatarian \n 4.Meat lover \n 5.All Eater");
        int selectDiet = scanner.nextInt();

        if (selectDiet >= 1 && selectDiet <= diets.size()) {
            Diet diet = diets.get(selectDiet - 1);
            createPizza(size, diet, sizes, scanner);
        } else {
            System.out.println("Invalid diet selection");
        }

    }
    public static void setUp () {
        sizes.put("S", new Size(10, 15.0));
        sizes.put("M", new Size(15, 20.0));
        sizes.put("L", new Size(20, 25.0));

        ArrayList<ArrayList<Topping>> listOfLists = ToppingLists();
        ArrayList<Topping> meatToppings = listOfLists.get(0);
        ArrayList<Topping> fishToppings = listOfLists.get(1);
        ArrayList<Topping> vegToppings = listOfLists.get(2);
        ArrayList<Topping> cheeseToppings = listOfLists.get(3);

        diets.add(new Diet("Vegan", vegToppings));
        ArrayList<Topping> vegetarian = new ArrayList<>();
        vegetarian.addAll(vegToppings);
        vegetarian.addAll(cheeseToppings);
        diets.add(new Diet("Vegetarian", vegetarian));
        ArrayList<Topping> pescatarian = new ArrayList<>();
        pescatarian.addAll(fishToppings);
        pescatarian.addAll(vegToppings);
        pescatarian.addAll(cheeseToppings);
        diets.add(new Diet("Pescatarian", pescatarian));
        ArrayList<Topping> meatEater = new ArrayList<>();
        meatEater.addAll(meatToppings);
        meatEater.addAll(vegToppings);
        meatEater.addAll(cheeseToppings);
        diets.add(new Diet("Meat lover", meatEater));
        ArrayList<Topping> omnivore = new ArrayList<>();
        omnivore.addAll(meatToppings);
        omnivore.addAll(fishToppings);
        omnivore.addAll(vegToppings);
        omnivore.addAll(cheeseToppings);
        diets.add(new Diet("All Eater", omnivore));
    }

    public static void createPizza(String size, Diet diet, Map<String, Size> sizes, Scanner scanner) {
        System.out.println("Choose your ingredients (enter 0 when done): ");
        int ind = 1;
        Integer categoryIndex = printToppingsByCategory(diet.getToppingList(), "Meat", ind);
        categoryIndex = printToppingsByCategory(diet.getToppingList(), "Fish", categoryIndex);
        categoryIndex = printToppingsByCategory(diet.getToppingList(), "Vegetable", categoryIndex);
        printToppingsByCategory(diet.getToppingList(), "Cheese", categoryIndex);
        List<String> categories = Arrays.asList("Meat", "Fish", "Vegetable", "Cheese");


        List<Topping> addedIngredients = new ArrayList<>();
        int ingredient;
        do {
            /*int index = 1;
            for (Topping topping : diet.getToppingList()) {
                //System.out.println(index + ". " + topping.getName());
                index++;
            }*/
            ingredient = scanner.nextInt();
            if (ingredient > 0 && ingredient <= diet.getToppingList().size()) {
                addedIngredients.add(diet.getToppingList().get(ingredient - 1));
                System.out.println("Enter 0 to exit when done");
            } else if (ingredient != 0) {
                System.out.print("Invalid selection");
            }
        } while (ingredient != 0);
        System.out.println("Base size " + size + " " + sizes.get(size).getPrice() + " $");
        System.out.println("Added ingredients: ");

        double ingredientsPrice = 0.0;
        int timeToCook = 5;
        for (Topping addedIngredient : addedIngredients) {
            System.out.println(addedIngredient.getName() + " " + addedIngredient.getPrice() + " $");
            ingredientsPrice += addedIngredient.getPrice();
            if (addedIngredient.getTimeToCook()> timeToCook){
                timeToCook = addedIngredient.getTimeToCook();
            }
        }
        Pizza<Topping> pizza = new Pizza<>(sizes.get(size), addedIngredients);
        System.out.println("Total price " + pizza.getPrice() + " $");

        CurrencyConversion currencyConversion = new CurrencyConversion();
        Currency.priceCurrency(pizza, currencyConversion, scanner);

        System.out.println("Cooking time " + timeToCook + " min");
    }
    private static Integer printToppingsByCategory(List<Topping> toppings, String category, Integer ind) {
        List<String> categoryIngredients = new ArrayList<>();

        //System.out.println(category + ":");

        //System.out.println("Topping list : " + toppings.stream().map(e -> e.getCategory()).toList());
        for (Topping topping : toppings) {
            if (topping.getCategory().equalsIgnoreCase(category)){
                //System.out.println(ind + ". " + topping.getName());
                categoryIngredients.add(ind + ". " + topping.getName());
                ind++;
            }
        }
        if (!categoryIngredients.isEmpty()) {
            System.out.println(category + ":");
            //categoryIngredients.forEach(System.out::println);
            for (String ingredient : categoryIngredients){
                System.out.println(ingredient);
            }
        }
        return ind;
}
    public static ArrayList<ArrayList<Topping>> ToppingLists() {
        ArrayList<ArrayList<Topping>> listOfLists = new ArrayList<>();
        ArrayList<Topping> meatToppings = new ArrayList<>();
        Meat beef = new Meat("beef", 2.0);
        meatToppings.add(beef);
        Meat chicken = new Meat("chicken", 2.0);
        meatToppings.add(chicken);
        Meat bacon = new Meat("bacon", 2.0);
        meatToppings.add(bacon);
        Meat pancetta = new Meat("pancetta", 2.0);
        meatToppings.add(pancetta);
        Meat pepperoni = new Meat("pepperoni", 1.5);
        meatToppings.add(pepperoni);

        ArrayList<Topping> fishToppings = new ArrayList<>();
        Fish salmon = new Fish("salmon", 2.5);
        fishToppings.add(salmon);
        Fish anchovies = new Fish("anchovies", 2.0);
        fishToppings.add(anchovies);
        Fish prawns = new Fish("prawns", 2.5);
        fishToppings.add(prawns);
        Fish mussels = new Fish("mussels", 2.0);
        fishToppings.add(mussels);
        Fish squid = new Fish("squid", 3.0);
        fishToppings.add(squid);

        ArrayList<Topping> vegToppings = new ArrayList<>();
        Vegetable capers = new Vegetable("capers", 1.0);
        vegToppings.add(capers);
        Vegetable mushrooms = new Vegetable("mushroom", 1.0);
        vegToppings.add(mushrooms);
        Vegetable olives = new Vegetable("olives", 1.0);
        vegToppings.add(olives);
        Vegetable broccoli = new Vegetable("broccoli", 1.0);
        vegToppings.add(broccoli);
        Vegetable rocket = new Vegetable("rocket", 0.5);
        vegToppings.add(rocket);
        Vegetable tomatoSlice = new Vegetable("tomato slices", 0.5);
        vegToppings.add(tomatoSlice);

        ArrayList<Topping> cheeseToppings = new ArrayList<>();
        Cheese mozzarella = new Cheese("mozzarella", 1.5);
        cheeseToppings.add(mozzarella);
        Cheese cheddar = new Cheese("cheddar", 1.5);
        cheeseToppings.add(cheddar);
        Cheese provolone = new Cheese("provolone", 1.5);
        cheeseToppings.add(provolone);
        Cheese gouda = new Cheese("gouda", 1.5);
        cheeseToppings.add(gouda);

        listOfLists.add(meatToppings);
        listOfLists.add(fishToppings);
        listOfLists.add(vegToppings);
        listOfLists.add(cheeseToppings);

        return listOfLists;
    }
}