package jframe;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DAO_VO.AccountDAO;
import DAO_VO.AccountVO;
import DAO_VO.TradeVO;

import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Withdraw {

	private JFrame frame;
	private JTextField textAccountNo;
	private JTextField textWithdraw;
	private JPasswordField passwordField;
	private JTable table;
	private JPanel contentPane;
	boolean check = false;

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

	public Withdraw() {
		
		AccountDAO accountDAO = new AccountDAO();
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JPanel pnlBalanceInfo = new JPanel();
		pnlBalanceInfo.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(pnlBalanceInfo);
		pnlBalanceInfo.setLayout(null);
		pnlBalanceInfo.setVisible(false);

		JLabel lblWithdrawTitle = new JLabel("출 금 계 좌 정 보");
		lblWithdrawTitle.setFont(new Font("굴림", Font.PLAIN, 20));
		lblWithdrawTitle.setBounds(139, 10, 156, 40);
		panel.add(lblWithdrawTitle);

		JLabel lblAccountNo = new JLabel("계좌번호");
		lblAccountNo.setFont(new Font("굴림", Font.PLAIN, 15));
		lblAccountNo.setBounds(12, 65, 100, 20);
		panel.add(lblAccountNo);

		textAccountNo = new JTextField();
		textAccountNo.setFont(new Font("굴림", Font.PLAIN, 15));
		textAccountNo.setBounds(117, 65, 200, 20);
		panel.add(textAccountNo);
		textAccountNo.setColumns(10);

		JLabel lblAccountPass = new JLabel("비밀번호");
		lblAccountPass.setFont(new Font("굴림", Font.PLAIN, 15));
		lblAccountPass.setBounds(12, 110, 100, 20);
		panel.add(lblAccountPass);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("굴림", Font.PLAIN, 15));
		passwordField.setBounds(117, 110, 100, 20);
		panel.add(passwordField);

		JLabel lblInfo = new JLabel("(\uC22B\uC790 4\uC790\uB9AC)");
		lblInfo.setFont(new Font("굴림", Font.PLAIN, 15));
		lblInfo.setBounds(222, 110, 90, 20);
		panel.add(lblInfo);

		JButton btnBalance = new JButton("계좌확인");

		btnBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DecimalFormat dfWon = new DecimalFormat("#,###원");
				AccountDAO accountDAO = new AccountDAO();
				
				AccountVO accountVO = accountDAO.read(textAccountNo.getText());
				if(textAccountNo.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "계좌번호를 입력해주세요.");
				}
				if(passwordField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
				} else if(passwordField.getText().equals(accountVO.getPassword())) {
					accountVO = accountDAO.read(textAccountNo.getText());
					JOptionPane.showMessageDialog(null, "확인되었습니다.");
					check = true;
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.\n비밀번호를 확인해주세요.");
				}
			}
		});
		btnBalance.setFont(new Font("굴림", Font.PLAIN, 15));
		btnBalance.setBounds(322, 108, 100, 24);
		panel.add(btnBalance);
		
		JLabel lblWithdraw = new JLabel("출금 금액");
		lblWithdraw.setFont(new Font("굴림", Font.PLAIN, 15));
		lblWithdraw.setBounds(12, 155, 100, 20);
		panel.add(lblWithdraw);
		
		textWithdraw = new JTextField();
		textWithdraw.setFont(new Font("굴림", Font.PLAIN, 15));
		textWithdraw.setColumns(10);
		textWithdraw.setBounds(117, 155, 200, 20);
		panel.add(textWithdraw);
		
		JLabel lblInfo2 = new JLabel("\uC6D0");
		lblInfo2.setFont(new Font("굴림", Font.PLAIN, 15));
		lblInfo2.setBounds(322, 155, 30, 20);
		panel.add(lblInfo2);

		JButton btnWithdraw = new JButton("출금");

		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DecimalFormat dfWon = new DecimalFormat("#,###원");
				AccountVO accountVO;
				TradeVO tradeVO;
				
				accountVO = accountDAO.read(textAccountNo.getText());
				
				if(textAccountNo.getText().equals("") || check == false) {
					JOptionPane.showMessageDialog(null, "계좌번호를 확인해주세요.");
				} else if (textWithdraw.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "출금 금액을 확인해주세요.");
				} else if(!passwordField.getText().equals(accountVO.getPassword())) {
					JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.\n비밀번호를 확인해주세요.");
				} else {
					int select = JOptionPane.showConfirmDialog(null, "출금하시겠습니까?", "출금 확인", JOptionPane.YES_NO_OPTION);
					
					if(select == JOptionPane.OK_OPTION) {
						String accountNo = textAccountNo.getText();
						int tradeBalance = Integer.valueOf(textWithdraw.getText());
						
						tradeVO = new TradeVO();
						tradeVO.setAccountNo(accountNo);
						tradeVO.setTradeTypeNo("T02");
						tradeVO.setTradeBalance(tradeBalance);
						
						accountDAO.withdraw(tradeVO);
						accountDAO.withdrawupdate(accountNo, tradeBalance);
						
						JOptionPane.showMessageDialog(null, "출금이 완료되었습니다.");
						System.exit(0);
					} else if(select == JOptionPane.NO_OPTION) {
						JOptionPane.showMessageDialog(null, "출금이 취소되었습니다.");
					}
				}
			}
		});
		
		btnWithdraw.setFont(new Font("굴림", Font.PLAIN, 15));
		btnWithdraw.setBounds(97, 210, 100, 40);
		panel.add(btnWithdraw);
		
		JButton btnCancel = new JButton("\uCDE8\uC18C");

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int select = JOptionPane.showConfirmDialog(null, "출금을 취소하시겠습니까?", "출금 취소", JOptionPane.YES_NO_OPTION);
				if(select == JOptionPane.OK_OPTION) {
					JOptionPane.showMessageDialog(null, "출금이 취소되었습니다.");
					frame.setVisible(false);
					Main main = new Main();
				}
			}
		});
		btnCancel.setFont(new Font("굴림", Font.PLAIN, 15));
		btnCancel.setBounds(237, 210, 100, 40);
		panel.add(btnCancel);
	}
}