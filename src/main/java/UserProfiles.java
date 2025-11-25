public class UserProfiles {
    private int age;
    private String name;
    private double weight;
    private double height;
    private Gender gender;
    private HealthCondition healthCondition;

    private FoodTracker foodTracker;
    private ExerciseTracker exerciseTracker;


    public UserProfiles(String name,
                        int age,
                        Gender gender,
                        double height,
                        double weight,
                        HealthCondition healthCondition,
                        FoodTracker foodTracker,
                        ExerciseTracker exerciseTracker) {
        this.age = age;
        this.exerciseTracker = exerciseTracker;
        this.gender = gender;
        this.healthCondition = healthCondition;
        this.height = height;
        this.name = name;
        this.foodTracker = foodTracker;
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

    public FoodTracker getFoodTracker() {
        return foodTracker;
    }

    public ExerciseTracker getExerciseTracker() {
        return exerciseTracker;
    }

    @Override
    public String toString() {
            return age + "," + name + "," + weight + "," + height + "," +
                    gender + "," + healthCondition + "," + foodTracker + "," + exerciseTracker;
    }
}


