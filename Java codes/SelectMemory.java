import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class SelectMemory extends JFrame implements ActionListener{
	private JComboBox<String> options;
	private JButton next;
	private ShowMemory sm;
	private String getOption="";
	private JFrame parent;
	private String userName;
	public SelectMemory(String x){
		super("Select Memory");
		sm=new ShowMemory(x);
		userName=x;
		options=new JComboBox<String>();
		next=new JButton("Next");
		add(options);
		add(next);
		options.addActionListener(this);
		next.addActionListener(this);
		options.setBounds(50,50,200,30);
		next.setBounds(100,100,100,30);
		loadData();
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setSize(350,300);
		getContentPane().setBackground(Color.decode("#001B3A"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setParent(JFrame x){
		parent=x;
	}
	public void loadData(){
		DataAccess da=new DataAccess();
		String sql1="select id from user where userName='"+userName+"'";
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
		options.addItem("Select Memory Name");
		String sql="select name from memory where userId='"+usId+"'";
		try{
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
	
	public void actionPerformed(ActionEvent e){
		String go=e.getActionCommand();
		if(go.equals("Next")){
			sm.setVisible(true);
			sm.setLocationRelativeTo(null);
			sm.setParent(parent);
			this.setVisible(false);
		}
		else{
			sm.mn=options.getSelectedItem().toString();
			
		}
	}
}