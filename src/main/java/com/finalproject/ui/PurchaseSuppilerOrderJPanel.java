/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.finalproject.ui;

import com.finalproject.model.BusinessProduct;
import com.finalproject.model.BusinessPurchaseOrder;
import com.finalproject.model.BusinessPurchaseOrderStatusType;
import com.finalproject.model.PermissionType;
import com.finalproject.model.User;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class PurchaseSuppilerOrderJPanel extends javax.swing.JPanel {
    MainJFrame jFrame;
    BusinessPurchaseOrder order;
    /**
     * Creates new form orderJPanel
     */
    public PurchaseSuppilerOrderJPanel(MainJFrame jFrame) {
        initComponents();
        this.jFrame = jFrame;

        String className = "PurchaseOrder";
        if (!jFrame.getUser().hasPermission(className, PermissionType.EDIT)) {
            purchaseOrderModifyjButton.setEnabled(false);
        }
        if (!jFrame.getUser().hasPermission(className, PermissionType.CREATE)) {
            purchaseOrderCreatejButton.setEnabled(false);
        }
        if (!jFrame.getUser().hasPermission(className, PermissionType.VIEW)) {
            purchaseOrderViewjButton.setEnabled(false);
        }
        if (!jFrame.getUser().hasPermission(className, PermissionType.DELETE)) {
            purchaseOrderDeletejButton.setEnabled(false);
        }
    }

    public final void displayOrder() {
        if (order == null) {
            productjComboBox.removeAllItems();
            statusjComboBox.removeAllItems();
            qijComboBox.removeAllItems();
		    for (BusinessProduct product: BusinessProduct.findProducts("Supplier")) {
				productjComboBox.addItem(product);
		    }
            for (BusinessPurchaseOrderStatusType ts: BusinessPurchaseOrderStatusType.values()) {
                statusjComboBox.addItem(ts);
            }
		    for (User u: User.find(User.class)) {
				qijComboBox.addItem(u);
		    }
		    createDatePicker.clear();
		    updateDatePicker.clear();
            totalAmountjTextField.setText("");
            quantityjTextField.setText("");
            descjTextField.setText("");
        } else {
            order.refresh();
            productjComboBox.setSelectedItem(order.getProduct());
            createDatePicker.setDateTimeStrict(order.getCreateDate());
            updateDatePicker.setDateTimeStrict(order.getUpdateDate());
            statusjComboBox.setSelectedItem(order.getOrderStatusType());
            totalAmountjTextField.setText(String.valueOf(order.getTotalAmount() / 100.0));
            quantityjTextField.setText(String.valueOf(order.getQuantity()));
            productjComboBox.setSelectedItem(order.getUser());
            descjTextField.setText(order.getDescription());
        }
    }
    
    public final void displayOrderList() {
        DefaultTableModel model = (DefaultTableModel) orderjTable.getModel();
        model.setRowCount(0);
        
        for (BusinessPurchaseOrder current: BusinessPurchaseOrder.find("Supplier", "Supplier")) {
            Object[] row = new Object[7];
            row[0] = current.getId();
            row[1] = current.getProduct();
            row[2] = current.getCreateDate();
            row[3] = current.getUpdateDate();
            row[4] = current.getOrderStatusType();
            row[5] = current.getTotalAmount() / 100.0;
            row[6] = current.getQuantity();
            
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

                productjComboBox = new javax.swing.JComboBox<>();
                buttonjPanel2 = new javax.swing.JPanel();
                createDatePicker = new com.github.lgooddatepicker.components.DateTimePicker();
                passwordjLabel1 = new javax.swing.JLabel();
                updateDatePicker = new com.github.lgooddatepicker.components.DateTimePicker();
                statusjComboBox = new javax.swing.JComboBox<>();
                passwordjLabel = new javax.swing.JLabel();
                userRolesjLabel = new javax.swing.JLabel();
                jScrollPane11 = new javax.swing.JScrollPane();
                orderjTable = new javax.swing.JTable();
                productjLabel = new javax.swing.JLabel();
                totalAmountjLabel = new javax.swing.JLabel();
                quantityjLabel = new javax.swing.JLabel();
                totalAmountjTextField = new javax.swing.JTextField();
                quantityjTextField = new javax.swing.JTextField();
                quantityInspectorjLabel = new javax.swing.JLabel();
                qijComboBox = new javax.swing.JComboBox<>();
                descjLabel = new javax.swing.JLabel();
                descjTextField = new javax.swing.JTextField();
                purchaseOrderModifyjButton = new javax.swing.JButton();
                purchaseOrderDeletejButton = new javax.swing.JButton();
                purchaseOrderCreatejButton = new javax.swing.JButton();
                purchaseOrderViewjButton = new javax.swing.JButton();

                setBackground(new java.awt.Color(242, 237, 231));

                buttonjPanel2.setBackground(new java.awt.Color(242, 237, 231));

                javax.swing.GroupLayout buttonjPanel2Layout = new javax.swing.GroupLayout(buttonjPanel2);
                buttonjPanel2.setLayout(buttonjPanel2Layout);
                buttonjPanel2Layout.setHorizontalGroup(
                        buttonjPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 84, Short.MAX_VALUE)
                );
                buttonjPanel2Layout.setVerticalGroup(
                        buttonjPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 236, Short.MAX_VALUE)
                );

                createDatePicker.setEnabled(false);

                passwordjLabel1.setText("Update Date");
                passwordjLabel1.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N

                updateDatePicker.setEnabled(false);

                passwordjLabel.setText("Create Date");
                passwordjLabel.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N

                userRolesjLabel.setText("Status");
                userRolesjLabel.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N

                orderjTable.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "ID", "Product", "Create Date", "Update Date", "Status", "Total Amount", "Quantity"
                        }
                ) {
                        Class[] types = new Class [] {
                                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Integer.class
                        };
                        boolean[] canEdit = new boolean [] {
                                false, false, false, false, false, false, false
                        };

                        public Class getColumnClass(int columnIndex) {
                                return types [columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                        }
                });
                orderjTable.setBackground(new java.awt.Color(242, 237, 231));
                jScrollPane11.setViewportView(orderjTable);

                productjLabel.setText("Product");
                productjLabel.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N

                totalAmountjLabel.setText("Total Amount");
                totalAmountjLabel.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N

                quantityjLabel.setText("Quantity");
                quantityjLabel.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N

                totalAmountjTextField.setEnabled(false);

                quantityInspectorjLabel.setText("Quality Inspector");
                quantityInspectorjLabel.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N

                qijComboBox.setEnabled(false);

                descjLabel.setText("Description");
                descjLabel.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N

                purchaseOrderModifyjButton.setText("Modify");
                purchaseOrderModifyjButton.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N
                purchaseOrderModifyjButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                purchaseOrderModifyjButtonActionPerformed(evt);
                        }
                });

                purchaseOrderDeletejButton.setText("Delete");
                purchaseOrderDeletejButton.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N
                purchaseOrderDeletejButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                purchaseOrderDeletejButtonActionPerformed(evt);
                        }
                });

                purchaseOrderCreatejButton.setText("Create");
                purchaseOrderCreatejButton.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N
                purchaseOrderCreatejButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                purchaseOrderCreatejButtonActionPerformed(evt);
                        }
                });

                purchaseOrderViewjButton.setText("View");
                purchaseOrderViewjButton.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N
                purchaseOrderViewjButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                purchaseOrderViewjButtonActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(119, 119, 119)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(passwordjLabel1)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(passwordjLabel)
                                                                        .addComponent(userRolesjLabel)
                                                                        .addComponent(productjLabel)
                                                                        .addComponent(totalAmountjLabel)
                                                                        .addComponent(quantityjLabel))
                                                                .addGap(101, 101, 101)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(updateDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(createDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(productjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(qijComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                .addComponent(descjTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(quantityjTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(totalAmountjTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(statusjComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 166, Short.MAX_VALUE)))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                                                .addComponent(buttonjPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(88, 88, 88))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(descjLabel)
                                                        .addComponent(quantityInspectorjLabel))
                                                .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(180, 180, 180)
                                .addComponent(purchaseOrderModifyjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(purchaseOrderDeletejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(purchaseOrderCreatejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(purchaseOrderViewjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(65, 65, 65)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(productjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(productjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                                        .addComponent(userRolesjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(statusjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(totalAmountjLabel)
                                                        .addComponent(totalAmountjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(14, 14, 14)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(quantityjLabel)
                                                        .addComponent(quantityjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(quantityInspectorjLabel)
                                                        .addComponent(qijComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(descjLabel)
                                                        .addComponent(descjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(19, 19, 19))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(60, 60, 60)
                                                .addComponent(buttonjPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(100, 100, 100)))
                                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(purchaseOrderModifyjButton)
                                        .addComponent(purchaseOrderDeletejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(purchaseOrderCreatejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(purchaseOrderViewjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39))
                );
        }// </editor-fold>//GEN-END:initComponents

    private void purchaseOrderModifyjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseOrderModifyjButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = orderjTable.getSelectedRow();

        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to view");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) orderjTable.getModel();
        int id = (Integer) model.getValueAt(selectedIndex, 0);
        order = BusinessPurchaseOrder.findById(BusinessPurchaseOrder.class, id);

        try {
            order.setProduct((BusinessProduct)productjComboBox.getSelectedItem());
            order.setCreateDate(createDatePicker.getDateTimeStrict());
            order.setUpdateDate(updateDatePicker.getDateTimeStrict());
            order.setOrderStatusType((BusinessPurchaseOrderStatusType)statusjComboBox.getSelectedItem());
            order.setQuantity(Integer.parseInt(quantityjTextField.getText()));
            order.setTotalAmount(order.getQuantity() * order.getProduct().getSellPrice());
            order.setEnterprise(jFrame.getRole().getOrganization().getEnterprise());
            order.setUser(jFrame.getUser());
            order.setDescription(descjTextField.getText());

            order.flush();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Modify error: " + e.getMessage());
            return;
        }
        JOptionPane.showMessageDialog(this, "Modify done");
        order = null;
        displayOrder();
        displayOrderList();
    }//GEN-LAST:event_purchaseOrderModifyjButtonActionPerformed

    private void purchaseOrderDeletejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseOrderDeletejButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = orderjTable.getSelectedRow();

        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to view");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) orderjTable.getModel();
        int id = (Integer) model.getValueAt(selectedIndex, 0);
        order = BusinessPurchaseOrder.findById(BusinessPurchaseOrder.class, id);
        order.delete();
        model.removeRow(selectedIndex);
        order = null;
        displayOrder();
        displayOrderList();
    }//GEN-LAST:event_purchaseOrderDeletejButtonActionPerformed

    private void purchaseOrderCreatejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseOrderCreatejButtonActionPerformed
        // TODO add your handling code here:
        BusinessPurchaseOrder bo = new BusinessPurchaseOrder();
        try {
            bo.setProduct((BusinessProduct)productjComboBox.getSelectedItem());
            bo.setCreateDate(createDatePicker.getDateTimeStrict());
            bo.setUpdateDate(updateDatePicker.getDateTimeStrict());
            bo.setOrderStatusType((BusinessPurchaseOrderStatusType)statusjComboBox.getSelectedItem());
            bo.setQuantity(Integer.parseInt(quantityjTextField.getText()));
            bo.setTotalAmount(bo.getQuantity() * bo.getProduct().getSellPrice());
            bo.setEnterprise(jFrame.getRole().getOrganization().getEnterprise());
            bo.setDescription(descjTextField.getText());
            bo.setUser(jFrame.getUser());
            bo.save();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Save error: " + e.getMessage());
            return;
        }
        JOptionPane.showMessageDialog(this, "Save done");
        bo = null;
        displayOrder();
        order = bo;
        displayOrderList();
    }//GEN-LAST:event_purchaseOrderCreatejButtonActionPerformed

    private void purchaseOrderViewjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseOrderViewjButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = orderjTable.getSelectedRow();

        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to view");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) orderjTable.getModel();
        int id = (Integer) model.getValueAt(selectedIndex, 0);
        order = BusinessPurchaseOrder.findById(BusinessPurchaseOrder.class, id);
        displayOrder();
    }//GEN-LAST:event_purchaseOrderViewjButtonActionPerformed


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JPanel buttonjPanel2;
        private com.github.lgooddatepicker.components.DateTimePicker createDatePicker;
        private javax.swing.JLabel descjLabel;
        private javax.swing.JTextField descjTextField;
        private javax.swing.JScrollPane jScrollPane11;
        private javax.swing.JTable orderjTable;
        private javax.swing.JLabel passwordjLabel;
        private javax.swing.JLabel passwordjLabel1;
        private javax.swing.JComboBox<com.finalproject.model.BusinessProduct> productjComboBox;
        private javax.swing.JLabel productjLabel;
        private javax.swing.JButton purchaseOrderCreatejButton;
        private javax.swing.JButton purchaseOrderDeletejButton;
        private javax.swing.JButton purchaseOrderModifyjButton;
        private javax.swing.JButton purchaseOrderViewjButton;
        private javax.swing.JComboBox<User> qijComboBox;
        private javax.swing.JLabel quantityInspectorjLabel;
        private javax.swing.JLabel quantityjLabel;
        private javax.swing.JTextField quantityjTextField;
        private javax.swing.JComboBox<BusinessPurchaseOrderStatusType> statusjComboBox;
        private javax.swing.JLabel totalAmountjLabel;
        private javax.swing.JTextField totalAmountjTextField;
        private com.github.lgooddatepicker.components.DateTimePicker updateDatePicker;
        private javax.swing.JLabel userRolesjLabel;
        // End of variables declaration//GEN-END:variables
}
