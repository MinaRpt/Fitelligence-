public class ConditionColon extends ConditionsHealth implements FoodAdvice {
    public ConditionColon() {}

    public ConditionColon(String conditionName, String conditionType, String conditionDescription){
        super(conditionName, conditionType, conditionDescription);
    }

    @Override
    public void getSafeFood() {
    }

    @Override
    public String[] getRecommendations() {
        return new String[] {
                "Bananas",
                "Applesauce",
                "White Rice",
                "Toast",
                "Boiled Potatoes",
                "Grilled Chicken ",
                "Yogurt",
                "Cucumber",
                "Zucchini"
        };
    }

    @Override
    public String[] getRestrictions (){
        return new String[]{
                "Spicy Food ",
                "Fried Foods ",
                "High fat meals",
                "Bean : gas-forming",
                "Large ammount of dairy " ,
                "Broccoli   ",
                "Cabbage "
        };
    }

}
