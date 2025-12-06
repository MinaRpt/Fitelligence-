public class ConditionGlutenTolerance extends ConditionsHealth implements FoodAdvice{

        public ConditionGlutenTolerance() {
        }

        public ConditionGlutenTolerance(String conditionDescription, String conditionName, String conditionType) {
            super(conditionDescription, conditionName, conditionType);
        }

    @Override
    public String getDietTips() {
        return "";
    }

    @Override
    public String getLifestyleTips() {
        return "";
    }

    @Override
    public String getImportantTips() {
        return "";
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



