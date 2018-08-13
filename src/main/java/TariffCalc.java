public class TariffCalc {
    private int km;

    private float tonn;
    private float daysInRace;
    private boolean circle;
    private boolean f2;
    private int fuelCost,amortizationCost,salaryCost;
    private int optimalTariff,income,expenses, profitInDay,minProfit;


    /*Constructor*/
    TariffCalc(int km, float tonn, boolean circle, float daysInRace, boolean f2) {
        setF2(f2);
        setKm(km);
        setCircle(circle);
        setTonn(tonn);
        setDaysInRace(daysInRace);
        //start();
    }

    /*Getters*/
    int getIncome() { return income; }
    float getDaysInRace() { return daysInRace; }
    int getExpenses() { return expenses; }
    int getProfitInDay() { return profitInDay; }
    int getKm() { return km; }
    float getTonn() { return tonn; }
    boolean isCircle() { return circle; }
    int getOptimalTariff() { return optimalTariff; }
    int getFuelCost() { return fuelCost; }
    int getAmortizationCost() { return amortizationCost; }
    int getSalaryCost() { return salaryCost; }
    public boolean isF2() {
        return f2;
    }


    /*Setters*/
    void setKm(int km) {
        if(km >= 1){
            this.km = km;
        }else {
            this.km = 1;
        }
    }
    void setTonn(float tonn) {
        if(tonn > 0 ){
            this.tonn = tonn;
        }else{
            this.tonn = (float)1.0;
        }


    }
    void setDaysInRace(float dInR) {
        if(dInR > 0 && dInR < 1000){
            this.daysInRace = dInR;
        }else {
            this.daysInRace = 0;
        }
    }
    void setCircle(boolean circle) { this.circle = circle; }
    void setMinProfit(int minProfit) {
        this.minProfit = minProfit;
    }
    public void setOptimalTariff(int optimalTariff) {
        this.optimalTariff = optimalTariff;
    }
    public void setF2(boolean f2) {
        this.f2 = f2;
        if(f2){
            minProfit = InitialData.PROFIT_CASH;
        }else{
            minProfit = InitialData.PROFIT_NONCASH;
        }
    }


    /*Another functions*/
    float daysInRaceFromKm(int km){

        float dInR;

        if(km >= 0 && km < 80){
            dInR = (float)1;
        }
        else if(km >= 80 && km < 250){
            dInR = (float) 1.5;
        }
        else if(km >= 250 && km < 500){
            dInR = (float) 2;
        }
        else if(km >= 500 && km < 1000){
            dInR = (float)2.5;
        }else{
            dInR = -1; //сделать норм ретурн
        }

        return dInR;
    }
    int fuelCost(){
        /*затраты на топливо БЕЗ НДС. Основные показатели:
        1. КМ (маршрут)
        2. Тип Авто
        3. Перевозимый тоннаж*/
        int x = circle ? 2: 1; // кругорейс ли?
        double y = f2 ? 1.2:1; // наличка?

        int fuelAmountEmpty = (int) (km*InitialData.EMPTY_PER_KM/100);//количество потраченого топлива в л (ПУСТОГО)
        int fuelForCargo = (int) (InitialData.FUEL_DT_CONSUM_CARGOPER100TKM / 100 * km * tonn); //доп.топливо на вес груза
        int fuelAmount = fuelAmountEmpty * x + fuelForCargo;

        return (int) (fuelAmount * InitialData.FUEL_PRICE_WO_NDS*y);
    }
    int amortizationCost(){
        int x = circle ? 2: 1; // кругорейс ли?
        double y = f2 ? 1.2:1; // наличка?
        return (int) (km * tonn * x * InitialData.AMORTIZATION_IN_TKM_WO_NDS * y);
    }
    int salaryCost(int tariff){
        return (int) (tariff * tonn * (InitialData.DRIVER_SALARY/100.0) + InitialData.DRIVER_COMMANDING);
    }
    void start(){
        start(0);
    }
    void start(int tariff){
        this.fuelCost = fuelCost();
        this.amortizationCost = amortizationCost();
        this.optimalTariff = tariff == 0 ? calcOptimalTariff(tariff): calcIndicators(tariff);
        this.salaryCost = salaryCost(optimalTariff);
    }
    private int calcIndicators(int tariff){
        income = (int) (tariff * tonn);
        expenses = fuelCost + amortizationCost + this.salaryCost(tariff);
        profitInDay = (int) ((income - expenses)/daysInRace);
        return tariff;
    }
    int calcOptimalTariff(int tariff){
        calcIndicators(tariff);
        int minProfitWithCargo = tonn>=30 ? minProfit + 800 : minProfit;
        while(this.profitInDay < minProfitWithCargo){
            return calcOptimalTariff(tariff+5);
        }
        return tariff;
    }

}
