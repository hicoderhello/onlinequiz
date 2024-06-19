import javax.swing.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
// import javax.swing.*;
public class onlinequiz {
    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);

        });
    }}
    public class LoginFrame extends JFrame{
        private JTextField usernameField;
        private JPasswordField PasswordField;

        public LoginFrame(){
            setTitle("Login");
            setSize(300,150);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            JLabel userLabel = new JLabel("Username:");
        usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginButtonListener());

        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        add(panel);
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (UserManager.validateLogin(username, password)) {
                dispose();
                QuizFrame quizFrame = new QuizFrame(username);
                quizFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(LoginFrame.this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public class UserManager {
        private static Map<String, String> users = new HashMap<>();
    
        static {
            users.put("user1", "password1");
            users.put("user2", "password2");
        }
    
        public static boolean validateLogin(String username, String password) {
            return users.containsKey(username) && users.get(username).equals(password);
        }
    
        public static boolean updatePassword(String username, String newPassword) {
            if (users.containsKey(username)) {
                users.put(username, newPassword);
                return true;
            }
            return false;
        }
    
        public static boolean updateProfile(String username, String newUsername, String newPassword) {
            if (users.containsKey(username)) {
                users.remove(username);
                users.put(newUsername, newPassword);
                return true;
            }
            return false;
        }
    }
    public class QuizFrame extends JFrame {
        private String username;
        private JRadioButton option1, option2, option3, option4;
        private ButtonGroup optionsGroup;
    
        public QuizFrame(String username) {
            this.username = username;
            setTitle("Quiz");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
    
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
            JLabel questionLabel = new JLabel("Question: What is 2 + 2?");
            option1 = new JRadioButton("3");
            option2 = new JRadioButton("4");
            option3 = new JRadioButton("5");
            option4 = new JRadioButton("6");
    
            optionsGroup = new ButtonGroup();
            optionsGroup.add(option1);
            optionsGroup.add(option2);
            optionsGroup.add(option3);
            optionsGroup.add(option4);
    
            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(new SubmitButtonListener());
    
            panel.add(questionLabel);
            panel.add(option1);
            panel.add(option2);
            panel.add(option3);
            panel.add(option4);
            panel.add(submitButton);
    
            add(panel);
        }
    
        private class SubmitButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (option2.isSelected()) {
                    JOptionPane.showMessageDialog(QuizFrame.this, "Correct Answer!", "Result", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(QuizFrame.this, "Wrong Answer!", "Result", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    public class ProfileFrame extends JFrame {
        private String username;
        private JTextField newUsernameField;
        private JPasswordField newPasswordField;
    
        public ProfileFrame(String username) {
            this.username = username;
            setTitle("Update Profile");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
    
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
            JLabel newUsernameLabel = new JLabel("New Username:");
            newUsernameField = new JTextField(20);
    
            JLabel newPasswordLabel = new JLabel("New Password:");
            newPasswordField = new JPasswordField(20);
    
            JButton updateButton = new JButton("Update");
            updateButton.addActionListener(new UpdateButtonListener());
    
            panel.add(newUsernameLabel);
            panel.add(newUsernameField);
            panel.add(newPasswordLabel);
            panel.add(newPasswordField);
            panel.add(updateButton);
    
            add(panel);
        }
    
        private class UpdateButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newUsername = newUsernameField.getText();
                String newPassword = new String(newPasswordField.getPassword());
    
                if (UserManager.updateProfile(username, newUsername, newPassword)) {
                    JOptionPane.showMessageDialog(ProfileFrame.this, "Profile updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    username = newUsername;
                } else {
                    JOptionPane.showMessageDialog(ProfileFrame.this, "Failed to update profile", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
        

        
