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
    if (사용자가 버튼을 클릭했을때) {
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

        setTitle("고객 관리 프로그램");

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    customerDAO = new CustomerDAO(); // DB객체준비

    setLayout(new BorderLayout());
    contentPane = getContentPane();
    pWest = new JPanel(new GridLayout(5, 1));

    p1 = new JPanel();
    p1.add(new JLabel("번호"));
    p1.add(tfNo = new JTextField(12));
    pWest.add(p1);
    
    p2 = new JPanel();
    p2.add(new JLabel("이름"));
    p2.add(tfName = new JTextField(12));
    pWest.add(p2);

    p3 = new JPanel();
    p3.add(new JLabel("이메일"));
    p3.add(tfEmail = new JTextField(12));
    pWest.add(p3);

    p4 = new JPanel();
    p4.add(new JLabel("전화번호"));
    p4.add(tfTel = new JTextField(12));
    pWest.add(p4);
    contentPane.add(pWest, BorderLayout.WEST);
    
    p5 = new JPanel();
    p5.add(btnSel = new JButton("보기"));
    p5.add(btnAdd = new JButton("추가"));
    p5.add(btnUpdate = new JButton("수정"));
    p5.add(btnDel = new JButton("삭제"));
    contentPane.add(p5, BorderLayout.SOUTH);

    // 버튼 이벤트 연결
    btnSel.addActionListener(this);
    btnAdd.addActionListener(this);
    btnUpdate.addActionListener(this);
    btnDel.addActionListener(this);

    setSize(700, 300);
    setLocationByPlatform(true);
    setVisible(true);
}
    
// 고객정보 보기

private void select() {
    Map<String, Object> map = customerDAO.getCustomer();

    Object[][] data = (Object[][]) map.get("data");
    String[] colNames = (String[]) map.get("colNames");

    // 테이블 생성
    table = new JTable(data, colNames);

    // 테이블을 프레임 컨텐트팬 중앙에 붙이기
    contentPane.add(new JScrollPane(table), BorderLayout.CENTER);

    // 보여주기 설정으로 화면 갱신
    setVisible(true);

} // select()

private void add() {

    String strNo = tfNo.getText().trim();
    String strName = tfName.getText().trim();
    String strEmail = tfEmail.getText().trim();
    String strTel = tfTel.getText().trim();

    if (strNo.length() == 0 || strName.length() == 0) {
        JOptionPane.showMessageDialog(this, "필수사항을 입력하세요", "에러", JOptionPane.ERROR_MESSAGE);
        return;

    }

    // 매개변수값을 Map 타입으로 준비
    Map<String, Object> map = new HashMap<>();
    map.put("no", Integer.parseInt(strNo));
    map.put("name", strName);
    map.put("email", strEmail);
    map.put("tel", strTel);

    // 실행
    int count = customerDAO.insertCustomer(map);
    
    // "입력성공" 메시지
    if (count > 0) {
        JOptionPane.showMessageDialog(this, "입력성공", "입력성공", JOptionPane.INFORMATION_MESSAGE);
    }

    // 입력상자 초기화 (비우기)
    tfNo.setText("");
    tfName.setText("");
    tfEmail.setText("");
    tfTel.setText("");

    // 반영결과를 테이블로 보여주기
    select();
} // add()

public void update() {

    String strNo = tfNo.getText().trim();
    String strName = tfName.getText().trim();
    String strEmail = tfEmail.getText().trim();
    String strTel = tfTel.getText().trim();
    
    if (strNo.length() == 0 || strName.length() == 0) {
        JOptionPane.showMessageDialog(this, "필수사항을 입력하세요", "에러", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // 매개변수값을 Map 타입으로 준비
    Map<String, Object> map = new HashMap<>();
    map.put("no", Integer.parseInt(strNo));
    map.put("name", strName);
    map.put("email", strEmail);
    map.put("tel", strTel);
    
    int count = customerDAO.updateCustomer(map);

    if (count > 0) {
    	JOptionPane.showMessageDialog(this, "수정성공", "수정성공", JOptionPane.INFORMATION_MESSAGE);
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
        JOptionPane.showMessageDialog(this, "필수사항을 입력하세요", "에러", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int no = Integer.parseInt(strNo);
    
    // DB객체 삭제 메소드 호출
    int count = customerDAO.delete(no);

    // "삭제성공" 메시지
    if (count > 0) {
        JOptionPane.showMessageDialog(this, "삭제성공", "삭제성공", JOptionPane.INFORMATION_MESSAGE);
    }

    // 입력상자 초기화 (비우기)
    tfNo.setText("");
    tfName.setText("");
    tfEmail.setText("");
    tfTel.setText("");

    // 반영결과를 테이블로 보여주기
    select();
}

@Override

public void actionPerformed(ActionEvent e) {
	
    Object obj = e.getSource();
    // instanceof 연산자는 참조변수의 타입이 아닌
    // 참조변수가 잡고있는 실제 객체의 타입을 확인함.
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
