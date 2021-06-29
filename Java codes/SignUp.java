import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class SignUp extends JFrame implements ActionListener{
	private JButton next,back;
	private JLabel fNameLabel,lNameLabel,eMailLabel,passowrdLabel,userNameLabel,imgLabel;
	private JTextField fName,lName,eMail,password,userName;
	private JFrame parent;
	private ImageIcon image;
	public SignUp(){
		super("Sign Up");
		fNameLabel=new JLabel("Enter Your First Name : ");
		lNameLabel=new JLabel("Enter Your Last Name : ");
		eMailLabel=new JLabel("Enter Your Email : ");
		passowrdLabel=new JLabel("Enter Your Password : ");
		userNameLabel=new JLabel("Enter Your User Name : ");
		fName=new JTextField(20);
		lName=new JTextField(20);
		eMail=new JTextField(20);
		password=new JTextField(20);
		userName=new JTextField(20);
		next=new JButton("Next");
		back=new JButton("Back");
		image=new ImageIcon("signupp.jpg");
		imgLabel=new JLabel("",image,JLabel.CENTER);
		add(fNameLabel);
		add(lNameLabel);
		add(eMailLabel);
		add(passowrdLabel);
		add(userNameLabel);
		add(fName);
		add(lName);
		add(eMail);
		add(userName);
		add(password);
		add(next);
		add(back);
		add(imgLabel);
		next.addActionListener(this);
		back.addActionListener(this);
		fNameLabel.setFont(new Font("Courier", Font.BOLD, 20));
		lNameLabel.setFont(new Font("Courier", Font.BOLD, 20));
		eMailLabel.setFont(new Font("Courier", Font.BOLD, 20));
		passowrdLabel.setFont(new Font("Courier", Font.BOLD, 20));
		userNameLabel.setFont(new Font("Courier", Font.BOLD, 20));
		fNameLabel.setForeground(Color.white);
		lNameLabel.setForeground(Color.white);
		eMailLabel.setForeground(Color.white);
		userNameLabel.setForeground(Color.white);
		passowrdLabel.setForeground(Color.white);
		fNameLabel.setBounds(300,100,300,30);
		fName.setBounds(600,100,250,30);
		lNameLabel.setBounds(300,200,300,30);
		lName.setBounds(600,200,250,30);
		eMailLabel.setBounds(300,300,300,30);
		eMail.setBounds(600,300,250,30);
		userNameLabel.setBounds(300,400,300,30);
		userName.setBounds(600,400,250,30);
		passowrdLabel.setBounds(300,500,300,30);
		password.setBounds(600,500,250,30);
		imgLabel.setBounds(0,0,1200,800);
		next.setBounds(480,600,100,40);
		back.setBounds(600,600,100,40);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setSize(1200,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void setParent(JFrame x){
		parent=x;
	}
	
	private boolean isEmpty(JTextField s){
		boolean flag=false;
		if(s.getText().length()==0)
			flag=true;
		return flag;
	}
	private boolean isValidEmail(String e){
		String s1="@";
		String s2=".com";
		if(e.contains(s1) && e.contains(s2)){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	private boolean checkValidity(){
		boolean flag=false;
		String un=userName.getText();
		String em=eMail.getText();
		String sql="select * from user where userName='"+un+"' and eMail='"+em+"'";
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
		if(go.equals("Next")){
			if(isEmpty(fName) || isEmpty(lName) || isEmpty(eMail) || isEmpty(userName) || isEmpty(password)){
				JOptionPane.showMessageDialog(this,"All fields are mandatory ");
			}
			else if(!isValidEmail(eMail.getText())){
				JOptionPane.showMessageDialog(this,"Invalid Email");
			}
			else if (checkValidity()){
				JOptionPane.showMessageDialog(this,"This Email or UserName has already taken ");
			}
			else{
				DataAccess da=new DataAccess();
				boolean flag=false;
				String sql0="select * from user ";
				try{
					ResultSet rs=da.getData(sql0);
					while(rs.next()){
						flag=true;
					}
				}
				catch(Exception ex){
					System.out.println("Exception occurred");
				}
				String sql="";
				if(flag){
					sql="insert into user(firstName,lastName,eMail,userName,password) values('"+fName.getText()+"','"+lName.getText()+"','"+eMail.getText()+"','"+userName.getText()+"','"+password.getText()+"')";
				}
				else{
					sql="insert into user(id,firstName,lastName,eMail,userName,password) values('0001','"+fName.getText()+"','"+lName.getText()+"','"+eMail.getText()+"','"+userName.getText()+"','"+password.getText()+"')";
				}	
				if(da.updateDB(sql)>0){
					JOptionPane.showMessageDialog(this,"SignUp complete");
				}
				else{
					JOptionPane.showMessageDialog(this,"SignUp Failed");
				}
			}
		}
		else if(go.equals("Back")){
			parent.setVisible(true);
			parent.setLocationRelativeTo(null);
			this.setVisible(false);
		}
	}
}