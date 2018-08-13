import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Gui extends JFrame implements ActionListener {


    private JTextField fieldKm, fieldTonn, fieldDaysInRace;
    private TariffCalc calc;
    private JCheckBox checkCircle, non_cashCheckBox;
    private JButton changeTariffBut,settingsBut ;
    private JPanel leftPanel,rightPanel;


    Gui() throws IOException {

        super("Тариф на перевозку");
        calc = new TariffCalc(1,25,true, 1,false);

        createLeftPanel();
        createRightPanel();


        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // чтобы процесс завершался после закрытия окна
        setSize(550,400);
        setLocationRelativeTo(null); // screen on center
    }

    private void createLeftPanel() {
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
        add(BorderLayout.NORTH, leftPanel);



    }


    private void createTopPanel() {


        // создаем доп. панели
        JPanel topPanelLeft = new JPanel();
        topPanel.add(topPanelLeft);
        JPanel topPanelRight = new JPanel();
        topPanel.add(topPanelRight);

        // Кастомизация доп.панелей
        topPanelLeft.setLayout(new GridLayout(2,3));
        topPanelRight.setLayout(new BoxLayout(topPanelRight,BoxLayout.Y_AXIS));
        topPanel.setBackground(Color.LIGHT_GRAY);


        /*Input Fields Labels*/
        JLabel labelKm = new JLabel("км",SwingConstants.CENTER);
        JLabel labelTonn = new JLabel("тонны",SwingConstants.CENTER);
        JLabel labelDaysInRace = new JLabel("дней на ходку");
        topPanelLeft.add(labelKm);
        topPanelLeft.add(labelTonn);
        topPanelLeft.add(labelDaysInRace);


        /*Input Fields */
        fieldKm = new JTextField(String.valueOf(calc.getKm())); // Integer.toString(calc.getKm())
        fieldTonn = new JTextField(String.valueOf((int)calc.getTonn())); //Double.toString(calc.getTonn())
        fieldDaysInRace = new JTextField(String.valueOf(calc.getDaysInRace()));
        checkCircle = new JCheckBox("кругорейс",true);
        non_cashCheckBox = new JCheckBox("безнал",false);
        topPanelLeft.add(fieldKm);
        topPanelLeft.add(fieldTonn);
        topPanelLeft.add(fieldDaysInRace);
        topPanelRight.add(checkCircle);
        topPanelRight.add(non_cashCheckBox);



        /* Listeners*/
        fieldKm.addActionListener(this);
        fieldTonn.addActionListener(this);
        non_cashCheckBox.addActionListener(this);
        checkCircle.addActionListener(this);
        fieldDaysInRace.addActionListener(this);

    }

    private void createCenterPanel(){
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));
        add(BorderLayout.CENTER, centerPanel);
        createInfoLabels(true);
    }
    private void createInfoLabels(boolean firstStart){

        /*Проверка первого запуска*/

        String fuelCostTXT,amortCostTXT,salaryCostTXT, optimalTariffTXT,
                allExpensTXT,incomeTXT, profitInDayTXT,kernelTXT, profitInRaceTXT;

        if(firstStart){
            fuelCostTXT = amortCostTXT = salaryCostTXT = optimalTariffTXT = allExpensTXT =
                    incomeTXT = profitInDayTXT = kernelTXT = profitInRaceTXT = "";
        }else{

            /*Корректируем отображение лейблов в зависимости от класса калькулятора*/
            double optimalTariffNds = 0;
            String optimalTariffNdsInfo, fuelNdsInfo, amorNdsInfo, profitNdsInfo,incomeNdsInfo,expensNdsInfo;


            // Проверка f2 или ф1?
            if(calc.isF2()){
                optimalTariffNds = 1;
                optimalTariffNdsInfo = fuelNdsInfo = amorNdsInfo = profitNdsInfo =
                        incomeNdsInfo = expensNdsInfo = "";

            }else{
                optimalTariffNds = 1.2;
                optimalTariffNdsInfo = "с НДС";
                fuelNdsInfo = amorNdsInfo = profitNdsInfo = incomeNdsInfo =
                        expensNdsInfo = "без НДС";
            }

            /*формируем надписи*/
            optimalTariffTXT = roundMult(calc.getOptimalTariff()* optimalTariffNds,10) + "грн/т " + optimalTariffNdsInfo;
            fuelCostTXT = calc.getFuelCost()  + " грн " + fuelNdsInfo;
            amortCostTXT = calc.getAmortizationCost() + " грн " + amorNdsInfo;
            salaryCostTXT = calc.getSalaryCost()  + " грн";
            allExpensTXT = calc.getExpenses()  + " грн " + expensNdsInfo;
            incomeTXT = calc.getIncome() + " грн " + incomeNdsInfo;
            kernelTXT = new KernelPrice().getPrice(calc.getKm())+ " грн/т c НДС";
            profitInDayTXT = calc.getProfitInDay() + " грн " ;
            profitInRaceTXT = (int)(calc.getProfitInDay()*calc.getDaysInRace()) + " грн " + profitNdsInfo;
        }


        // - Оптимальный тариф
        JLabel optTariffLabel = new JLabel(" Оптимальный тариф - " + optimalTariffTXT);
        optTariffLabel.setForeground(new Color(0, 153, 0));
        optTariffLabel.setFont(new Font("Serif", Font.BOLD, 22));


        // Создаем Лейблз
        JLabel incomeLabel = new JLabel(" Доход : " + incomeTXT);
        JLabel expensLabel = new JLabel(" Всего затраты : " + allExpensTXT);
        JLabel fuelCostLabel = new JLabel("     Затраты на топливо : " + fuelCostTXT);
        JLabel amortCostLabel = new JLabel("     Амортизация авто : " + amortCostTXT);
        JLabel salaryCostLabel = new JLabel("     Зарплата водителя : " + salaryCostTXT);
        JLabel profitLabel = new JLabel(" Чистая прибыль с ходки : " + profitInRaceTXT + " (" + profitInDayTXT + "/сутки)");
        JLabel separator1 = new JLabel("-------------------------------------------------------------------");

        //Прайс Кернела
        JLabel kernelPrice = new JLabel(" Кернел : " + kernelTXT);
        kernelPrice.setFont(new Font("Serif", Font.PLAIN, 16));

        //- затраты/доходы/прибыль. Групируем Labels
        ArrayList <JLabel> infoLabels = new ArrayList<JLabel>();
        infoLabels.addAll(Arrays.asList(incomeLabel,expensLabel,fuelCostLabel,amortCostLabel,
                salaryCostLabel,profitLabel));


        // Меняем шрифт ярылков
        Font font1 = new Font("Serif", Font.ITALIC, 16);
        changeLabelsFont(infoLabels,font1);

        /* Добавляем ярлыки на панель*/
        centerPanel.add(optTariffLabel);
        centerPanel.add(incomeLabel);
        centerPanel.add (expensLabel);
        centerPanel.add(fuelCostLabel);
        centerPanel.add(amortCostLabel);
        centerPanel.add(salaryCostLabel);
        centerPanel.add(profitLabel);
        centerPanel.add(separator1);
        centerPanel.add(kernelPrice);

    }

    private void createBotPanel() throws IOException {
        botPanel = new JPanel();
        botPanel.setLayout(new BoxLayout(botPanel,BoxLayout.Y_AXIS));
        add(BorderLayout.SOUTH, botPanel);


        Image img = ImageIO.read(getClass().getResource("change.png"));
        ImageIcon img_icon = new ImageIcon(img);

       /* //Кнопка изменения тарифа
        changeTariffBut = new JButton(img_icon);
        changeTariffBut.setMaximumSize(new Dimension(24,24));
        botPanel.add(Box.createVerticalStrut(3));
        botPanel.add(changeTariffBut);
        changeTariffBut.addActionListener(this);*/

        //Кнопка настроек
        settingsBut = new JButton("НАСТРОЙКИ");
        //botPanel.add(Box.createVerticalStrut(200));
        botPanel.add(settingsBut);
        settingsBut.addActionListener(this);
    }

    private void changeLabelsFont(ArrayList<JLabel> allLabels, Font font) {
        for(JLabel label : allLabels) label.setFont(font);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == settingsBut){
            new Gui_SettingsPanel();
        }else if(e.getSource() == changeTariffBut ){
            if(non_cashCheckBox.isSelected()){
                calc.start((int)(inputTariff()/1.2));
            }else {
                calc.start(inputTariff());
            }

        } else{
            changeKmFieldEnter();
            changeTonnFieldEnter();
            if(e.getSource()== fieldKm)fieldDaysInRace.setText(Float.toString(calc.daysInRaceFromKm(calc.getKm())));
            changeDaysInRace();
            changeNonCashIsSelected();

            // стартуем калк
            calc.start();

            if(!checkCircle.isSelected()){
                calc.setCircle(false);
                calc.start(calc.getOptimalTariff());
                calc.setCircle(true);
            }

        }


        /*repaint labels*/
        centerPanel.removeAll();
        createInfoLabels(false);
        centerPanel.updateUI();

    }

    private int inputTariff(){
        String s = JOptionPane.showInputDialog("Укажите тариф");
        return Integer.parseInt(s);
    }

    private void changeKmFieldEnter(){
        try{
            calc.setKm((int) Double.parseDouble(fieldKm.getText()));
        }catch (NumberFormatException i){
            //calc.setKm(-999);
            fieldKm.setText("err");
        }
    }

    private void changeTonnFieldEnter(){
        try{
            calc.setTonn(Float.parseFloat(fieldTonn.getText()));
        }catch (NumberFormatException i){
            fieldTonn.setText("err");
        }
    }

    private void changeCircleIsSelected(){
        if(checkCircle.isSelected()){
            calc.setCircle(true);
        }else{
            calc.setCircle(false);
        }
    }

    private void changeNonCashIsSelected(){
        if (non_cashCheckBox.isSelected()) {
            calc.setF2(false);
        } else {
            calc.setF2(true);
        }
    }

    private void changeDaysInRace(){
        try{
            calc.setDaysInRace(Float.parseFloat(fieldDaysInRace.getText()));
        }catch (NumberFormatException i){
            fieldDaysInRace.setText("err");
        }
    }

    private int parseInt(String str){
        try{
            return (int) Integer.parseInt(str);
        }catch (NumberFormatException i){
            return -999;
        }
    }

    private float parseFloat(String str){
        try{
            return Float.parseFloat(str);
        }catch (NumberFormatException i){
            return -999;
        }
    }

    private double parseDouble(String str){
        try{
            return Double.parseDouble(str);
        }catch (NumberFormatException i){
            return -999;
        }
    }

    private int roundMult(double n,int mult){
        return (int)(mult*(Math.round(n/mult)));
    }


}
