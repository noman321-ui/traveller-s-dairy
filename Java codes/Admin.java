import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Admin extends JFrame implements ActionListener{
	private JButton logout,delete,number,district,division,tag,delNumber,delDistrict,delDivision;
	private JLabel userNameLabel,memberListLabel,emergencyNumberLabel,addLabel,delLabel;
	private JFrame parent;
	private JTextArea memberListTextArea;
	private Delete deletePage;
	private EmergencyNumbers enPage;
	private AddDivision adPage;
	private DeleteDivision ddPage;
	public Admin(){
		super("Admin");
		deletePage=new Delete();
		adPage=new AddDivision();
		ddPage=new DeleteDivision();
		enPage=new EmergencyNumbers();
		logout=new JButton("Logout");
		delete=new JButton("Delete");
		number=new JButton("Add Number");
		district=new JButton("Add District");
		division=new JButton("Add Division");
		tag=new JButton("Add Tag");
		delNumber=new JButton("Delete Number");
		delDistrict=new JButton("Delete District");
		delDivision=new JButton("Delete Division");
		addLabel=new JLabel("Add Information");
		userNameLabel=new JLabel("Welcome, Admin-18");
		memberListLabel=new JLabel("Member List : ");
		emergencyNumberLabel=new JLabel("Emergency Numbers ");
		delLabel=new JLabel("Delete Information");
		memberListTextArea=new JTextArea("");
		memberListTextArea.setEditable(false);
		addLabel.setForeground(Color.WHITE);
		delLabel.setForeground(Color.WHITE);
		userNameLabel.setForeground(Color.WHITE);
		memberListLabel.setForeground(Color.WHITE);
		emergencyNumberLabel.setForeground(Color.WHITE);
		userNameLabel.setFont(new Font("Courier", Font.BOLD, 20));
		memberListLabel.setFont(new Font("Courier", Font.BOLD, 20));
		delLabel.setFont(new Font("Courier", Font.BOLD, 20));
		emergencyNumberLabel.setFont(new Font("Courier", Font.BOLD, 20));
		addLabel.setFont(new Font("Courier", Font.BOLD, 20));
		add(logout);
		add(delete);
		add(userNameLabel);
		add(memberListLabel);
		add(memberListTextArea);
		add(emergencyNumberLabel);
		add(number);
		add(addLabel);
		add(district);
		add(division);
		add(tag);
		add(delLabel);
		add(delNumber);
		add(delDistrict);
		add(delDivision);
		logout.addActionListener(this);
		delete.addActionListener(this);
		number.addActionListener(this);
		district.addActionListener(this);
		division.addActionListener(this);
		tag.addActionListener(this);
		delNumber.addActionListener(this);
		delDistrict.addActionListener(this);
		delDivision.addActionListener(this);
		logout.setBounds(650,20,100,30);
		userNameLabel.setBounds(400,20,220,30);
		memberListLabel.setBounds(50,60,200,30);
		emergencyNumberLabel.setBounds(370+100,60+50,300,30);
		number.setBounds(410+100,100+50,120,30);
		addLabel.setBounds(380+100,150+50,280,30);
		district.setBounds(410+100,200+50,120,30);
		division.setBounds(410+100,250+50,120,30);
		tag.setBounds(410+100,300+50,120,30);
		delLabel.setBounds(370+100,350+50,280,30);
		delNumber.setBounds(410+100,400+50,120,30);
		delDistrict.setBounds(410+100,450+50,120,30);
		delDivision.setBounds(410+100,500+50,120,30);
		memberListTextArea.setBounds(50,100,300,500);
		delete.setBounds(250,610,100,30);
		loadData();
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(800,700);
		getContentPane().setBackground(Color.decode("#001B3A"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void setParent(JFrame x){
		parent=x;
	}
	public void loadData(){
		String sql="select userName, eMail from user";
		try{
			DataAccess da=new DataAccess();
			ResultSet rs=da.getData(sql);
			String data="";
			while(rs.next()){
				data=data+rs.getString("userName")+"  -  ";
				data=data+rs.getString("eMail")+"\n";
			}
			memberListTextArea.setText(data);
		}
		catch(Exception ex){
			System.out.println("Exception in home");
		}
	}
	
	public void actionPerformed(ActionEvent e){
		String go=e.getActionCommand();
		if(go.equals("Logout")){
			parent.setVisible(true);
			parent.setLocationRelativeTo(null);
			this.setVisible(false);
			Login.setAdmin(false);
		}
		else if(go.equals("Delete")){
			deletePage.setVisible(true);
			deletePage.setLocationRelativeTo(null);
			deletePage.setParent(this);
			this.setVisible(false);
		}
		
		else if (go.equals("Add Number")){
			enPage.setVisible(true);
			enPage.setLocationRelativeTo(null);
			enPage.setParent(this);
			this.setVisible(false);
		}
		
		else if (go.equals("Add District")){

		}
		
		else if (go.equals("Add Division")){
			adPage.setVisible(true);
			adPage.setLocationRelativeTo(null);
			adPage.setParent(this);
			this.setVisible(false);
		}
		
		else if (go.equals("Add Tag")){

		}
		
		else if (go.equals("Delete Number")){
	
		}
		
		else if (go.equals("Delete District")){
		
		}
		
		else if (go.equals("Delete Division")){
			ddPage.setVisible(true);
			ddPage.setParent(this);
			ddPage.setLocationRelativeTo(null);
			this.setVisible(false);
		}
	}
}