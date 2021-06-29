import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class DeleteMemory extends JFrame implements ActionListener{
	private JComboBox<String> options;
	private JButton delete,back;
	private JFrame parent;
	private String getOption;
	private String userName;
	public DeleteMemory(String x){
		super("Delete Memory");
		userName=x;
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
		options.addItem("Select Memory");
		System.out.println(userName);
		String sql="select * from memory where userId='"+userName+"'";
		try{
			DataAccess da=new DataAccess();
			ResultSet rs=da.getData(sql);
			while(rs.next()){
				String x=rs.getString("name");
				options.addItem(x);
			}		
		}
		catch(Exception ex){
			System.out.println("Exception in home");
		}
	}
	
	public void deleteData(){
		DataAccess da=new DataAccess();
		String sql1="select id from user where userName='"+userName+"'";
		System.out.println(userName);
		String usId1="0";
		try{
			ResultSet rs=da.getData(sql1);
			while(rs.next()){
				usId1=rs.getString("id");
			}		
		}
		catch(Exception ex){
			System.out.println("Exception in home");
		}
		int usId=Integer.parseInt(usId1);
		String sql="delete from memory where name='"+getOption+"'"+"and userId='"+usId+"'";
		try{	
			if(da.updateDB(sql)>0){
				JOptionPane.showMessageDialog(this,"Memory Deleted");
			}
			else{
				JOptionPane.showMessageDialog(this,"Error!");
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