import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExerciseTracker implements Serializable {
    private ArrayList<Exercise> exerciseEntries;
    private double totalCaloriesBurned;

    public ExerciseTracker() {
        this.exerciseEntries = new ArrayList<>();
        this.totalCaloriesBurned = 0;
    }

    public void addExercise(String exerciseName, int durationMinutes, double caloriesBurned) {
        Exercise add = new Exercise(caloriesBurned, durationMinutes, exerciseName);
        exerciseEntries.add(add);
        totalCaloriesBurned += caloriesBurned;
    }

    public double getTotalCaloriesBurned() {
        return totalCaloriesBurned;
    }

    public List<Exercise> getExerciseEntries() {
        return new ArrayList<>(exerciseEntries);
    }

    @Override
    public String toString() {
        return "ExerciseName = " + exerciseEntries + ", TotalCaloriesBurned = " + totalCaloriesBurned;
    }
}