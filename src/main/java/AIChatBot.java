import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class AIChatBot {
    private static final String API_KEY = "AIzaSyARP0rXqE_z9_-W0kcJ3cakukkk0oGT44g";

    // Method for Breakfast suggestion
    public static String suggestBreakfast(String userProfile) {
        String userInput = userProfile +
                " Suggest ONLY breakfast items. Format: item1, item2, item3. " +
                "DO NOT add any other text or explanations.";

        try {
            String response = getGeminiResponse(userInput);
            String cleaned = cleanResponse(response);
            return (cleaned == null || cleaned.isEmpty())
                    ? "80g oatmeal + 150g Greek yogurt + 100g berries"
                    : cleaned;
        } catch (Exception e) {
            System.err.println("Breakfast suggestion error: " + e.getMessage());
            return "80g oatmeal + 150g Greek yogurt + 100g berries";
        }
    }

    // Method for Lunch suggestion
    public static String suggestLunch(String userProfile) {
        String userInput = userProfile +
                " Suggest ONLY lunch items. Format: item1, item2, item3. " +
                "DO NOT add any other text or explanations.";

        try {
            String response = getGeminiResponse(userInput);
            String cleaned = cleanResponse(response);
            return (cleaned == null || cleaned.isEmpty())
                    ? "Grilled chicken breast + quinoa + mixed greens"
                    : cleaned;
        } catch (Exception e) {
            System.err.println("Lunch suggestion error: " + e.getMessage());
            return "Grilled chicken breast + quinoa + mixed greens";
        }
    }

    // Method for Dinner suggestion
    public static String suggestDinner(String userProfile) {
        String userInput = userProfile +
                " Suggest ONLY dinner items. Format: item1, item2, item3. " +
                "DO NOT add any other text or explanations.";

        try {
            String response = getGeminiResponse(userInput);
            String cleaned = cleanResponse(response);
            return (cleaned == null || cleaned.isEmpty())
                    ? "Grilled salmon + steamed broccoli + brown rice"
                    : cleaned;
        } catch (Exception e) {
            System.err.println("Dinner suggestion error: " + e.getMessage());
            return "Grilled salmon + steamed broccoli + brown rice";
        }
    }

    // Method for Snacks suggestion
    public static String suggestSnacks(String userProfile) {
        String userInput = userProfile +
                " Suggest ONLY snack items. Format: item1, item2, item3. " +
                "DO NOT add any other text or explanations.";

        try {
            String response = getGeminiResponse(userInput);
            String cleaned = cleanResponse(response);
            return (cleaned == null || cleaned.isEmpty())
                    ? "Protein shake + 20g almonds or rice cakes"
                    : cleaned;
        } catch (Exception e) {
            System.err.println("Snacks suggestion error: " + e.getMessage());
            return "Protein shake + 20g almonds or rice cakes";
        }
    }

    // Helper method to get AI response with detailed error info
    private static String getGeminiResponse(String input) throws Exception {
        String json = String.format(
                "{\"contents\":[{\"parts\":[{\"text\":\"%s\"}]}]}",
                input.replace("\"", "\\\"")
        );

        System.out.println("Sending request to Gemini API...");
        System.out.println("Input length: " + input.length());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash-lite:generateContent?key=" + API_KEY))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(java.time.Duration.ofSeconds(10))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response status: " + response.statusCode());

        if (response.statusCode() == 200) {
            return parseAIResponse(response.body());
        } else {
            // Print the error response for debugging
            System.err.println("API Error Response: " + response.body());
            throw new RuntimeException("API error: " + response.statusCode() + " - " + response.body());
        }
    }

    // Helper method to parse AI response
    private static String parseAIResponse(String json) {
        try {
            System.out.println("Raw JSON response: " + (json.length() > 100 ? json.substring(0, 100) + "..." : json));

            if (json.contains("\"text\": \"")) {
                int start = json.indexOf("\"text\": \"") + 9;
                int end = json.indexOf("\"", start);
                String result = json.substring(start, end);
                System.out.println("Parsed response: " + result);
                return result;
            } else if (json.contains("\"text\":\"")) {
                int start = json.indexOf("\"text\":\"") + 8;
                int end = json.indexOf("\"", start);
                String result = json.substring(start, end);
                System.out.println("Parsed response: " + result);
                return result;
            } else {
                System.err.println("No 'text' field found in response");
                return "";
            }
        } catch (Exception e) {
            System.err.println("Parse error: " + e.getMessage());
            return "";
        }
    }

    // Helper method to clean up the response
    private static String cleanResponse(String response) {
        if (response == null || response.trim().isEmpty()) {
            return "";
        }
        // Remove any "breakfast:", "lunch:", etc. if they appear
        String cleaned = response.replaceAll("(?i)(breakfast|lunch|dinner|snacks)[:\\s]*", "").trim();
        // Also remove any quotes
        cleaned = cleaned.replaceAll("[\"']", "");
        return cleaned;
    }

    // Test method to check if API is working
    public static boolean testAPI() {
        try {
            String testResponse = getGeminiResponse("Say 'Hello'");
            return testResponse != null && !testResponse.isEmpty();
        } catch (Exception e) {
            System.err.println("API Test failed: " + e.getMessage());
            return false;
        }
    }
}