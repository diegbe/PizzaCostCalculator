import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyConversion {
    private static final String URL = "https://v6.exchangerate-api.com/v6/7298c00edad1769469b7957c/latest/EUR";
    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;
    public CurrencyConversion() {
        this.objectMapper = new ObjectMapper ();
        this.httpClient = HttpClient.newHttpClient();
    }
    public double convert(double amount, String fromCurrency, String toCurrency) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode rootNode = objectMapper.readTree(response.body());

        // Get the conversion rates
        JsonNode ratesNode = rootNode.path("conversion_rates");
        double fromRate = ratesNode.path(fromCurrency).asDouble();
        double toRate = ratesNode.path(toCurrency).asDouble();

        // Perform the conversion
        return amount * (toRate / fromRate);
    }
}
