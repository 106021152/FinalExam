import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MainFrame2 extends JFrame {
    private JLabel[][] jlb = new JLabel[10][10];
    private JLabel jlbair = new JLabel();
    private JLabel jlbMonster = new JLabel();
    private JLabel jlbbullet = new JLabel();
    private JLabel jlbrocket = new JLabel();
    private JLabel jlbBackground = new JLabel();
    private JButton jbtnStart = new JButton("Start");
    private JButton jbtnreset = new JButton("Reset");
    private JButton jbtnAuto = new JButton("Auto");
    private JButton jbtnexit = new JButton("Exit");
    private ImageIcon icon1 = new ImageIcon("月球.png");
    private ImageIcon AirRight = new ImageIcon("飛機.png");
    private ImageIcon AirUp = new ImageIcon("飛機Up.png");
    private ImageIcon AirDown = new ImageIcon("飛機Down.png");
    private ImageIcon AirLeft = new ImageIcon("飛機Left.png");
    private ImageIcon iconMonster = new ImageIcon("Monster.png");
    private ImageIcon iconBulletU = new ImageIcon("子彈往上.png");
    private ImageIcon iconBulletD = new ImageIcon("子彈往下.png");
    private ImageIcon iconBulletL = new ImageIcon("子彈往左.png");
    private ImageIcon iconBulletR = new ImageIcon("子彈.png");
    private ImageIcon iconRocketU = new ImageIcon("火箭往上.png");
    private ImageIcon iconRocketD = new ImageIcon("火箭往下.png");
    private ImageIcon iconRocketL = new ImageIcon("火箭往左.png");
    private ImageIcon iconRocketR = new ImageIcon("火箭.png");
    private Container cp;
    private JPanel jpnc = new JPanel();
    private JPanel jpns = new JPanel(new GridLayout(4,1,3,3));
    private int ObjX = 0, ObjY = 0;
    private Timer Auto;
    private Timer Bulletshoot;
    private Timer RocketShoot;
    private boolean boo = true;
    private boolean booR = true;
    private int x = 0;
    private int y = 0;

    private int dir = 0;
    private int path = 0 ,airpath =0;
    private int path2 = 0;
    private int[][] maze =
            {{2, 0, 0, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 0, 1, 0, 0, 0, 1, 3, 1},
                    {1, 1, 0, 1, 0, 1, 3, 1, 0, 1},
                    {1, 0, 0, 1, 0, 1, 1, 0, 0, 1},
                    {1, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 1, 0, 1, 0, 1, 1, 0, 1},
                    {1, 0, 0, 0, 1, 3, 1, 1, 0, 1},
                    {1, 3, 1, 1, 1, 1, 1, 1, 0, 1},
                    {1, 1, 3, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 1},};
    public MainFrame2() {
        this.init();
    }

    public void init() {
        cp = this.getContentPane();
        cp.add(jpnc);
        cp.add(jpns,BorderLayout.EAST);
        jpnc.add(jlbair);
        jpnc.add(jlbMonster);
        jpnc.add(jlbbullet);
        jpnc.add(jlbrocket);
        jpnc.setLayout(null);
        jpns.add(jbtnStart);
        jpns.add(jbtnreset);
        jpns.add(jbtnAuto);
        jpns.add(jbtnexit);
        Bulletshoot = new Timer(20, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boo =false;
                switch (path){
                    case 0://上
                        jlbbullet.setIcon(iconBulletU);
                        jlbbullet.setLocation(jlbbullet.getX(),jlbbullet.getY()-5);
                        if(jlbbullet.getY() <0){
                            boo=true;
                            Bulletshoot.stop();
                        }
                        break;
                    case 1://下
                        jlbbullet.setIcon(iconBulletD);
                        jlbbullet.setLocation(jlbbullet.getX(),jlbbullet.getY()+5);
                        if(jlbbullet.getY() >760){
                            boo=true;
                            Bulletshoot.stop();
                        }
                        break;
                    case 2://左
                        jlbbullet.setIcon(iconBulletL);
                        jlbbullet.setLocation(jlbbullet.getX()-5,jlbbullet.getY());
                        if(jlbbullet.getX() <0){
                            boo=true;
                            Bulletshoot.stop();
                        }
                        break;
                    case 3://右
                        jlbbullet.setLocation(jlbbullet.getX()+5,jlbbullet.getY());
                        if(jlbbullet.getX() >1180){
                            boo=true;
                            Bulletshoot.stop();
                        }
                        break;
                }
            }
        });
        RocketShoot = new Timer(20, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                booR =false;
                switch (path2) {
                    case 0://上
                        jlbrocket.setIcon(iconRocketU);
                        jlbrocket.setLocation(jlbrocket.getX(), jlbrocket.getY() - 5);
                        if (jlbrocket.getY() < 0) {
                            booR = true;
                            RocketShoot.stop();
                        }
                        break;
                    case 1://下
                        jlbrocket.setIcon(iconRocketD);
                        jlbrocket.setLocation(jlbrocket.getX(), jlbrocket.getY() + 5);
                        if (jlbrocket.getY() > 760) {
                            booR = true;
                            RocketShoot.stop();
                        }
                        break;
                    case 2://左
                        jlbrocket.setIcon(iconRocketL);
                        jlbrocket.setLocation(jlbrocket.getX() - 5, jlbrocket.getY());
                        if (jlbrocket.getX() < 0) {
                            booR = true;
                            RocketShoot.stop();
                        }
                        break;
                    case 3://右
                        jlbrocket.setLocation(jlbrocket.getX() + 5, jlbrocket.getY());
                        if (jlbrocket.getX() > 1180) {
                            booR = true;
                            RocketShoot.stop();
                        }
                        break;
                }
            }
        });
        Auto = new Timer(30, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (dir) {
                    case 0:
                        jlb[x][y].setIcon(AirRight);
                        y++;
                        if(y==2){
                            dir=1;
                            System.out.println(dir);
                        }
                        break;
                    case 1:
                        jlb[x][y].setIcon(AirDown);
                        x++;
                        if(x==3){
                            dir=2;
                            System.out.println(dir);
                        }
                        break;
                    case 2:
                        jlb[x][y].setIcon(AirLeft);
                        y--;
                        if(y==1){
                            dir=3;
                            System.out.println(dir);
                        }
                        break;
                    case 3:
                        jlb[x][y].setIcon(AirDown);
                        x++;
                        if(x==6){
                            dir=4;
                            System.out.println(dir);
                        }
                        break;
                    case 4:
                        jlb[x][y].setIcon(AirRight);
                        y++;
                        if(y==3){
                            dir=5;
                            System.out.println(dir);
                        }
                        break;
                    case 5:
                        jlb[x][y].setIcon(AirUp);
                        x--;
                        if(x==4){
                            dir=6;
                            System.out.println(dir);
                        }
                        break;
                    case 6:
                        jlb[x][y].setIcon(AirRight);
                        y++;
                        if(y==8){
                            dir=7;
                            System.out.println(dir);
                        }
                        break;
                    case 7:
                        jlb[x][y].setIcon(AirDown);
                        x++;
                        if(x==10 && y == 8){
                            MainFrame3 frm3 = new MainFrame3();
                            frm3.setVisible(true);
                        }
                        break;
                    case 8:
                }
                System.out.println(x+" "+y);
            }
        });
        this.setResizable(false);
        this.setBounds(0, 0, 1180, 760);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                jlb[i][j] = new JLabel();
                jpnc.add(jlb[i][j]);

            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                jlb[i][j].setBounds(ObjX, ObjY, 100, 150);
                if (maze[i][j] == 1) {
                    jlb[i][j].setIcon(icon1);
                }
                else if(maze[i][j]==3){
                    jlb[i][j].setIcon(iconMonster);
                }
                ObjX += 110;
            }
            ObjX = 0;
            ObjY += 69;
            jpnc.setVisible(true);

        }

        jbtnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame2.this.requestFocus(true);
                jlbair.setIcon(AirRight);
                jlbair.setBounds(0,35,80,80);
            }
        });
        jbtnreset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlbair.setLocation(0,35);
                jlbair.setIcon(AirRight);
            }
        });
        jbtnAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Auto.start();
            }
        });
        jbtnexit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        jlbair.setIcon(AirUp);
                        jlbair.setLocation(jlbair.getX() ,   jlbair.getY()-30);
                        System.out.println(jlbair.getX()+" "+jlbair.getY());
                        airpath = 0 ;

                        break;
                    case KeyEvent.VK_DOWN:
                        jlbair.setIcon(AirDown);
                        jlbair.setLocation(jlbair.getX() ,   jlbair.getY()+30);
                        System.out.println(jlbair.getX()+" "+jlbair.getY());

                        airpath = 1 ;

                        break;
                    case KeyEvent.VK_LEFT:
                        jlbair.setIcon(AirLeft);
                        jlbair.setLocation(jlbair.getX() -30 , jlbair.getY());
                        System.out.println(jlbair.getX()+" "+jlbair.getY());
                        airpath = 2 ;

                        break;
                    case KeyEvent.VK_RIGHT:
                        jlbair.setIcon(AirRight);
                        jlbair.setLocation(jlbair.getX() +30 , jlbair.getY());
                        System.out.println(jlbair.getX()+" "+jlbair.getY());

                        airpath = 3 ;
                        break;
                    case KeyEvent.VK_X:
                        if (boo){
                            path=airpath;
                            jlbbullet.setIcon(iconBulletR);
                            jlbbullet.setBounds(jlbair.getX()+30,jlbair.getY(),30,60);
                            Bulletshoot.start();
                            break;
                        }
                    case KeyEvent.VK_Z:
                        if(booR) {
                            path2 = airpath;
                            jlbrocket.setIcon(iconRocketR);
                            jlbrocket.setBounds(jlbair.getX() + 30, jlbair.getY() + 10, 30, 60);
                            RocketShoot.start();
                            break;
                        }
                }
            }
        });
    }
}