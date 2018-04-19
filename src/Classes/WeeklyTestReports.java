/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import static java.awt.Frame.MAXIMIZED_BOTH;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author asadj
 */
public class WeeklyTestReports extends JFrame{
    
    Connection conn = null;

    
 public void showReport(String reportfilename,String titles,String firstpos,String secondpos,String thirdpos,String failures,String subject,String grade,float thridhighestmark,String Date,String Section,String selectedClass){

     
     try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:49172;"
                    + "databaseName=sscc;user=sa;password=sa9";
           conn= DriverManager.getConnection(url);
            

            Map<String, Object> params = new HashMap<String, Object>();







            params.put("FirtsPosition", firstpos);
            params.put("SecondPosition", secondpos);
            params.put("ThirdPosition", thirdpos);
            params.put("Failures", failures);
            params.put("Subject", subject);
            params.put("Grade", grade);
            params.put("thirdhighestmarks", thridhighestmark);
            params.put("Date", Date);
            params.put("Section", Section);
            params.put("Class", selectedClass);
            
            
//            params.put("Class", Grade);
            
//            parameters.put("ino",ino);
//            JOptionPane.showMessageDialog(rootPane, pack);
            BasicConfigurator.configure();
            JasperDesign jasperDesign = JRXmlLoader.load(reportfilename);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            //for print 
            //parameters holds the parameter passs to the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,params,conn);
            //Viewer Window to show
            JRViewer viewer = new JRViewer(jasperPrint);
            viewer.setOpaque(true);
            viewer.setVisible(true);
            //make your JFrame visible
            this.add(viewer);
//            this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width-100,Toolkit.getDefaultToolkit().getScreenSize().height-100);
            this.setExtendedState(MAXIMIZED_BOTH);
//            this.setSize(1270,750);
            this.setVisible(true);
            this.setTitle(titles);



}catch(Exception ex){



}


}   
}