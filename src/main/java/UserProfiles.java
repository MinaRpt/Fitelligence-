import java.io.Serializable;

public class UserProfiles  implements Serializable {
    private int age;
    private String name;
    private double weight;
    private double height;
    private Gender gender;
    private HealthCondition healthCondition;
    private FitnessGoal fitnessGoal;
    private FoodTracker foodTracker;
    private ExerciseTracker exerciseTracker;
    private String email;

    public UserProfiles(String email, String name, int age, Gender gender, double height, double weight, HealthCondition hc) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.healthCondition = HealthCondition.valueOf(hc.toString());
        this.fitnessGoal = FitnessGoal.MAINTAIN_WEIGHT;
        this.foodTracker = new FoodTracker();
        this.exerciseTracker = new ExerciseTracker();
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

    public void setHealthCondition(HealthCondition healthCondition) {
        this.healthCondition = healthCondition;
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
                gender + "," + healthCondition + "," + foodTracker + "," + exerciseTracker + "," + fitnessGoal;
    }
    }



