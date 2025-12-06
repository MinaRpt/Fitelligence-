public class ConditionNone extends ConditionsHealth implements FoodAdvice {
    public ConditionNone() {
    }

    public ConditionNone(String conditionName, String conditionType, String conditionDescription) {
        super(conditionName, conditionType, conditionDescription);
    }

    @Override
    public String getDietTips() {
        return "Eat varied foods\n" +
                "Control portions\n" +
                "Stay hydrated\n" +
                "Eat mindfully\n" +
                "Plan meals";
    }

    @Override
    public String getLifestyleTips() {
        return "• Regular physical activity (30 minutes daily)\n" +
                "• Adequate sleep (7-8 hours)\n" +
                "• Stress management\n" +
                "• Social connections\n" +
                "• Health check-ups";
    }

    @Override
    public String getImportantTips() {
        return
                "Stay hydrated\n" +
                "Listen to your body\n" +
                "Regular check-ups";
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




