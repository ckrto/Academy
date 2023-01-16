package jframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import DAO_VO.AccountDAO;

import javax.swing.JTable;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class AccountList extends JFrame {

	private JPanel panel;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button1;
	private JButton button2;

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
	 * Create the frame.
	 */
	public AccountList() {
		AccountDAO accountDAO = new AccountDAO();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		
		String[] header = {"계좌번호", "계좌주명", "잔액", "유형", "개설일"};
		String[][] data = accountDAO.getlist();
		panel.setLayout(null);
		
		table = new JTable(data, header);
		table.setAlignmentX(0);
		table.setPreferredScrollableViewportSize(new Dimension(440, 290));
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(5, 5, 424, 225);
		panel.add(scrollPane);
		
		button1 = new JButton("이전");
		button1.setBounds(263, 232, 80, 25);
		panel.add(button1);
		
		button2 = new JButton("종료");
		button2.setBounds(348, 232, 80, 25);
		panel.add(button2);
		
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Main main = new Main();
			}
		});
	}
	
}
