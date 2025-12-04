public class ColonCondition extends HealthConditions implements FoodAdvice {
    public ColonCondition() {}

    public ColonCondition(String conditionName, String conditionType, String conditionDescription){
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
