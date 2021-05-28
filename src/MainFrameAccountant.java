import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrameAccountant extends JFrame {
	JMenu menuAccount, menuFinance; 
	JMenuItem logout, menuReport;
	JDesktopPane desktopPane = new JDesktopPane();
	
	
	public MainFrameAccountant() {
		tampil();
	}
	
	public void tampil() {
		setTitle("Amore POS");
		JMenuBar mb=new JMenuBar();  
	    menuAccount =new JMenu("Account");  
	    menuFinance =new JMenu("Finance");  
	    FinanceReport rp = new FinanceReport();
		desktopPane.add(rp);
	    logout=new JMenuItem("Logout");
	    logout.addActionListener(new ActionListener() {
	    
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginFrame lf = new LoginFrame();
				dispose();
			}
	    });
	    
	    menuReport=new JMenuItem("View Monthly Report");
	    menuReport.addActionListener(new ActionListener() {
	    
				@Override
				public void actionPerformed(ActionEvent e) {
					rp.setLocation(650,150);
					rp.setVisible(true);
					
							
				}
	      });
	    
	    menuAccount.add(logout);menuFinance.add(menuReport);
	    mb.add(menuAccount); 
	    mb.add(menuFinance);
	    setJMenuBar(mb);  
	    getContentPane().setLayout(new CardLayout(0, 0));
	    desktopPane.setBackground(Color.WHITE);
	    getContentPane().add(desktopPane, "name_32588005190353");
	    setVisible(true); 
	    setLocationRelativeTo(null);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
		
}
