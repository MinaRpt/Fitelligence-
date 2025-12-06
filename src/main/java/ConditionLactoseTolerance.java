public class ConditionLactoseTolerance extends ConditionsHealth implements FoodAdvice {

    public ConditionLactoseTolerance() {}

    public ConditionLactoseTolerance(String conditionDescription, String conditionName, String conditionType) {
        super(conditionDescription, conditionName, conditionType);
    }

    @Override
    public void getSafeFood() {}

    @Override
    public String[] getRecommendations() {
        return new String[]{
                "Lactose-free milk",
                "Plant-based milks: Almond, Soy, Coconut, Oat",
                "Lactose-free yogurt",
                "Hard cheeses: Cheddar, Parmesan",
                "Gluten-free bread and pasta",
                "Meat, fish, eggs",
                "Fruits and vegetables"
        };
    }

    @Override
    public String[] getRestrictions() {
        return new String[]{
                "Regular milk",
                "Ice cream",
                "Soft cheeses",
                "Cream",
                "Butter",
                "Milk chocolate"
        };
    }

    @Override
    public String getDietTips() {
        return "• Choose lactose-free dairy alternatives\n" +
                "• Prefer hard cheeses over soft ones\n" +
                "• Check ingredient labels for hidden lactose\n" +
                "• Introduce dairy slowly to test tolerance";
    }

    @Override
    public String getLifestyleTips() {
        return "• Eat small portions of dairy with meals\n" +
                "• Use lactase enzyme supplements if needed\n" +
                "• Keep track of foods that trigger symptoms\n" +
                "• Stay hydrated to help digestion";
    }

    @Override
    public String getImportantTips() {
        return "• Avoid dairy during flare-ups\n" +
                "• Watch for bloating and abdominal pain after eating dairy\n" +
                "• Choose fortified plant milks to maintain calcium levels\n" +
                "• Consult a doctor if symptoms become severe";
    }
}

