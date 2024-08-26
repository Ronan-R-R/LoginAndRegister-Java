import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Registration and Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout()); // Center buttons using GridBagLayout

        setLocationRelativeTo(null); // Center the window

        // Create buttons with rounded edges
        JButton loginButton = createRoundedButton("Login");
        JButton registerButton = createRoundedButton("Register");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginFrame();
                dispose();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RegisterFrame();
                dispose();
            }
        });

        // Add buttons to the center of the layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(loginButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(registerButton, gbc);

        setVisible(true);
    }

    private JButton createRoundedButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(100, 40));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBackground(Color.LIGHT_GRAY);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1, true),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        button.setBorder(BorderFactory.createCompoundBorder(
                button.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return button;
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}