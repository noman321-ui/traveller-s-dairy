import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Welcome extends JFrame implements ActionListener{
	private JButton[] jb=new JButton[10];
	private JLabel[] jl=new JLabel[10];
	private ImageIcon image;
	private Login logPage;
	private SignUp signUpPage;
	public Welcome(){
		super("Welcome");
		jb[0]=new JButton("Sign In");
		jb[0].setBackground(Color.ORANGE);
		jb[1]=new JButton("Sign Up");
		jb[1].setBackground(Color.ORANGE);
		jl[0]=new JLabel("Welcome to Travellers Diary");
		jl[0].setForeground(Color.white);
		image=new ImageIcon("img.jpg");
		jl[1]=new JLabel("",image,JLabel.CENTER);
		logPage=new Login();
		signUpPage=new SignUp();
		add(jb[0]);
		add(jb[1]);
		add(jl[0]);
		add(jl[1]);
		jb[0].setBounds(425,600,100,40);
		jb[0].addActionListener(this);
		jb[1].setBounds(575,600,100,40);
		jb[1].addActionListener(this);
		jl[0].setBounds(100,200,1100,200);
		jl[0].setFont(new Font("Courier", Font.BOLD, 60));
		jl[1].setBounds(0,0,1200,800);
		setLayout(null);
		setResizable(false);
		setSize(1200,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e){
		String go=e.getActionCommand();
		if(go.equals("Sign In")){
			logPage.setVisible(true);
			logPage.setParent(this);
			logPage.setLocationRelativeTo(null);
			this.setVisible(false);
		}
		else if(go.equals("Sign Up")){
			signUpPage.setVisible(true);
			signUpPage.setParent(this);
			signUpPage.setLocationRelativeTo(null);
			this.setVisible(false);
		}
		else{
			
		}
	}
}