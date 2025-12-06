public class ConditionGlutenTolerance extends ConditionsHealth implements FoodAdvice{

        public ConditionGlutenTolerance() {
        }

        public ConditionGlutenTolerance(String conditionDescription, String conditionName, String conditionType) {
            super(conditionDescription, conditionName, conditionType);
        }

    @Override
    public String getDietTips() {
        return "Always check labels for hidden gluten.\",\n" +
                "                \"Choose naturally gluten-free foods like fruits, vegetables, rice, and potatoes.\",\n" +
                "                \"Use gluten-free flour alternatives such as almond flour or rice flour.\",\n" +
                "              \n" +
                "                \"Cook more meals at home to avoid contamination.";
    }

    @Override
    public String getLifestyleTips() {
        return "Avoid eating out at places that don’t offer gluten-free options.\",\n" +
                "                \n" +
                "                \"Store gluten-free foods separately from other foods at home.\",\n" +
                "                \"Use separate kitchen tools if someone else in the home uses gluten.\",\n" +
                "                \"Join a gluten-free support community for recipes and learning";
    }

    @Override
    public String getImportantTips() {
        return "Even small amounts of gluten can cause symptoms\",\n" +
                "                \"Be cautious with sauces, soy sauce, and soups bec they often contain wheat.\",\n" +
                "                \"Watch out for gluten in medications and supplements.\",\n" +
                "                \"If symptoms continue, consult a doctor or dietitian.";
    }

    @Override
        public void getSafeFood () {
        }

        @Override
        public String[] getRecommendations() {
            return new String[]{
                    "Rice",
                    "Corn",
                    "Potatoes",
                    "Fruits and Vegetables",
                    "Gluteen free products like : Pasta and Bread",
                    "Meat and eggs"
            };
        }

    @Override
    public String[] getRestrictions (){
            return new String[]{
                    "Wheat",
                    "Barley",
                    "Rye",
                    "Non gluten free bread",
                    "Cakes and pastries (baked goods )" ,
                    "non gluten free Cereals "
            };
    }

        }



