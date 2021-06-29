import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class ShowMemory extends JFrame implements ActionListener{
	
	private JLabel memoryName, startDate, totalDays,placeName,districtName,travelCost,hotelCost,foodCost;
	private JButton back,see;
	private JLabel memory,sDate,tDays,pName,dName,tCost,hCost,fCost,dArea;
	private JFrame parent;
	private JTextArea detailsArea;
	private String userName;
	public  String mn;
	
	public ShowMemory(){
		
	}
	public ShowMemory(String x){
		super("Show Memory");
		userName=x;
		memoryName=new JLabel();
		memoryName.setForeground(Color.white);
		memoryName.setFont(new Font("Courier", Font.BOLD, 20));
		startDate=new JLabel();
		startDate.setForeground(Color.white);
		startDate.setFont(new Font("Courier", Font.BOLD, 20));
		totalDays=new JLabel();
		totalDays.setForeground(Color.white);
		totalDays.setFont(new Font("Courier", Font.BOLD, 20));
		placeName=new JLabel();
		placeName.setForeground(Color.white);
		placeName.setFont(new Font("Courier", Font.BOLD, 20));
		districtName=new JLabel();
		districtName.setForeground(Color.white);
		districtName.setFont(new Font("Courier", Font.BOLD, 20));
		travelCost=new JLabel();
		travelCost.setForeground(Color.white);
		travelCost.setFont(new Font("Courier", Font.BOLD, 20));
		hotelCost=new JLabel();
		hotelCost.setForeground(Color.white);
		hotelCost.setFont(new Font("Courier", Font.BOLD, 20));
		foodCost=new JLabel();
		foodCost.setForeground(Color.white);
		foodCost.setFont(new Font("Courier", Font.BOLD, 20));
		detailsArea=new JTextArea();
		detailsArea.setFont(new Font("Courier", Font.BOLD, 20));
		
		
		
		memory=new JLabel("Memory Name: ");
		sDate=new JLabel("Start Date: ");
		tDays=new JLabel("Traveled days: ");
		pName=new JLabel("Place Name: ");
		dName=new JLabel("District Name: ");
		tCost=new JLabel("Travel Cost: ");
		hCost=new JLabel("Hotel cost: ");
		fCost=new JLabel("Food Cost: ");
		dArea=new JLabel("Details Area");
		back=new JButton("back");
		see=new JButton("See");
		add(dArea); add(detailsArea);
		add(memory); add(memoryName);
		add(sDate); add(startDate);
		add(tDays); add(totalDays);
		add(pName); add(placeName);
		add(dName); add(districtName);
		add(tCost); add(travelCost);
		add(hCost); add(hotelCost); 
		add(fCost); add(foodCost);
		add(back); add(see);
		
		back.addActionListener(this);
		see.addActionListener(this);
		
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
		dName.setForeground(Color.white);
		tCost.setForeground(Color.white);
		hCost.setForeground(Color.white);
		fCost.setForeground(Color.white);
		dArea.setForeground(Color.white);
		
		getContentPane().setBackground(Color.decode("#001B3A"));
		memory.setBounds(100,100,250,30);
		memoryName.setBounds(350,100,250,30);
		dArea.setBounds(700,50,500,30); 
		
		detailsArea.setBounds(700,100,450,730);
		
		sDate.setBounds(100,200,250,30);
		startDate.setBounds(350,200,250,30);
		
		tDays.setBounds(100,300,250,30);
		totalDays.setBounds(350,300,250,30);
		pName.setBounds(100,400,250,30);
		placeName.setBounds(350,400,250,30);
		dName.setBounds(100,500,250,30);
		districtName.setBounds(350,500,250,30);
		
		tCost.setBounds(100,600,250,30);
		travelCost.setBounds(350,600,250,30);
		
		hCost.setBounds(100,700,250,30);
		hotelCost.setBounds(350,700,250,30);
		
		fCost.setBounds(100,800,250,30);
		foodCost.setBounds(350,800,250,30);
	
		back.setBounds(800,850,100,30);
		see.setBounds(650,850,100,30);
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
	
	public void loadData(){
		System.out.println(mn);
		String sql="select  * from memory m,cost c,place p,district d where name='"+mn+"'"+" and m.costId=c.id "+" and m.placeId=p.id "+" and p.districtId=d.id";
		try{
			DataAccess da=new DataAccess();
			ResultSet rs=da.getData(sql);
			String data="";
			while(rs.next()){
				data=data+rs.getString("name");
				memoryName.setText(data);
				data=rs.getString("date");
				startDate.setText(data);
				data=rs.getString("days");
				totalDays.setText(data);
				data=rs.getString("districtName");
				districtName.setText(data);
				data=rs.getString("placeName");
				placeName.setText(data);
				data=rs.getString("travelCost");
				travelCost.setText(data);
				data=rs.getString("hotelCost");
				hotelCost.setText(data);
				data=rs.getString("foodCost");
				foodCost.setText(data);
				data=rs.getString("details");
				detailsArea.setText(data);
				
			}
		}
		catch(Exception ex){
			System.out.println("Exception in home");
		}
	}
	
	public void setMemoryName(String x){
		mn=x;
	}
	
	public void actionPerformed(ActionEvent e){
		String go=e.getActionCommand();
		if(go.equals("back")){
			parent.setVisible(true);
			parent.setLocationRelativeTo(null);
			this.setVisible(false);
		}
		else if(go.equals("See")){
			loadData();
		}
	}
}