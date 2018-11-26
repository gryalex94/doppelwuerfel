package pis.hue1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CodecGUI extends JFrame {
    private JPanel panel1;
    private JButton button1;
    Container c;
    Codec codec1;
    Codec codec2;

    public CodecGUI() {
        c = getContentPane();
        c.setLayout(new FlowLayout());

        JLabel labelKlartext = new JLabel("Klartext:       ");
        c.add(labelKlartext);

        JTextArea textAreaKlartext = new JTextArea(3,50);
        c.add(textAreaKlartext);

        JLabel labelGeheimtext = new JLabel("Geheimtext: ");
        c.add(labelGeheimtext);

        JTextArea textAreaGeheimtext = new JTextArea(3,50);
        c.add(textAreaGeheimtext);

        JLabel labelLosungswort1 = new JLabel("Losungswort 1: ");
        c.add(labelLosungswort1);

        JTextField textFieldLosungswort1 = new JTextField(50);
        c.add(textFieldLosungswort1);

        JLabel labelLosungswort2 = new JLabel("Losungswort 2: ");
        c.add(labelLosungswort2);

        JTextField textFieldLosungswort2 = new JTextField(50);
        c.add(textFieldLosungswort2);

        ButtonGroup group = new ButtonGroup();
        JRadioButton radioButtonDoppelWuerfel = new JRadioButton("DoppelWuerfel");
        radioButtonDoppelWuerfel.setSelected(true);
        JRadioButton radioButtonCaesar = new JRadioButton("Caesar");
        group.add(radioButtonDoppelWuerfel);
        group.add(radioButtonCaesar);
        c.add(radioButtonDoppelWuerfel);
        c.add(radioButtonCaesar);

        JButton button = new JButton("Verschlüsseln / Entschlüsseln");
        c.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String klartext = textAreaKlartext.getText();
                String geheimText = textAreaGeheimtext.getText();
                String losungsWort1 = textFieldLosungswort1.getText();
                String losungsWort2 = textFieldLosungswort2.getText();

                if (radioButtonDoppelWuerfel.isSelected()) { // Doppelwürfel
                    codec1 = new Wuerfel(losungsWort1);
                    codec2 = new Wuerfel(losungsWort2);
                    if (!klartext.isEmpty()) { // Verschlüsseln
                        textAreaGeheimtext.setText(codec2.kodiere(codec1.kodiere(klartext)));
                    } else if (!geheimText.isEmpty()) { // Entschlüsseln
                        textAreaKlartext.setText(codec1.dekodiere(codec2.dekodiere(geheimText)));
                    }
                } else if (radioButtonCaesar.isSelected()) {  // Caesar
                    codec1 = new Caesar();
                    codec1.setzeLosung(losungsWort1);
                    if (!klartext.isEmpty()) { // Verschlüsseln
                        textAreaGeheimtext.setText(codec1.kodiere(klartext));
                    } else if (!geheimText.isEmpty()) { // Entschlüsseln
                        textAreaKlartext.setText(codec1.dekodiere(geheimText));
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        CodecGUI fenster = new CodecGUI();

        fenster.setSize(700, 500);
        fenster.setVisible(true);
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
