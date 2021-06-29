import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener{
	private JLabel userNameLabel,passwordLabel,imgLabel;
	private JTextField userName;
	private JPasswordField password;
	private JButton login,back;
	private ImageIcon image;
	private JFrame parent;
	private Admin adminPage;
	private User userPage;
	private String un;
	private  static boolean admin=false;
	public Login(){
		super("Login");
		adminPage=new Admin();
		userNameLabel=new JLabel("Enter Your User Name : ");
		passwordLabel=new JLabel("Enter Your Password : ");
		userName=new JTextField(20);
		password=new JPasswordField(20);
		login=new JButton("Login");
		back=new JButton("Back");
		image=new ImageIcon("log.jpg");
		imgLabel=new JLabel("",image,JLabel.CENTER);
		add(userNameLabel);
		add(passwordLabel);
		add(userName);
		add(password);
		add(login);
		add(back);
		add(imgLabel);
		userNameLabel.setFont(new Font("Courier", Font.BOLD, 20));
		passwordLabel.setFont(new Font("Courier", Font.BOLD, 20));
		userNameLabel.setBounds(100,100,300,30);
		userNameLabel.setForeground(Color.white);
		userName.setBounds(450,100,250,30);
		passwordLabel.setBounds(100,200,300,30);
		passwordLabel.setForeground(Color.white);
		password.setBounds(450,200,250,30);
		login.setBounds(400,300,100,40);
		back.setBounds(520,300,100,40);
		imgLabel.setBounds(0,0,1200,800);
		login.addActionListener(this);
		back.addActionListener(this);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(1200,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
	public void  setParent(JFrame x){
		parent=x;
	}
	public static void setAdmin(boolean x){
		admin=x;
	}
	
	private boolean checkAuth(){
		boolean flag=false;
		un=userName.getText();
		char[] p=password.getPassword();
		String x=new String(p);
		String sql="";
		if(un.contains("-")){
			sql="select * from admin where id='"+un+"' and password='"+x+"'";
			admin=true;
		}
		else{
			sql="select * from user where userName='"+un+"' and password='"+x+"'";
		}
		try{
			DataAccess da=new DataAccess();
			ResultSet rs=da.getData(sql);
			while(rs.next()){
					flag=true;
			}
		}
		catch(Exception ex){
			System.out.println("Exception occurred");
		}
		return flag;
	}
	
	public void actionPerformed(ActionEvent e){
		String go=e.getActionCommand();
		if(go.equals("Login")){
			if(checkAuth()){
				if(admin){
					adminPage.setVisible(true);
					adminPage.setLocationRelativeTo(null);
					adminPage.setParent(this);
				}
				else{
					userPage=new User(un);
					userPage.setVisible(true);
					userPage.setLocationRelativeTo(null);
					userPage.setParent(this);					
				}
				this.setVisible(false);
			}
			else{
				JOptionPane.showMessageDialog(this,"Invalid User Name or password ");
				System.out.println("Incorrect");
			}
		}
		else if(go.equals("Back")){
			parent.setVisible(true);
			parent.setLocationRelativeTo(null);
			this.setVisible(false);
		}
	}
}