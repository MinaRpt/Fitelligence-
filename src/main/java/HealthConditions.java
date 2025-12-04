public abstract class HealthConditions {
    private String conditionName;
    private String conditionDescription;
    private String conditionType;


    public HealthConditions(){

    }

    public HealthConditions(String conditionDescription, String conditionName, String conditionType) {
        this.conditionDescription = conditionDescription;
        this.conditionName = conditionName;
        this.conditionType = conditionType;

    }

    public String getConditionName() {
        return conditionName;
    }

    public String getConditionType() {
        return conditionType;
    }



    public abstract void getSafeFood();

}
