import java.util.HashMap;
import java.util.regex.Pattern;

public class FormativeLogin {
    private HashMap<String, String[]> userDatabase = new HashMap<>(); // username -> [password, firstName, lastName]

    public String correctUserName(String username) {
        if (username.contains("#") && username.length() <= 8) {
            return "Username Accepted, Proceed";
        } else {
            return "Username does not meet the criteria, please ensure that your username contains a pound sign and is no more than 8 characters in length.";
        }
    }

    public String meetPasswordComplexity(String password) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*]).{8,}$";
        if (Pattern.matches(passwordPattern, password)) {
            return "Password Accepted";
        } else {
            return "Password not Accepted, please check that you have met all the criteria required";
        }
    }

    public String regUser(String username, String password, String firstName, String lastName) {
        String usernameCheck = correctUserName(username);
        if (!usernameCheck.equals("Username Accepted, Proceed")) {
            return usernameCheck;
        }

        String passwordCheck = meetPasswordComplexity(password);
        if (!passwordCheck.equals("Password Accepted")) {
            return passwordCheck;
        }

        userDatabase.put(username, new String[]{password, firstName, lastName});
        return "User successfully registered!";
    }

    public String login(String username, String password) {
        if (userDatabase.containsKey(username)) {
            String[] userDetails = userDatabase.get(username);
            if (userDetails[0].equals(password)) {
                return "Congratulations " + userDetails[1] + " " + userDetails[2] + ", You have made it to the second year. Wishing you all the best";
            } else {
                return "Incorrect credentials have been supplied, try again";
            }
        } else {
            return "Incorrect credentials have been supplied, try again";
        }
    }
}
