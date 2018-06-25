import javax.swing.*;
import java.awt.*;
public class WinFrame extends JFrame {
    private JLabel jlb = new JLabel("Win！");
    private JPanel jpnc =new JPanel();
    private Container cp;

    public WinFrame(){
        this.init();
    }
    public void init(){
        cp = this.getContentPane();
        cp.add(jpnc, BorderLayout.CENTER);
        jpnc.add(jlb);
        this.add(jlb);
        this.setTitle("恭喜你贏了！");
        jlb.setFont(new Font(null,Font.BOLD,25 ));
        jlb.setBounds(0,0,100,100);
        this.setBounds(500,300,100,100);
    }
}
