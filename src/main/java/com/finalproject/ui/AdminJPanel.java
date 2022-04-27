/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.finalproject.ui;

import com.finalproject.model.Organization;
import com.finalproject.model.Permission;
import com.finalproject.model.PermissionType;
import com.finalproject.model.Role;
import com.finalproject.model.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class AdminJPanel extends javax.swing.JPanel {
    MainJFrame jFrame;
    Permission permission;
    Role role;
    User user;
    /**
     * Creates new form adminJPanel
     */
    public AdminJPanel(MainJFrame jFrame) {
        initComponents();
        this.jFrame = jFrame;
        DataJPanel dataJPanel = new DataJPanel();
        jTabbedPane.add("Data", dataJPanel);
        jTabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int index = jTabbedPane.getSelectedIndex();
                switch (index) {
                    case 0:
                        otherRolesjList.setModel(new DefaultListModel<>());
                        currentRolesjList.setModel(new DefaultListModel<>());
                        displayUser();
                        displayUserList();
                        break;
                    case 1:
                        otherUserjList.setModel(new DefaultListModel<>());
                        otherPMjList.setModel(new DefaultListModel<>());
                        currentPMjList.setModel(new DefaultListModel<>());
                        currentUserjList.setModel(new DefaultListModel<>());
                        displayRole();
                        displayRoleList();
                        break;
                    case 2:
                        otherjList.setModel(new DefaultListModel<>());
                        currentjList.setModel(new DefaultListModel<>());
                        displayPermission();
                        displayPermissionList();
                        break;
                    case 3:
                        dataJPanel.displayMonthlyOrderData();
                        dataJPanel.displayOrderStatusData();
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        });

        String className = "User";
        if (!jFrame.getUser().hasPermission(className, PermissionType.EDIT)) {
            userModifyjButton.setEnabled(false);
        }
        if (!jFrame.getUser().hasPermission(className, PermissionType.CREATE)) {
            userCreatejButton.setEnabled(false);
        }
        if (!jFrame.getUser().hasPermission(className, PermissionType.VIEW)) {
            userViewjButton.setEnabled(false);
        }
        if (!jFrame.getUser().hasPermission(className, PermissionType.DELETE)) {
            userDeletejButton.setEnabled(false);
        }
        className = "Role";
        if (!jFrame.getUser().hasPermission(className, PermissionType.EDIT)) {
            roleModifyjButton.setEnabled(false);
        }
        if (!jFrame.getUser().hasPermission(className, PermissionType.CREATE)) {
            roleCreatejButton.setEnabled(false);
        }
        if (!jFrame.getUser().hasPermission(className, PermissionType.VIEW)) {
            roleViewjButton.setEnabled(false);
        }
        if (!jFrame.getUser().hasPermission(className, PermissionType.DELETE)) {
            roleDeletejButton.setEnabled(false);
        }
        className = "Permission";
        if (!jFrame.getUser().hasPermission(className, PermissionType.EDIT)) {
            pmModifyjButton.setEnabled(false);
        }
        if (!jFrame.getUser().hasPermission(className, PermissionType.CREATE)) {
            pmCreatejButton.setEnabled(false);
        }
        if (!jFrame.getUser().hasPermission(className, PermissionType.VIEW)) {
            pmViewjButton.setEnabled(false);
        }
        if (!jFrame.getUser().hasPermission(className, PermissionType.DELETE)) {
            pmDeletejButton.setEnabled(false);
        }

        otherRolesjList.setModel(new DefaultListModel<>());
        currentRolesjList.setModel(new DefaultListModel<>());
        displayUser();
        displayUserList();
    }
    
    public void displayPermission() {
        if (permission == null) {
            namejTextField.setText("");
            permissionTypejComboBox.removeAllItems();
            ((DefaultListModel<Role>)otherjList.getModel()).removeAllElements();
            ((DefaultListModel<Role>)currentjList.getModel()).removeAllElements();
            for (PermissionType pt: PermissionType.values()) {
                permissionTypejComboBox.addItem(pt);
            }
            for (Role r: Role.find(Role.class)) {
                ((DefaultListModel<Role>)otherjList.getModel()).addElement(r);
            }
        } else {
            permission.refresh();
            namejTextField.setText(permission.getName());
            permissionTypejComboBox.setSelectedItem(permission.getPermissionType());
            ((DefaultListModel<Role>)otherjList.getModel()).removeAllElements();
            ((DefaultListModel<Role>)currentjList.getModel()).removeAllElements();
            List<Role> roles = permission.getRoles();
            for (Role r: roles) {
                ((DefaultListModel<Role>)currentjList.getModel()).addElement(r);
            }
            for (Role r: Role.find(Role.class)) {
                if (!roles.contains(r)) {
                    ((DefaultListModel<Role>)otherjList.getModel()).addElement(r);
                }
            }
        }
    }
    
    public void displayPermissionList() {
        DefaultTableModel model = (DefaultTableModel) pmjTable.getModel();
        model.setRowCount(0);
        
        for (Permission current: Permission.find(Permission.class)) {
            Object[] row = new Object[3];
            row[0] = current.getId();
            row[1] = current.getName();
            row[2] = current.getPermissionType();
            
            model.addRow(row);
        }
    }
    
    public void displayRole() {
        if (role == null) {
            roleNamejTextField.setText("");
            organizationjComboBox.removeAllItems();
            ((DefaultListModel<User>)otherUserjList.getModel()).removeAllElements();
            ((DefaultListModel<Permission>)otherPMjList.getModel()).removeAllElements();
            ((DefaultListModel<User>)currentUserjList.getModel()).removeAllElements();
            ((DefaultListModel<Permission>)currentPMjList.getModel()).removeAllElements();
            for (Organization organization: Organization.find(Organization.class)) {
                organizationjComboBox.addItem(organization);
            }
            for (User r: User.find(User.class)) {
                ((DefaultListModel<User>)otherUserjList.getModel()).addElement(r);
            }
            for (Permission r: Permission.find(Permission.class)) {
                ((DefaultListModel<Permission>)otherPMjList.getModel()).addElement(r);
            }
        } else {
            role.refresh();
            roleNamejTextField.setText(role.getName());
            organizationjComboBox.setSelectedItem(role.getOrganization());
            ((DefaultListModel<User>)otherUserjList.getModel()).removeAllElements();
            ((DefaultListModel<Permission>)otherPMjList.getModel()).removeAllElements();
            ((DefaultListModel<User>)currentUserjList.getModel()).removeAllElements();
            ((DefaultListModel<Permission>)currentPMjList.getModel()).removeAllElements();
            List<Permission> permissions = role.getPermissions();
            List<User> users = role.getUsers();
            for (Permission r: permissions) {
                ((DefaultListModel<Permission>)currentPMjList.getModel()).addElement(r);
            }
            for (User r: users) {
                ((DefaultListModel<User>)currentUserjList.getModel()).addElement(r);
            }
            for (Permission r: Permission.find(Permission.class)) {
                if (!permissions.contains(r)) {
                    ((DefaultListModel<Permission>)otherPMjList.getModel()).addElement(r);
                }
            }
            for (User r: User.find(User.class)) {
                if (!users.contains(r)) {
                    ((DefaultListModel<User>)otherUserjList.getModel()).addElement(r);
                }
            }
        }
    }
    
    public void displayRoleList() {
        DefaultTableModel model = (DefaultTableModel) rolejTable.getModel();
        model.setRowCount(0);
        
        for (Role current: Role.find(Role.class)) {
            Object[] row = new Object[3];
            row[0] = current.getId();
            row[1] = current.getName();
            row[2] = current.getOrganization();
            
            model.addRow(row);
        }
    }
    
    public void displayUser() {
        if (user == null) {
            userNamejTextField.setText("");
            passwordjTextField.setText("");
            
            ((DefaultListModel<Role>)otherRolesjList.getModel()).removeAllElements();
            ((DefaultListModel<Role>)currentRolesjList.getModel()).removeAllElements();
            for (Role r: Role.find(Role.class)) {
                ((DefaultListModel<Role>)otherRolesjList.getModel()).addElement(r);
            }
        } else {
            user.refresh();
            userNamejTextField.setText(user.getUsername());
            passwordjTextField.setText(user.getPassword());
            ((DefaultListModel<Role>)otherRolesjList.getModel()).removeAllElements();
            ((DefaultListModel<Role>)currentRolesjList.getModel()).removeAllElements();
            List<Role> roles = user.getRoles();
            for (Role r: roles) {
                ((DefaultListModel<Role>)currentRolesjList.getModel()).addElement(r);
            }
            for (Role r: Role.find(Role.class)) {
                if (!roles.contains(r)) {
                    ((DefaultListModel<Role>)otherRolesjList.getModel()).addElement(r);
                }
            }
        }
    }
    
    public void displayUserList() {
        DefaultTableModel model = (DefaultTableModel) userjTable.getModel();
        model.setRowCount(0);
        
        for (User current: User.find(User.class)) {
            Object[] row = new Object[3];
            row[0] = current.getId();
            row[1] = current.getUsername();
            row[2] = current.getPassword();
            
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

        jTabbedPane = new javax.swing.JTabbedPane();
        rolejPanel = new javax.swing.JPanel();
        roleNamejTextField = new javax.swing.JTextField();
        buttonjPanel1 = new javax.swing.JPanel();
        roleModifyjButton = new javax.swing.JButton();
        roleDeletejButton = new javax.swing.JButton();
        roleCreatejButton = new javax.swing.JButton();
        roleViewjButton = new javax.swing.JButton();
        organizationjLabel = new javax.swing.JLabel();
        permissionsjLabel = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        otherPMjList = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        currentPMjList = new javax.swing.JList<>();
        organizationjComboBox = new javax.swing.JComboBox<>();
        pmToRightjButton = new javax.swing.JButton();
        pmToLeftjButton = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        rolejTable = new javax.swing.JTable();
        roleNamejLabel = new javax.swing.JLabel();
        usersjLabel = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        otherUserjList = new javax.swing.JList<>();
        usersToRightjButton = new javax.swing.JButton();
        usersToLeftjButton = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        currentUserjList = new javax.swing.JList<>();
        permissionjPanel = new javax.swing.JPanel();
        namejLabel = new javax.swing.JLabel();
        namejTextField = new javax.swing.JTextField();
        permissionTypejLabel = new javax.swing.JLabel();
        rolesjLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        otherjList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        currentjList = new javax.swing.JList<>();
        toRightjButton = new javax.swing.JButton();
        toLeftjButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        pmjTable = new javax.swing.JTable();
        buttonjPanel = new javax.swing.JPanel();
        pmModifyjButton = new javax.swing.JButton();
        pmDeletejButton = new javax.swing.JButton();
        pmCreatejButton = new javax.swing.JButton();
        pmViewjButton = new javax.swing.JButton();
        permissionTypejComboBox = new javax.swing.JComboBox<>();
        userjPanel = new javax.swing.JPanel();
        userNamejTextField = new javax.swing.JTextField();
        buttonjPanel2 = new javax.swing.JPanel();
        userModifyjButton = new javax.swing.JButton();
        userDeletejButton = new javax.swing.JButton();
        userCreatejButton = new javax.swing.JButton();
        userViewjButton = new javax.swing.JButton();
        passwordjLabel = new javax.swing.JLabel();
        userRolesjLabel = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        otherRolesjList = new javax.swing.JList<>();
        jScrollPane10 = new javax.swing.JScrollPane();
        currentRolesjList = new javax.swing.JList<>();
        toRolesRightjButton = new javax.swing.JButton();
        toRolesLeftjButton = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        userjTable = new javax.swing.JTable();
        userNamejLabel = new javax.swing.JLabel();
        passwordjTextField = new javax.swing.JTextField();

        jTabbedPane.setBackground(new java.awt.Color(242, 237, 231));

        roleNamejTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roleNamejTextFieldActionPerformed(evt);
            }
        });

        roleModifyjButton.setText("modify");
        roleModifyjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roleModifyjButtonActionPerformed(evt);
            }
        });

        roleDeletejButton.setText("delete");
        roleDeletejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roleDeletejButtonActionPerformed(evt);
            }
        });

        roleCreatejButton.setText("create");
        roleCreatejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roleCreatejButtonActionPerformed(evt);
            }
        });

        roleViewjButton.setText("view");
        roleViewjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roleViewjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonjPanel1Layout = new javax.swing.GroupLayout(buttonjPanel1);
        buttonjPanel1.setLayout(buttonjPanel1Layout);
        buttonjPanel1Layout.setHorizontalGroup(
            buttonjPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonjPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonjPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roleModifyjButton, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roleViewjButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roleCreatejButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roleDeletejButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        buttonjPanel1Layout.setVerticalGroup(
            buttonjPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonjPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(roleModifyjButton)
                .addGap(18, 18, 18)
                .addComponent(roleDeletejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roleCreatejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roleViewjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        organizationjLabel.setText("organization");

        permissionsjLabel.setText("permissions");

        jScrollPane4.setViewportView(otherPMjList);

        jScrollPane5.setViewportView(currentPMjList);

        pmToRightjButton.setText(">>");
        pmToRightjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmToRightjButtonActionPerformed(evt);
            }
        });

        pmToLeftjButton.setText("<<");
        pmToLeftjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmToLeftjButtonActionPerformed(evt);
            }
        });

        rolejTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "name", "organization"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(rolejTable);

        roleNamejLabel.setText("name");

        usersjLabel.setText("users");

        jScrollPane7.setViewportView(otherUserjList);

        usersToRightjButton.setText(">>");
        usersToRightjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usersToRightjButtonActionPerformed(evt);
            }
        });

        usersToLeftjButton.setText("<<");
        usersToLeftjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usersToLeftjButtonActionPerformed(evt);
            }
        });

        jScrollPane8.setViewportView(currentUserjList);

        javax.swing.GroupLayout rolejPanelLayout = new javax.swing.GroupLayout(rolejPanel);
        rolejPanel.setLayout(rolejPanelLayout);
        rolejPanelLayout.setHorizontalGroup(
            rolejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rolejPanelLayout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addGroup(rolejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rolejPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rolejPanelLayout.createSequentialGroup()
                        .addGroup(rolejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roleNamejLabel)
                            .addComponent(organizationjLabel)
                            .addComponent(permissionsjLabel)
                            .addComponent(usersjLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(rolejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rolejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(rolejPanelLayout.createSequentialGroup()
                                    .addGroup(rolejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(pmToLeftjButton, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rolejPanelLayout.createSequentialGroup()
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(12, 12, 12)
                                            .addComponent(pmToRightjButton)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(rolejPanelLayout.createSequentialGroup()
                                    .addGroup(rolejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rolejPanelLayout.createSequentialGroup()
                                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(12, 12, 12)
                                            .addComponent(usersToRightjButton))
                                        .addComponent(usersToLeftjButton, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(rolejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(organizationjComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(roleNamejTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)))
                        .addGap(59, 59, 59)
                        .addComponent(buttonjPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(88, 88, 88))
        );
        rolejPanelLayout.setVerticalGroup(
            rolejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rolejPanelLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(rolejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roleNamejLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roleNamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(rolejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(organizationjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(organizationjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(rolejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rolejPanelLayout.createSequentialGroup()
                        .addComponent(permissionsjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(80, 80, 80))
                    .addGroup(rolejPanelLayout.createSequentialGroup()
                        .addGroup(rolejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(rolejPanelLayout.createSequentialGroup()
                                .addComponent(pmToRightjButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addComponent(pmToLeftjButton))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(27, 27, 27)))
                .addGroup(rolejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rolejPanelLayout.createSequentialGroup()
                        .addComponent(usersjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(71, 71, 71))
                    .addGroup(rolejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(rolejPanelLayout.createSequentialGroup()
                            .addComponent(usersToRightjButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                            .addComponent(usersToLeftjButton))
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(305, 305, 305))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rolejPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(buttonjPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(459, 459, 459))
        );

        jTabbedPane.addTab("Role", rolejPanel);

        namejLabel.setText("name");

        namejTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namejTextFieldActionPerformed(evt);
            }
        });

        permissionTypejLabel.setText("permission type");

        rolesjLabel.setText("roles");

        jScrollPane1.setViewportView(otherjList);

        jScrollPane2.setViewportView(currentjList);

        toRightjButton.setText(">>");
        toRightjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toRightjButtonActionPerformed(evt);
            }
        });

        toLeftjButton.setText("<<");
        toLeftjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toLeftjButtonActionPerformed(evt);
            }
        });

        pmjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "name", "permission type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(pmjTable);

        pmModifyjButton.setText("modify");
        pmModifyjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmModifyjButtonActionPerformed(evt);
            }
        });

        pmDeletejButton.setText("delete");
        pmDeletejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmDeletejButtonActionPerformed(evt);
            }
        });

        pmCreatejButton.setText("create");
        pmCreatejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmCreatejButtonActionPerformed(evt);
            }
        });

        pmViewjButton.setText("view");
        pmViewjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmViewjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonjPanelLayout = new javax.swing.GroupLayout(buttonjPanel);
        buttonjPanel.setLayout(buttonjPanelLayout);
        buttonjPanelLayout.setHorizontalGroup(
            buttonjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonjPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pmModifyjButton, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pmViewjButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pmCreatejButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pmDeletejButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        buttonjPanelLayout.setVerticalGroup(
            buttonjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonjPanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(pmModifyjButton)
                .addGap(18, 18, 18)
                .addComponent(pmDeletejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pmCreatejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pmViewjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout permissionjPanelLayout = new javax.swing.GroupLayout(permissionjPanel);
        permissionjPanel.setLayout(permissionjPanelLayout);
        permissionjPanelLayout.setHorizontalGroup(
            permissionjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(permissionjPanelLayout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addGroup(permissionjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(permissionjPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(permissionjPanelLayout.createSequentialGroup()
                        .addGroup(permissionjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(namejLabel)
                            .addComponent(permissionTypejLabel)
                            .addComponent(rolesjLabel))
                        .addGap(126, 126, 126)
                        .addGroup(permissionjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(permissionjPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(permissionjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(toRightjButton, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(toLeftjButton, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49))
                            .addGroup(permissionjPanelLayout.createSequentialGroup()
                                .addGroup(permissionjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(namejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(permissionTypejComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(buttonjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(88, 88, 88))
        );
        permissionjPanelLayout.setVerticalGroup(
            permissionjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(permissionjPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(permissionjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(permissionjPanelLayout.createSequentialGroup()
                        .addGroup(permissionjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(permissionjPanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(namejLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(namejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(permissionjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(permissionjPanelLayout.createSequentialGroup()
                                .addComponent(permissionTypejLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(19, 19, 19))
                            .addGroup(permissionjPanelLayout.createSequentialGroup()
                                .addComponent(permissionTypejComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(permissionjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(permissionjPanelLayout.createSequentialGroup()
                                .addComponent(rolesjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(191, 191, 191))
                            .addGroup(permissionjPanelLayout.createSequentialGroup()
                                .addGroup(permissionjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(permissionjPanelLayout.createSequentialGroup()
                                        .addComponent(toRightjButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(toLeftjButton))
                                    .addComponent(jScrollPane2)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(57, 57, 57))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, permissionjPanelLayout.createSequentialGroup()
                        .addComponent(buttonjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(300, 300, 300))
        );

        jTabbedPane.addTab("Permisson", permissionjPanel);

        userjPanel.setBackground(new java.awt.Color(242, 237, 231));

        userNamejTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNamejTextFieldActionPerformed(evt);
            }
        });

        buttonjPanel2.setBackground(new java.awt.Color(242, 237, 231));

        userModifyjButton.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N
        userModifyjButton.setText("Modify");
        userModifyjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userModifyjButtonActionPerformed(evt);
            }
        });

        userDeletejButton.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N
        userDeletejButton.setText("Delete");
        userDeletejButton.setMaximumSize(new java.awt.Dimension(92, 29));
        userDeletejButton.setMinimumSize(new java.awt.Dimension(92, 29));
        userDeletejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userDeletejButtonActionPerformed(evt);
            }
        });

        userCreatejButton.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N
        userCreatejButton.setText("Create");
        userCreatejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userCreatejButtonActionPerformed(evt);
            }
        });

        userViewjButton.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N
        userViewjButton.setText("View");
        userViewjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userViewjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonjPanel2Layout = new javax.swing.GroupLayout(buttonjPanel2);
        buttonjPanel2.setLayout(buttonjPanel2Layout);
        buttonjPanel2Layout.setHorizontalGroup(
            buttonjPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonjPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userModifyjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(userDeletejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(userCreatejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(userViewjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        buttonjPanel2Layout.setVerticalGroup(
            buttonjPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonjPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(buttonjPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userModifyjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userDeletejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userCreatejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userViewjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        passwordjLabel.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N
        passwordjLabel.setText("Password");

        userRolesjLabel.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N
        userRolesjLabel.setText("Roles");

        jScrollPane9.setViewportView(otherRolesjList);

        jScrollPane10.setViewportView(currentRolesjList);

        toRolesRightjButton.setText(">>");
        toRolesRightjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toRolesRightjButtonActionPerformed(evt);
            }
        });

        toRolesLeftjButton.setText("<<");
        toRolesLeftjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toRolesLeftjButtonActionPerformed(evt);
            }
        });

        userjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Username", "Password"
            }
        ));
        jScrollPane11.setViewportView(userjTable);

        userNamejLabel.setFont(new java.awt.Font("Chalkboard SE", 0, 14)); // NOI18N
        userNamejLabel.setText("Username");

        javax.swing.GroupLayout userjPanelLayout = new javax.swing.GroupLayout(userjPanel);
        userjPanel.setLayout(userjPanelLayout);
        userjPanelLayout.setHorizontalGroup(
            userjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userjPanelLayout.createSequentialGroup()
                .addGroup(userjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(userjPanelLayout.createSequentialGroup()
                            .addGap(119, 119, 119)
                            .addGroup(userjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(userNamejLabel)
                                .addComponent(passwordjLabel)
                                .addComponent(userRolesjLabel))
                            .addGap(122, 122, 122)
                            .addGroup(userjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(userNamejTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                                .addComponent(passwordjTextField)))
                        .addGroup(userjPanelLayout.createSequentialGroup()
                            .addGap(198, 198, 198)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userjPanelLayout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(37, 37, 37)
                            .addGroup(userjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(toRolesLeftjButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(toRolesRightjButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(36, 36, 36)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(42, 42, 42)))
                    .addGroup(userjPanelLayout.createSequentialGroup()
                        .addGap(261, 261, 261)
                        .addComponent(buttonjPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(447, Short.MAX_VALUE))
        );
        userjPanelLayout.setVerticalGroup(
            userjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userjPanelLayout.createSequentialGroup()
                .addGroup(userjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userjPanelLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(userNamejLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userjPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(userNamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(userjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passwordjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(userjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userjPanelLayout.createSequentialGroup()
                        .addComponent(userRolesjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(750, 750, 750))
                    .addGroup(userjPanelLayout.createSequentialGroup()
                        .addGroup(userjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(userjPanelLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(toRolesRightjButton)
                                .addGap(72, 72, 72)
                                .addComponent(toRolesLeftjButton))
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonjPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(354, 354, 354))))
        );

        jTabbedPane.addTab("User", userjPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void namejTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namejTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namejTextFieldActionPerformed

    private void pmCreatejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmCreatejButtonActionPerformed
        // TODO add your handling code here:
        Permission pm = new Permission();
        try {
            pm.setName(namejTextField.getText());
            pm.setPermissionType((PermissionType)permissionTypejComboBox.getSelectedItem());
            List<Role> afterRoles = new ArrayList<>();
            for (Object roleObject: ((DefaultListModel<Role>)currentjList.getModel()).toArray()) {
                afterRoles.add((Role)roleObject);
            }
            // add new
            List<Role> new_set = new ArrayList<>(afterRoles);
            for (Role r: new_set) {
                r.getPermissions().add(permission);
            }
            pm.setRoles(afterRoles);
            pm.save();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Save error: " + e.toString());
            return;
        }
        JOptionPane.showMessageDialog(this, "Save done");
        permission = null;
        displayPermission();
        permission = pm;
        displayPermissionList();
    }//GEN-LAST:event_pmCreatejButtonActionPerformed

    private void pmViewjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmViewjButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = pmjTable.getSelectedRow();
        
        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to view");
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) pmjTable.getModel();
        int id = (Integer) model.getValueAt(selectedIndex, 0);
        permission = Permission.findById(Permission.class, id);
        displayPermission();
    }//GEN-LAST:event_pmViewjButtonActionPerformed

    private void pmDeletejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmDeletejButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = pmjTable.getSelectedRow();
        
        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete");
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) pmjTable.getModel();
        int id = (Integer) model.getValueAt(selectedIndex, 0);
        permission = Permission.findById(Permission.class, id);
        permission.delete();
        model.removeRow(selectedIndex);
        permission = null;
        displayPermission();
        displayPermissionList();
    }//GEN-LAST:event_pmDeletejButtonActionPerformed

    private void pmModifyjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmModifyjButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = pmjTable.getSelectedRow();
        
        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to modify");
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) pmjTable.getModel();
        int id = (Integer) model.getValueAt(selectedIndex, 0);
        permission = Permission.findById(Permission.class, id);
        
        try {
            permission.setName(namejTextField.getText());
            permission.setPermissionType((PermissionType)permissionTypejComboBox.getSelectedItem());
            List<Role> previousRoles = permission.getRoles();
            List<Role> afterRoles = new ArrayList<>();
            for (Object roleObject: ((DefaultListModel<Role>)currentjList.getModel()).toArray()) {
                afterRoles.add((Role)roleObject);
            }
            
            // remove old
            List<Role> old = new ArrayList<>(previousRoles);
            old.removeAll(afterRoles);
            for (Role r: old) {
                r.getPermissions().remove(permission);
            }
            // add new
            List<Role> new_set = new ArrayList<>(afterRoles);
            new_set.removeAll(previousRoles);
            for (Role r: new_set) {
                r.getPermissions().add(permission);
            }
            
            permission.setRoles(afterRoles);
            permission.flush();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Modify error: " + e.toString());
            return;
        }
        JOptionPane.showMessageDialog(this, "Modify done");
        permission = null;
        displayPermission();
        displayPermissionList();
    }//GEN-LAST:event_pmModifyjButtonActionPerformed

    private void toRightjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toRightjButtonActionPerformed
        // TODO add your handling code here:
        List<Role> roles = otherjList.getSelectedValuesList();
        
        if (roles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a role to add");
            return;
        }
        
        for (Role r: roles) {
            ((DefaultListModel<Role>)otherjList.getModel()).removeElement(r);
            ((DefaultListModel<Role>)currentjList.getModel()).addElement(r);
        }
    }//GEN-LAST:event_toRightjButtonActionPerformed

    private void toLeftjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toLeftjButtonActionPerformed
        // TODO add your handling code here:
        List<Role> roles = currentjList.getSelectedValuesList();
        
        if (roles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a role to remove");
            return;
        }
        
        for (Role r: roles) {
            ((DefaultListModel<Role>)currentjList.getModel()).removeElement(r);
            ((DefaultListModel<Role>)otherjList.getModel()).addElement(r);
        }
    }//GEN-LAST:event_toLeftjButtonActionPerformed

    private void roleNamejTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roleNamejTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roleNamejTextFieldActionPerformed

    private void roleModifyjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roleModifyjButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = rolejTable.getSelectedRow();
        
        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to modify");
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) rolejTable.getModel();
        int id = (Integer) model.getValueAt(selectedIndex, 0);
        role = Role.findById(Role.class, id);
        
        try {
            role.setName(roleNamejTextField.getText());
            role.setOrganization((Organization)organizationjComboBox.getSelectedItem());
            List<Permission> previousPermissions = role.getPermissions();
            List<Permission> afterPermissions = new ArrayList<>();
            List<User> previousUsers = role.getUsers();
            List<User> afterUsers = new ArrayList<>();
            for (Object object: ((DefaultListModel<Permission>)currentPMjList.getModel()).toArray()) {
                afterPermissions.add((Permission)object);
            }
            for (Object object: ((DefaultListModel<User>)currentUserjList.getModel()).toArray()) {
                afterUsers.add((User)object);
            }
            // remove old
//            Set<Permission> oldPermissions = new HashSet<Permission>(previousPermissions);
//            oldPermissions.removeAll(afterPermissions);
//            for (Permission p: oldPermissions) {
//                role.getPermissions().remove(permission);
//            }
            List<User> oldUsers = new ArrayList<>(previousUsers);
            oldUsers.removeAll(afterUsers);
            for (User u: oldUsers) {
                u.getRoles().remove(role);
            }
            // add new
            List<User> newUsers = new ArrayList<>(afterUsers);
            newUsers.removeAll(previousUsers);
            for (User u: afterUsers) {
                u.getRoles().add(role);
            }
            
            role.setPermissions(afterPermissions);
            role.setUsers(afterUsers);
            role.flush();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Modify error: " + e.toString());
            return;
        }
        JOptionPane.showMessageDialog(this, "Modify done");
        role = null;
        displayRole();
        displayRoleList();
    }//GEN-LAST:event_roleModifyjButtonActionPerformed

    private void roleDeletejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roleDeletejButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = rolejTable.getSelectedRow();
        
        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) rolejTable.getModel();
        int id = (Integer) model.getValueAt(selectedIndex, 0);
        role = Role.findById(Role.class, id);
        for (User u: role.getUsers()) {
            u.getRoles().remove(role);
        }
        role.delete();
        model.removeRow(selectedIndex);
        role = null;
        displayRole();
        displayRoleList();
    }//GEN-LAST:event_roleDeletejButtonActionPerformed

    private void roleCreatejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roleCreatejButtonActionPerformed
        // TODO add your handling code here:
        Role r = new Role();
        try {
            r.setName(roleNamejTextField.getText());
            r.setOrganization((Organization)organizationjComboBox.getSelectedItem());
            List<Permission> afterPermissions = new ArrayList<>();
            List<User> afterUsers = new ArrayList<>();
            for (Object object: ((DefaultListModel<Permission>)currentPMjList.getModel()).toArray()) {
                afterPermissions.add((Permission)object);
            }
            for (Object object: ((DefaultListModel<User>)currentUserjList.getModel()).toArray()) {
                afterUsers.add((User)object);
            }
            // add new
            for (User u: afterUsers) {
                u.getRoles().add(r);
            }
            r.setPermissions(afterPermissions);
            r.setUsers(afterUsers);
            r.save();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Save error: " + e.toString());
            return;
        }
        JOptionPane.showMessageDialog(this, "Save done");
        role = null;
        displayRole();
        role = r;
        displayRoleList();
    }//GEN-LAST:event_roleCreatejButtonActionPerformed

    private void roleViewjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roleViewjButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = rolejTable.getSelectedRow();
        
        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to view");
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) rolejTable.getModel();
        int id = (Integer) model.getValueAt(selectedIndex, 0);
        role = Role.findById(Role.class, id);
        displayRole();
    }//GEN-LAST:event_roleViewjButtonActionPerformed

    private void pmToRightjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmToRightjButtonActionPerformed
        // TODO add your handling code here:
        List<Permission> permissions = otherPMjList.getSelectedValuesList();
        
        if (permissions.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a role to add");
            return;
        }
        
        for (Permission r: permissions) {
            ((DefaultListModel<Permission>)otherPMjList.getModel()).removeElement(r);
            ((DefaultListModel<Permission>)currentPMjList.getModel()).addElement(r);
        }
    }//GEN-LAST:event_pmToRightjButtonActionPerformed

    private void pmToLeftjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmToLeftjButtonActionPerformed
        // TODO add your handling code here:
        List<Permission> permissions = currentPMjList.getSelectedValuesList();
        
        if (permissions.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a role to remove");
            return;
        }
        for (Permission r: permissions) {
            ((DefaultListModel<Permission>)currentPMjList.getModel()).removeElement(r);
            ((DefaultListModel<Permission>)otherPMjList.getModel()).addElement(r);
        }
    }//GEN-LAST:event_pmToLeftjButtonActionPerformed

    private void usersToRightjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersToRightjButtonActionPerformed
        // TODO add your handling code here:
        List<User> users = otherUserjList.getSelectedValuesList();
        
        if (users.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a role to add");
            return;
        }
        
        for (User r: users) {
            ((DefaultListModel<User>)otherUserjList.getModel()).removeElement(r);
            ((DefaultListModel<User>)currentUserjList.getModel()).addElement(r);
        }
    }//GEN-LAST:event_usersToRightjButtonActionPerformed

    private void usersToLeftjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersToLeftjButtonActionPerformed
        // TODO add your handling code here:
        List<User> users = currentUserjList.getSelectedValuesList();
        
        if (users.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a role to remove");
            return;
        }
        
        for (User r: users) {
            ((DefaultListModel<User>)currentUserjList.getModel()).removeElement(r);
            ((DefaultListModel<User>)otherUserjList.getModel()).addElement(r);
        }
    }//GEN-LAST:event_usersToLeftjButtonActionPerformed

    private void userNamejTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNamejTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userNamejTextFieldActionPerformed

    private void userModifyjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userModifyjButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = userjTable.getSelectedRow();
        
        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to modify");
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) userjTable.getModel();
        int id = (Integer) model.getValueAt(selectedIndex, 0);
        user = User.findById(User.class, id);
        
        try {
            user.setUsername(userNamejTextField.getText());
            user.setPassword(passwordjTextField.getText());
            List<Role> previousRoles = user.getRoles();
            List<Role> afterRoles = new ArrayList<>();
            for (Object roleObject: ((DefaultListModel<Role>)currentRolesjList.getModel()).toArray()) {
                afterRoles.add((Role)roleObject);
            }
            
            // remove old
            Set<Role> old = new HashSet<>(previousRoles);
            old.removeAll(afterRoles);
            for (Role r: old) {
                user.getRoles().remove(r);
            }
            
            user.setRoles(afterRoles);
            user.flush();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Modify error: " + e.toString());
            return;
        }
        JOptionPane.showMessageDialog(this, "Modify done");
        user = null;
        displayUser();
        displayUserList();
    }//GEN-LAST:event_userModifyjButtonActionPerformed

    private void userDeletejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userDeletejButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = userjTable.getSelectedRow();
        
        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete");
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) userjTable.getModel();
        int id = (Integer) model.getValueAt(selectedIndex, 0);
        user = User.findById(User.class, id);
        user.delete();
        model.removeRow(selectedIndex);
        user = null;
        displayUser();
        displayUserList();
    }//GEN-LAST:event_userDeletejButtonActionPerformed

    private void userCreatejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userCreatejButtonActionPerformed
        // TODO add your handling code here:
        User u = new User();
        try {
            u.setUsername(userNamejTextField.getText());
            u.setPassword(passwordjTextField.getText());
            List<Role> afterRoles = new ArrayList<>();
            for (Object roleObject: ((DefaultListModel<Role>)currentRolesjList.getModel()).toArray()) {
                afterRoles.add((Role)roleObject);
            }
            // add new
            u.setRoles(afterRoles);
            u.save();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Save error: " + e.toString());
            return;
        }
        JOptionPane.showMessageDialog(this, "Save done");
        user = null;
        displayUser();
        user = u;
        displayUserList();
    }//GEN-LAST:event_userCreatejButtonActionPerformed

    private void userViewjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userViewjButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = userjTable.getSelectedRow();
        
        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to view");
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) userjTable.getModel();
        int id = (Integer) model.getValueAt(selectedIndex, 0);
        user = User.findById(User.class, id);
        displayUser();
    }//GEN-LAST:event_userViewjButtonActionPerformed

    private void toRolesRightjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toRolesRightjButtonActionPerformed
        // TODO add your handling code here:
        List<Role> roles = otherRolesjList.getSelectedValuesList();
        
        if (roles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a role to add");
            return;
        }
        
        for (Role r: roles) {
            ((DefaultListModel<Role>)otherRolesjList.getModel()).removeElement(r);
            ((DefaultListModel<Role>)currentRolesjList.getModel()).addElement(r);
        }
    }//GEN-LAST:event_toRolesRightjButtonActionPerformed

    private void toRolesLeftjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toRolesLeftjButtonActionPerformed
        // TODO add your handling code here:
        List<Role> roles = currentRolesjList.getSelectedValuesList();
        
        if (roles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a role to remove");
            return;
        }
        
        for (Role r: roles) {
            ((DefaultListModel<Role>)currentRolesjList.getModel()).removeElement(r);
            ((DefaultListModel<Role>)otherRolesjList.getModel()).addElement(r);
        }
    }//GEN-LAST:event_toRolesLeftjButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonjPanel;
    private javax.swing.JPanel buttonjPanel1;
    private javax.swing.JPanel buttonjPanel2;
    private javax.swing.JList<Permission> currentPMjList;
    private javax.swing.JList<Role> currentRolesjList;
    private javax.swing.JList<User> currentUserjList;
    private javax.swing.JList<Role> currentjList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JLabel namejLabel;
    private javax.swing.JTextField namejTextField;
    private javax.swing.JComboBox<Organization> organizationjComboBox;
    private javax.swing.JLabel organizationjLabel;
    private javax.swing.JList<Permission> otherPMjList;
    private javax.swing.JList<Role> otherRolesjList;
    private javax.swing.JList<User> otherUserjList;
    private javax.swing.JList<Role> otherjList;
    private javax.swing.JLabel passwordjLabel;
    private javax.swing.JTextField passwordjTextField;
    private javax.swing.JComboBox<PermissionType> permissionTypejComboBox;
    private javax.swing.JLabel permissionTypejLabel;
    private javax.swing.JPanel permissionjPanel;
    private javax.swing.JLabel permissionsjLabel;
    private javax.swing.JButton pmCreatejButton;
    private javax.swing.JButton pmDeletejButton;
    private javax.swing.JButton pmModifyjButton;
    private javax.swing.JButton pmToLeftjButton;
    private javax.swing.JButton pmToRightjButton;
    private javax.swing.JButton pmViewjButton;
    private javax.swing.JTable pmjTable;
    private javax.swing.JButton roleCreatejButton;
    private javax.swing.JButton roleDeletejButton;
    private javax.swing.JButton roleModifyjButton;
    private javax.swing.JLabel roleNamejLabel;
    private javax.swing.JTextField roleNamejTextField;
    private javax.swing.JButton roleViewjButton;
    private javax.swing.JPanel rolejPanel;
    private javax.swing.JTable rolejTable;
    private javax.swing.JLabel rolesjLabel;
    private javax.swing.JButton toLeftjButton;
    private javax.swing.JButton toRightjButton;
    private javax.swing.JButton toRolesLeftjButton;
    private javax.swing.JButton toRolesRightjButton;
    private javax.swing.JButton userCreatejButton;
    private javax.swing.JButton userDeletejButton;
    private javax.swing.JButton userModifyjButton;
    private javax.swing.JLabel userNamejLabel;
    private javax.swing.JTextField userNamejTextField;
    private javax.swing.JLabel userRolesjLabel;
    private javax.swing.JButton userViewjButton;
    private javax.swing.JPanel userjPanel;
    private javax.swing.JTable userjTable;
    private javax.swing.JButton usersToLeftjButton;
    private javax.swing.JButton usersToRightjButton;
    private javax.swing.JLabel usersjLabel;
    // End of variables declaration//GEN-END:variables
}
