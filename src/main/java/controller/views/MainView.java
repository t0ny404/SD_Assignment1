package controller.views;

import controller.UserController;

import javax.swing.*;
import java.util.Arrays;

public class MainView extends View {
    protected JPanel panel;
    private JTextField username;
    private JPasswordField password;
    private JButton loginButton;
    private JButton registerButton;

    private final UserController controller;

    public MainView() {
        frame = new JFrame("Travelling agency");
        frame.setContentPane(panel);
        init();

        controller = new UserController();

        loginButton.addActionListener(e -> {
            String message = controller.login(username.getText(), password.getPassword());
            if (message.startsWith("Hello")) {
                frame.setVisible(false);
                new CustomerView(this, message, controller);
            } else if (message.equals("admin")) {
                frame.setVisible(false);
                new AdminView(this);
            } else {
                JOptionPane.showMessageDialog(null, message);
            }
            reset();
        });

        registerButton.addActionListener(e -> {
            JPanel panel = new JPanel();
            JTextField username = new JTextField(10);
            JPasswordField password = new JPasswordField(10);
            JPasswordField confirm = new JPasswordField(10);
            JTextField firstname = new JTextField(10);
            JTextField lastname = new JTextField(10);
            JTextField email = new JTextField(10);
            JTextField age = new JTextField(10);

            panel.add(new JLabel("\nusername: "));
            panel.add(username);
            panel.add(new JLabel("First Name: "));
            panel.add(firstname);
            panel.add(new JLabel("Last Name: "));
            panel.add(lastname);
            panel.add(new JLabel("\nEmail: "));
            panel.add(email);
            panel.add(new JLabel("Age: "));
            panel.add(age);
            panel.add(new JLabel("\npassword: "));
            panel.add(password);
            panel.add(new JLabel("\nconfirm password: "));
            panel.add(confirm);

            while (true) {
                if (JOptionPane.showConfirmDialog(null, panel, "Please Enter your details",
                        JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                    if (!Arrays.equals(password.getPassword(), confirm.getPassword())) {
                        JOptionPane.showMessageDialog(null, "Passwords do not match!");
                        password.setText("");
                        confirm.setText("");
                    } else {
                        String message = controller.register(username.getText(), password.getPassword(), firstname.getText(), lastname.getText(),
                                email.getText(), age.getText());
                        if (message.equals("Success!"))
                            break;
                        else {
                            JOptionPane.showMessageDialog(null, message);
                            password.setText("");
                            confirm.setText("");
                        }
                    }
                }
                else break;
            }
        });
    }

    @Override
    public void reset() {
        username.setText("");
        password.setText("");
    }
}
