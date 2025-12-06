public class ConditionHeartDisease extends ConditionsHealth implements FoodAdvice{


    public ConditionHeartDisease() {
    }

    public ConditionHeartDisease(String conditionDescription, String conditionName, String conditionType) {
        super(conditionDescription, conditionName, conditionType);
    }

    @Override
    public void getSafeFood() {

    }

    @Override
    public String[] getRecommendations() {
        return new String[]{
                "Oats",
                "fish rich in omega 3",
                "Nuts",
                "olive oil",
                "vegetables",
                "fruits"
        };
    }

    @Override
    public String[] getRestrictions() {
        return new String[]{
                "saturated fats",
                "trans fats",
                "high sodium foods",
                "fried foods",
                "high fat meat"
        };
    }
}
