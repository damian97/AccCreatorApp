package pl.acc_creator.app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {

    static String fName, sName, password, mail;
    static int gander, month, day, year;
    static int accAmount;

    public static void main(String[] args) {

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(500,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);


        JRadioButton[] userDataRadio = new JRadioButton[2];
        int radioPositionX = 30;
        int radioPositionY = 30;

        ButtonGroup bg = new ButtonGroup();

        for (int i = 0; i < userDataRadio.length; i++) {
            userDataRadio[i] = new JRadioButton();
            userDataRadio[i].setBounds(radioPositionX, radioPositionY, 20, 30);
            panel.add(userDataRadio[i]);
            radioPositionY += 30;
            bg.add(userDataRadio[i]);
        }
        userDataRadio[0].setSelected(true);


        JLabel[] userData = new JLabel[2];
        String[] userDataText = {"Random date user", "Data entered manually"};
        int posY = 30;

        for (int i = 0; i < userData.length; i++) {
            userData[i] = new JLabel(userDataText[i]);
            userData[i].setBounds(60, posY,150,30);
            panel.add(userData[i]);
            posY = posY*2;
        }

        SpinnerModel value = new SpinnerNumberModel(1, 1, 5, 1);
        JSpinner accCounter = new JSpinner(value);
        accCounter.setBounds(350,30,40,30);
        panel.add(accCounter);



        JLabel accCounterLabel = new JLabel("How many acc should be created?");
        accCounterLabel.setBounds(280,55,300,30);
        panel.add(accCounterLabel);

        JTextField[] udtf = new JTextField[6];
        String[] udt = {"First Name", "Second Name", "Day", "Year", "Password", "Mail"};
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        String[] gender = {"Male", "Female"};




        JButton create = new JButton("Create Accounts");
        create.setBounds(150, 280, 150, 50);
        panel.add(create);



        JComboBox<String> monthsChoice = new JComboBox<>(months);
        JComboBox<String> genderChoice = new JComboBox<>(gender);


        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                accAmount = (int) accCounter.getValue();

                if (userDataRadio[0].isSelected()) {
                    System.out.println("Zakładam konta na losowych danych");
                    Engine.random = true;
                } else {
                    Engine.random = false;
                    Engine.firstName = udtf[0].getText();
                    Engine.secondName = udtf[1].getText();
                    Engine.password = udtf[4].getText();
                    Engine.email = udtf[5].getText();
                    Engine.gender = genderChoice.getSelectedIndex();
                    Birthdate.day = udtf[2].getText();
                    Birthdate.month = monthsChoice.getSelectedIndex();
                    Birthdate.year = udtf[3].getText();




                }

                for (int i = 0; i < accAmount; i++) {

                    PagesActions pa = new PagesActions();

                    pa.makeAccountInteria();
                    Engine.wait(3000);
                    pa.makeAccountTibia();
                    pa.confirmAccount();

                    ManageFile mf = new ManageFile();
                    mf.addAccountData();

                    System.out.println("The account has been created and confirmed and the data to login has been saved to a file");
                    System.out.println("Patch to file: " + Engine.documentsPath);

                }

                Engine.openFileAfterCreate();

            }
        });




        userDataRadio[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int y = 100, y1 = 100;
                for (int i = 0; i < udt.length; i++) {
                    udtf[i] = new JTextField(udt[i]);
                    if (i >= 2 && i <= 3) {
                        udtf[i].setBounds(150, y, 100, 30);
                        y += 35 * 2;
                    } else {
                        udtf[i].setBounds(30, y1, 100, 30);
                        y1 += 35;
                    }
                    panel.add(udtf[i]);
                }


                monthsChoice.setBounds(150, 135, 100, 30);
                panel.add(monthsChoice);

                genderChoice.setBounds(150, 205, 100, 30);
                panel.add(genderChoice);



            }
        });


        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}





//    Menu menu = new Menu();
//        menu.makeMenu();