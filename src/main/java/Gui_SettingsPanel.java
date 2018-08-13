import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


public class Gui_SettingsPanel extends JFrame implements ActionListener {

    private JPanel contents, labelPanel, textFieldPanel;
    private JTextField emptyPerKm, amortization,salary,commanding,fuelCost, profitInMonth;
    private JLabel currencyLabel,emptyPerKmLabel, amortizationLabel,
            salaryLabel,commandingLabel,fuelCostLabel,profitInMonthLabel,emptyLabel;
    private JComboBox currency;
    private JButton okBut,cancelBut;

    public Gui_SettingsPanel(){

        super("Настройки");

        // Create GUI
        createPanels();
        createInputFields();
        createLabels();
        createButtons();
        addElementsInContainers();

        // Establish variables
        establishTextInFields();

        // Вывод окна на экран
        setSize(320, 250);
        setVisible(true);
        setLocationRelativeTo(null); // screen on center

    }

    private void createButtons() {
        okBut = new JButton("OK");
        cancelBut = new JButton("CANCEL");

        // add action listeners on buttons
        okBut.addActionListener(this);
        cancelBut.addActionListener(this);
    }

    private void establishTextInFields() {
        //currency = new JComboBox(InitialData.CURRENCIES);
        emptyPerKm.setText(String.valueOf(InitialData.EMPTY_PER_KM));
        String amortizWithNds = String.format("%(.2f",InitialData.AMORTIZATION_IN_TKM_WO_NDS*1.2);
        amortization.setText(convertNumberSeparator(amortizWithNds));
        salary.setText(String.valueOf(InitialData.DRIVER_SALARY));
        commanding.setText(String.valueOf(InitialData.DRIVER_COMMANDING));
        String fuelCostWithNds = String.format("%(.2f",InitialData.FUEL_PRICE_WO_NDS*1.2);
        fuelCost.setText(convertNumberSeparator(fuelCostWithNds));
        profitInMonth.setText(String.valueOf(InitialData.PROFIT_CASH*30));
    }

    private void addElementsInContainers() {
        //добавляем основную панель во фрейм
        setContentPane(contents);

        // вставляем доп.панели в главную панель
        contents.add(labelPanel);
        contents.add(textFieldPanel);

        //добавляем кнопки на главную панель
        contents.add(okBut);
        contents.add(cancelBut);

        //высота разделителя между ярлыками
        int h = 4;

        // добавляем ярлыки в их панель
        labelPanel.add(Box.createVerticalStrut(7));
        labelPanel.add(currencyLabel);
        labelPanel.add(Box.createVerticalStrut(h));
        labelPanel.add(emptyPerKmLabel);
        labelPanel.add(Box.createVerticalStrut(h));
        labelPanel.add(amortizationLabel);
        labelPanel.add(Box.createVerticalStrut(h));
        labelPanel.add(salaryLabel);
        labelPanel.add(Box.createVerticalStrut(h));
        labelPanel.add(commandingLabel);
        labelPanel.add(Box.createVerticalStrut(h));
        labelPanel.add(fuelCostLabel);
        labelPanel.add(Box.createVerticalStrut(h));
        labelPanel.add(profitInMonthLabel);
        labelPanel.add(Box.createVerticalStrut(h));

        //добавляем поля ввода в их панель
        textFieldPanel.add(currency);
        textFieldPanel.add(emptyPerKm);
        textFieldPanel.add(amortization);
        textFieldPanel.add(salary);
        textFieldPanel.add(commanding);
        textFieldPanel.add(fuelCost);
        textFieldPanel.add(profitInMonth);



    }

    private void createLabels() {
        //создаем ярлыки полей ввода
        currencyLabel = new JLabel(" Валюта");
        emptyPerKmLabel = new JLabel(" Расход пустого авто, л/100км");
        amortizationLabel = new JLabel(" Амортизация авто, " + currency.getSelectedItem() + "/т*км с НДС");
        salaryLabel = new JLabel(" Зарплата водителя, % от дохода");
        commandingLabel = new JLabel(" Командировочные водителя, " + currency.getSelectedItem());
        fuelCostLabel = new JLabel(" Стоимость топлива, " + currency.getSelectedItem() + "/л с НДС");
        profitInMonthLabel = new JLabel(" Желаемый доход в месяц, " + currency.getSelectedItem());
    }

    private void createInputFields() {
        // создаем поля ввода
        currency = new JComboBox(InitialData.CURRENCIES);
        emptyPerKm = new JTextField();
        amortization = new JTextField();
        salary = new JTextField();
        commanding = new JTextField();
        fuelCost = new JTextField();
        profitInMonth = new JTextField();
    }

    private void createPanels() {
        // создаем первую панель фрейма
        contents = new JPanel();

        // создаем две панели с вертикальным лейаутом в основной панели
        labelPanel = new JPanel();
        textFieldPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel,BoxLayout.Y_AXIS));
        textFieldPanel.setLayout(new BoxLayout(textFieldPanel,BoxLayout.Y_AXIS));

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == okBut){
            InitialData.EMPTY_PER_KM = Float.parseFloat(emptyPerKm.getText());
            InitialData.AMORTIZATION_IN_TKM_WO_NDS = (float) (Float.parseFloat(amortization.getText())/1.2);
            InitialData.DRIVER_SALARY = Integer.parseInt(salary.getText());
            InitialData.DRIVER_COMMANDING = Integer.parseInt(commanding.getText());
            InitialData.FUEL_PRICE_WO_NDS = (float) (Float.parseFloat(fuelCost.getText())/1.2);
            InitialData.PROFIT_CASH = Integer.parseInt(profitInMonth.getText())/30;
        }else if(e.getSource() == cancelBut){

        }
        dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
    }

    public String convertNumberSeparator(String number ){
        return number.replaceAll(",",".");
    }
}
