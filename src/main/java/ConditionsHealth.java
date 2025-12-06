public abstract class ConditionsHealth {
    private String conditionName;
    private String conditionDescription;
    private String conditionType;


    public ConditionsHealth(){

    }

    public ConditionsHealth(String conditionDescription, String conditionName, String conditionType) {
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


    // here i added tips methods that must provide its own advice
    public abstract String getDietTips();
    public abstract String getLifestyleTips();
    public abstract String getImportantTips();


    public abstract void getSafeFood();

}
