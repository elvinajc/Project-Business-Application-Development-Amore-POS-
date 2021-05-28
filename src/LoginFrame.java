import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame implements ActionListener {
	
	ResultSet rs;
	
	JPanel parent_panel = new JPanel();
	
	//Title Component
	JPanel title_panel = new JPanel();
	JLabel title_label = new JLabel();
	
	//Email Component
	JPanel email_panel = new JPanel();
	JLabel email_label = new JLabel();
	JTextField email_field = new JTextField();
	
	//Password Component
	JPanel password_panel = new JPanel();
	JLabel password_label = new JLabel();
	JPasswordField password_field = new JPasswordField();
	
	//Button Component
	JPanel button_panel = new JPanel();
	JButton login_btn = new JButton("Login");
	
	public static String idLogin;
	
	public LoginFrame() {
		//Title
		title_label.setText("Amore POS");
		title_panel.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
		title_label.setPreferredSize(new Dimension(80, 20));
		title_panel.add(title_label);
		
		//Email
		email_label.setText("Email:");
		email_panel.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
		email_label.setPreferredSize(new Dimension(80, 20));
		email_field.setPreferredSize(new Dimension(200, 35));
		email_panel.add(email_label);
		email_panel.add(email_field);
		
		//Password
		password_label.setText("Password:");
		password_panel.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
		password_label.setPreferredSize(new Dimension(80, 40));
		password_field.setPreferredSize(new Dimension(200, 35));
		password_panel.add(password_label);
		password_panel.add(password_field);
		
		//Button
		login_btn.setPreferredSize(new Dimension(300, 35));
		login_btn.addActionListener(this);
		button_panel.add(login_btn);
		
		//Parent panel
		parent_panel.setLayout(new BoxLayout(parent_panel, BoxLayout.Y_AXIS));
		parent_panel.add(Box.createRigidArea(new Dimension(0,40)));
		parent_panel.add(title_panel);
		parent_panel.add(email_panel);
		parent_panel.add(password_panel);
		parent_panel.add(button_panel);
		
		add(parent_panel);
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(500,350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Amore POS");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==login_btn) {
			if(email_field.getText().equals("")||password_field.getText().equals("")){
				JOptionPane.showMessageDialog(this, new String("Email and Password cannot be Empty"));
			}else {
				rs = Main.con.execQuery("SELECT userid,fullname,role,email,password FROM users WHERE email like '"
						+email_field.getText()+"' AND password like '"+password_field.getText()+"'");
				boolean exist = false;
				try {
					while(rs.next()) {
						exist = true;
						User ussLogin;
						
						ussLogin = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
						idLogin = ussLogin.getUserid();
						
						JOptionPane.showMessageDialog(this, new String("Welcome, "+rs.getString(2)));
						
						if(rs.getString(3).equals("Admin")){
							MainFrameAdmin mfadmin = new MainFrameAdmin();
							dispose();	
						}else if(rs.getString(3).equals("Accountant")){
							MainFrameAccountant mfaccountant = new MainFrameAccountant();
							dispose();	
						}else if(rs.getString(3).equals("Cashier")){
							MainFrameCashier mfcashier = new MainFrameCashier();
							dispose();	
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(exist) {
					
				}else {
					JOptionPane.showMessageDialog(this, new String("Incorrect Email or Password"));
				}
			}
			 
		}
	}

}
