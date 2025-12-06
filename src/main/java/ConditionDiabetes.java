public class ConditionDiabetes extends ConditionsHealth implements FoodAdvice{
    public ConditionDiabetes(){
    }

    public ConditionDiabetes(String conditionDescription, String conditionName, String conditionType) {
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
    //here is a general advice for diabetes
    @Override
    public String getDietTips() {
        return "• Avoid sugar, pastries, soda, and sweetened drinks\n" +
                "• Choose whole grains like oats and brown rice\n" +
                "• Build meals around vegetables and lean proteins\n" +
                "• Prefer low-sugar fruits (berries, apples)";
    }

    @Override
    public String getLifestyleTips() {
        return "• Exercise at least 30 minutes daily\n" +
                "• Monitor blood sugar regularly\n" +
                "• Eat small, frequent meals instead of large ones\n" +
                "• Stay hydrated throughout the day";
    }

    @Override
    public String getImportantTips() {
        return "• Do NOT skip meals to avoid sugar drops\n" +
                "• Limit high-carb meals and avoid eating late at night\n" +
                "• Seek medical attention if sugar remains always high";
    }
}
