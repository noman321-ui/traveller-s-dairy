import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class DeleteDivision extends JFrame implements ActionListener{
	private JLabel divisionLabel;
	private JTextField division;
	private JButton delete,back;
	private JFrame parent;
	
	public DeleteDivision(){
		super("Delete Division");
		
		divisionLabel=new JLabel("Enter Division Name ");
		division=new JTextField(20);
		delete=new JButton("Delete");
		back=new JButton("Back");
		
		add(divisionLabel);
		add(division);
		add(delete);
		add(back);
		
		delete.addActionListener(this);
		back.addActionListener(this);
		
		divisionLabel.setBounds(50,50,300,30);
		division.setBounds(50,100,300,30);
		delete.setBounds(80,150,100,30);
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
		else if(go.equals("Delete")){
			String sql="delete from division where divisionName='"+division.getText()+"'";
			try{
				DataAccess da=new DataAccess();		
				if(da.updateDB(sql)>0){
					JOptionPane.showMessageDialog(this,"Data Deleted");
				}
				else{
					JOptionPane.showMessageDialog(this,"Delete Error!");
				}			
			}
			catch(Exception ex){
				System.out.println("Exception in home");
			}
		}
	}
}