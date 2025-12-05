public class LactoseIntolerance extends ConditionsHealth implements FoodAdvice{


    public LactoseIntolerance(){
    }

    public LactoseIntolerance(String conditionType, String conditionDescription , String conditionName  ) {
        super(conditionType,  conditionDescription , conditionName ) ;
    }

    @Override
    public void getSafeFood () {
    }

    @Override
    public String[] getRecommendations() {
        return new String[]{
                "Lactose Free Milk",
                "Plant based milk : Almond Milk, Soy Milk, Coconut Milk and Oat Milk",
                "Lactose free yogurt",
                "Hard cheese: cheddar , parmesan",
                "Gluten free products like : Pasta and Bread",
                "Meat , Fish and Eggs",
                "Fruits and Vegetables"
        };
    }

    @Override
    public String[] getRestrictions (){
        return new String[]{
                "Regular milk ",
                "Ice Cream",
                "Soft Cheeses",
                "Cream",
                "Butter " ,
                "Milk chocolate"
        };
    }

}
