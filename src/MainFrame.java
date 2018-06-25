
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainFrame extends JFrame {
    private JButton jbtnExit = new JButton("Exit");
    private JButton jbtnStart = new JButton("開始");
    private JLabel jlbtitle = new JLabel("星際特工");
    public MainFrame(){
        this.init();
        this.setTitle("星際特工");
    }
    public void init(){

        this.setBounds(80,60,300,300);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(jbtnExit);
        this.add(jbtnStart);
        this.add(jlbtitle);
        jlbtitle.setFont(new Font(null,Font.BOLD,24));
        jlbtitle.setForeground(new Color(20, 113, 255));
        jlbtitle.setBounds(100,-60,150,150);
        jbtnExit.setBounds(100,150,100,100);
        jbtnStart.setBounds(100,40,100,100);
        jbtnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });
        jbtnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame2 frm2 = new MainFrame2();
                frm2.setVisible(true);
            }
        });
    }
}
