import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class DailyHistory implements Serializable {

    private LocalDateTime date;
    private LocalTime time;
    private int totalcaloriesburnt;
    private int calories;
    private ArrayList<Exercise> exercises;

    public DailyHistory(LocalDateTime date, LocalTime time, int totalcaloriesburnt, int calories, ArrayList<Exercise> exercises) {
        this.date = date;
        this.time = time;
        this.totalcaloriesburnt=totalcaloriesburnt;
        this.calories = calories;
        this.exercises = new ArrayList<>(exercises);
    }
    public LocalTime  getTime() {
        return time;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public int getTotalcaloriesburnt() {
        return totalcaloriesburnt;
    }
    public int getCalories() {
       return calories;
    }
    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

}
