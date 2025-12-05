public class MacroCalculator {

    public static int calculateCalorieGoal(UserProfiles user) {
        // BMR
        double bmr;
        if (user.getGender() == Gender.MALE) {
            bmr = 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() + 5;
        } else {
            bmr = 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() - 161;
        }

        switch (user.getFitnessGoal()) {
            case WEIGHT_LOSS:
                return (int) (bmr - 500);
            case MUSCLE_GAIN:
                return (int) (bmr + 350);
            default:
                return (int) bmr;
        }
    }

    public static int calculateProteinGoal(UserProfiles user) {
        switch (user.getFitnessGoal()) {
            case MUSCLE_GAIN:
                return (int) (2.2 * user.getWeight());
            case WEIGHT_LOSS:
                return (int)(1.8 * user.getWeight());
            default:
                return (int)(1.6 * user.getWeight());
        }
    }
}
