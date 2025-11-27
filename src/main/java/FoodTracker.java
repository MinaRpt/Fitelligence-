import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FoodTracker implements Serializable {
    private List<Food> foodList;

    public FoodTracker() {
        foodList = new ArrayList<>();

    }

    public void addFood(Food nut){
        foodList.add(nut);
    }

    public void removeFood(Food nut){
        foodList.remove(nut);
    }

    public double CalculateTotalCalories(){
        double totalCalories =0;
        for (int i =0 ; i< foodList.size() ; i++) {
            totalCalories += foodList.get(i).getCalories();
        }
        return totalCalories;
    }

    public double calculatTotalprotein() {
        double totalProtein =0;
        for (int i =0 ; i< foodList.size() ; i++) {
            totalProtein += foodList.get(i).getProtein();
        }
        return totalProtein;
    }

    public double calculatTotalfat() {
        double totalFat =0;
        for (int i =0 ; i< foodList.size() ; i++) {
            totalFat += foodList.get(i).getFat();
        }
        return totalFat;
    }

     public double calculatTotalcarb() {
        double totalCarb =0;
        for (int i =0 ; i< foodList.size() ; i++) {
            totalCarb += foodList.get(i).getCarbs();
        }
        return totalCarb;
     }

    @Override
    public String toString() {
        return "FoodTracker{" +
                "foodList=" + foodList +
                '}' + "Total calories=" + CalculateTotalCalories() +
                ", Total Protein=" + calculatTotalprotein() +
                ", Total Fat=" + calculatTotalfat() +
                ", Total Carbs=" + calculatTotalcarb();
    }
}
