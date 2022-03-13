package controller.views;

import controller.PackageController;
import controller.UserController;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CustomerView extends View {
    protected JPanel panel;
    private JComboBox<String> comboBox;
    private JTextField textField;
    private JTable table;
    private JButton myBookingsButton;
    private JButton bookButton;
    private JButton logOutButton;
    private JButton searchButton;

    private final PackageController packageController = new PackageController();
    private final UserController userController;

    public CustomerView(View prev, String message, UserController controller) {
        frame = new JFrame(message);
        frame.setContentPane(panel);
        init();

        userController = controller;

        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Destination", "Price", "Period"}));

        logOutButton.addActionListener(e -> {
            frame.setVisible(false);
            prev.show();
        });

        searchButton.addActionListener(e -> {
            table.setModel(packageController.filter((String) comboBox.getSelectedItem(), textField.getText()));
        });

        addBookingListeners();
    }

    private void addBookingListeners() {
        bookButton.addActionListener(e -> {
            for (int i : table.getSelectedRows()) {
                userController.book((String) table.getValueAt(i, 0));
            }
            reset();
        });

        myBookingsButton.addActionListener(e -> {
            JPanel panel = new JPanel();
            panel.add(new JScrollPane(new JTable(userController.myBookings())));
            JOptionPane.showConfirmDialog(null, panel, "My bookings",
                    JOptionPane.OK_CANCEL_OPTION);
        });
    }

    @Override
    public void reset() {
        table.setModel(packageController.getCustomerTable());
        textField.setText("");
    }
}
