import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.regex.Pattern;

public class RegisterFrame extends JFrame {
    private JTextField usernameField, firstNameField, lastNameField;
    private JPasswordField passwordField, confirmPasswordField;
    private JCheckBox showPassword;
    private JLabel usernameRequirement, passwordRequirement;
    private boolean usernameValid = false, passwordValid = false;

    public RegisterFrame() {
        setTitle("Register");
        setSize(600, 400); // Set initial size
        setResizable(false); // Disable window resizing
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null); // Center the window
        setPadding(); // Add padding

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
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

        // Adjusted requirement labels to span more horizontally
        usernameRequirement = new JLabel("<html>Username must contain # and be no more than 8 characters.</html>");
        usernameRequirement.setForeground(Color.RED);
        usernameRequirement.setVisible(false);
        usernameField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                validateUsername();
                animateRequirements(usernameRequirement);
            }
            public void focusLost(FocusEvent evt) {
                usernameRequirement.setVisible(false);
            }
        });

        passwordRequirement = new JLabel("<html>Password must be at least 8 characters long, contain one capital letter, a number, and a special character.</html>");
        passwordRequirement.setForeground(Color.RED);
        passwordRequirement.setVisible(false);
        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                validatePassword();
                animateRequirements(passwordRequirement);
            }
            public void focusLost(FocusEvent evt) {
                passwordRequirement.setVisible(false);
            }
        });

        JButton registerButton = createRoundedButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isAnyFieldEmpty()) {
                    return; // Exit if any field is empty
                }

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

                if (registrationMessage.contains("successfully")) {
                    LoginFrame.addUser(username, password, firstName, lastName);
                }
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

        // Requirement labels positioned separately to avoid affecting the rest of the layout
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(usernameRequirement, gbc);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        add(passwordRequirement, gbc);

        // Horizontal alignment of buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        gbc.gridx = 1; gbc.gridy = 8; gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        adjustWindowSize();
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
                BorderFactory.createLineBorder(Color.GRAY, 2, true),
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

    private void validateUsername() {
        String username = usernameField.getText();
        if (username.contains("#") && username.length() <= 8) {
            usernameRequirement.setForeground(Color.GREEN);
            usernameValid = true;
        } else {
            usernameRequirement.setForeground(Color.RED);
            usernameValid = false;
        }
    }

    private void validatePassword() {
        String password = new String(passwordField.getPassword());
        boolean hasUpperCase = !password.equals(password.toLowerCase());
        boolean hasNumber = Pattern.compile("[0-9]").matcher(password).find();
        boolean hasSpecialChar = Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
        boolean isValidLength = password.length() >= 8;

        if (hasUpperCase && hasNumber && hasSpecialChar && isValidLength) {
            passwordRequirement.setForeground(Color.GREEN);
            passwordValid = true;
        } else {
            passwordRequirement.setForeground(Color.RED);
            passwordValid = false;
        }
    }

    private void animateRequirements(JLabel label) {
        Timer timer = new Timer(10, new ActionListener() {
            int height = label.getPreferredSize().height;
            public void actionPerformed(ActionEvent e) {
                if (label.getHeight() < height) {
                    label.setPreferredSize(new Dimension(label.getWidth(), label.getHeight() + 2));
                    label.revalidate();
                    label.repaint();
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        label.setPreferredSize(new Dimension(label.getWidth(), 0));
        label.setVisible(true);
        timer.start();
    }

    private void adjustWindowSize() {
        // Automatically adjust window size based on content
        pack();
        setSize(getWidth(), getHeight() + 50); // Add some space for the buttons
    }

    private boolean isAnyFieldEmpty() {
        if (usernameField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username cannot be empty.");
            return true;
        }
        if (firstNameField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "First Name cannot be empty.");
            return true;
        }
        if (lastNameField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Last Name cannot be empty.");
            return true;
        }
        if (new String(passwordField.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password cannot be empty.");
            return true;
        }
        if (new String(confirmPasswordField.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Confirm Password cannot be empty.");
            return true;
        }
        return false;
    }
}
