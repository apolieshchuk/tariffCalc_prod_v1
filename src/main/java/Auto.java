public class Auto {

    private final String autoMark;
    private final String autoModel;
    private float consumEmptyPerKm;
    private float driveSalaryPerc;

    Auto(String autoMark, String autoModel, float consumEmptyPerKm, float driveSalaryPerc) {
        this.autoMark = autoMark;
        this.autoModel = autoModel;
        this.consumEmptyPerKm = consumEmptyPerKm;
        this.driveSalaryPerc = driveSalaryPerc;
    }

    public Auto(String autoMark, String autoModel) {
        this.autoMark = autoMark;
        this.autoModel = autoModel;
    }

    String getAutoMark() {
        return autoMark;
    }

    String getAutoModel() {
        return autoModel;
    }

    double getConsumEmptyPerKm() {
        return consumEmptyPerKm;
    }

    double getDriveSalaryPerc() {
        return driveSalaryPerc;
    }

    public void setConsumEmptyPerKm(float consumEmptyPerKm) {
        this.consumEmptyPerKm = consumEmptyPerKm;
    }

    public void setDriveSalaryPerc(float driveSalaryPerc) {
        this.driveSalaryPerc = driveSalaryPerc;
    }


}
