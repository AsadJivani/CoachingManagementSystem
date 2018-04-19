/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import Classes.MonthlyReport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author asadj
 */
public class Print_MonthlyResult extends javax.swing.JDialog {

      public Connection conn() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:49172;"
                    + "databaseName=sscc;user=sa;password=sa9";
            return DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "An error occured while Connecting to Database.");
        }
        return null;
    }
    String[] str = {"January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"};
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int numberoftest;
    int noofstudents;

    /**
     * Creates new form MonthlyResult
     */
    public Print_MonthlyResult(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        con = conn();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MonthResult = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        GradeReslut = new javax.swing.JComboBox();
        Month_result = new javax.swing.JComboBox();
        print = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        MonthResult.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel13.setText("Class:");

        jLabel14.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel14.setText("Month:");

        GradeReslut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "IX-A", "X-A", "X-B", "XI-A", "XI-B", "XII-A", "XI-Commerce", "XII-Commerce", "B.com-I", "B.com-II" }));
        GradeReslut.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                GradeReslutItemStateChanged(evt);
            }
        });

        print.setText("Print");
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MonthResultLayout = new javax.swing.GroupLayout(MonthResult);
        MonthResult.setLayout(MonthResultLayout);
        MonthResultLayout.setHorizontalGroup(
            MonthResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MonthResultLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MonthResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(print, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(MonthResultLayout.createSequentialGroup()
                        .addGroup(MonthResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(MonthResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(GradeReslut, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Month_result, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        MonthResultLayout.setVerticalGroup(
            MonthResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MonthResultLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MonthResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(MonthResultLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(GradeReslut, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(MonthResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(MonthResultLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(Month_result, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        MonthResultLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {GradeReslut, Month_result});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MonthResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MonthResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GradeReslutItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_GradeReslutItemStateChanged
        Month_result.removeAllItems();
        try {
            ps = con.prepareStatement("select DISTINCT month(Date_of_Entry),year(Date_of_Entry) from WeeklyTestTable where Class='" + GradeReslut.getSelectedItem() + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                Month_result.addItem(str[rs.getInt(1) - 1]);
            }
        } catch (SQLException ex) {
        }
    }//GEN-LAST:event_GradeReslutItemStateChanged

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
          if (Month_result.getItemCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Please Select Appropriate Month");
        } else {
            int index = Arrays.asList(str).indexOf(Month_result.getSelectedItem());
//            System.out.println(index);

            try {

                ps = con.prepareStatement("select Count(DISTINCT Date_of_Entry) from WeeklyTestTable where Class='" + GradeReslut.getSelectedItem() + "' AND month(Date_of_Entry)=" + (index + 1) + "");
                rs = ps.executeQuery();
                while (rs.next()) {
                    numberoftest = rs.getInt(1);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());

            }

            

            int totalmarks = 15 * numberoftest;


       
            //for no of students
            try {

                ps = con.prepareStatement("select count(Name) from Basic_info where class='" + GradeReslut.getSelectedItem() + "'");
                rs = ps.executeQuery();
                while (rs.next()) {

                    noofstudents = rs.getInt(1);

                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
            }

            ///now calculating final month marks and inserting it
            float totalmarksobtained = 0;
            String name = "";
            int roll_no = 0;

//        getting markstotal
            for (int i = 1; i <= noofstudents; i++) {

                //first get roll no then use it
                try {
                    /**
                     * query logic OFFSET: Specifies the number of rows to skip
                     * before it starts to return rows from the query
                     * expression.
                     *
                     * FETCH NEXT: Specifies the number of rows to return after
                     * the OFFSET clause has been processed. *
                     *
                     */
                    String querry=";WITH Results_CTE AS (SELECT Roll_No,ROW_NUMBER() OVER (ORDER BY Roll_No ASC ) AS RowNum From Basic_info  where class='"+GradeReslut.getSelectedItem()+"') select Roll_No from Results_CTE where RowNum="+i+"";
                    ps = con.prepareStatement(querry);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        roll_no = rs.getInt(1);

                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
                }

                //////getting name 
                try {

                    ps = con.prepareStatement("select Name from Basic_info where Roll_No='" + roll_no + "' AND class='" + GradeReslut.getSelectedItem() + "'");
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        name = rs.getString(1);

                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
                }

                //totalmarksobtain
                try {

                    ps = con.prepareStatement("select sum(Marks) from WeeklyTestTable where Roll_No='" + roll_no + "' AND class='" + GradeReslut.getSelectedItem() + "' AND month(Date_of_Entry)=" + (index + 1) + "");
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        totalmarksobtained = rs.getFloat(1);
                        
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
                }

                System.out.println(totalmarksobtained);
//        JOptionPane.showMessageDialog(rootPane, roll_no+name+totalmarksobtained);
                String insert = "Insert into Monthly_Result(Roll_No,Class,Name,Month,Marks_obtain,Total_marks)"
                        + "Values(" + roll_no + ",'" + GradeReslut.getSelectedItem() + "','" + name + "','" + Month_result.getSelectedItem() + "'," + totalmarksobtained + "," + totalmarks + ")";
                String update = "update Monthly_Result set Marks_obtain=" + totalmarksobtained + ",Total_marks=" + totalmarks + " where Class ='" + GradeReslut.getSelectedItem() + "' AND Roll_No=" + roll_no + "AND Month='" + Month_result.getSelectedItem() + "'";

                String mainquery
                        = "IF (NOT EXISTS(SELECT * FROM Monthly_Result WHERE Name='" + name + "' AND Class ='" + GradeReslut.getSelectedItem() + "' AND Month='" + Month_result.getSelectedItem() + "'))"
                        + "BEGIN\n"
                        + insert + "\n"
                        + "END\n"
                        + "ELSE\n"
                        + "BEGIN\n"
                        + update + "\n"
                        + "END";

                try {
                    ps = con.prepareStatement(mainquery);
                    ps.executeUpdate();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
                }

            }

            ////for calculating percentage anotherloop
            float monthmarks = 0;
            float percentage;
            for (int i = 1; i <= noofstudents; i++) {

                ///////geting highst mark in descending
                try {
                    ////////////query logic
                    /**
                     * OFFSET: Specifies the number of rows to skip before it
                     * starts to return rows from the query expression.
                     *
                     * FETCH NEXT: Specifies the number of rows to return after
                     * the OFFSET clause has been processed. *
                     *
                     */
                    ps = con.prepareStatement(";WITH Results_CTE AS (SELECT Marks_obtain,ROW_NUMBER() OVER (ORDER BY Marks_obtain DESC ) AS RowNum From Monthly_Result  where class='"+GradeReslut.getSelectedItem()+"' AND Month='"+Month_result.getSelectedItem()+"') select Marks_obtain from Results_CTE where RowNum="+i+"");
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        monthmarks = rs.getFloat(1);

                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
                }

                ///geting name
                try {

                    ps = con.prepareStatement(";WITH Results_CTE AS (SELECT Name,ROW_NUMBER() OVER (ORDER BY Marks_obtain DESC ) AS RowNum From Monthly_Result  where class='"+GradeReslut.getSelectedItem()+"' AND Month='"+Month_result.getSelectedItem()+"') select Name from Results_CTE where RowNum="+i+"");
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        name = rs.getString(1);

                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
                }


                percentage = monthmarks * 100 / totalmarks;
                 
                try {
                    String Rank = i + getOrdinalFor(i);
                    ps = con.prepareStatement("update Monthly_Result set percentage=" + String.format("%.2f", percentage)+ ", rank='" + Rank + "' where Marks_obtain=" + monthmarks + " AND Name='" + name + "' AND Class='" + GradeReslut.getSelectedItem() + "' AND Month='" + Month_result.getSelectedItem() + "'");
                    ps.executeUpdate();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage()+"error");
                }

            }

            this.setVisible(false);
            MonthlyReport report = new MonthlyReport();
            report.showReport("src/Reports/Month_Result.jrxml", "MONTHLY RESULT", Month_result.getSelectedItem().toString(), GradeReslut.getSelectedItem().toString());
        }

    }//GEN-LAST:event_printActionPerformed
   
    public static String getOrdinalFor(int value) {
        int hundredRemainder = value % 100;
        int tenRemainder = value % 10;
        if (hundredRemainder - tenRemainder == 10) {
            return "th";
        }

        switch (tenRemainder) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Print_MonthlyResult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Print_MonthlyResult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Print_MonthlyResult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Print_MonthlyResult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Print_MonthlyResult dialog = new Print_MonthlyResult(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox GradeReslut;
    private javax.swing.JPanel MonthResult;
    public javax.swing.JComboBox Month_result;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JButton print;
    // End of variables declaration//GEN-END:variables
}
