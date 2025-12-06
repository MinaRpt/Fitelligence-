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

    //here i provided tips methods so each condition provaids its own advice
    public abstract String getDietTips();
    public abstract String getLifestyleTips();
    public abstract String getImportantTips();


    //i made a  Safe food method for extra information
    public abstract void getSafeFood();

}
