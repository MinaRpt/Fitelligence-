import java.time.LocalDateTime;

public class Food {
    private String foodName;
    private double calories;
    private double protein;
    private double fat;
    private double carbs;
    private LocalDateTime timestamp;

    public Food(double calories, double fat, String foodName, double protein, LocalDateTime timestamp) {
        this.calories = calories;
        this.fat = fat;
        this.foodName = foodName;
        this.protein = protein;
        this.timestamp = timestamp;
    }

    public double getCalories() {
        return calories;
    }

    public double getFat() {
        return fat;
    }

    public String getFoodName() {
        return foodName;
    }

    public double getProtein() {
        return protein;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public double getCarbs() {
        return carbs;
    }
}
