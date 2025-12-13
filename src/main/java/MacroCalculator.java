public class MacroCalculator {

    public static int calculateCalorieGoal(UserProfiles user) {
        // BMR

        double bmr;
        if (user.getGender() == Gender.MALE) {
            bmr = 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() + 5;
        } else {
            bmr = 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() - 161;
        }

        double multiplier ;
        switch (user.getActivityLevel()) {
            case LIGHTLY_ACTIVE:multiplier= 1.375;
                break;
            case MODERATELY_ACTIVE:multiplier= 1.55;
                break;
            case HIGHLY_ACTIVE:multiplier= 1.725;
                break;
            default:multiplier= 1.2;
        }

        double tdee=bmr * multiplier;


        switch (user.getFitnessGoal()) {
            case WEIGHT_LOSS:
                return (int) (tdee - 500);
            case MUSCLE_GAIN:
                return (int) (tdee + 350);
            default:
                return (int) tdee;
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
