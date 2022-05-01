/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.finalproject.ui;

import com.finalproject.model.BusinessWarehouse;
import com.finalproject.model.PermissionType;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class WarehouseJPanel extends javax.swing.JPanel {
    MainJFrame jFrame;
    BusinessWarehouse warehouse;
    /**
     * Creates new form orderJPanel
     */
    public WarehouseJPanel(MainJFrame jFrame) {
        initComponents();
        this.jFrame = jFrame;

        String className = "Warehouse";
        if (!jFrame.getUser().hasPermission(className, PermissionType.EDIT)) {
            warehouseModifyjButton.setEnabled(false);
        }
        if (!jFrame.getUser().hasPermission(className, PermissionType.CREATE)) {
            warehouseCreatejButton.setEnabled(false);
        }
        if (!jFrame.getUser().hasPermission(className, PermissionType.VIEW)) {
            warehousejButton.setEnabled(false);
        }
        if (!jFrame.getUser().hasPermission(className, PermissionType.DELETE)) {
            warehouseDeletejButton.setEnabled(false);
        }
    }

    public final void displayWarehouse() {
        if (warehouse == null) {
		    createDatePicker.clear();
		    updateDatePicker.clear();
            locationjTextField.setText("");
            availableQuantityjTextField.setText("");
            totalQuantityjTextField.setText("");
        } else {
            warehouse.refresh();
            createDatePicker.setDateTimeStrict(warehouse.getCreateDate());
            updateDatePicker.setDateTimeStrict(warehouse.getUpdateDate());
            locationjTextField.setText(warehouse.getLocation());
            availableQuantityjTextField.setText(String.valueOf(warehouse.getAvailableQuantity()));
            totalQuantityjTextField.setText(String.valueOf(warehouse.getTotalQuantity()));
        }
    }
    
    public final void displayWarehouseList() {
        DefaultTableModel model = (DefaultTableModel) productjTable.getModel();
        model.setRowCount(0);
        
        for (BusinessWarehouse current: BusinessWarehouse.find(BusinessWarehouse.class)) {
            Object[] row = new Object[6];
            row[0] = current.getId();
            row[1] = current.getLocation();
            row[2] = current.getCreateDate();
            row[3] = current.getUpdateDate();
            row[4] = current.getAvailableQuantity();
            row[5] = current.getTotalQuantity();
            
            model.addRow(row);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonjPanel2 = new javax.swing.JPanel();
        warehouseModifyjButton = new javax.swing.JButton();
        warehouseDeletejButton = new javax.swing.JButton();
        warehouseCreatejButton = new javax.swing.JButton();
        warehousejButton = new javax.swing.JButton();
        createDatePicker = new com.github.lgooddatepicker.components.DateTimePicker();
        passwordjLabel1 = new javax.swing.JLabel();
        updateDatePicker = new com.github.lgooddatepicker.components.DateTimePicker();
        passwordjLabel = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        productjTable = new javax.swing.JTable();
        locationjLabel = new javax.swing.JLabel();
        locationjTextField = new javax.swing.JTextField();
        availableQuantityjLabel = new javax.swing.JLabel();
        availableQuantityjTextField = new javax.swing.JTextField();
        totalQuantityjLabel = new javax.swing.JLabel();
        totalQuantityjTextField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(242, 237, 231));

        buttonjPanel2.setBackground(new java.awt.Color(242, 237, 231));

        warehouseModifyjButton.setText("Modify");
        warehouseModifyjButton.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N
        warehouseModifyjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                warehouseModifyjButtonActionPerformed(evt);
            }
        });

        warehouseDeletejButton.setText("Delete");
        warehouseDeletejButton.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N
        warehouseDeletejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                warehouseDeletejButtonActionPerformed(evt);
            }
        });

        warehouseCreatejButton.setText("Create");
        warehouseCreatejButton.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N
        warehouseCreatejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                warehouseCreatejButtonActionPerformed(evt);
            }
        });

        warehousejButton.setText("View");
        warehousejButton.setFocusable(false);
        warehousejButton.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N
        warehousejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                warehousejButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonjPanel2Layout = new javax.swing.GroupLayout(buttonjPanel2);
        buttonjPanel2.setLayout(buttonjPanel2Layout);
        buttonjPanel2Layout.setHorizontalGroup(
            buttonjPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonjPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(warehouseModifyjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warehouseDeletejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warehouseCreatejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(warehousejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );
        buttonjPanel2Layout.setVerticalGroup(
            buttonjPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonjPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(buttonjPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(warehouseModifyjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(warehouseDeletejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(warehouseCreatejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(warehousejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(175, Short.MAX_VALUE))
        );

        createDatePicker.setEnabled(false);

        passwordjLabel1.setText("Update Date");
        passwordjLabel1.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N

        updateDatePicker.setEnabled(false);

        passwordjLabel.setText("Create Date");
        passwordjLabel.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N

        productjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Location", "Create Date", "Update Date", "Available Quantity", "Total Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane11.setViewportView(productjTable);

        locationjLabel.setText("Location");
        locationjLabel.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N

        availableQuantityjLabel.setText("Available Quantity");
        availableQuantityjLabel.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N

        availableQuantityjTextField.setEnabled(false);

        totalQuantityjLabel.setText("Total Quantity");
        totalQuantityjLabel.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordjLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(passwordjLabel)
                                    .addComponent(locationjLabel)
                                    .addComponent(availableQuantityjLabel)
                                    .addComponent(totalQuantityjLabel))
                                .addGap(101, 101, 101)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(updateDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(createDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(locationjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(totalQuantityjTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                                        .addComponent(availableQuantityjTextField, javax.swing.GroupLayout.Alignment.LEADING))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(buttonjPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(234, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(locationjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(locationjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(createDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordjLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(availableQuantityjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(availableQuantityjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalQuantityjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalQuantityjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonjPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void warehouseModifyjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_warehouseModifyjButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = productjTable.getSelectedRow();

        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to view");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) productjTable.getModel();
        int id = (Integer) model.getValueAt(selectedIndex, 0);
        warehouse = BusinessWarehouse.findById(BusinessWarehouse.class, id);

        try {
            warehouse.setCreateDate(createDatePicker.getDateTimeStrict());
            warehouse.setUpdateDate(updateDatePicker.getDateTimeStrict());
            warehouse.setLocation(locationjTextField.getText());
            warehouse.setTotalQuantity(Integer.parseInt(totalQuantityjTextField.getText()));

            warehouse.flush();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Modify error: " + e.getMessage());
            return;
        }
        JOptionPane.showMessageDialog(this, "Modify done");
        warehouse = null;
        displayWarehouse();
        displayWarehouseList();
    }//GEN-LAST:event_warehouseModifyjButtonActionPerformed

    private void warehouseDeletejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_warehouseDeletejButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = productjTable.getSelectedRow();

        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to view");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) productjTable.getModel();
        int id = (Integer) model.getValueAt(selectedIndex, 0);
        warehouse = BusinessWarehouse.findById(BusinessWarehouse.class, id);
        warehouse.delete();
        model.removeRow(selectedIndex);
        warehouse = null;
        displayWarehouse();
        displayWarehouseList();
    }//GEN-LAST:event_warehouseDeletejButtonActionPerformed

    private void warehouseCreatejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_warehouseCreatejButtonActionPerformed
        // TODO add your handling code here:
        BusinessWarehouse bp = new BusinessWarehouse();
        try {
            bp.setCreateDate(createDatePicker.getDateTimeStrict());
            bp.setUpdateDate(updateDatePicker.getDateTimeStrict());
            bp.setLocation(locationjTextField.getText());
            bp.setTotalQuantity(Integer.parseInt(totalQuantityjTextField.getText()));
            bp.save();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Save error: " + e.getMessage());
            return;
        }
        JOptionPane.showMessageDialog(this, "Save done");
        bp = null;
        displayWarehouse();
        warehouse = bp;
        displayWarehouseList();
    }//GEN-LAST:event_warehouseCreatejButtonActionPerformed

    private void warehousejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_warehousejButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = productjTable.getSelectedRow();

        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to view");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) productjTable.getModel();
        int id = (Integer) model.getValueAt(selectedIndex, 0);
        warehouse = BusinessWarehouse.findById(BusinessWarehouse.class, id);
        displayWarehouse();
    }//GEN-LAST:event_warehousejButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel availableQuantityjLabel;
    private javax.swing.JTextField availableQuantityjTextField;
    private javax.swing.JPanel buttonjPanel2;
    private com.github.lgooddatepicker.components.DateTimePicker createDatePicker;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JLabel locationjLabel;
    private javax.swing.JTextField locationjTextField;
    private javax.swing.JLabel passwordjLabel;
    private javax.swing.JLabel passwordjLabel1;
    private javax.swing.JTable productjTable;
    private javax.swing.JLabel totalQuantityjLabel;
    private javax.swing.JTextField totalQuantityjTextField;
    private com.github.lgooddatepicker.components.DateTimePicker updateDatePicker;
    private javax.swing.JButton warehouseCreatejButton;
    private javax.swing.JButton warehouseDeletejButton;
    private javax.swing.JButton warehouseModifyjButton;
    private javax.swing.JButton warehousejButton;
    // End of variables declaration//GEN-END:variables
}
