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

import DAO_VO.AccountDAO;
import DAO_VO.AccountVO;
import DAO_VO.TradeVO;

import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Deposit {

	JFrame frame;
	private JTextField textAccountNo;
	private JTextField textDeposit;
	private JPasswordField passwordField;
	private JTable table;
	boolean check = false;

	/**
	 * Launch the application.
	 */
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
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public Deposit() {
		
		AccountDAO accountDAO = new AccountDAO();
		boolean accountCheck = false;
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		// 입금계좌정보 틀
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		// 잔액확인 틀
		JPanel pnlBalanceInfo = new JPanel();
		pnlBalanceInfo.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(pnlBalanceInfo);
		pnlBalanceInfo.setLayout(null);
		pnlBalanceInfo.setVisible(false);
		
		// 입금계좌정보
		JLabel lblDepositTitle = new JLabel("\uC785 \uAE08 \uACC4 \uC88C \uC815 \uBCF4");
		lblDepositTitle.setFont(new Font("굴림", Font.PLAIN, 20));
		lblDepositTitle.setBounds(139, 10, 156, 40);
		panel.add(lblDepositTitle);
		
		// 입금 계좌번호
		JLabel lblAccountNo = new JLabel("\uC785\uAE08 \uACC4\uC88C\uBC88\uD638");
		lblAccountNo.setFont(new Font("굴림", Font.PLAIN, 15));
		lblAccountNo.setBounds(12, 65, 100, 20);
		panel.add(lblAccountNo);
		
		// 계좌번호 입력
		textAccountNo = new JTextField();
		textAccountNo.setFont(new Font("굴림", Font.PLAIN, 15));
		textAccountNo.setBounds(117, 65, 200, 20);
		panel.add(textAccountNo);
		textAccountNo.setColumns(10);
		
//		boolean accountCheck = false;
//		System.out.println(accountCheck);
		
		// 계좌확인 버튼
		JButton btnAccountNo = new JButton("\uACC4\uC88C\uD655\uC778");
		// 계좌확인 버튼 클릭 시
		btnAccountNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountDAO accountDAO = new AccountDAO();
				AccountVO accountVO;
				boolean accountCheck = false;
				
				accountVO = accountDAO.read(textAccountNo.getText());
//				System.out.println(accountVO.toString());	// accountVO 확인
				if(textAccountNo.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "계좌번호를 입력해주세요.");
				} else if(textAccountNo.getText().equals(accountVO.getAccountNo())) {	// ** 이벤트처리 필요 확인 요망 **
					check = true;
					JOptionPane.showMessageDialog(null, "계좌번호가 확인되었습니다.");
//					accountCheck = true;
				} else {
					JOptionPane.showMessageDialog(null, "입력하신 계좌번호의 정보가 없습니다.\n 계좌번호를 확인해주세요.");
				}
			}
		});
		btnAccountNo.setFont(new Font("굴림", Font.PLAIN, 15));
		btnAccountNo.setBounds(322, 63, 100, 24);
		panel.add(btnAccountNo);
		
		// 계좌 비밀번호
		JLabel lblAccountPass = new JLabel("\uACC4\uC88C \uBE44\uBC00\uBC88\uD638");
		lblAccountPass.setFont(new Font("굴림", Font.PLAIN, 15));
		lblAccountPass.setBounds(12, 110, 100, 20);
		panel.add(lblAccountPass);
		
		// 비밀번호 입력
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("굴림", Font.PLAIN, 15));
		passwordField.setBounds(117, 110, 100, 20);
		panel.add(passwordField);
		
		// (숫자 4자리)
		JLabel lblInfo = new JLabel("(\uC22B\uC790 4\uC790\uB9AC)");
		lblInfo.setFont(new Font("굴림", Font.PLAIN, 15));
		lblInfo.setBounds(222, 110, 90, 20);
		panel.add(lblInfo);
		
		// 잔액확인 버튼
		JButton btnBalance = new JButton("\uC794\uC561\uD655\uC778");
		// 잔액확인 버튼 클릭 시
		btnBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DecimalFormat dfWon = new DecimalFormat("#,###원");
				AccountDAO accountDAO = new AccountDAO();
				AccountVO accountVO;
				String balance;
				
				accountVO = accountDAO.read(textAccountNo.getText());
//				System.out.println(accountVO.toString());	// accountVO 확인
				if(passwordField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
				} else if(passwordField.getText().equals(accountVO.getPassword())) {
//					System.out.println(accountVO);	// accountVO 확인
					// 잔액확인 거래내역 정보 출력
					String[] header = {"계좌번호", "거래유형", "거래금액", "거래일"};
					String[][] data = accountDAO.getTrade(textAccountNo.getText());
					
					table = new JTable(data, header);
					table.setAlignmentX(0);
					table.setPreferredScrollableViewportSize(new Dimension(434, 200));
					JScrollPane scrollPane = new JScrollPane(table);
					scrollPane.setBounds(0, 0, 434, 200);
					pnlBalanceInfo.add(scrollPane);
					
					accountVO = accountDAO.read(textAccountNo.getText());
					balance = dfWon.format(accountVO.getBalance());
//					System.out.println(balance);	// balance 확인
					// 현재잔액 : #,###원
					JLabel lblBalance = new JLabel("\uD604\uC7AC\uC794\uC561 : " + balance);
					lblBalance.setFont(new Font("굴림", Font.PLAIN, 15));
					lblBalance.setBounds(12, 220, 300, 20);
					pnlBalanceInfo.add(lblBalance);
					
					panel.setVisible(false);
					pnlBalanceInfo.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.\n비밀번호를 확인해주세요.");
				}
			}
		});
		btnBalance.setFont(new Font("굴림", Font.PLAIN, 15));
		btnBalance.setBounds(322, 108, 100, 24);
		panel.add(btnBalance);
		
		// 잔액확인 확인 버튼
		JButton btnBalanceChk = new JButton("\uD655\uC778");
		// 잔액확인 확인 버튼 클릭 시
		btnBalanceChk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				pnlBalanceInfo.setVisible(false);
			}
		});
		btnBalanceChk.setFont(new Font("굴림", Font.PLAIN, 15));
		btnBalanceChk.setBounds(323, 210, 100, 40);
		pnlBalanceInfo.add(btnBalanceChk);
		
		// 입금 금액
		JLabel lblDeposit = new JLabel("\uC785\uAE08 \uAE08\uC561");
		lblDeposit.setFont(new Font("굴림", Font.PLAIN, 15));
		lblDeposit.setBounds(12, 155, 100, 20);
		panel.add(lblDeposit);
		
		// 입금 금액 입력
		textDeposit = new JTextField();
		textDeposit.setFont(new Font("굴림", Font.PLAIN, 15));
		textDeposit.setColumns(10);
		textDeposit.setBounds(117, 155, 200, 20);
		panel.add(textDeposit);
		
		// 원
		JLabel lblInfo2 = new JLabel("\uC6D0");
		lblInfo2.setFont(new Font("굴림", Font.PLAIN, 15));
		lblInfo2.setBounds(322, 155, 30, 20);
		panel.add(lblInfo2);
		
		// 입금 버튼
		JButton btnDeposit = new JButton("\uC785\uAE08");
		// 입금 버튼 클릭 시
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DecimalFormat dfWon = new DecimalFormat("#,###원");
				AccountVO accountVO;
				TradeVO tradeVO;
				
				accountVO = accountDAO.read(textAccountNo.getText());
				
				if(textAccountNo.getText().equals("") || check == false) {
					JOptionPane.showMessageDialog(null, "확인해주세요.");
				} else if (textDeposit.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "입금 금액을 확인해주세요.");
				} else if(!passwordField.getText().equals(accountVO.getPassword())) {
					JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.\n비밀번호를 확인해주세요.");
				} else {
					// 입금 컨펌 ** 확인요망  입금액 없이 입금 버튼 클릭시 메시지 **
					int select = JOptionPane.showConfirmDialog(null, "입금하시겠습니까?", "입금확인", JOptionPane.YES_NO_OPTION);
					
					
					if(select == JOptionPane.OK_OPTION) {
						String accountNo = textAccountNo.getText();
						int tradeBalance = Integer.valueOf(textDeposit.getText());
						
						tradeVO = new TradeVO();
						tradeVO.setAccountNo(accountNo);
						tradeVO.setTradeTypeNo("T01");
						tradeVO.setTradeBalance(tradeBalance);
						
						accountDAO.deposit(tradeVO);
						accountDAO.update(accountNo, tradeBalance);
						
						JOptionPane.showMessageDialog(null, "입금이 완료되었습니다.");
						System.exit(0);
					} else if(select == JOptionPane.NO_OPTION) {
						JOptionPane.showMessageDialog(null, "입금이 취소되었습니다.");
					}
				}
			}
		});
		
		btnDeposit.setFont(new Font("굴림", Font.PLAIN, 15));
		btnDeposit.setBounds(97, 210, 100, 40);
		panel.add(btnDeposit);
		
		// 입금 취소 버튼
		JButton btnCancel = new JButton("\uCDE8\uC18C");
		// 입금 취소 버튼 클릭 시
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int select = JOptionPane.showConfirmDialog(null, "입금을 취소하시겠습니까?", "입금취소", JOptionPane.YES_NO_OPTION);
				if(select == JOptionPane.OK_OPTION) {
					JOptionPane.showMessageDialog(null, "입금이 취소되었습니다.");
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