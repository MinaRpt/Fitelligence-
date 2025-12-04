public class NoCondition extends HealthConditions implements FoodAdvice {
    public NoCondition() {
    }

    public NoCondition(String conditionName, String conditionType, String conditionDescription) {
        super(conditionName, conditionType, conditionDescription);
    }

    @Override
    public void getSafeFood() {
    }

    @Override
    public String[] getRecommendations() {
        return new String[0];
    }

    @Override
    public String[] getRestrictions() {
        return new String[0];
    }

}




