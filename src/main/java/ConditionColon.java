public class ConditionColon extends ConditionsHealth implements FoodAdvice {

    public ConditionColon() {}

    public ConditionColon(String conditionDescription, String conditionName, String conditionType) {
        super(conditionDescription, conditionName, conditionType);
    }

    @Override
    public void getSafeFood() {}

    @Override
    public String[] getRecommendations() {
        return new String[]{
                "Bananas",
                "Applesauce",
                "White rice",
                "Toast",
                "Boiled potatoes",
                "Grilled chicken",
                "Yogurt",
                "Cucumber",
                "Zucchini"
        };
    }

    @Override
    public String[] getRestrictions() {
        return new String[]{
                "Spicy foods",
                "Fried foods",
                "High-fat meals",
                "Beans",
                "Large amounts of dairy",
                "Broccoli",
                "Cabbage"
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
                "• ask for help if pain becomes severe\n" +

    }
}

