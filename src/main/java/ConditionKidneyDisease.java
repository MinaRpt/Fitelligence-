public class ConditionKidneyDisease extends ConditionsHealth implements FoodAdvice{

    public ConditionKidneyDisease() {
    }

    public ConditionKidneyDisease(String conditionName, String conditionType, String conditionDescription){
        super(conditionName, conditionType, conditionDescription);
    }

    @Override
    public void getSafeFood() {
    }

    @Override
    public String[] getRecommendations() {
        return new String[] {
            "Apples",
            "Berries",
            "Grapes",
            "White Rice",
            "Rice Milk",
            "Egg White ",
            "Lean Meat: Chicken, Turkey "
        };
    }

    @Override
    public String[] getRestrictions (){
        return new String[]{
                "High Sodium Food ",
                "Bananas : contains high potassium ",
                "Oranges",
                "Tomatoes",
                "Potatoes " ,
                "Dairy products:contains high phosphorus   ",
                "Beans "
        };
    }
}
