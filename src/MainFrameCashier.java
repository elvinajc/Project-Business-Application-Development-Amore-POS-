import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.Random;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class MainFrameCashier extends JFrame implements ActionListener{

	ResultSet rs;
	Vector<Cart> cart = new Vector<Cart>();
	JDesktopPane desktop_pane = new JDesktopPane();
	JMenuBar menubar = new JMenuBar();
	JInternalFrame internal = new JInternalFrame("Transaction", true, true, true, true);
	
	JMenu account = new JMenu("Account");
	JMenu transaction = new JMenu("Transaction");
	JMenuItem logout = new JMenuItem("Logout");
	JMenuItem ct = new JMenuItem("Create Transaction");
	
	//JInternalFrame Component
	JPanel parent_panel = new JPanel();
	JPanel upper_panel = new JPanel();
	JPanel middle_panel = new JPanel();
	JPanel lower_panel = new JPanel();
	
	JPanel title_panel = new JPanel();
	JLabel idTitle_label = new JLabel();
	JLabel nameTitle_label = new JLabel();
	JLabel quantityTitle_label = new JLabel();
	
	JPanel menu_panel = new JPanel();
	JLabel menuId_label = new JLabel();
	JLabel menuName_label = new JLabel();
	JSpinner menuQuantity_spinner = new JSpinner();
	
	JPanel button_panel = new JPanel();
	JButton add_btn = new JButton("Add");
	JButton update_btn = new JButton("Update");
	JButton remove_btn = new JButton("Remove");
	
	JPanel menuTable_panel = new JPanel();
	JLabel menuTitle_label = new JLabel();
	DefaultTableModel tm = new DefaultTableModel();
	JTable tableMenu = new JTable(tm);
	JScrollPane spTableMenu = new JScrollPane(tableMenu);
	
	Object[] columnName = {
		"ID","Name","Price"	,"Quantity"
	};
	JPanel cartTable_panel = new JPanel();
	JLabel cartTitle_label = new JLabel();
	DefaultTableModel tc = new DefaultTableModel(null,columnName);
	JTable tableCart = new JTable(tc);
	JScrollPane spTableCart = new JScrollPane(tableCart);
	
	JLabel totalTitle_label = new JLabel();
	JLabel total_label = new JLabel();
	JButton cancel_btn = new JButton("Cancel");
	JButton finish_btn = new JButton("Finish");
		
	public MainFrameCashier() {
		
		account.add(logout);
		menubar.add(account);
		transaction.add(ct);
		menubar.add(transaction);
		logout.addActionListener(this);
		ct.addActionListener(this);
		
		add(desktop_pane);
		setJMenuBar(menubar);
		setVisible(true);
		setLocationRelativeTo(null);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Amore POS");
		
		TransactionFrame();
		loadTable();
		desktop_pane.add(internal);
		
		}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==logout) {
			LoginFrame lf = new LoginFrame();
			dispose();
			
		}else if(e.getSource()==ct) {
			internal.setLocation(500,150);
			internal.setVisible(true);
		}
		
		if(e.getSource()==add_btn) {
			if(tableMenu.getSelectedRow()==-1 || menuId_label.getText()=="") {
				JOptionPane.showMessageDialog(this, new String("Please select menu to be inserted"));
			}else {
				if((int) menuQuantity_spinner.getValue()<=0){
					JOptionPane.showMessageDialog(this, new String("Item quantity cannot be zero!"));
				}else {
					String id = "";
					String cartId = menuId_label.getText();
					String cartName = menuName_label.getText();
					int cartPrice =  0;
					for (int i=0; i<tableMenu.getRowCount(); i++){    
					    id = tableMenu.getValueAt(i, 0).toString();
					    if(cartId.equals(id))
					    {
					       cartPrice = (int) tableMenu.getValueAt(i, 2);
					    }
					}
					
					int cartQuantity = (int) menuQuantity_spinner.getValue();
					Cart c = new Cart(cartId,cartName,cartPrice,cartQuantity);
					DefaultTableModel model = (DefaultTableModel) tableCart.getModel();
					
					
					boolean exists = false;
					for (int i=0; i<tableCart.getRowCount(); i++){    
					    id = tableCart.getValueAt(i, 0).toString();
					    if(cartId.equals(id))
					    {
					       exists = true;
					    }
					} 
					if(!exists){
						cart.add(c);
						model.addRow(c.returnAsObject());
						JOptionPane.showMessageDialog(this, new String("Item Added!"));
						total();
						menuId_label.setText("");
						menuName_label.setText("");
						menuQuantity_spinner.setValue(0);
						tableMenu.clearSelection();
						tableCart.clearSelection();
					}else {
						int quantity;
						for (int i=0; i<tableCart.getRowCount(); i++){   
							
						    id = tableCart.getValueAt(i, 0).toString();
						    if(cartId.equals(id))
						    {
						    	quantity = (int) tableCart.getValueAt(i, 3)+ (int) menuQuantity_spinner.getValue();
						    	tableCart.setValueAt(quantity, i, 3);
						    }
						}
						JOptionPane.showMessageDialog(this, new String("Item Added!"));
						total();
						menuId_label.setText("");
						menuName_label.setText("");
						menuQuantity_spinner.setValue(0);
						tableMenu.clearSelection();
						tableCart.clearSelection();
					}
				}
			}
		}
		
		if(e.getSource()==update_btn) {
			if(tableCart.getSelectedRow()==-1){
				JOptionPane.showMessageDialog(this, new String("Please select item to be updated"));
			}else {
				if((int) menuQuantity_spinner.getValue()<=0){
					JOptionPane.showMessageDialog(this, new String("Item quantity cannot be zero!"));
				}else {
					int quantity;
					String id = "";
					String cartId = menuId_label.getText();
					for (int i=0; i<tableCart.getRowCount(); i++){   
						
					    id = tableCart.getValueAt(i, 0).toString();
					    if(cartId.equals(id))
					    {
					    	quantity = (int) menuQuantity_spinner.getValue();
					    	tableCart.setValueAt(quantity, i, 3);
					    }
					}
					JOptionPane.showMessageDialog(this, new String("Item Updated!"));
					total();
					menuId_label.setText("");
					menuName_label.setText("");
					menuQuantity_spinner.setValue(0);
					tableMenu.clearSelection();
					tableCart.clearSelection();
				}
			}
		}
		
		if(e.getSource()==remove_btn) {
			if(tableCart.getSelectedRow()==-1){
				JOptionPane.showMessageDialog(this, new String("Please select item to be removed"));
			}else {
				tc.removeRow(tableCart.getSelectedRow());
				JOptionPane.showMessageDialog(this, new String("Item Removed!"));
				total_label.setText("0");
				total();
				menuId_label.setText("");
				menuName_label.setText("");
				menuQuantity_spinner.setValue(0);
				tableMenu.clearSelection();
				tableCart.clearSelection();
			}
		}
		
		if(e.getSource()==finish_btn) {
			Connect sql = new Connect();
			int quantity;
			JLabel randomId = new JLabel();
			randomId.setText(generateId());
			String idTransaction = randomId.getText();
			String id = "";
			for (int i=0; i<tableCart.getRowCount(); i++){
			    id = tableCart.getValueAt(i, 0).toString();
			    quantity = (int) tableCart.getValueAt(i, 3);
				sql.execUpdate("INSERT INTO transactiondetail VALUES ('" +idTransaction+ "','" +id+ "','" +quantity+ "')");
			}
			sql.execUpdate("INSERT INTO transactionheader VALUES ('" +idTransaction+ "','" +  LoginFrame.idLogin + "','" + java.time.LocalDate.now() + "')");

			JOptionPane.showMessageDialog(this, new String("Transaction saved!"));
			for (int i = tableCart.getRowCount() - 1; i >= 0; i--) {
			    tc.removeRow(i);
			}
			total_label.setText("0");
			menuId_label.setText("");
			menuName_label.setText("");
			menuQuantity_spinner.setValue(0);
			tableMenu.clearSelection();
			tableCart.clearSelection();
		}
		
		if(e.getSource()==cancel_btn) {
			internal.setVisible(false);
			for (int i = tableCart.getRowCount() - 1; i >= 0; i--) {
			    tc.removeRow(i);
			}
			total_label.setText("0");
			menuId_label.setText("");
			menuName_label.setText("");
			menuQuantity_spinner.setValue(0);
			tableMenu.clearSelection();
			tableCart.clearSelection();
		}
	}

	private void TransactionFrame() {
		
		idTitle_label.setText("ID:");
		nameTitle_label.setText("Name:");
		quantityTitle_label.setText("Quantity:");
		title_panel.setLayout(new GridLayout(3,0,0,10)); 
		title_panel.add(idTitle_label);
		title_panel.add(nameTitle_label);
		title_panel.add(quantityTitle_label);
		
		menu_panel.setLayout(new GridLayout(3,0,0,10));
		menu_panel.setPreferredSize(new Dimension(50,0));
		menu_panel.add(menuId_label);
		menu_panel.add(menuName_label);
		menu_panel.add(menuQuantity_spinner);
		
		add_btn.addActionListener(this);
		update_btn.addActionListener(this);
		remove_btn.addActionListener(this);
		button_panel.setLayout(new GridLayout(3,0,0,10));
		button_panel.add(add_btn);
		button_panel.add(update_btn);
		button_panel.add(remove_btn);
		
		upper_panel.setLayout(new BoxLayout(upper_panel, BoxLayout.X_AXIS));
		upper_panel.add(title_panel);
		upper_panel.add(Box.createRigidArea(new Dimension(10,0)));
		upper_panel.add(menu_panel);
		upper_panel.add(Box.createRigidArea(new Dimension(10,0)));
		upper_panel.add(button_panel);
		upper_panel.setPreferredSize(new Dimension(0,55));
		
		menuTitle_label.setText("Menu:");
		menuTitle_label.setFont(new Font("Tahoma", Font.BOLD, 15));
		menuTable_panel.add(Box.createRigidArea(new Dimension(0,10)));
		menuTable_panel.add(menuTitle_label);
		menuTable_panel.add(Box.createRigidArea(new Dimension(0,10)));
		menuTable_panel.add(spTableMenu);
		spTableMenu.setAlignmentX(Component.LEFT_ALIGNMENT);
		spTableMenu.setPreferredSize(new Dimension(480,230));
		
		cartTitle_label.setText("Cart:");
		cartTitle_label.setFont(new Font("Tahoma", Font.BOLD, 15));
		cartTable_panel.add(Box.createRigidArea(new Dimension(0,10)));
		cartTable_panel.add(cartTitle_label);
		cartTable_panel.add(Box.createRigidArea(new Dimension(0,10)));
		cartTable_panel.add(spTableCart);
		spTableCart.setAlignmentX(Component.LEFT_ALIGNMENT);
		spTableCart.setPreferredSize(new Dimension(480,230));
		
		menuTable_panel.setLayout(new BoxLayout(menuTable_panel, BoxLayout.Y_AXIS));
		cartTable_panel.setLayout(new BoxLayout(cartTable_panel, BoxLayout.Y_AXIS));
		middle_panel.add(menuTable_panel);
		middle_panel.add(cartTable_panel);
		middle_panel.setPreferredSize(new Dimension(0,250));
		
		totalTitle_label.setText("Total:");
		total_label.setText("0");
		cancel_btn.addActionListener(this);
		finish_btn.addActionListener(this);
		lower_panel.setLayout(new GridLayout(2,2,10,10));
		lower_panel.add(totalTitle_label);
		lower_panel.add(total_label);
		lower_panel.add(cancel_btn);
		lower_panel.add(finish_btn);
		lower_panel.setPreferredSize(new Dimension(0,20));
		
		
		parent_panel.setLayout(new BoxLayout(parent_panel, BoxLayout.Y_AXIS));
		parent_panel.add(upper_panel);
		parent_panel.add(middle_panel);
		parent_panel.add(lower_panel);
		parent_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		internal.add(parent_panel);
		internal.setBounds(0, 0, 1000, 500);
		internal.setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
	
	public String generateId(){
		return "" + generateChar() + generateChar() + generateChar() + generateChar() + generateChar() + generateChar() 
		+ generateChar() + generateChar() + generateChar() + generateChar();
	}
	
	public char generateChar(){
		Random rand = new Random();
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		return alphabet.charAt(rand.nextInt(alphabet.length()));
	}
	
	public void total(){
		int total = 0;
		for(int i = 0; i < tableCart.getRowCount(); i++){
	        int quantity = Integer.parseInt(tableCart.getValueAt(i, 2)+"");
	        int price = Integer.parseInt(tableCart.getValueAt(i, 3)+"");
	        total = (quantity*price)+total;
	        total_label.setText(String.valueOf(total));
	    }
	}
	
	public void loadTable(){
		tableMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCart.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableMenu.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = tableMenu.getSelectedRow();
				menuId_label.setText(tableMenu.getValueAt(index, 0).toString());
				menuName_label.setText(tableMenu.getValueAt(index, 1).toString());
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			
		});
		tableCart.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = tableCart.getSelectedRow();
				menuId_label.setText(tableCart.getValueAt(index, 0).toString());
				menuName_label.setText(tableCart.getValueAt(index, 1).toString());
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			
		});
		Connect sql = new Connect();
		
		tm.addColumn("ID");
		tm.addColumn("Name");
		tm.addColumn("Sell Price");
		
		
		try{
		ResultSet rs = sql.execQuery("SELECT * FROM menu");
		while(rs.next()){
		tm.addRow(new Object[]{rs.getString("menuid"),rs.getString("name"),rs.getInt("sellprice")});
		}
		}catch(Exception e){
			
		}
	}

}
