import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class EmergencyNumbers extends JFrame implements ActionListener{
	private JButton save,back;
	private JLabel fireLabel,policeLabel,ambulanceLabel,districtLabel,emergencyLabel;
	private JTextField fire,police,ambulance;
	private JFrame parent;
	//private JComboBox<String> options;
	public EmergencyNumbers(){
		super("Emergency Numbers");
		fireLabel=new JLabel("Fire Service:");
		policeLabel=new JLabel("Police: ");
		ambulanceLabel=new JLabel("Ambulance: ");
		districtLabel=new JLabel("District: ");
		emergencyLabel=new JLabel("Emergency Numbers");
		//options=new JComboBox<String>();
		
		fire=new JTextField(20);
		police=new JTextField(20);
		ambulance=new JTextField(20);
		
		save=new JButton("Save");
		back=new JButton("Back");
		
		//add(options);
		add(emergencyLabel);
		add(fireLabel);
		add(fire);
		add(policeLabel);
		add(police);
		add(ambulanceLabel);
		add(ambulance);
		//add(districtLabel);
		add(save);
		add(back);
		
		//options.addActionListener(this);
		save.addActionListener(this);
		back.addActionListener(this);
		emergencyLabel.setFont(new Font("Courier", Font.BOLD, 20));
		fireLabel.setFont(new Font("Courier", Font.BOLD, 20));
		policeLabel.setFont(new Font("Courier", Font.BOLD, 20));
		ambulanceLabel.setFont(new Font("Courier", Font.BOLD, 20));
		districtLabel.setFont(new Font("Courier", Font.BOLD, 20));

		emergencyLabel.setBounds(250,100,300,30);
		fireLabel.setBounds(100,200,200,30);
		fire.setBounds(350,200,300,30);
		policeLabel.setBounds(100,300,100,30);
		police.setBounds(350,300,300,30);
		ambulanceLabel.setBounds(100,400,300,30);
		ambulance.setBounds(350,400,300,30);
		districtLabel.setBounds(100,500,200,30);
		//options.setBounds(350,500,200,30);
		save.setBounds(350,500,100,30);
		back.setBounds(475,500,100,30);
		
		
		fireLabel.setForeground(Color.WHITE);
		policeLabel.setForeground(Color.WHITE);
		ambulanceLabel.setForeground(Color.WHITE);
		districtLabel.setForeground(Color.WHITE);
		emergencyLabel.setForeground(Color.WHITE);

		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setSize(750,700);
		getContentPane().setBackground(Color.decode("#001B3A"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void setParent(JFrame x){
		parent=x;
	}
	
	/*public void loadData(){
		options.addItem("Select District");
		String sql="select districtName from district";
		try{
			DataAccess da=new DataAccess();
			ResultSet rs=da.getData(sql);
			while(rs.next()){
				String x=rs.getString("districtName");
				options.addItem(x);
			}		
		}
		catch(Exception ex){
			System.out.println("Exception in home");
		}
	}*/
	

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
				sql="insert into number(police,fireService,ambulance) values('"+police.getText()+"','"+fire.getText()+"','"+ambulance.getText()+"')";
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