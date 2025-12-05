import java.io.Serializable;

public class UserProfiles  implements Serializable {
    private int age;
    private String name;
    private double weight;
    private double height;
    private Gender gender;
    private ConditionHealth conditionHealth;
    private FitnessGoal fitnessGoal;
    private FoodTracker foodTracker;
    private ExerciseTracker exerciseTracker;
    private String email;
    private int MacroCalorieGoal;
    private int dailyCalories; // e.g., calories eaten today
    private int exerciseCalories; // calories burned today
    private int totalSteps;
    public UserProfiles(String email, String name, int age, Gender gender, double height, double weight, ConditionHealth hc) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.conditionHealth = ConditionHealth.valueOf(hc.toString());
        this.fitnessGoal = FitnessGoal.MAINTAIN_WEIGHT;
        this.foodTracker = new FoodTracker();
        this.exerciseTracker = new ExerciseTracker();
        this.dailyCalories = 0;
        this.exerciseCalories = 0;
        this.totalSteps = 0;
    }

    public int getTotalSteps() {
        return totalSteps;
    }

    public void setTotalSteps(int totalSteps) {
        this.totalSteps = totalSteps;
    }

    public int getExerciseCalories() {
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
        ;
        System.out.println(MacroCalculator.calculateCalorieGoal(user));
        return MacroCalorieGoal;
    }


    public double calculateBMI(double weightKg, double heightMeters) {
        return weightKg / (heightMeters * heightMeters);
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


    @Override
    public String toString() {
        return email + " , " + name + "," + age + "," + weight + "," + height + "," +
                gender + "," + conditionHealth + "," + foodTracker + "," + exerciseTracker + "," + fitnessGoal;
    }
    }



