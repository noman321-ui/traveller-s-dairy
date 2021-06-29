import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Delete extends JFrame implements ActionListener{
	private JComboBox<String> options;
	private JButton delete,back;
	private JFrame parent;
	private String getOption;
	public Delete(){
		super("Delete");
		options=new JComboBox<String>();
		delete=new JButton("Delete");
		back=new JButton("Back");
		add(delete);
		add(back);
		add(options);
		options.setBounds(20,100,350,30);
		delete.setBounds(90,200,100,30);
		back.setBounds(210,200,100,30);
		delete.addActionListener(this);
		back.addActionListener(this);
		options.addActionListener(this);
		loadData();
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(400,400);
		getContentPane().setBackground(Color.decode("#001B3A"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void loadData(){
		options.addItem("Select User");
		String sql="select userName from user";
		try{
			DataAccess da=new DataAccess();
			ResultSet rs=da.getData(sql);
			while(rs.next()){
				String x=rs.getString("userName");
				options.addItem(x);
			}		
		}
		catch(Exception ex){
			System.out.println("Exception in home");
		}
	}
	
	public void deleteData(){
		String sql="delete from user where userName='"+getOption+"'";
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
			deleteData();
		}
		else{
			getOption=options.getSelectedItem().toString();
		}
	}
	
}