public class ConditionHeartDisease extends ConditionsHealth implements FoodAdvice {

    public ConditionHeartDisease() {}

    public ConditionHeartDisease(String conditionDescription, String conditionName, String conditionType) {
        super(conditionDescription, conditionName, conditionType);
    }

    @Override
    public void getSafeFood() {}

    @Override
    public String[] getRecommendations() {
        return new String[] {
                "Oats",
                "Fish rich in omega 3",
                "Nuts",
                "Olive oil",
                "Vegetables",
                "Fruits"
        };
    }

    @Override
    public String[] getRestrictions() {
        return new String[] {
                "Saturated fats",
                "Trans fats",
                "High sodium foods",
                "Fried foods",
                "High fat meat"
        };
    }

//these are some advises for heart diseases
    @Override
    public String getDietTips() {
        return "• Avoid fried foods and saturated fats\n" +
                "• Use olive oil instead of butter\n" +
                "• Eat oats, vegetables, nuts, whole grains";
    }

    @Override
    public String getLifestyleTips() {
        return "• Exercise moderately (walking, cycling)\n" +
                "• Reduce stress and sleep well\n" +
                "• Avoid smoking";
    }

    @Override
    public String getImportantTips() {
        return "• Limit salt intake strictly\n" +
                "• Monitor blood pressure regularly\n" +
                "• Seek help if you feel chest pain or shortness of breath";
    }
}

