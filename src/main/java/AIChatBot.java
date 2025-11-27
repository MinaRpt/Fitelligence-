import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.Scanner;

public class AIChatBot {
    private static final String API_KEY = "AIzaSyDYWe6BGBqzVow8jafRMH6kTzvn5H8PLlY";

    public static void startChat() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("🤖 Fitelligence AI: Hello! Chat with me freely about anything!");
        System.out.println("🤖 Type 'quit' to exit.\n");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("quit")) {
                System.out.println("🤖 Goodbye! 👋");
                break;
            }

            try {
                String response = getGeminiResponse(userInput);
                System.out.println("🤖 " + response + "\n");
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    public static void startChat(String user) {
        System.out.println("🤖 Fitelligence AI: Generating your diet plan...");

        String userInput = user + "Suggest A DIET FOR THIS USER AND ONLY SUGGEST A DIET DO NOT TYPE ANYTHING ELSE AT ALL ONLY SUGGEST A DIET ONLY and I want the output to be small in this format Breakfast : item1 , item2 , item 3 , etc.. lunch: item1, item2, item3 ,etcc ";

        try {
            String response = getGeminiResponse(userInput); // This needs the method below!
            System.out.println("🤖 " + response);
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public static String getGeminiResponse(String input) throws Exception {
        String json = String.format(
                "{\"contents\":[{\"parts\":[{\"text\":\"%s\"}]}]}",
                input.replace("\"", "\\\"").replace("\n", " ")
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + API_KEY))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return parseAIResponse(response.body());
        } else {
            throw new RuntimeException("API error: " + response.statusCode());
        }
    }

    static String parseAIResponse(String json) {
        try {
            if (json.contains("\"text\": \"")) {
                int start = json.indexOf("\"text\": \"") + 9;
                int end = json.indexOf("\"", start);
                return json.substring(start, end).replace("\\n", "\n");
            } else if (json.contains("\"text\":\"")) {
                int start = json.indexOf("\"text\":\"") + 8;
                int end = json.indexOf("\"", start);
                return json.substring(start, end).replace("\\n", "\n");
            } else {
                return "Hello! I'm your nutrition assistant. How can I help you today?";
            }
        } catch (Exception e) {
            return "Hi there! Ask me about food and nutrition!";
        }
    }
}