import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class AddMemory extends JFrame implements ActionListener{
	
	private JTextField memoryName, startDate, totalDays,placeName,districtName,travelCost,hotelCost,foodCost;
	private JButton submit,back;
	private JLabel memory,sDate,tDays,pName,dName,tCost,hCost,fCost,dArea;
	private JFrame parent;
	private JTextArea detailsArea;
	private String userName;
	
	public AddMemory(String x){
		super("Add Memory");
		userName=x;
		memoryName=new JTextField(20);
		startDate=new JTextField(20);
		totalDays=new JTextField(20);
		placeName=new JTextField(20);
		districtName=new JTextField(20);
		travelCost=new JTextField(20);
		hotelCost=new JTextField(20);
		foodCost=new JTextField(20);
		detailsArea=new JTextArea();
		
		
		memory=new JLabel("Memory Name: ");
		sDate=new JLabel("Start Date: ");
		tDays=new JLabel("Traveled days: ");
		pName=new JLabel("Place Name: ");
		dName=new JLabel("District Name: ");
		tCost=new JLabel("Travel Cost: ");
		hCost=new JLabel("Hotel cost: ");
		fCost=new JLabel("Food Cost: ");
		dArea=new JLabel("Details: ");
		submit=new JButton("Submit");
		back=new JButton("back");
		
		add(memory); add(memoryName);
		add(sDate); add(startDate);
		add(tDays); add(totalDays);
		add(pName); add(placeName);
		add(dName); add(districtName);
		add(tCost); add(travelCost);
		add(hCost); add(hotelCost); add(fCost); add(foodCost);
		add(submit); add(detailsArea);
		add(dArea); add(back);
		
		submit.addActionListener(this);
		back.addActionListener(this);
		
		memory.setFont(new Font("Courier", Font.BOLD, 20));
		sDate.setFont(new Font("Courier", Font.BOLD, 20));
		tDays.setFont(new Font("Courier", Font.BOLD, 20));
		pName.setFont(new Font("Courier", Font.BOLD, 20));
		dName.setFont(new Font("Courier", Font.BOLD, 20));
		tCost.setFont(new Font("Courier", Font.BOLD, 20));
		hCost.setFont(new Font("Courier", Font.BOLD, 20));
		fCost.setFont(new Font("Courier", Font.BOLD, 20));
		dArea.setFont(new Font("Courier", Font.BOLD, 20));
		//userNameLabel.setFont(new Font("Courier", Font.BOLD, 20));
		getContentPane().setBackground(Color.decode("#001B3A"));
		memory.setForeground(Color.white);
		sDate.setForeground(Color.white);
		tDays.setForeground(Color.white);
		pName.setForeground(Color.white);
		tCost.setForeground(Color.white);
		hCost.setForeground(Color.white);
		fCost.setForeground(Color.white);
		dArea.setForeground(Color.white);
		dName.setForeground(Color.white);
		dArea.setForeground(Color.white);
		
		dArea.setBounds(650,50,300,30);
		detailsArea.setBounds(650,100,500,730);
		memory.setBounds(100,100,300,30);
		memoryName.setBounds(300,100,250,30);
		sDate.setBounds(100,200,300,30);
		startDate.setBounds(300,200,250,30);
		tDays.setBounds(100,300,300,30);
		totalDays.setBounds(300,300,250,30);
		pName.setBounds(100,400,300,30);
		placeName.setBounds(300,400,250,30);
		dName.setBounds(100,500,250,30);
		districtName.setBounds(300,500,250,30);
		tCost.setBounds(100,600,250,30);
		travelCost.setBounds(300,600,250,30);
		hCost.setBounds(100,700,250,30);
		hotelCost.setBounds(300,700,250,30);
		fCost.setBounds(100,800,250,30);
		foodCost.setBounds(300,800,250,30);
		
		submit.setBounds(770,900,100,30);
		back.setBounds(900,900,100,30);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setSize(1200,1000);
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
	public void addData(){
		DataAccess da=new DataAccess();
		String sql="";
		sql="insert into cost(travelCost,hotelCost,foodCost) values('"+travelCost.getText()+"','"+hotelCost.getText()+"','"+foodCost.getText()+"')";
		da.updateDB(sql);
		sql="select id from cost where travelCost='"+travelCost.getText()+"'"+"and hotelCost='"+hotelCost.getText()+"'"+"and foodCost='"+foodCost.getText()+"'";
		String costId1="1";
		try{
			ResultSet rs=da.getData(sql);
			while(rs.next()){
				costId1=rs.getString("id");
			}		
		}
		catch(Exception ex){
			System.out.println("Exception in home");
		}
		int costId=Integer.parseInt(costId1);
		sql="select id from place where placeName='"+placeName.getText()+"'";
		String placeId1="1";
		try{
			ResultSet rs=da.getData(sql);
			while(rs.next()){
				placeId1=rs.getString("id");
			}		
		}
		catch(Exception ex){
			System.out.println("Exception in home");
		}
		int placeId=Integer.parseInt(placeId1);
		sql="select id from user where userName='"+userName+"'";
		String usId1="1";
		try{
			ResultSet rs=da.getData(sql);
			while(rs.next()){
				usId1=rs.getString("id");
			}		
		}
		catch(Exception ex){
			System.out.println("Exception in home");
		}
		int usId=Integer.parseInt(usId1);
		String s=detailsArea.getText();
		sql="insert into memory(name,date,days,placeId,costId,userId,details) values('"+memoryName.getText()+"','"+startDate.getText()+"','"+totalDays.getText()+"','"+placeId+"','"+costId+"','"+usId+"','"+s+"')";	
		if(da.updateDB(sql)>0){
			JOptionPane.showMessageDialog(this,"Added");
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
		else if(go.equals("Submit")){
			addData();
		}
	}
}