import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class User extends JFrame implements ActionListener{
	private JFrame parent;
	private String userName="";
	private JLabel userNameLabel;
	private JButton logout,addMem,updateMem,delMem,showMem,searchAll;
	private AddMemory addMemoryPage;
	private SelectMemory smPage;
	private EditMemory emPage;
	private DeleteMemory dmPage;
	public User(String x){
		super("User");
		emPage=new EditMemory(x);
		dmPage=new DeleteMemory(x);
		userNameLabel=new JLabel("Welcome, "+x);
		userNameLabel.setFont(new Font("Courier", Font.BOLD, 20));
		userNameLabel.setForeground(Color.WHITE);
		logout=new JButton("Logout");
		addMem=new JButton("Add Memory");
		delMem=new JButton("Delete Memory");
		updateMem=new JButton("Update Memory");
		showMem=new JButton("Show Memory");
		searchAll=new JButton("Search");
		addMemoryPage=new AddMemory(x);
		smPage=new SelectMemory(x);
		add(userNameLabel);
		add(logout);
		add(addMem);
		add(updateMem);
		add(delMem);
		add(showMem);
		add(searchAll);
		logout.addActionListener(this);
		addMem.addActionListener(this);
		updateMem.addActionListener(this);
		delMem.addActionListener(this);
		showMem.addActionListener(this);
		searchAll.addActionListener(this);
		userNameLabel.setBounds(850,20,220,30);
		logout.setBounds(1070,20,100,30);
		addMem.setBounds(20,70,200,30);
		updateMem.setBounds(20,120,200,30);
		delMem.setBounds(20,170,200,30);
		showMem.setBounds(20,220,200,30);
		searchAll.setBounds(20,270,200,30);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(1200,800);
		getContentPane().setBackground(Color.decode("#001B3A"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void setParent(JFrame x){
		parent=x;
	}

	public void setUserName(String x){
		userName=x;
	}

	public void actionPerformed(ActionEvent e){
		String go=e.getActionCommand();
		if(go.equals("Logout")){
			parent.setVisible(true);
			parent.setLocationRelativeTo(null);
			this.setVisible(false);
		}
		else if (go.equals("Add Memory")){
			addMemoryPage.setVisible(true);
			addMemoryPage.setLocationRelativeTo(null);
			addMemoryPage.setParent(this);
			this.setVisible(false);
		}
		else if (go.equals("Show Memory")){
			smPage.setVisible(true);
			smPage.setLocationRelativeTo(null);
			smPage.setParent(this);
			this.setVisible(false);
		}
		else if (go.equals("Update Memory")){
			emPage.setVisible(true);
			emPage.setLocationRelativeTo(null);
			emPage.setParent(this);
			this.setVisible(false);
		}
		else if (go.equals("Delete Memory")){
			dmPage.setVisible(true);
			dmPage.setLocationRelativeTo(null);
			dmPage.setParent(this);
			this.setVisible(false);
		}
		
	}
}