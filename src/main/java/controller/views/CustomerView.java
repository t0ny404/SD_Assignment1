package controller.views;

import controller.PackageController;

import javax.swing.*;

public class CustomerView extends View {
    protected JPanel panel;
    private JComboBox<String> comboBox;
    private JTextField textField;
    private JTable table;
    private JButton myBookingsButton;
    private JButton bookButton;
    private JButton logOutButton;

    private PackageController controller = new PackageController();

    public CustomerView(View prev, String message) {
        frame = new JFrame(message);
        frame.setContentPane(panel);
        init();

        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Destination", "Price", "Period"}));

        logOutButton.addActionListener(e -> {
            frame.setVisible(false);
            prev.show();
        });

        table.setModel(controller.getCustomerTable());
    }

    @Override
    public void reset() {
        textField.setText("");
    }
}
