import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class EditMemory extends JFrame implements ActionListener{
	
	private JTextField oMemoryName,nMemoryName, startDate, totalDays,placeName,districtName,travelCost,hotelCost,foodCost;
	private JButton save,back;
	private JLabel oMemory,nMemory,sDate,tDays,pName,dName,tCost,hCost,fCost,dArea;
	private JFrame parent;
	private JTextArea detailsArea;
	private String userName;
	
	public EditMemory(String x){
		super("Edit Memory");
		userName=x;
		oMemoryName=new JTextField(20);
		nMemoryName=new JTextField(20);
		startDate=new JTextField(20);
		totalDays=new JTextField(20);
		placeName=new JTextField(20);
		
		
		
		oMemory=new JLabel("Old Memory Name: ");
		nMemory=new JLabel("New Memory Name: ");
		sDate=new JLabel("Start Date: ");
		tDays=new JLabel("Traveled days: ");
		pName=new JLabel("Place Name: ");
		
		save=new JButton("Save");
		back=new JButton("back");
		
		add(oMemory); add(oMemoryName);
		add(nMemory); add(nMemoryName);
		add(sDate); add(startDate);
		add(tDays); add(totalDays);
		//add(pName); //add(placeName);
		
		add(save);
		add(back);
		
		save.addActionListener(this);
		back.addActionListener(this);
		
		oMemory.setFont(new Font("Courier", Font.BOLD, 20));
		sDate.setFont(new Font("Courier", Font.BOLD, 20));
		tDays.setFont(new Font("Courier", Font.BOLD, 20));
		pName.setFont(new Font("Courier", Font.BOLD, 20));
		nMemory.setFont(new Font("Courier", Font.BOLD, 20));
		//userNameLabel.setFont(new Font("Courier", Font.BOLD, 20));
		getContentPane().setBackground(Color.decode("#001B3A"));
		oMemory.setForeground(Color.white);
		nMemory.setForeground(Color.white);
		sDate.setForeground(Color.white);
		tDays.setForeground(Color.white);
		pName.setForeground(Color.white);
		
		
		
		oMemory.setBounds(100,100,300,30);
		oMemoryName.setBounds(300,100,250,30);
		nMemory.setBounds(100,150,300,30);
		nMemoryName.setBounds(300,150,250,30);
		sDate.setBounds(100,200,300,30);
		startDate.setBounds(300,200,250,30);
		tDays.setBounds(100,250,300,30);
		totalDays.setBounds(300,250,250,30);
		pName.setBounds(100,300,300,30);
		placeName.setBounds(300,300,250,30);
		
		
		save.setBounds(300,350,100,30);
		back.setBounds(170,350,100,30);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setSize(650,450);
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
	
	public void updateData(){
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
		String sql="";
		sql="update memory set name='"+nMemoryName.getText()+"',date='"+startDate.getText()+"',days='"+totalDays.getText()+"' where name='"+oMemoryName.getText()+"'"+"and userId='"+usId+"'";
		if(da.updateDB(sql)>0){
			JOptionPane.showMessageDialog(this,"Update complete");
		}
		else{
			JOptionPane.showMessageDialog(this,"Failed");
		}
	}
	
	public void actionPerformed(ActionEvent e){
		String go=e.getActionCommand();
		if(go.equals("back")){
			parent.setVisible(true);
			parent.setLocationRelativeTo(null);
			this.setVisible(false);
		}
		else if(go.equals("Save")){
			updateData();
			System.out.println("working");
		}
	}
}