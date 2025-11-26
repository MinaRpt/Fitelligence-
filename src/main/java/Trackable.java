import java.time.LocalDateTime;
// This abstract class represents a trackable item with a timestamp and a method to get calories for an exercise or a food item.
public abstract class Trackable {
    private LocalDateTime timestamp;

    public Trackable() {
        this.timestamp = LocalDateTime.now();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public abstract double getCalories();
}