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

    @Override
    public String getDietTips() {
        return "• Eat small, frequent meals\n" +
                "• Choose low-fiber foods during flare-ups\n" +
                "• Avoid spicy and greasy meals\n" +
                "• Include yogurt for probiotics";
    }

    @Override
    public String getLifestyleTips() {
        return "• Drink plenty of water\n" +
                "• Reduce stress\n" +
                "• Do light exercise\n" +
                "• Avoid eating too fast";
    }

    @Override
    public String getImportantTips() {
        return "• Avoid foods that cause gas\n" +
                "• Limit caffeine\n" +
                "• ask for help if pain becomes severe\n";

    }
}

