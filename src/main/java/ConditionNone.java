public class ConditionNone extends ConditionsHealth implements FoodAdvice {
    public ConditionNone() {
    }

    public ConditionNone(String conditionName, String conditionType, String conditionDescription) {
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




