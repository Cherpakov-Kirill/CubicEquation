import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GetValueFrame extends JFrame {
    GetValueFrame() {
        super("Set parameters");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 120);

        JPanel panel = new JPanel(new FlowLayout());
        JTextField aField = new JTextField(10);
        JTextField bField = new JTextField(10);
        JTextField cField = new JTextField(10);
        JLabel aLabel = new JLabel("a = ");
        JLabel bLabel = new JLabel("b = ");
        JLabel cLabel = new JLabel("c = ");

        JButton button = new JButton("Set parameters");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double a = 0;
                double b = 0;
                double c = 0;
                try {
                    a = Double.parseDouble(aField.getText());
                    b = Double.parseDouble(bField.getText());
                    c = Double.parseDouble(cField.getText());
                    Main.startCalculations(a,b,c);
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel, "Некорректный ввод!");
                }

            }
        });

        panel.add(aLabel);
        panel.add(aField);
        panel.add(bLabel);
        panel.add(bField);
        panel.add(cLabel);
        panel.add(cField);
        panel.add(button);
        add(panel);
        setVisible(true);
    }
}
