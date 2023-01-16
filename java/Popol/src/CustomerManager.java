import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/*
addActionListener(ActionListener l) {
    if (����ڰ� ��ư�� Ŭ��������) {
        l.actionPerformed();
    }
}

*/

public class CustomerManager extends JFrame implements ActionListener {

    JPanel p1, p2, p3, p4, p5, pWest;
    JTextField tfNo, tfName, tfEmail, tfTel;
    JButton btnSel, btnAdd, btnUpdate, btnDel;
    JTable table;
    Container contentPane;
    CustomerDAO customerDAO;

    public CustomerManager() {

        setTitle("�� ���� ���α׷�");

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    customerDAO = new CustomerDAO(); // DB��ü�غ�

    setLayout(new BorderLayout());
    contentPane = getContentPane();
    pWest = new JPanel(new GridLayout(5, 1));

    p1 = new JPanel();
    p1.add(new JLabel("��ȣ"));
    p1.add(tfNo = new JTextField(12));
    pWest.add(p1);
    
    p2 = new JPanel();
    p2.add(new JLabel("�̸�"));
    p2.add(tfName = new JTextField(12));
    pWest.add(p2);

    p3 = new JPanel();
    p3.add(new JLabel("�̸���"));
    p3.add(tfEmail = new JTextField(12));
    pWest.add(p3);

    p4 = new JPanel();
    p4.add(new JLabel("��ȭ��ȣ"));
    p4.add(tfTel = new JTextField(12));
    pWest.add(p4);
    contentPane.add(pWest, BorderLayout.WEST);
    
    p5 = new JPanel();
    p5.add(btnSel = new JButton("����"));
    p5.add(btnAdd = new JButton("�߰�"));
    p5.add(btnUpdate = new JButton("����"));
    p5.add(btnDel = new JButton("����"));
    contentPane.add(p5, BorderLayout.SOUTH);

    // ��ư �̺�Ʈ ����
    btnSel.addActionListener(this);
    btnAdd.addActionListener(this);
    btnUpdate.addActionListener(this);
    btnDel.addActionListener(this);

    setSize(700, 300);
    setLocationByPlatform(true);
    setVisible(true);
}
    
// ������ ����

private void select() {
    Map<String, Object> map = customerDAO.getCustomer();

    Object[][] data = (Object[][]) map.get("data");
    String[] colNames = (String[]) map.get("colNames");

    // ���̺� ����
    table = new JTable(data, colNames);

    // ���̺��� ������ ����Ʈ�� �߾ӿ� ���̱�
    contentPane.add(new JScrollPane(table), BorderLayout.CENTER);

    // �����ֱ� �������� ȭ�� ����
    setVisible(true);

} // select()

private void add() {

    String strNo = tfNo.getText().trim();
    String strName = tfName.getText().trim();
    String strEmail = tfEmail.getText().trim();
    String strTel = tfTel.getText().trim();

    if (strNo.length() == 0 || strName.length() == 0) {
        JOptionPane.showMessageDialog(this, "�ʼ������� �Է��ϼ���", "����", JOptionPane.ERROR_MESSAGE);
        return;

    }

    // �Ű��������� Map Ÿ������ �غ�
    Map<String, Object> map = new HashMap<>();
    map.put("no", Integer.parseInt(strNo));
    map.put("name", strName);
    map.put("email", strEmail);
    map.put("tel", strTel);

    // ����
    int count = customerDAO.insertCustomer(map);
    
    // "�Է¼���" �޽���
    if (count > 0) {
        JOptionPane.showMessageDialog(this, "�Է¼���", "�Է¼���", JOptionPane.INFORMATION_MESSAGE);
    }

    // �Է»��� �ʱ�ȭ (����)
    tfNo.setText("");
    tfName.setText("");
    tfEmail.setText("");
    tfTel.setText("");

    // �ݿ������ ���̺�� �����ֱ�
    select();
} // add()

public void update() {

    String strNo = tfNo.getText().trim();
    String strName = tfName.getText().trim();
    String strEmail = tfEmail.getText().trim();
    String strTel = tfTel.getText().trim();
    
    if (strNo.length() == 0 || strName.length() == 0) {
        JOptionPane.showMessageDialog(this, "�ʼ������� �Է��ϼ���", "����", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // �Ű��������� Map Ÿ������ �غ�
    Map<String, Object> map = new HashMap<>();
    map.put("no", Integer.parseInt(strNo));
    map.put("name", strName);
    map.put("email", strEmail);
    map.put("tel", strTel);
    
    int count = customerDAO.updateCustomer(map);

    if (count > 0) {
    	JOptionPane.showMessageDialog(this, "��������", "��������", JOptionPane.INFORMATION_MESSAGE);
    }

    tfNo.setText("");
    tfName.setText("");
    tfEmail.setText("");
    tfTel.setText("");

	select();
} // update()

public void del() {

    String strNo = tfNo.getText().trim();

    if (strNo.length() == 0) {
        JOptionPane.showMessageDialog(this, "�ʼ������� �Է��ϼ���", "����", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int no = Integer.parseInt(strNo);
    
    // DB��ü ���� �޼ҵ� ȣ��
    int count = customerDAO.delete(no);

    // "��������" �޽���
    if (count > 0) {
        JOptionPane.showMessageDialog(this, "��������", "��������", JOptionPane.INFORMATION_MESSAGE);
    }

    // �Է»��� �ʱ�ȭ (����)
    tfNo.setText("");
    tfName.setText("");
    tfEmail.setText("");
    tfTel.setText("");

    // �ݿ������ ���̺�� �����ֱ�
    select();
}

@Override

public void actionPerformed(ActionEvent e) {
	
    Object obj = e.getSource();
    // instanceof �����ڴ� ���������� Ÿ���� �ƴ�
    // ���������� ����ִ� ���� ��ü�� Ÿ���� Ȯ����.
    if (obj instanceof JButton) {
        if (obj == btnSel) {
            select();
        } else if (obj == btnAdd) {
            add();
        } else if (obj == btnUpdate) {
        	update();
        } else { // btnDel
	        del();
	    }
    } else if (obj instanceof JTextField) {

    }
}

	public static void main(String[] args) {
		new CustomerManager();
	}

}
