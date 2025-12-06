public class ConditionHeartDisease extends ConditionsHealth implements FoodAdvice{


    public ConditionHeartDisease() {
    }

    public ConditionHeartDisease(String conditionDescription, String conditionName, String conditionType) {
        super(conditionDescription, conditionName, conditionType);
    }

    @Override
    public void getSafeFood() {

    }

    @Override
    public String[] getRecommendations() {
        return new String[]{
                "Oats",
                "fish rich in omega 3",
                "Nuts",
                "olive oil",
                "vegetables",
                "fruits"
        };
    }

    @Override
    public String[] getRestrictions() {
        return new String[]{
                "saturated fats",
                "trans fats",
                "high sodium foods",
                "fried foods",
                "high fat meat"
        };
    }
    @Override
    public String getDietTips() {
        return "• Avoid fried foods and saturated fats\n" +
                "• Use olive oil instead of butter\n" +
                "• Eat oats, fruits, nuts, and vegetables";
    }


    @Override
    public String getLifestyleTips() {
        return "• Exercise moderately (walking, cycling)\n" +
                "• Reduce stress and sleep well\n" +
                "• Avoid smoking ";
    }

    // i added  important warnings the user should follow
    @Override
    public String getImportantTips() {
        return "• Limit salt as much as possible\n" +
                "• Monitor blood pressure regularly\n" +
                "• Seek medical help if you feel chest painn";
    }
}
