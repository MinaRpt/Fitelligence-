public class UserProfiles {
    private int age;
    private String name;
    private double weight;
    private double height;
    private Gender gender;
    private HealthCondition healthCondition;
    private FitnessGoal fitnessGoal;
    private FoodTracker foodTracker;
    private ExerciseTracker exerciseTracker;


   public UserProfiles(String name, int age, Gender gender, double height , double weight ) {
        this.name = name;
        this.age = age;
        this.gender = Gender.valueOf(gender.toString());
        this.height = height;
        this.weight = weight;
        this.healthCondition = HealthCondition.NONE;
        this.fitnessGoal = FitnessGoal.MAINTAIN_WEIGHT;
        this.foodTracker = new FoodTracker();
        this.exerciseTracker = new ExerciseTracker();
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
            return name + "," + age + "," + weight + "," + height + "," +
                    gender + "," + healthCondition + "," + foodTracker + "," + exerciseTracker + "," + fitnessGoal;
    }
}


