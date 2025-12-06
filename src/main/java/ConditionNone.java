public class ConditionNone extends ConditionsHealth implements FoodAdvice {
    public ConditionNone() {
    }

    public ConditionNone(String conditionName, String conditionType, String conditionDescription) {
        super(conditionName, conditionType, conditionDescription);
    }

    @Override
    public String getDietTips() {
        return "• Eat a variety of foods from all food groups\\n\" +\n" +
                "               \"• Practice portion control\\n\" +\n" +
                "               \"• Stay hydrated throughout the day\\n\" +\n" +
                "               \"• Enjoy your meals mindfully\\n\" +\n" +
                "               \"• Plan meals ahead for balanced nutrition";
    }

    @Override
    public String getLifestyleTips() {
        return "• Regular physical activity (30 minutes daily)\\n\" +\n" +
                "               \"• Adequate sleep (7-8 hours)\\n\" +\n" +
                "               \"• Stress management techniques\\n\" +\n" +
                "               \"• Social connections and support\\n\" +\n" +
                "               \"• Regular health check-ups";
    }

    @Override
    public String getImportantTips() {
        return "• Consult healthcare provider before major diet changes\\n\" +\n" +
                "               \"• Stay hydrated, especially during exercise\\n\" +\n" +
                "               \"• Listen to your body's signals\\n\" +\n" +
                "               \"• Seek medical attention for persistent symptoms\\n\" +\n" +
                "               \"• Regular check-ups are important";
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




