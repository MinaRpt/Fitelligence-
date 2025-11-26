import java.time.LocalDateTime;

public class Exercise extends Trackable {
    private String exerciseName;
    private int durationMinutes;
    private double caloriesBurned;
    private LocalDateTime timestamp;

    public Exercise(double caloriesBurned, int durationMinutes, String exerciseName) {
        this.caloriesBurned = caloriesBurned;
        this.durationMinutes = durationMinutes;
        this.exerciseName = exerciseName;
        this.timestamp = LocalDateTime.now();
    }
    @Override
    public double getCalories() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(double caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "caloriesBurned=" + caloriesBurned +
                ", exerciseName='" + exerciseName + '\'' +
                ", durationMinutes=" + durationMinutes +
                ", timestamp=" + timestamp +
                '}';
    }
}
