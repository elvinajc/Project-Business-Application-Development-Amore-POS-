import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu.Separator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import java.security.SecureRandom;


public class MainFrameAdmin extends JFrame implements ActionListener{
	JMenuBar menuBar;
	JMenu menuAccount, menuManage;
	JMenuItem menuLogout,  menuAccounts, menuRestomenu;
	JDesktopPane dpaneAdm;
	JInternalFrame internalfrAccs, frameManageMenu;
	JPanel accountsMainPanel, restomenuMainPanel;
	ArrayList<User>usList = new ArrayList<>();
	ResultSet rs;
	Connect con;
	JPanel superPanel,mmPanel, mainPanel, idmainPanel, fullnameMainPanel, roleMainPanel, emailMainPanel, passwordMainPanel,fnameFieldPanel, emailFieldPanel, pwFieldPanel, roleCbBoxPanel,tablePanel;
	JLabel idTitle, userId, fullname, role, email, password, emptylabel;
	JTextField fullnameField, emailField;
	JComboBox <String> roleCbBox;
	JPasswordField pwField;
	JButton insertBtn, updateBtn, deleteBtn;
	JTable user;
	JScrollPane jspUser;

			//NOMOR 4//
	JPanel parentPanel = new JPanel();
	
	//ID Component
	JPanel panelId = new JPanel();
	JLabel labelId = new JLabel();
	JLabel labelMenuId = new JLabel();
	
	//Name Component
	JPanel panelName = new JPanel();
	JLabel labelName = new JLabel();
	JTextField tfName = new JTextField();
	JButton btnInsert = new JButton("Insert");
	
	//Sell Price Component
	JPanel panelSellPrice = new JPanel();
	JLabel labelSellPrice = new JLabel();
	JTextField tfSellPrice = new JTextField();
	JButton btnUpdate = new JButton("Update");
	
	//Ingredient Component
	JPanel panelIngrePrice = new JPanel();
	JLabel labelIngrePrice = new JLabel();
	JTextField tfIngrePrice = new JTextField();
	JButton btnDelete = new JButton("Delete");
	
	//Table Component
	JTable tableMenu = new JTable();
	JScrollPane spTableMenu = new JScrollPane(tableMenu);
	//end
	
	public MainFrameAdmin() {
		
		menuBar = new JMenuBar();
		menuAccount = new JMenu("Account");
		menuManage = new JMenu("Manage");
		menuLogout = new JMenuItem("Logout");
		menuAccounts = new JMenuItem("Accounts");
		menuRestomenu = new JMenuItem("Restaurant Menu");
		menuAccount.add(menuLogout);
		menuManage.add(menuAccounts);
		menuManage.add(menuRestomenu);
		menuBar.add(menuAccount);
		menuBar.add(menuManage);
		menuLogout.addActionListener(this);
		menuAccounts.addActionListener(this);
		menuRestomenu.addActionListener(this);

		dpaneAdm = new JDesktopPane();
		internalfrAccounts(); 
		dpaneAdm.add(internalfrAccs);
		initManageMenu();
		dpaneAdm.add(frameManageMenu);
		
		getContentPane().add(dpaneAdm, BorderLayout.CENTER);
			
	
		setJMenuBar(menuBar);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("Amore POS");
		setLocationRelativeTo(null);
	}
	

	private void internalfrAccounts() {
				internalfrAccs = new JInternalFrame("Manage Account Menu", true, true, true, true);
				accountsMainPanel = new JPanel();
			
				superPanel = new JPanel(new GridLayout(5,1));
				mainPanel = new JPanel(new BorderLayout());
				mmPanel = new JPanel();
				idmainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,30,0));
				fullnameMainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,30,0));
				roleMainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,30,0));
				emailMainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,30,0));
				passwordMainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,30,0));
				tablePanel = new JPanel();
				fnameFieldPanel = new JPanel();
				emailFieldPanel = new JPanel();
				pwFieldPanel = new JPanel();
				roleCbBoxPanel = new JPanel();
		        idTitle = new JLabel("ID: ");
				userId  = new JLabel(" ");
				fullname  = new JLabel("Fullname: ");
				role  = new JLabel("Role: ");
				email  = new JLabel("Email: ");
				password  = new JLabel("Password ");
				emptylabel = new JLabel("                                                   ");
				fullnameField = new JTextField();
				emailField = new JTextField();
				String [] roles = {"Accountant", "Admin", "Cashier"};
				roleCbBox = new JComboBox<>(roles);
				pwField = new JPasswordField();
				insertBtn = new JButton("Insert");
				updateBtn = new JButton("Update");
				deleteBtn = new JButton("Delete");
				Object[] columnName = {"ID","FullName","Role","Email","Password"};
				DefaultTableModel dtms = new DefaultTableModel(null, columnName) {
					    public boolean isCellEditable(int rowIdx, int columnIdx) {
					        return false;
					    }
				};
				
				///
				user = new JTable(dtms);
				user.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				user.addMouseListener(new MouseListener() {
					

					@Override
					public void mouseClicked(MouseEvent arg0) {
						int idx = user.getSelectedRow();
						userId.setText(user.getValueAt(idx, 0).toString());
						fullnameField.setText(user.getValueAt(idx, 1).toString());
						roleCbBox.setSelectedItem(user.getValueAt(idx, 2).toString());
						emailField.setText(user.getValueAt(idx, 3).toString());
						pwField.setText(user.getValueAt(idx, 4).toString());
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});

				jspUser = new JScrollPane(user);

				idmainPanel.add(idTitle);
				idTitle.setPreferredSize(new Dimension(112, 30));
				idmainPanel.add(userId);
			
				fullname.setPreferredSize(new Dimension(100, 30));
				fullnameMainPanel.add(fullname);
				fullnameField.setPreferredSize(new Dimension(150, 30));
				fnameFieldPanel.add(fullnameField);
				fullnameMainPanel.add(fnameFieldPanel);
				insertBtn.setPreferredSize(new Dimension(150, 30));
				fullnameMainPanel.add(insertBtn);

				role.setPreferredSize(new Dimension(100, 30));
				roleMainPanel.add(role);
				roleCbBox.setPreferredSize(new Dimension(150, 30));
				roleCbBoxPanel.add(roleCbBox);
				roleMainPanel.add(roleCbBoxPanel);
				updateBtn.setPreferredSize(new Dimension(150, 30));
				roleMainPanel.add(updateBtn);

				email.setPreferredSize(new Dimension(100, 30));
				emailMainPanel.add(email);
				emailField.setPreferredSize(new Dimension(150, 30));
				emailFieldPanel.add(emailField);
				emailMainPanel.add(emailFieldPanel);
				deleteBtn.setPreferredSize(new Dimension(150, 30));
				emailMainPanel.add(deleteBtn);
				
				password.setPreferredSize(new Dimension(100, 30));
				passwordMainPanel.add(password);
				pwField.setPreferredSize(new Dimension(150, 30));
				pwFieldPanel.add(pwField);
				passwordMainPanel.add(pwFieldPanel);
				passwordMainPanel.add(emptylabel);
				
				insertBtn.addActionListener(this);
				updateBtn.addActionListener(this);
				deleteBtn.addActionListener(this);

				 BoxLayout boxlayout = new BoxLayout(mmPanel, BoxLayout.Y_AXIS); 
			     mmPanel.setLayout(boxlayout); 
			     mmPanel.setBorder(new EmptyBorder(new Insets(20, 10, 20, 20))); //top left bottom right
			     mainPanel.add(idmainPanel, BorderLayout.NORTH);		      
			     superPanel.add(fullnameMainPanel);
			     superPanel.add(roleMainPanel);
			     superPanel.add(emailMainPanel);
			     superPanel.add(passwordMainPanel);	        
			     mainPanel.add(superPanel, BorderLayout.CENTER);
			     mainPanel.add(jspUser, BorderLayout.SOUTH);		   
			     mmPanel.add(mainPanel);
			     accountsMainPanel.add(mmPanel);
			     
			    con = new Connect();
			    getDataFrmDB();
			    
				internalfrAccs.getContentPane().add(accountsMainPanel);
				internalfrAccs.setSize(650, 700);
				internalfrAccs.setDefaultCloseOperation(HIDE_ON_CLOSE);
	}


	
	private void getDataFrmDB() {
		rs = con.execQuery("SELECT * FROM users");
		DefaultTableModel model = (DefaultTableModel) user.getModel();
		try {
			while(rs.next()) {
				User uss = new User(rs.getObject(1).toString(), rs.getObject(2).toString(), rs.getObject(3).toString(), rs.getObject(4).toString(),rs.getObject(5).toString());
				usList.add(uss);
				model.addRow(usList.get(usList.size()-1).returnAsObj());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		//klik menu yg ada di dropdown / menu itemnya, tar dy bsa lakuin ssuatu
		if(e.getSource()==menuAccounts) {
			internalfrAccs.setLocation(650,120);
			internalfrAccs.setVisible(true);
		}
		
		if(e.getSource()==menuRestomenu) {
			frameManageMenu.setLocation(650,150);
			frameManageMenu.setVisible(true);
		}
		
		if(e.getSource()==menuLogout) {
			this.dispose();
			new LoginFrame();
		}

		if(e.getSource()==insertBtn) {
			if(fullnameField.getText().isEmpty()|| emailField.getText().isEmpty() || pwField.getText().isEmpty()){
				JOptionPane.showMessageDialog(this, "All Fields must be filled!", "Message", JOptionPane.INFORMATION_MESSAGE);
				return;
		}

		    int countAt = countofAt();
		    int countDotAftAt = countofDotAftAt();
		    Character dotBefAt = checkIdxDotBefAt();
		    Character dotAftAt = checkIdxDotAftAt();
	        
		    if((emailField.getText().startsWith("@")||emailField.getText().endsWith("@")||emailField.getText().endsWith(".")||countAt>1||countDotAftAt==0||dotBefAt.equals('.')||dotAftAt.equals('.'))) {
				JOptionPane.showMessageDialog(this, "Wrong email format!", "Message", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		     
			 String em = emailField.getText();
	         String pw = pwField.getText();

	         rs = con.execQuery("SELECT * FROM users WHERE email LIKE '"+em+"'");
	         boolean exists = false;
		
				try {
					while(rs.next()) {
					 	exists = true;
					 	if(exists) {
					 		JOptionPane.showMessageDialog(this, "Email already used!", "Message", JOptionPane.INFORMATION_MESSAGE);
					 		return;
					 	}
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		
				Boolean pwVal = pwValidation();
			if(pwVal.equals(false)){
				JOptionPane.showMessageDialog(this, "Password must be Alphanumeric", "Message", JOptionPane.INFORMATION_MESSAGE);
				return;
					}
			
			String useridRand = generateRandomString(10);
			userId.setText(useridRand);
			User newUs = new User(useridRand, fullnameField.getText(), roleCbBox.getSelectedItem().toString(), emailField.getText(), pwField.getText()); 
			usList.add(newUs);
			con.insertUser(newUs);
			JOptionPane.showMessageDialog(this, "Insert Success!", "Message", JOptionPane.INFORMATION_MESSAGE);
			userId.setText("");
			fullnameField.setText("");
			emailField.setText("");
			pwField.setText("");
			DefaultTableModel mo = (DefaultTableModel) user.getModel();
			mo.addRow(usList.get(usList.size()-1).returnAsObj());
			
		}
		if(e.getSource()==updateBtn) {
			if(user.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(this, "Please select data to be updated!", "Message", JOptionPane.INFORMATION_MESSAGE);
			}
			
			if(fullnameField.getText().isEmpty()|| emailField.getText().isEmpty() || pwField.getText().isEmpty()){
				JOptionPane.showMessageDialog(this, "All Fields must be filled!", "Message", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

		    int countAt = countofAt();    
		    int countDotAftAt = countofDotAftAt();
		    Character dotBefAt = checkIdxDotBefAt();
		    Character dotAftAt = checkIdxDotAftAt();
	        
		    if((emailField.getText().startsWith("@")||emailField.getText().endsWith("@")||emailField.getText().endsWith("."))) {
				JOptionPane.showMessageDialog(this, "Wrong email format!", "Message", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		    
		    if(countAt>1||countDotAftAt==0||dotBefAt.equals('.')||dotAftAt.equals('.')) {
		  	JOptionPane.showMessageDialog(this, "Wrong email format!", "Message", JOptionPane.INFORMATION_MESSAGE);
		  	return;
		    }
		    
		
			 
			 String em = emailField.getText();
	         String pw = pwField.getText();

	         rs = con.execQuery("SELECT * FROM users WHERE email LIKE '"+em+"'");
	         boolean exists = false;
		
				try {
					while(rs.next()) {
					 	exists = true;
					 	if(exists) {
					 		JOptionPane.showMessageDialog(this, "Email already used!", "Message", JOptionPane.INFORMATION_MESSAGE);
					 		return;
					 	}
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}

				Boolean pwVal = pwValidation();
				if(pwVal.equals(false)){
					JOptionPane.showMessageDialog(this, "Password must be Alphanumeric", "Message", JOptionPane.INFORMATION_MESSAGE);
					return;
						}
			
				con.updateUser(userId.getText(),fullnameField.getText(),roleCbBox.getSelectedItem().toString(), emailField.getText(), pwField.getText());	
				JOptionPane.showMessageDialog(this, "Update Success!", "Message", JOptionPane.INFORMATION_MESSAGE);
				userId.setText("");
				fullnameField.setText("");
				emailField.setText("");
				pwField.setText("");
				DefaultTableModel mo = (DefaultTableModel) user.getModel();
				mo.setRowCount(0);
				getDataFrmDB();

		}
		
		if(e.getSource()==deleteBtn) {
				if(user.getSelectedRow()==-1){
					JOptionPane.showMessageDialog(this, new String("Please select data to be deleted first"));
					return;
				}
				else{
					con.deleteUser(userId.getText());
					JOptionPane.showMessageDialog(this, "Delete Success!", "Message", JOptionPane.INFORMATION_MESSAGE);
					userId.setText("");
					fullnameField.setText("");
					emailField.setText("");
					pwField.setText("");
					DefaultTableModel mo = (DefaultTableModel) user.getModel();
					mo.setRowCount(0);
					getDataFrmDB();

					
				}
			
		}
		
		if(e.getSource()==btnInsert){
			if(tfName.getText().equals("")){
				JOptionPane.showMessageDialog(this, new String("Name must be filled"));;
			}
			else if(tfSellPrice.getText().equals("")){
				JOptionPane.showMessageDialog(this, new String("Sell Price must be filled"));
			}
			else if(tfIngrePrice.getText().equals("")){
				JOptionPane.showMessageDialog(this, new String("Ingredient Price must be filled"));
			}
			else if(isInteger(tfSellPrice.getText().toString())==false){
				JOptionPane.showMessageDialog(this, new String("Prices must be a number"));
			}
			else if(isInteger(tfIngrePrice.getText().toString())==false){
				JOptionPane.showMessageDialog(this, new String("Prices must be a number"));
			}
			else{
				labelMenuId.setText(generateId());
				Connect sql = new Connect();
				sql.execUpdate("INSERT INTO menu VALUES ('" +labelMenuId.getText()+ "','" +tfName.getText()+ "','" +tfSellPrice.getText()+ "','" +tfIngrePrice.getText()+ "')");
				JOptionPane.showMessageDialog(this, new String("Insert Success!"));
				loadTable();
			}
		}
		if(e.getSource()==btnUpdate){
			if(tableMenu.getSelectedRow()==-1){
				JOptionPane.showMessageDialog(this, new String("Please select data to be updated first"));
			}
			else if(tfName.getText().equals("")){
				JOptionPane.showMessageDialog(this, new String("Fields must be filled"));;
			}
			else if(tfSellPrice.getText().equals("")){
				JOptionPane.showMessageDialog(this, new String("Fields must be filled"));
			}
			else if(tfIngrePrice.getText().equals("")){
				JOptionPane.showMessageDialog(this, new String("Fields must be filled"));
			}
			else if(isInteger(tfSellPrice.getText().toString())==false){
				JOptionPane.showMessageDialog(this, new String("Prices must be a number"));
			}
			else if(isInteger(tfIngrePrice.getText().toString())==false){
				JOptionPane.showMessageDialog(this, new String("Prices must be a number"));
			}
			else{
				Connect sql = new Connect();
				sql.execUpdate("UPDATE menu SET name = '" +tfName.getText()+ "', sellprice = '" +tfSellPrice.getText()+ "', ingredientprice = '" +tfIngrePrice.getText()+ "' WHERE menuid = '" +labelMenuId.getText()+ "'");
				JOptionPane.showMessageDialog(this, new String("Update Success!"));
				loadTable();
			}
		}
		if(e.getSource()==btnDelete){
			if(tableMenu.getSelectedRow()==-1){
				JOptionPane.showMessageDialog(this, new String("Please select data to be updated first"));
			}
			else{
				Connect sql = new Connect();
				sql.execUpdate("DELETE FROM menu WHERE menuid ='" +labelMenuId.getText()+ "'");
				JOptionPane.showMessageDialog(this, new String("Delete Success!"));
				loadTable();
			}
		}
		
	}
	
	

private boolean pwValidation() {
		
	String pw = pwField.getText();
	int t = 0;
	for(int i=0;i<pw.length();i++) {
		char ch = pw.charAt(i);
		if(Character.isLetterOrDigit(ch)) {
			t+=1;
		} 
	}
	if(t==pw.length()) {
		return true;
	}
	return false;
	
	}


	private Character checkIdxDotAftAt() {
		 int idxAt = (emailField.getText().indexOf('@')); 
		 System.out.println(idxAt); //check
		 if(idxAt<(emailField.getText().length()-1)) {
			 int idxCheck = idxAt+1;
			 System.out.println("idxCheck: " + idxCheck);//check
			 String emailChck = emailField.getText();
			 System.out.println("Email Chck: " + emailChck);//check
			 char charRes = emailChck.charAt(idxCheck); 
			 System.out.println("charRes: " + charRes);//check
			Character refCharRes = Character.valueOf(charRes);
			 System.out.println("refCharRes: " + refCharRes);//check
			  return refCharRes; 
		 }
		return ('.');
	}

	private Character checkIdxDotBefAt() {
	 int idxAt = (emailField.getText().indexOf('@')); 
	 if(idxAt>=1) {
	 int idxCheck = idxAt-1;
	 String emailChck = emailField.getText();
	 char charRes = emailChck.charAt(idxCheck); 
	Character refCharRes = Character.valueOf(charRes);
	  return refCharRes;
	}
	  
	  return ('.');
	}


	private int countofDotAftAt() {
		String buffer = emailField.getText().substring(emailField.getText().indexOf('@')+1, emailField.getText().length());
        int length = buffer.length();

        int countDotAfterAt = 0;
        for (int i=0; i < length; i++) {
            if(buffer.charAt(i)=='.')
                countDotAfterAt++; 
            }
        return countDotAfterAt;
	}

	private int countofAt() {
		int countAt = 0;

        for (int i = 0; i < emailField.getText().length(); i++) {
            if(emailField.getText().charAt(i)=='@')
                countAt++; }
		return countAt;
	}

	public static String generateRandomString(int length) {
	   
	    String lc = "abcdefghijklmnopqrstuvwxyz";
	    String uc = lc.toUpperCase();
	    String num = "0123456789";

	    String randomData = lc + uc + num;
	    SecureRandom secRandom = new SecureRandom();

	    if (length < 1) throw new IllegalArgumentException();

	    StringBuilder sb = new StringBuilder(length);
	    
	    for (int i = 0; i < length; i++) {
	      
	        int rndCharAt = secRandom.nextInt(randomData.length());
	        char rndChar = randomData.charAt(rndCharAt);

	        sb.append(rndChar);
	    }

	    return sb.toString();
	}

	public void initManageMenu(){
		
			frameManageMenu = new JInternalFrame("Manage Restaurant Menu", true, true, true, true);
		
			parentPanel.setLayout(new GridLayout(4,3));
			parentPanel.add(panelId);
			parentPanel.add(panelName);
			parentPanel.add(panelSellPrice);
			parentPanel.add(panelIngrePrice);
			
			//ID 
			panelId.setLayout(new GridLayout());
			labelId.setText("ID: ");
			panelId.add(labelId);
			panelId.add(labelMenuId);
	
			//Name
			panelName.setLayout(new GridLayout());
			labelName.setText("Name: ");
			panelName.add(labelName);
			panelName.add(tfName);
			panelName.add(btnInsert);
	
			//Sell Price 
			panelSellPrice.setLayout(new GridLayout());
			labelSellPrice.setText("Sell Price: "); 
			panelSellPrice.add(labelSellPrice);
			panelSellPrice.add(tfSellPrice);
			panelSellPrice.add(btnUpdate);
	
			//Ingredient Price
			panelIngrePrice.setLayout(new GridLayout());
			labelIngrePrice.setText("Ingredient Price: ");
			panelIngrePrice.add(labelIngrePrice);
			panelIngrePrice.add(tfIngrePrice);
			panelIngrePrice.add(btnDelete);
			
			//Table
			loadTable();
			frameManageMenu.add(spTableMenu);
	
			btnInsert.addActionListener(this);
			btnUpdate.addActionListener(this);
			btnDelete.addActionListener(this);
			
			frameManageMenu.add(parentPanel,BorderLayout.NORTH);
			frameManageMenu.setBounds(0, 0, 1000, 500);
			frameManageMenu.setDefaultCloseOperation(HIDE_ON_CLOSE);

	}
	
	public boolean isInteger(String number){
		boolean yesno = true;
		try {
		long angka = Long.parseLong(number);
		} 
		catch (Exception e) {
			yesno = false;
		}
		return yesno;
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
	
	public void loadTable(){
		tableMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableMenu.addMouseListener(new MouseListener(){
	
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = tableMenu.getSelectedRow();
				labelMenuId.setText(tableMenu.getValueAt(index, 0).toString());
				tfName.setText(tableMenu.getValueAt(index, 1).toString());
				tfSellPrice.setText(tableMenu.getValueAt(index, 2).toString());
				tfIngrePrice.setText(tableMenu.getValueAt(index, 3).toString());
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
		DefaultTableModel tm = new DefaultTableModel();
		tableMenu.setModel(tm);
		
		tm.addColumn("ID");
		tm.addColumn("Name");
		tm.addColumn("Sell Price");
		tm.addColumn("Ingredient Price");
		
		try{
		ResultSet rs = sql.execQuery("SELECT * FROM menu");
		while(rs.next()){
		tm.addRow(new Object[]{rs.getString("menuid"),rs.getString("name"),rs.getInt("sellprice"),rs.getInt("ingredientprice")});
		}
		}catch(Exception e){
			
		}
	}

	
}
