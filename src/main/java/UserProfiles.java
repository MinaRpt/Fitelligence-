public class UserProfiles {
    private int age;
    private String name;
    private double weight;
    private double height;
    private Gender gender;
    private HealthCondition healthCondition;

    private NutritionTracker nutritionTracker;
    private ExerciseTracker exerciseTracker;


    public UserProfiles(int age, ExerciseTracker exerciseTracker, Gender gender, HealthCondition healthCondition,
                        double height, String name, NutritionTracker nutritionTracker, double weight) {
        this.age = age;
        this.exerciseTracker = exerciseTracker;
        this.gender = gender;
        this.healthCondition = healthCondition;
        this.height = height;
        this.name = name;
        this.nutritionTracker = nutritionTracker;
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public HealthCondition getHealthCondition() {
        return healthCondition;
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

    public NutritionTracker getNutritionTracker() {
        return nutritionTracker;
    }

    public ExerciseTracker getExerciseTracker() {
        return exerciseTracker;
    }

}


