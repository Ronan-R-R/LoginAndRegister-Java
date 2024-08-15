import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPassword;

    public LoginFrame() {
        setTitle("Login");
        setSize(450, 250);
        setLayout(new GridBagLayout()); // Use GridBagLayout for better control
        setLocationRelativeTo(null); // Center the window
        setPadding(); // Add padding

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margin between elements

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        styleTextField(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        stylePasswordField(passwordField);

        showPassword = new JCheckBox("Show Password");
        showPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');
                }
            }
        });

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FormativeLogin login = new FormativeLogin();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                String loginMessage = login.login(username, password);
                JOptionPane.showMessageDialog(null, loginMessage);
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MainFrame();
                dispose();
            }
        });

        // Add components to the grid
        gbc.gridx = 0; gbc.gridy = 0;
        add(usernameLabel, gbc);
        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        add(showPassword, gbc);

        // Horizontal alignment of buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);

        gbc.gridx = 1; gbc.gridy = 3;
        add(buttonPanel, gbc);

        setVisible(true);
    }

    private void styleTextField(JTextField textField) {
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        textField.setPreferredSize(new Dimension(150, 30));
        textField.setBorder(BorderFactory.createCompoundBorder(
                textField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }

    private void stylePasswordField(JPasswordField passwordField) {
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        passwordField.setPreferredSize(new Dimension(150, 30));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                passwordField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }

    private void setPadding() {
        getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
}
