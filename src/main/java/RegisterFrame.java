import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame {
    private JTextField usernameField, firstNameField, lastNameField;
    private JPasswordField passwordField, confirmPasswordField;
    private JCheckBox showPassword;

    public RegisterFrame() {
        setTitle("Register");
        setSize(450, 350);
        setLayout(new GridBagLayout()); // Use GridBagLayout for better control
        setLocationRelativeTo(null); // Center the window
        setPadding(); // Add padding

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margin between elements

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        styleTextField(usernameField);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField();
        styleTextField(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField();
        styleTextField(lastNameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        stylePasswordField(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordField = new JPasswordField();
        stylePasswordField(confirmPasswordField);

        showPassword = new JCheckBox("Show Password");
        showPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                    confirmPasswordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');
                    confirmPasswordField.setEchoChar('*');
                }
            }
        });

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FormativeLogin login = new FormativeLogin();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match.");
                    return;
                }

                String registrationMessage = login.regUser(username, password, firstName, lastName);
                JOptionPane.showMessageDialog(null, registrationMessage);
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
        add(firstNameLabel, gbc);
        gbc.gridx = 1;
        add(firstNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(lastNameLabel, gbc);
        gbc.gridx = 1;
        add(lastNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(confirmPasswordLabel, gbc);
        gbc.gridx = 1;
        add(confirmPasswordField, gbc);

        gbc.gridx = 1; gbc.gridy = 5;
        add(showPassword, gbc);

        // Horizontal alignment of buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        gbc.gridx = 1; gbc.gridy = 6;
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
