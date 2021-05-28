import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

public class FinanceReport extends JInternalFrame{
	
	private JTable TableMenu;
	private JLabel titleMonth, titleYear;
	private JTextField textYear;
	private JScrollPane scrollPane;
	private JButton ButtonView;
	private JTextField textMonth;
	
	public FinanceReport() {
		showframe();
	}
	
	public void showframe() {
		setTitle("Finance Report");
		setSize(721 , 600);
	 	setResizable(true);
	 	setMaximizable(true);
	 	setIconifiable(true);
		setClosable(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		titleMonth = new JLabel("Month:");
		springLayout.putConstraint(SpringLayout.NORTH, titleMonth, 11, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, titleMonth, 142, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, titleMonth, 25, SpringLayout.NORTH, getContentPane());
		titleMonth.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(titleMonth);
		
		 titleYear = new JLabel("Year :");
		 springLayout.putConstraint(SpringLayout.EAST, titleMonth, 0, SpringLayout.EAST, titleYear);
		 springLayout.putConstraint(SpringLayout.NORTH, titleYear, 56, SpringLayout.NORTH, getContentPane());
		 springLayout.putConstraint(SpringLayout.WEST, titleYear, 142, SpringLayout.WEST, getContentPane());
		 springLayout.putConstraint(SpringLayout.SOUTH, titleYear, 70, SpringLayout.NORTH, getContentPane());
		 springLayout.putConstraint(SpringLayout.EAST, titleYear, 232, SpringLayout.WEST, getContentPane());
		titleYear.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(titleYear);
		
		textYear = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textYear, -1, SpringLayout.NORTH, titleYear);
		springLayout.putConstraint(SpringLayout.WEST, textYear, 6, SpringLayout.EAST, titleYear);
		springLayout.putConstraint(SpringLayout.EAST, textYear, -249, SpringLayout.EAST, getContentPane());
		getContentPane().add(textYear);
		textYear.setColumns(10);
		
		scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 25, SpringLayout.SOUTH, textYear);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(scrollPane);
		
		TableMenu = new JTable();
		TableMenu.setRowSelectionAllowed(true);
		String head [] = {"Transaction ID","Modal","Earn","Gain"};		
		TableMenu.setModel(new DefaultTableModel(
				new Object [][] {
					{null,null,null,null}
				}, head		
				
		));
		TableMenu.setFillsViewportHeight(true);;
		scrollPane.setViewportView(TableMenu);
		
		ButtonView = new JButton("View");
		ButtonView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(validasi()) {
					String data = "";
					if(Integer.parseInt(textMonth.getText()) < 10) {
						data = "'" + textYear.getText() + "-0" + textMonth.getText() + "-%'";
					}else {
						data = "'" + textYear.getText() + "-" + textMonth.getText() + "-%'";
					}
					Load_DataTable(data);
				}
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, ButtonView, -6, SpringLayout.NORTH, titleMonth);
		springLayout.putConstraint(SpringLayout.EAST, ButtonView, -77, SpringLayout.EAST, getContentPane());
		ButtonView.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(ButtonView);
		
		textMonth = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, ButtonView, 46, SpringLayout.EAST, textMonth);
		springLayout.putConstraint(SpringLayout.NORTH, textMonth, -1, SpringLayout.NORTH, titleMonth);
		springLayout.putConstraint(SpringLayout.WEST, textMonth, 6, SpringLayout.EAST, titleMonth);
		springLayout.putConstraint(SpringLayout.EAST, textMonth, 0, SpringLayout.EAST, textYear);
		textMonth.setColumns(10);
		getContentPane().add(textMonth);
	}
	
	public void Load_DataTable(String condition) {
		DefaultTableModel dt = (DefaultTableModel) TableMenu.getModel();
		dt.setRowCount(0);
		String[][] data = Main.con.getDataReport(condition);
		String row[];
		for(int i=0;i<data.length;i++) {
			row = data[i];
			dt.addRow(row);
		}
	}
	
	public boolean validasi() {
		if(textYear.getText().equals("") || textMonth.getText().equals("")) {
			return false;
		}else {
			try {
				int year = Integer.parseInt(textYear.getText());
				int month = Integer.parseInt(textMonth.getText());
				if(month<1 || month>12) {
					JOptionPane.showMessageDialog(null, "Month must be between 1-12");
					return false;
				}
				
				return true;
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Month and Year must be a number");
				return false;
			}
		}
	}
	
}