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
        return new String[]{
                "Whole grains",
                "Lean proteins",
                "Vegetables",
                "Fruits",
                "Nuts and seeds",
                "Olive oil",
                "Legumes",
                "Low-fat dairy"

        };
    }

    @Override
    public String[] getRestrictions() {
        return new String[]{
                "Excess sugar",
                "Excess salt",
                "Junk food",
                "Too many fried foods",
                "Soft drinks",
                "Processed meats"
        };
    }

}




