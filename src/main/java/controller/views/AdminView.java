package controller.views;

import controller.DestinationController;
import controller.PackageController;

import javax.swing.*;

public class AdminView extends View {

    protected JPanel panel;
    private JTable destinations;
    private JTable packages;
    private JButton addButton;
    private JButton deleteButton;
    private JButton addPackageButton;
    private JButton deletePackageButton;
    private JButton editPackageButton;
    private JButton logOutButton;
    private JButton showPackagesButton;
    private JButton showAllButton;

    private final PackageController packageController = new PackageController();
    private final DestinationController destinationController = new DestinationController();

    public AdminView(View prev) {
        frame = new JFrame("Admin");
        frame.setContentPane(panel);
        init();
        updateDestinations();

        logOutButton.addActionListener(e -> {
            frame.setVisible(false);
            prev.show();
        });
        addDestinationListeners();
        addPackageListeners();
        updateDestinations();
        updatePackages();
    }

    private void updateDestinations() {
        destinations.setModel(destinationController.getTable());
    }

    private void updatePackages() {
        packages.setModel(packageController.getAgencyTable((Integer) destinations.getValueAt(destinations.getSelectedRow(), 0)));
    }

    private void addDestinationListeners() {
        addButton.addActionListener(e -> {
            JPanel panel = new JPanel();
            JTextField city = new JTextField(10);
            JTextField country = new JTextField(10);
            JTextField hotel = new JTextField(10);
            JCheckBox safe  = new JCheckBox();

            panel.add(new JLabel("\nCity: "));
            panel.add(city);
            panel.add(new JLabel("Country: "));
            panel.add(country);
            panel.add(new JLabel("Hotel: "));
            panel.add(hotel);
            panel.add(new JLabel("\nSafe: "));
            panel.add(safe);

            while (true) {
                if (JOptionPane.showConfirmDialog(null, panel, "Please Enter destination details",
                        JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                    String message = destinationController.add(city.getText(), country.getText(), hotel.getText(), safe.isSelected());
                    if (message.equals("Success!"))
                        break;
                    else {
                        JOptionPane.showMessageDialog(null, message);
                    }
                }
                else break;
            }
            updateDestinations();
        });

        deleteButton.addActionListener(e -> {
            int[] rows = destinations.getSelectedRows();
            if (rows == null || rows.length == 0)
                JOptionPane.showMessageDialog(null, "Please select a destination!");
            else {
                for (int i : rows) {
                    destinationController.delete((Integer) destinations.getValueAt(i, 0));
                }
                updateDestinations();
            }
        });

        showPackagesButton.addActionListener(e -> {
            try {
                updatePackages();
            } catch(ArrayIndexOutOfBoundsException ignored) {
                JOptionPane.showMessageDialog(null, "Please select a destination!");
            }
        });
    }

    private void addPackageListeners() {
        addPackageButton.addActionListener(e -> {
            try {
                Integer destID = (Integer) destinations.getValueAt(destinations.getSelectedRow(), 0);
                JPanel panel = new JPanel();
                JTextField name = new JTextField(10);
                JTextField price = new JTextField(10);
                JTextField limit = new JTextField(10);
                JTextField details = new JTextField(10);
                JTextField start = new JTextField(10);
                start.setText("--");
                JTextField end = new JTextField(10);
                end.setText("--");

                addToPopUp(panel, name, price, limit, details, start, end);

                while (true) {
                    if (JOptionPane.showConfirmDialog(null, panel, "Please Enter package details",
                            JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                        String message = packageController.add(destID, name.getText(), price.getText(), limit.getText(),
                                details.getText(), start.getText(), end.getText());
                        if (message.equals("Success!"))
                            break;
                        else {
                            JOptionPane.showMessageDialog(null, message);
                        }
                    }
                    else break;
                }
                updatePackages();
            } catch(ArrayIndexOutOfBoundsException ignored) {
                JOptionPane.showMessageDialog(null, "Please select a destination!");
            }
        });

        deletePackageButton.addActionListener(e -> {
            int[] rows = packages.getSelectedRows();
            if (rows == null || rows.length == 0)
                JOptionPane.showMessageDialog(null, "Please select a package!");
            else {
                for (int i : rows) {
                    packageController.delete((Integer) packages.getValueAt(i, 0));
                }
                updatePackages();
            }
        });

        editPackageButton.addActionListener(e -> {
            try {
                Integer id = (Integer) packages.getValueAt(packages.getSelectedRow(), 0);
                JPanel panel = new JPanel();
                JTextField name = new JTextField(10);
                name.setText((String) packages.getValueAt(packages.getSelectedRow(), 1));
                JTextField price = new JTextField(10);
                price.setText(packages.getValueAt(packages.getSelectedRow(), 2).toString());
                JTextField start = new JTextField(10);
                start.setText(packages.getValueAt(packages.getSelectedRow(), 3).toString());
                JTextField end = new JTextField(10);
                end.setText(packages.getValueAt(packages.getSelectedRow(), 4).toString());
                JTextField details = new JTextField(10);
                details.setText((String) packages.getValueAt(packages.getSelectedRow(), 5));
                JTextField limit = new JTextField(10);
                limit.setText(packages.getValueAt(packages.getSelectedRow(), 6).toString());
                JTextField status = new JTextField(10);
                status.setText(packages.getValueAt(packages.getSelectedRow(), 7).toString());

                addToPopUp(panel, name, price, limit, details, start, end);

                while (true) {
                    if (JOptionPane.showConfirmDialog(null, panel, "Please Enter package details",
                            JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                        String message = packageController.edit(id, name.getText(), price.getText(), limit.getText(),
                                details.getText(), start.getText(), end.getText());
                        if (message.equals("Success!"))
                            break;
                        else {
                            JOptionPane.showMessageDialog(null, message);
                        }
                    }
                    else break;
                }
                packages.setModel(packageController.getAllTable());
            } catch(ArrayIndexOutOfBoundsException ignored) {
                JOptionPane.showMessageDialog(null, "Please select a package!");
            }
        });

        showAllButton.addActionListener(e -> packages.setModel(packageController.getAllTable()));
    }

    private void addToPopUp(JPanel panel, JTextField name, JTextField price, JTextField limit, JTextField details, JTextField start, JTextField end) {
        panel.add(new JLabel("\nName: "));
        panel.add(name);
        panel.add(new JLabel("Price: "));
        panel.add(price);
        panel.add(new JLabel("limit: "));
        panel.add(limit);
        panel.add(new JLabel("\nDetails: "));
        panel.add(details);
        panel.add(new JLabel("\nStart date: "));
        panel.add(start);
        panel.add(new JLabel("\nEnd date: "));
        panel.add(end);
    }

    @Override
    public void reset() {

    }
}
