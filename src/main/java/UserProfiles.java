import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class UserProfiles  implements Serializable {
    private int age;
    private String name;
    private double weight;
    private double height;
    private Gender gender;
    private ConditionHealth conditionHealth;
    private FitnessGoal fitnessGoal;
    private FoodTracker foodTracker;
    private ExerciseTracker exerciseTracker;              // dh 	Encapsulation
    private String email;
    private int MacroCalorieGoal;
    private int dailyCalories; // e.g., calories eaten today
    private int exerciseCalories; // calories burned today
    private int totalSteps;
    private int totalUpdate;
    private ActivityLevel activityLevel;



    public UserProfiles(String email, String name, int age, Gender gender, double height, double weight, ConditionHealth hc ,FitnessGoal fg) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.fitnessGoal = FitnessGoal.valueOf(fg.toString());
        this.conditionHealth = ConditionHealth.valueOf(hc.toString());
        this.foodTracker = new FoodTracker();
        this.exerciseTracker = new ExerciseTracker();
        this.dailyCalories = 0;
        this.exerciseCalories = 0;
        this.totalSteps = 0;
        this.autoResetEnabled= false;
        this.lastResetDateTime=LocalDateTime.now();
        this.activityLevel = ActivityLevel.SEDENTARY;
    }

    public int getTotalUpdate() {
        return totalUpdate;
    }

    public void setTotalUpdate(int totalUpdate) {
        this.totalUpdate = totalUpdate;
    }

    public ConditionHealth getConditionHealth() {
        return conditionHealth;
    }

    public void setConditionHealth(ConditionHealth conditionHealth) {
        this.conditionHealth = conditionHealth;
    }

    public int getTotalSteps() {
        return totalSteps;
    }

    public void setTotalSteps(int totalSteps) {
        this.totalSteps = totalSteps;
    }

    public int getExercisexCalories() {
        return exerciseCalories;
    }

    public void setExerciseCalories(int exerciseCalories) {
        this.exerciseCalories = exerciseCalories;
    }

    public int getDailyCalories() {
        return dailyCalories;
    }

    public void setDailyCalories(int dailyCalories) {
        this.dailyCalories = dailyCalories;
    }

    public int getMacroCalorieGoal(UserProfiles user) {
         this.MacroCalorieGoal = MacroCalculator.calculateCalorieGoal(user);

        System.out.println(MacroCalculator.calculateCalorieGoal(user));
        return MacroCalorieGoal;
    }


    public double calculateBMI(double weight, double height) {
        double heightInMeters = height / 100.0;
        return weight / (heightInMeters * heightInMeters);
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public FitnessGoal getFitnessGoal() {
        return fitnessGoal;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public ConditionHealth getHealthCondition() {
        return conditionHealth;
    }

    public double getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public FoodTracker getFoodTracker() {
        return foodTracker;
    }

    public ExerciseTracker getExerciseTracker() {
        return exerciseTracker;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setExerciseTracker(ExerciseTracker exerciseTracker) {
        this.exerciseTracker = exerciseTracker;
    }

    public void setFitnessGoal(FitnessGoal fitnessGoal) {
        this.fitnessGoal = fitnessGoal;
    }

    public void setFoodTracker(FoodTracker foodTracker) {
        this.foodTracker = foodTracker;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setHealthCondition(ConditionHealth conditionHealth) {
        this.conditionHealth = conditionHealth;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
    }
    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }


    @Override
    public String toString() {
        return email + " , " + name + "," + age + "," + weight + "," + height + "," +
                gender + "," + conditionHealth + "," + foodTracker + "," + exerciseTracker + "," + fitnessGoal;
    }
    private ArrayList<DailyHistory> history= new ArrayList<>();

    public ArrayList<DailyHistory> getHistory() {
        return history;
    }
    public void saveDayToHistory() {
        DailyHistory record=new DailyHistory(
                LocalDateTime.now(),
                LocalTime.now(),
                this.exerciseCalories,
                this.dailyCalories,
                new ArrayList<>(this.exerciseTracker.getExerciseEntries() )
        );
                history.add(record);

                this.dailyCalories=0;
                this.exerciseCalories=0;

                this.exerciseTracker.clearExercises();
    }
    private boolean autoResetEnabled;
//.
    public boolean getAutoResetEnabled() {
        return autoResetEnabled;
    }
    public void setAutoResetEnabled(boolean autoResetEnabled) {
        this.autoResetEnabled = autoResetEnabled;
    }
    private LocalDateTime lastResetDateTime;
    public void autoResetAt3AM(){
        if (!autoResetEnabled) {
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        if (lastResetDateTime == null) {
            lastResetDateTime=now;
            return;
        }
        boolean passed3AM=now.getHour()>=3&&now.toLocalDate().isAfter(lastResetDateTime.toLocalDate());
        if (passed3AM) {
            saveDayToHistory();
            lastResetDateTime=now;
        }
    }
    public void manualReset() {
        saveDayToHistory();
    }



    }



