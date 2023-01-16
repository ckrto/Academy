package jframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO_VO.AccountInfoDAO;
import DAO_VO.AccountInfoVO;
import DAO_VO.PasswordDAO;
import DAO_VO.PasswordVO;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class read extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public read(PasswordVO vo) {
		DecimalFormat df = new DecimalFormat("£Ü0,000¿ø");
		
		AccountInfoDAO idao = new AccountInfoDAO();
		AccountInfoVO ivo = new AccountInfoVO();

		String balance =""; //Æ÷¸ä Àû¿ëÇÑ ÀÜ¾×
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uACC4\uC88C\uC870\uD68C");
		lblNewLabel.setBounds(182, 0, 57, 15);
		contentPane.add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(12, 46, 410, 23);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("\uCDE8\uC18C");
		btnNewButton.setBounds(325, 228, 97, 23);
		contentPane.add(btnNewButton);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(12, 79, 410, 126);
		contentPane.add(textArea_1);
		
		balance = df.format(vo.getBalance());
		ivo=idao.read(vo.getAccountTypeNo());
		
		textArea.setText("°èÁÂÁÖ\tÅ¸ÀÔ\t»óÇ°¸í\t°èÁÂ°³¼³ÀÏ\tÀÜ¾×");
		textArea_1.setText(vo.getAccountName()+"\t"+ivo.getAccountType()+"\t"+ivo.getAccountTypeName()+"\t"+vo.getAccountDate()+"\t"+balance);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
	
}
