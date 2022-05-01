/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.finalproject.ui;

import com.finalproject.model.BusinessProduct;
import com.finalproject.model.BusinessProductStatusType;
import com.finalproject.model.PermissionType;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class ProductJPanel extends javax.swing.JPanel {
    MainJFrame jFrame;
    BusinessProduct product;
    /**
     * Creates new form orderJPanel
     */
    public ProductJPanel(MainJFrame jFrame) {
        initComponents();
        this.jFrame = jFrame;

        String className = "Product";
        if (!jFrame.getUser().hasPermission(className, PermissionType.EDIT)) {
            productModifyjButton.setEnabled(false);
        }
        if (!jFrame.getUser().hasPermission(className, PermissionType.CREATE)) {
            productCreatejButton.setEnabled(false);
        }
        if (!jFrame.getUser().hasPermission(className, PermissionType.VIEW)) {
            productViewjButton.setEnabled(false);
        }
        if (!jFrame.getUser().hasPermission(className, PermissionType.DELETE)) {
            productDeletejButton.setEnabled(false);
        }
        if (!jFrame.getUser().hasPermission("Product.status", PermissionType.EDIT)) {
            statusjComboBox.setEnabled(false);
        }
        if (!jFrame.getUser().hasPermission("Product.purchasePrice", PermissionType.EDIT)) {
            purchasePricejTextField.setEnabled(false);
        }
        if (!jFrame.getUser().hasPermission("Product.sellPrice", PermissionType.EDIT)) {
            sellPricejTextField.setEnabled(false);
        }
    }

    public final void displayProduct() {
        if (product == null) {
            statusjComboBox.removeAllItems();
            for (BusinessProductStatusType ts: BusinessProductStatusType.values()) {
                statusjComboBox.addItem(ts);
            }
		    createDatePicker.clear();
		    updateDatePicker.clear();
            namejTextField.setText("");
            purchasePricejTextField.setText("");
            sellPricejTextField.setText("");
        } else {
            product.refresh();
            createDatePicker.setDateTimeStrict(product.getCreateDate());
            updateDatePicker.setDateTimeStrict(product.getUpdateDate());
            statusjComboBox.setSelectedItem(product.getProductStatusType());
            namejTextField.setText(product.getName());
            purchasePricejTextField.setText(String.valueOf(product.getPurchasePrice() / 100.0));
            sellPricejTextField.setText(String.valueOf(product.getSellPrice() / 100.0));
        }
    }
    
    public final void displayProductList() {
        DefaultTableModel model = (DefaultTableModel) productjTable.getModel();
        model.setRowCount(0);
        
        for (BusinessProduct current: BusinessProduct.findProducts("Digital Platform")) {
            Object[] row = new Object[7];
            row[0] = current.getId();
            row[1] = current.getName();
            row[2] = current.getCreateDate();
            row[3] = current.getUpdateDate();
            row[4] = current.getProductStatusType();
            row[5] = current.getPurchasePrice() / 100.0;
            row[6] = current.getSellPrice() / 100.0;
            
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
        createDatePicker = new com.github.lgooddatepicker.components.DateTimePicker();
        passwordjLabel1 = new javax.swing.JLabel();
        updateDatePicker = new com.github.lgooddatepicker.components.DateTimePicker();
        statusjComboBox = new javax.swing.JComboBox<>();
        passwordjLabel = new javax.swing.JLabel();
        userRolesjLabel = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        productjTable = new javax.swing.JTable();
        namejLabel = new javax.swing.JLabel();
        namejTextField = new javax.swing.JTextField();
        purchasePricejLabel = new javax.swing.JLabel();
        purchasePricejTextField = new javax.swing.JTextField();
        sellPricejLabel = new javax.swing.JLabel();
        sellPricejTextField = new javax.swing.JTextField();
        productModifyjButton = new javax.swing.JButton();
        productDeletejButton = new javax.swing.JButton();
        productCreatejButton = new javax.swing.JButton();
        productViewjButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(242, 237, 231));

        buttonjPanel2.setBackground(new java.awt.Color(242, 237, 231));

        javax.swing.GroupLayout buttonjPanel2Layout = new javax.swing.GroupLayout(buttonjPanel2);
        buttonjPanel2.setLayout(buttonjPanel2Layout);
        buttonjPanel2Layout.setHorizontalGroup(
            buttonjPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );
        buttonjPanel2Layout.setVerticalGroup(
            buttonjPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 115, Short.MAX_VALUE)
        );

        createDatePicker.setEnabled(false);

        passwordjLabel1.setText("Update Date");
        passwordjLabel1.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N

        updateDatePicker.setEnabled(false);

        passwordjLabel.setText("Create Date");
        passwordjLabel.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N

        userRolesjLabel.setText("Status");
        userRolesjLabel.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N

        productjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Product", "Create Date", "Update Date", "Status", "Purchase Price", "Sell Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane11.setViewportView(productjTable);

        namejLabel.setText("Name");
        namejLabel.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N

        purchasePricejLabel.setText("Purchase Price");
        purchasePricejLabel.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N

        sellPricejLabel.setText("Sell Price");
        sellPricejLabel.setDoubleBuffered(true);
        sellPricejLabel.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N

        productModifyjButton.setText("Modify");
        productModifyjButton.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N
        productModifyjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productModifyjButtonActionPerformed(evt);
            }
        });

        productDeletejButton.setText("Delete");
        productDeletejButton.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N
        productDeletejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productDeletejButtonActionPerformed(evt);
            }
        });

        productCreatejButton.setText("Create");
        productCreatejButton.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N
        productCreatejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productCreatejButtonActionPerformed(evt);
            }
        });

        productViewjButton.setText("View");
        productViewjButton.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N
        productViewjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productViewjButtonActionPerformed(evt);
            }
        });

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
                                    .addComponent(userRolesjLabel)
                                    .addComponent(namejLabel)
                                    .addComponent(purchasePricejLabel)
                                    .addComponent(sellPricejLabel))
                                .addGap(101, 101, 101)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(updateDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(createDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(namejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(sellPricejTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(purchasePricejTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(statusjComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 240, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(buttonjPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(productModifyjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productDeletejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(productCreatejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productViewjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(261, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namejLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(namejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(purchasePricejLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(purchasePricejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sellPricejLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sellPricejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productModifyjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productDeletejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productCreatejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productViewjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonjPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void productModifyjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productModifyjButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = productjTable.getSelectedRow();

        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to view");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) productjTable.getModel();
        int id = (Integer) model.getValueAt(selectedIndex, 0);
        product = BusinessProduct.findById(BusinessProduct.class, id);

        try {
            product.setCreateDate(createDatePicker.getDateTimeStrict());
            product.setUpdateDate(updateDatePicker.getDateTimeStrict());
            product.setProductStatusType((BusinessProductStatusType)statusjComboBox.getSelectedItem());
            product.setName(namejTextField.getText());
            product.setPurchasePrice((int)(Double.parseDouble(purchasePricejTextField.getText()) * 100));
            product.setSellPrice((int)(Double.parseDouble(sellPricejTextField.getText()) * 100));
            product.setEnterprise(jFrame.getRole().getOrganization().getEnterprise());

            product.flush();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Modify error: " + e.getMessage());
            return;
        }
        JOptionPane.showMessageDialog(this, "Modify done");
        product = null;
        displayProduct();
        displayProductList();
    }//GEN-LAST:event_productModifyjButtonActionPerformed

    private void productDeletejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productDeletejButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = productjTable.getSelectedRow();

        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to view");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) productjTable.getModel();
        int id = (Integer) model.getValueAt(selectedIndex, 0);
        product = BusinessProduct.findById(BusinessProduct.class, id);
        product.delete();
        model.removeRow(selectedIndex);
        product = null;
        displayProduct();
        displayProductList();
    }//GEN-LAST:event_productDeletejButtonActionPerformed

    private void productCreatejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productCreatejButtonActionPerformed
        // TODO add your handling code here:
        BusinessProduct bp = new BusinessProduct();
        try {
            bp.setCreateDate(createDatePicker.getDateTimeStrict());
            bp.setUpdateDate(updateDatePicker.getDateTimeStrict());
            bp.setProductStatusType((BusinessProductStatusType)statusjComboBox.getSelectedItem());
            bp.setName(namejTextField.getText());
            bp.setPurchasePrice((int)(Double.parseDouble(purchasePricejTextField.getText()) * 100));
            bp.setSellPrice((int)(Double.parseDouble(sellPricejTextField.getText()) * 100));
            bp.setEnterprise(jFrame.getRole().getOrganization().getEnterprise());
            bp.save();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Save error: " + e.getMessage());
            return;
        }
        JOptionPane.showMessageDialog(this, "Save done");
        bp = null;
        displayProduct();
        product = bp;
        displayProductList();
    }//GEN-LAST:event_productCreatejButtonActionPerformed

    private void productViewjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productViewjButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = productjTable.getSelectedRow();

        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to view");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) productjTable.getModel();
        int id = (Integer) model.getValueAt(selectedIndex, 0);
        product = BusinessProduct.findById(BusinessProduct.class, id);
        displayProduct();
    }//GEN-LAST:event_productViewjButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonjPanel2;
    private com.github.lgooddatepicker.components.DateTimePicker createDatePicker;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JLabel namejLabel;
    private javax.swing.JTextField namejTextField;
    private javax.swing.JLabel passwordjLabel;
    private javax.swing.JLabel passwordjLabel1;
    private javax.swing.JButton productCreatejButton;
    private javax.swing.JButton productDeletejButton;
    private javax.swing.JButton productModifyjButton;
    private javax.swing.JButton productViewjButton;
    private javax.swing.JTable productjTable;
    private javax.swing.JLabel purchasePricejLabel;
    private javax.swing.JTextField purchasePricejTextField;
    private javax.swing.JLabel sellPricejLabel;
    private javax.swing.JTextField sellPricejTextField;
    private javax.swing.JComboBox<BusinessProductStatusType> statusjComboBox;
    private com.github.lgooddatepicker.components.DateTimePicker updateDatePicker;
    private javax.swing.JLabel userRolesjLabel;
    // End of variables declaration//GEN-END:variables
}
