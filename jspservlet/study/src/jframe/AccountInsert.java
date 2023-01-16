package jframe;

import java.awt.EventQueue;
import java.awt.event.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.event.*;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;

import java.awt.*;
import javax.swing.border.*;
import javax.swing.text.*;

import DAO_VO.AccountDAO;
import DAO_VO.AccountVO;
import DAO_VO.PasswordDAO;
import DAO_VO.PasswordVO;

public class AccountInsert {

	private JFrame frame;
	private JTextField nametxt;
	private JFormattedTextField anotxt;
	private JTextField passAtxt;
	private JTextField datetxt;
	private JFormattedTextField balancetxt;
	private JButton insertbtn;
	private JFormattedTextField passtxt1;
	private JFormattedTextField passtxt2;
	private JFormattedTextField passtxt3;
	private JFormattedTextField passtxt4;
	private JLabel passlbl;
	private JLabel vlabel1;
	JList<String> list;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public AccountInsert() {
		initialize();
		
	}
	
	private void initialize() {
		
		
		JRadioButton typebtn2;
		JRadioButton typebtn1;
		AccountDAO adao = new AccountDAO();
		PasswordDAO psdao = new PasswordDAO();

		
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("±¼¸²", Font.BOLD, 15));
		frame.setBounds(100, 100, 651, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		ButtonGroup bg = new ButtonGroup();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(12, 10, 623, 426);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel title = new JLabel("\uC785\uAE08");
		title.setBounds(12, 5, 109, 33);
		title.setFont(new Font("³ª´®¸íÁ¶", Font.BOLD, 28));
		panel_1.add(title);
		
		JLabel namelb = new JLabel("\uC774\uB984");
		namelb.setBounds(22, 48, 31, 18);
		namelb.setFont(new Font("±¼¸²", Font.BOLD, 15));
		panel_1.add(namelb);
		
		nametxt = new JTextField();
		nametxt.setBounds(125, 47, 116, 21);
		nametxt.setColumns(10);
		panel_1.add(nametxt);
		
		JLabel typelb = new JLabel("\uD1B5\uC7A5\uD0C0\uC785");
		typelb.setBounds(12, 76, 62, 18);
		typelb.setFont(new Font("±¼¸²", Font.BOLD, 15));
		panel_1.add(typelb);
		
		JLabel Ano = new JLabel("\uACC4\uC88C\uBC88\uD638");
		Ano.setBounds(12, 111, 62, 18);
		Ano.setFont(new Font("±¼¸²", Font.BOLD, 15));
		panel_1.add(Ano);
		String newCode=adao.getMaxNo();
		anotxt = new JFormattedTextField(new NumberFormatter());
		anotxt.setBounds(125, 110, 116, 21);
		anotxt.setText(newCode);
		anotxt.setForeground(Color.DARK_GRAY);
		anotxt.setEnabled(false);
		anotxt.setColumns(10);
		panel_1.add(anotxt);
		
		typebtn2 = new JRadioButton("\uC790\uC728\uC785\uCD9C\uAE08");
		typebtn2.setBounds(222, 74, 101, 23);
		bg.add(typebtn2);
		
		typebtn1 = new JRadioButton("\uAC1C\uBBF8\uC801\uAE08");
		typebtn1.setBounds(125, 74, 93, 23);
		bg.add(typebtn1);
		panel_1.add(typebtn1);
		panel_1.add(typebtn2);
		
		JLabel passQ = new JLabel("\uBE44\uBC00\uBC88\uD638\uC9C8\uBB38");
		passQ.setBounds(12, 180, 93, 18);
		passQ.setFont(new Font("±¼¸²", Font.BOLD, 15));
		panel_1.add(passQ);
		
		JLabel passA = new JLabel("\uB2F5");
		passA.setBounds(53, 219, 15, 18);
		passA.setFont(new Font("±¼¸²", Font.BOLD, 15));
		panel_1.add(passA);
		
		passAtxt = new JTextField();
		passAtxt.setBounds(125, 218, 116, 21);
		passAtxt.setColumns(10);
		panel_1.add(passAtxt);
		
		JLabel date = new JLabel("\uAC1C\uC124\uC77C");
		date.setBounds(27, 247, 46, 18);
		date.setFont(new Font("±¼¸²", Font.BOLD, 15));
		panel_1.add(date);
		
		Date now = new Date();
		String nd = sdf.format(now);
		datetxt = new JTextField();
		datetxt.setBounds(125, 246, 116, 21);
		datetxt.setText(nd);
		datetxt.setForeground(Color.DARK_GRAY);
		datetxt.setEnabled(false);
		datetxt.setColumns(10);
		panel_1.add(datetxt);
		
		JLabel balancelb = new JLabel("\uC785\uAE08\uC561");
		balancelb.setBounds(27, 275, 46, 18);
		balancelb.setFont(new Font("±¼¸²", Font.BOLD, 15));
		panel_1.add(balancelb);
		
		balancetxt = new JFormattedTextField();
		balancetxt.setBounds(125, 274, 116, 21);
		balancetxt.setForeground(Color.DARK_GRAY);
		balancetxt.setColumns(10);
		panel_1.add(balancetxt);
		
		JLabel balancelb2 = new JLabel("\uC6D0");
		balancelb2.setBounds(253, 275, 15, 18);
		balancelb2.setFont(new Font("±¼¸²", Font.BOLD, 15));
		panel_1.add(balancelb2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(125, 155, 116, 43);
		panel_1.add(scrollPane);
		
		list = new JList();
		list.setVisibleRowCount(1);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"\uC704\uC778", "\uC9C0\uC5ED", "\uCD08\uB4F1\uD559\uAD50"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list);

		NumberFormatter nf= new NumberFormatter();
		nf.setValueClass(Integer.class);
		nf.setMinimum(0);
		nf.setMaximum(9);
		passtxt1 = new JFormattedTextField(nf);
		passtxt1.setText("");
		passtxt1.setColumns(1);
		passtxt1.setBounds(125, 305, 23, 21);
		panel_1.add(passtxt1);
		passtxt2 = new JFormattedTextField(nf);
		passtxt2.setText("");
		passtxt2.setColumns(1);
		passtxt2.setBounds(152, 305, 23, 21);
		panel_1.add(passtxt2);
		
		passtxt3 = new JFormattedTextField(nf);
		passtxt3.setText("");
		passtxt3.setColumns(1);
		passtxt3.setBounds(187, 305, 23, 21);
		panel_1.add(passtxt3);
		
		passtxt4 = new JFormattedTextField(nf);
		passtxt4.setText("");
		passtxt4.setColumns(1);
		passtxt4.setBounds(218, 305, 23, 21);
		panel_1.add(passtxt4);
		
		passlbl = new JLabel("\uBE44\uBC00\uBC88\uD638");
		passlbl.setFont(new Font("±¼¸²", Font.BOLD, 15));
		passlbl.setBounds(27, 308, 62, 18);
		panel_1.add(passlbl);
		
		vlabel1 = new JLabel("\uC22B\uC790\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694");
		vlabel1.setBounds(280, 277, 116, 15);
		vlabel1.setText("¼ýÀÚ¸¸ ÀÔ·ÂÇÏ¼¼¿ä");
		panel_1.add(vlabel1);
		insertbtn = new JButton("\uB4F1\uB85D");
		insertbtn.setFont(new Font("±¼¸²", Font.BOLD, 20));
		insertbtn.setBounds(8, 336, 190, 80);
		panel_1.add(insertbtn);
		
		JLabel vlabel2 = new JLabel("\uC22B\uC790\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694");
		vlabel2.setBounds(280, 308, 116, 15);
		panel_1.add(vlabel2);
		frame.setVisible(true);
		
		insertbtn.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				AccountVO avo= new AccountVO();
				PasswordVO psvo = new PasswordVO();
				Object obj = e.getSource();
				String p1 = "";
				String p2 = "";
				String p3 = "";
				String p4 = "";
				if((JButton)obj == insertbtn){ //µî·Ï¹öÆ°À» ´©¸¥°æ¿ì
				String ATN="";
				
				if(typebtn1.isSelected()==true) {
					ATN="A01";
				}else {
					ATN="A02";
				}
				
				avo.setAccountNo(anotxt.getText());
				avo.setAccountName(nametxt.getText());
				avo.setBalance(Integer.valueOf(balancetxt.getText()));
				avo.setAccountTypeNo(ATN);
				avo.setAccountDate(datetxt.getText());
				adao.insert(avo);
				psvo.setAccountNo(anotxt.getText());
				p1=passtxt1.getText();
				p2=passtxt2.getText();
				p3=passtxt3.getText();
				p4=passtxt4.getText();
				String psw = p1+p2+p3+p4;
				psvo.setPassword(psw);
				int p = list.getSelectedIndex()+1;
				String psa ="Q"+0+p;
				psvo.setPasswordTypeNo(psa);
				psvo.setPasswordAnswer(passAtxt.getText());
				psdao.insert(psvo);
				System.out.println(psa);
				System.exit(0);
				}
				
			}
		});
	}
}




