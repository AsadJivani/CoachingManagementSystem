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
public class FeeReceipt extends JFrame{
    
    Connection conn = null;

    
 public void showReport(String reportfilename,String titles,String Name,String fname,int amount,int rollno,String grade,String Fee_package,int feepaid,int feeleft){

     
     try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            conn =DriverManager.getConnection( "jdbc:odbc:abc","sa","sesame46");
            

            Map<String, Object> params = new HashMap<String, Object>();







            params.put("Name", Name);
            params.put("F.Name", fname);
            params.put("Grade", grade);
            params.put("Amount", amount);
            params.put("Roll_No", rollno);
            params.put("Fee_paid", feepaid);
            params.put("Fee_left", feeleft);
            params.put("Fee_package", Fee_package);
            
            BasicConfigurator.configure();
//            parameters.put("ino",ino);
//            JOptionPane.showMessageDialog(rootPane, pack);
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
