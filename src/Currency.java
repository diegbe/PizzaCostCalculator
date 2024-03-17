import java.awt.desktop.SystemSleepEvent;
import java.io.IOException;
import java.util.Scanner;

public class Currency {
    public static void priceCurrency(Pizza<?> pizza, CurrencyConversion convert, Scanner scanner) {
        boolean validConversion = false;

        while (!validConversion) {
            System.out.println("Enter your currency code (e.g. USD): ");
            String fromCurrency = scanner.next().toUpperCase();
            System.out.println("Enter the currency code you want to convert to (e.g. EUR): ");
            String toCurrency = scanner.next().toUpperCase();

            try {
                double endPriceCurrency = convert.convert(pizza.getPrice(), fromCurrency, toCurrency);
                if (Double.isFinite(endPriceCurrency) && endPriceCurrency>0){
                    System.out.println("Total price in " + toCurrency + ": " + String.format("%.2f", endPriceCurrency));
                    validConversion = true;
                }
                else {
                    System.out.println("Invalid currency codes or conversion rates. Please try again.");
                    //System.out.println("Error getting exchange rates ");
                }
            }
            catch (IOException | InterruptedException e) {
                System.out.println("Error getting exchange rates ");
                e.printStackTrace();
            }
        }
    }
}
