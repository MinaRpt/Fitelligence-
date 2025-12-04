import java.io.Serializable;
import java.time.LocalDateTime;

public class Food extends Trackable implements Serializable {
    private String foodName;
    private double calories;
    private double protein;
    private double fat;
    private double carbs;
    private LocalDateTime timestamp;

    public Food(double calories, double fat, String foodName, double protein, LocalDateTime timestamp , double carbs) {
        this.calories = calories;
        this.fat = fat;
        this.foodName = foodName;
        this.protein = protein;
        this.timestamp = timestamp;
        this.carbs = carbs;
    }

    @Override
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

    @Override
    public String toString() {
        return "Food{" +
                "calories=" + calories +
                ", foodName='" + foodName + '\'' +
                ", protein=" + protein +
                ", fat=" + fat +
                ", carbs=" + carbs +
                ", timestamp=" + timestamp +
                '}';
    }
}
