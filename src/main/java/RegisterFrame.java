import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class RegisterFrame extends JFrame {
    private JTextField usernameField, firstNameField, lastNameField;
    private JPasswordField passwordField, confirmPasswordField;
    private JCheckBox showPassword;
    private JLabel usernameRequirement, passwordRequirement;

    public RegisterFrame() {
        setTitle("Register");
        setSize(450, 350);
        setLayout(new GridBagLayout()); // Use GridBagLayout for better control
        setLocationRelativeTo(null); // Center the window
        setPadding(); // Add padding

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margin between elements
        gbc.anchor = GridBagConstraints.WEST; // Align labels to the left

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

        usernameRequirement = new JLabel("<html>Username must contain # and be no more than 8 characters.</html>");
        usernameRequirement.setForeground(Color.RED);
        usernameRequirement.setVisible(false);
        usernameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameRequirement.setVisible(true);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameRequirement.setVisible(false);
            }
        });

        passwordRequirement = new JLabel("<html>Password must be at least 8 characters long, contain at least one capital letter, one number, and one special character.</html>");
        passwordRequirement.setForeground(Color.RED);
        passwordRequirement.setVisible(false);
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordRequirement.setVisible(true);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordRequirement.setVisible(false);
            }
        });

        JButton registerButton = createRoundedButton("Register");
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

        JButton backButton = createRoundedButton("Back");
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
        gbc.fill = GridBagConstraints.HORIZONTAL;
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

        gbc.gridx = 1; gbc.gridy = 6;
        add(usernameRequirement, gbc);

        gbc.gridx = 1; gbc.gridy = 7;
        add(passwordRequirement, gbc);

        // Horizontal alignment of buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        gbc.gridx = 1; gbc.gridy = 8;
        add(buttonPanel, gbc);

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
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(100, 40));
        button.setBackground(Color.LIGHT_GRAY);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
        button.setBorder(BorderFactory.createCompoundBorder(
                button.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return button;
    }

    private void styleTextField(JTextField textField) {
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
        textField.setPreferredSize(new Dimension(200, 30));
        textField.setBorder(BorderFactory.createCompoundBorder(
                textField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }

    private void stylePasswordField(JPasswordField passwordField) {
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
        passwordField.setPreferredSize(new Dimension(200, 30));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                passwordField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }

    private void setPadding() {
        getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
}
