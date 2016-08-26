/*     */ package netcap;
/*     */ 
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.net.InetAddress;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Vector;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JSeparator;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ import jpcap.packet.IPPacket;
/*     */ import jpcap.packet.Packet;
/*     */ 
/*     */ public class JFrameMain extends JFrame
/*     */   implements ActionListener
/*     */ {
/*     */   private JMenuItem exitMenuItem;
/*     */   private JSeparator jSeparator2;
/*     */   private JMenuItem saveAsMenuItem;
/*     */   private JMenuItem saveMenuItem;
/*     */   private JMenuItem stopMenuItem;
/*     */   private JMenuItem startMenuItem;
/*     */   private JMenu Menu;
/*     */   private JMenuBar jMenuBar1;
/*  44 */   JTable tabledisplay = null;
/*     */   Vector rows;
/*     */   Vector columns;
/*     */   DefaultTableModel tabModel;
/*     */   JScrollPane scrollPane;
/*     */   JLabel statusLabel;
/*  50 */   Netcaptor captor = new Netcaptor();
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*  56 */     JFrameMain inst = new JFrameMain();
/*  57 */     inst.setVisible(true);
/*     */   }
/*     */ 
/*     */   public JFrameMain()
/*     */   {
/*  62 */     initGUI();
/*     */   }
/*     */ 
/*     */   private void initGUI() {
/*     */     try {
/*  67 */       setSize(400, 300);
/*     */ 
/*  69 */       this.jMenuBar1 = new JMenuBar();
/*  70 */       setJMenuBar(this.jMenuBar1);
/*     */ 
/*  72 */       this.Menu = new JMenu();
/*  73 */       this.jMenuBar1.add(this.Menu);
/*  74 */       this.Menu.setText("菜单");
/*  75 */       this.Menu.setPreferredSize(new Dimension(35, 21));
/*     */ 
/*  77 */       this.startMenuItem = new JMenuItem();
/*  78 */       this.Menu.add(this.startMenuItem);
/*  79 */       this.startMenuItem.setText("开始");
/*  80 */       this.startMenuItem.setActionCommand("start");
/*  81 */       this.startMenuItem.addActionListener(this);
/*     */ 
/*  84 */       this.stopMenuItem = new JMenuItem();
/*  85 */       this.Menu.add(this.stopMenuItem);
/*  86 */       this.stopMenuItem.setText("停止");
/*  87 */       this.stopMenuItem.setActionCommand("stop");
/*  88 */       this.stopMenuItem.addActionListener(this);
/*     */ 
/*  91 */       this.saveMenuItem = new JMenuItem();
/*  92 */       this.Menu.add(this.saveMenuItem);
/*  93 */       this.saveMenuItem.setText("保存");
/*     */ 
/*  96 */       this.saveAsMenuItem = new JMenuItem();
/*  97 */       this.Menu.add(this.saveAsMenuItem);
/*  98 */       this.saveAsMenuItem.setText("保存为 ...");
/*     */ 
/* 101 */       this.jSeparator2 = new JSeparator();
/* 102 */       this.Menu.add(this.jSeparator2);
/*     */ 
/* 105 */       this.exitMenuItem = new JMenuItem();
/* 106 */       this.Menu.add(this.exitMenuItem);
/* 107 */       this.exitMenuItem.setText("Exit");
/* 108 */       this.exitMenuItem.setActionCommand("exit");
/* 109 */       this.exitMenuItem.addActionListener(this);
/*     */ 
/* 114 */       this.rows = new Vector();
/* 115 */       this.columns = new Vector();
/*     */ 
/* 117 */       this.columns.addElement("数据报时间");
/* 118 */       this.columns.addElement("源IP地址");
/* 119 */       this.columns.addElement("目的IP地址");
/* 120 */       this.columns.addElement("首部长度");
/* 121 */       this.columns.addElement("数据长度");
/* 122 */       this.columns.addElement("是否分段");
/* 123 */       this.columns.addElement("分段偏移量");
/* 124 */       this.columns.addElement("首部内容");
/* 125 */       this.columns.addElement("数据内容");
/*     */ 
/* 128 */       this.tabModel = new DefaultTableModel();
/* 129 */       this.tabModel.setDataVector(this.rows, this.columns);
/* 130 */       this.tabledisplay = new JTable(this.tabModel);
/* 131 */       this.scrollPane = new JScrollPane(this.tabledisplay);
/* 132 */       getContentPane().add(new JScrollPane(this.tabledisplay), "Center");
/*     */ 
/* 134 */       this.statusLabel = new JLabel("221.7.245.185 ");
/* 135 */       getContentPane().add(this.statusLabel, "South");
/*     */     } catch (Exception e) {
/* 137 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent event) {
/* 142 */     String cmd = event.getActionCommand();
/*     */ 
/* 144 */     if (cmd.equals("start")) {
/* 145 */       this.captor.capturePacketsFromDevice();
/* 146 */       this.captor.setJFrame(this);
/*     */     }
/* 148 */     else if (cmd.equals("stop")) {
/* 149 */       this.captor.stopCapture();
/*     */     }
/* 151 */     else if (cmd.equals("exit")) {
/* 152 */       System.exit(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void dealPacket(Packet packet)
/*     */   {
/*     */     try
/*     */     {
/* 160 */       Vector r = new Vector();
/*     */ 
/* 162 */       Timestamp timestamp = new Timestamp(packet.sec * 1000L + packet.usec / 1000L);
/*     */ 
/* 164 */       r.addElement(timestamp.toString());
/* 165 */       r.addElement(((IPPacket)packet).src_ip.toString());
/* 166 */       r.addElement(((IPPacket)packet).dst_ip.toString());
/* 167 */       r.addElement(Integer.valueOf(packet.header.length));
/* 168 */       r.addElement(Integer.valueOf(packet.data.length));
/* 169 */       r.addElement(((IPPacket)packet).dont_frag ? "分段" : "不分段");
/* 170 */       r.addElement(Short.valueOf(((IPPacket)packet).offset));
/*     */ 
/* 172 */       String strtmp = "";
/* 173 */       for (int i = 0; i < packet.header.length; i++) {
/* 174 */         strtmp = strtmp + Byte.toString(packet.header[i]);
/*     */       }
/* 176 */       r.addElement(strtmp);
/*     */ 
/* 178 */       strtmp = "";
/*     */ 
/* 182 */       //strtmp = printHexString(packet.data);
                strtmp =  new String(packet.data, "ISO-8859-1");
                strtmp = new String(strtmp.getBytes("ISO-8859-1"),"UTF-8");
                System.out.println(strtmp);
/* 183 */       r.addElement(strtmp);
/* if ((strtmp.contains("7E0200"))||(strtmp.contains("7E0001"))||(strtmp.contains("7E8001"))||
(strtmp.contains("7E0002"))||(strtmp.contains("7E0100"))||(strtmp.contains("7E8100"))||
(strtmp.contains("7E0101"))||(strtmp.contains("7E0102"))||(strtmp.contains("7E8100"))){*/
//14537602024 14537602033 14537602027
//14537602011 14537602024



//if(strtmp.contains("14537600115")){
// SendMsgFun.sendByUdp(packet.data);
/* 187 */        // SendMsgFun.sendByTcp(packet.data);
                  //SendMsgFun.sendByTcp(packet.data, "211.138.244.16", 9990);
    this.rows.addElement(r);
//}
/* 190 */         if (this.rows.size() > 15) {
/* 191 */           this.rows.remove(0);
/*     */         }
/* 193 */         this.tabledisplay.addNotify();
/*     */     // }
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public String printHexString(byte[] b)
/*     */   {
/* 204 */     String strm = "";
/*     */ 
/* 206 */     for (int i = 0; i < b.length; i++) {
/* 207 */       String hex = Integer.toHexString(b[i] & 0xFF);
/* 208 */       if (hex.length() == 1) {
/* 209 */         hex = '0' + hex;
/*     */       }
/*     */ 
/* 212 */       String str = hex.toUpperCase();
/* 213 */       strm = strm + str;
/*     */     }
/* 215 */     return strm;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\jcapture3.jar
 * Qualified Name:     netcap.JFrameMain
 * JD-Core Version:    0.6.2
 */