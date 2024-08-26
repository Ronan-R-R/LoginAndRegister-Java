import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPassword;
    private JLabel loginStatus;
    private static final Map<String, String[]> userDatabase = new HashMap<>(); // Store user details in memory

    public LoginFrame() {
        setTitle("Login");
        setSize(400, 300); // Increased size for better visibility
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

        loginStatus = new JLabel();
        loginStatus.setForeground(Color.RED);

        JButton loginButton = createRoundedButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (userDatabase.containsKey(username) && userDatabase.get(username)[0].equals(password)) {
                    String firstName = userDatabase.get(username)[1];
                    String lastName = userDatabase.get(username)[2];
                    JOptionPane.showMessageDialog(null,
                            "Congratulations " + firstName + " " + lastName + ", You have made it to the second year. Wishing you all the best.",
                            "Login Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Incorrect credentials have been supplied, try again.",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE);
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
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        add(showPassword, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        add(loginStatus, gbc);

        // Horizontal alignment of buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);

        gbc.gridx = 1; gbc.gridy = 4;
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

    public static void addUser(String username, String password, String firstName, String lastName) {
        userDatabase.put(username, new String[]{password, firstName, lastName});
    }
}
