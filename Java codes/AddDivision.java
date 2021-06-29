import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class AddDivision extends JFrame implements ActionListener{
	
	private JLabel divisionLabel;
	private JTextField division;
	private JButton save,back;
	private JFrame parent;
	
	public AddDivision(){
		super("Add Division");
		
		divisionLabel=new JLabel("Enter Division Name ");
		division=new JTextField(20);
		save=new JButton("Save");
		back=new JButton("Back");
		
		add(divisionLabel);
		add(division);
		add(save);
		add(back);
		
		save.addActionListener(this);
		back.addActionListener(this);
		
		divisionLabel.setBounds(50,50,300,30);
		division.setBounds(50,100,300,30);
		save.setBounds(80,150,100,30);
		back.setBounds(200,150,100,30);
		
		divisionLabel.setForeground(Color.white);
		divisionLabel.setFont(new Font("Courier", Font.BOLD, 20));
		
		
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(500,300);
		getContentPane().setBackground(Color.decode("#001B3A"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setParent(JFrame x){
		parent=x;
	}
	
	public void actionPerformed(ActionEvent e){
		String go=e.getActionCommand();
		if(go.equals("Back")){
			parent.setVisible(true);
			parent.setLocationRelativeTo(null);
			this.setVisible(false);
		}
		else if(go.equals("Save")){
			DataAccess da=new DataAccess();
			String sql="";
			try{
				sql="insert into division(divisionName) values('"+division.getText()+"')";
			}
			catch(Exception ex){
				System.out.println("Exception occurred");
			}
			if(da.updateDB(sql)>0){
				JOptionPane.showMessageDialog(this,"Success");
			}
			else{
				JOptionPane.showMessageDialog(this,"Error");
			}
		}
	}
	
	
}