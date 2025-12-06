public class ConditionKidneyDisease extends ConditionsHealth implements FoodAdvice{

    public ConditionKidneyDisease() {
    }

    public ConditionKidneyDisease(String conditionName, String conditionType, String conditionDescription){
        super(conditionName, conditionType, conditionDescription);
    }

    @Override
    public void getSafeFood() {
    }

    @Override
    public String[] getRecommendations() {
        return new String[] {
            "Apples",
            "Berries",
            "Grapes",
            "White Rice",
            "Rice Milk",
            "Egg White ",
            "Lean Meat: Chicken, Turkey "
        };
    }

    @Override
    public String[] getRestrictions (){
        return new String[]{
                "High Sodium Food ",
                "Bananas : contains high potassium ",
                "Oranges",
                "Tomatoes",
                "Potatoes " ,
                "Dairy products:contains high phosphorus   ",
                "Beans "
        };
    }
// i added some tips for kidney disease
    @Override
    public String getDietTips() {
        return "• Choose low-potassium fruits like apples, berries, and grapes\n" +
                "• Use low-sodium seasonings instead of salt\n" +
                "• Prefer white rice over whole grains \n";

    }

    @Override
    public String getLifestyleTips() {
        return "• Drink water according to doctor instructions \n" +
                "• Avoid bodybuilding supplements or protein powders\n" +
                "• Maintain a healthy blood pressure\n" +
                "• Exercise lightly and consistently";
    }

    @Override
    public String getImportantTips() {
        return
                "• Monitor swelling and report if it increases\n" +
                "• Follow a low-sodium and low-potassium diet strictly\n" +
                "• Avoid processed, canned, or packaged foods";
    }
}

