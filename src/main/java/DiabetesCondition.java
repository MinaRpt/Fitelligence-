public class DiabetesCondition extends HealthConditions implements FoodAdvice{
    public DiabetesCondition(){
    }

    public DiabetesCondition(String conditionDescription, String conditionName, String conditionType) {
        super(conditionDescription, conditionName, conditionType);
    }

    @Override
    public void getSafeFood() {

    }

    @Override
    public String[] getRecommendations() {
        return new String[]{
                "whole grains",
                "vegetables",
                "lean protein",
                "healthy fats",
                "fruits with low sugar"
        };
    }

    @Override
    public String[] getRestrictions() {
        return new String[]{
                "sugar",
                "Refined carbs",
                "sweetened drinks",
                "pastries"
        };
    }
}
