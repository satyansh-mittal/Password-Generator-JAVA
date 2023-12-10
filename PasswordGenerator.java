import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class PasswordGenerator {
    public static Random random = new Random();
    public static JCheckBox[] conditions = { new JCheckBox("Has Uppercase letters"), new JCheckBox("Has numbers"),
            new JCheckBox("Has Symbols"), new JCheckBox("Has other characters") };

    public static void main(String[] args) {
        JFrame frame = new JFrame("Password Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10, 10, 500, 500);
        frame.getContentPane().setBackground(new Color(255, 182, 193));
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 182, 193)); // Match the background color
        for (JCheckBox chk : conditions) {
            chk.setBackground(new Color(255, 182, 193)); // Set background color of checkboxes to pink
            panel.add(chk);
        }
        JSpinner letterCount = new JSpinner(new SpinnerNumberModel(8, 0, 20, 1));
        JLabel letterCountLabel = new JLabel("How many letters?");
        panel.add(letterCountLabel);
        panel.add(letterCount);

        JTextField password = new JTextField(20);
        panel.add(password);
        JButton generate = new JButton("Generate");
        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder generated = new StringBuilder("");
                int count = (int) letterCount.getValue();
                for (int i = 0; i < count; i++) {
                    int rand;
                    if (conditions[0].isSelected()) {
                        if (random.nextBoolean()) {
                            rand = 65 + random.nextInt(25);
                            generated.append((char) rand);
                            continue;
                        }
                    }
                    if (conditions[1].isSelected()) {
                        if (random.nextBoolean()) {
                            rand = 48 + random.nextInt(10);
                            generated.append((char) rand);
                            continue;
                        }

                    }
                    if (conditions[2].isSelected()) {
                        if (random.nextBoolean()) {
                            rand = 33 + random.nextInt(14);
                            generated.append((char) rand);
                            continue;
                        }
                    }
                    if (conditions[3].isSelected()) {
                        if (random.nextBoolean()) {
                            rand = random.nextInt(Character.MAX_VALUE);
                            generated.append((char) rand);
                            continue;
                        }
                    }
                    rand = 97 + random.nextInt(25);
                    generated.append((char) rand);
                }
                password.setText(generated.toString());
            }
        });

        JButton copy = new JButton("Copy");
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(password.getText()),null);
            }
        });
        panel.add(generate);
        panel.add(copy);

        for (Component c : panel.getComponents()) {
            c.setFont(new Font("Ariel", Font.PLAIN, 20));
            if (c instanceof JCheckBox || c instanceof JButton) {
                c.setForeground(Color.BLACK); // Customize the foreground color
            }
        }

        frame.add(panel);
        frame.setVisible(true);
    }
}
