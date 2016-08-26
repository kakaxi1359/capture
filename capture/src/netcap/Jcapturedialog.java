/*     */ package netcap;
/*     */ 
/*     */ import java.awt.Container;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.IOException;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.JTextField;
/*     */ import jpcap.JpcapCaptor;
/*     */ import jpcap.NetworkInterface;
/*     */ 
/*     */ public class Jcapturedialog extends JDialog
/*     */   implements ActionListener
/*     */ {
/*  29 */   static JpcapCaptor jpcap = null;
/*     */   private JRadioButton wholeRadioButton;
/*     */   private JPanel buttonPanel;
/*     */   private JButton cancelButton;
/*     */   private JButton okButton;
/*     */   private JRadioButton userRadioButton;
/*     */   private JRadioButton headRadioButton;
/*     */   private JPanel netPanel;
/*     */   private JTextField caplenTextField;
/*     */   private JPanel caplenPanel;
/*     */   private JTextField filterField;
/*     */   private JPanel filterPanel;
/*     */   private JCheckBox CheckBox;
/*     */   private JComboBox netJComboBox;
/*     */   private JPanel jPanel_east;
/*     */   private JPanel jPanel_west;
/*  45 */   private JTextField ipField = new JTextField("222.217.240.243");
/*  46 */   private JTextField portField = new JTextField("7788");
/*     */   NetworkInterface[] devices;
/*  49 */   public static String zfip = "127.0.0.1";
/*  50 */   public static int zfport = 8000;
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*  54 */     JFrame frame = new JFrame();
/*  55 */     Jcapturedialog inst = new Jcapturedialog(frame);
/*  56 */     inst.setVisible(true);
/*     */   }
/*     */ 
/*     */   public Jcapturedialog(JFrame frame) {
/*  60 */     super(frame, "选择要检测的网卡并设置参数", true);
/*     */     try
/*     */     {
/*  63 */       BoxLayout thisLayout = new BoxLayout(
/*  64 */         getContentPane(), 
/*  65 */         0);
/*  66 */       getContentPane().setLayout(thisLayout);
/*     */ 
/*  68 */       this.jPanel_west = new JPanel();
/*  69 */       this.jPanel_west.setLayout(new BoxLayout(this.jPanel_west, 1));
/*  70 */       getContentPane().add(this.jPanel_west);
/*     */ 
/*  72 */       this.netPanel = new JPanel();
/*  73 */       FlowLayout netPanelLayout = new FlowLayout();
/*  74 */       netPanelLayout.setAlignOnBaseline(true);
/*  75 */       this.netPanel.setBorder(BorderFactory.createTitledBorder("选择网卡"));
/*  76 */       this.netPanel.setAlignmentX(0.0F);
/*  77 */       this.jPanel_west.add(this.netPanel);
/*  78 */       this.netPanel.setLayout(netPanelLayout);
/*     */ 
/*  81 */       this.devices = JpcapCaptor.getDeviceList();
/*  82 */       if (this.devices == null) {
/*  83 */         JOptionPane.showMessageDialog(frame, "没有找到网卡");
/*  84 */         dispose();
/*  85 */         return;
/*     */       }
/*     */ 
/*  88 */       String[] names = new String[this.devices.length];
/*  89 */       for (int i = 0; i < names.length; i++) {
/*  90 */         names[i] = (this.devices[i].description == null ? this.devices[i].name : this.devices[i].description);
/*     */       }
/*  92 */       this.netJComboBox = new JComboBox(names);
/*     */ 
/*  94 */       this.netPanel.add(this.netJComboBox);
/*     */ 
/*  98 */       this.CheckBox = new JCheckBox();
/*  99 */       this.jPanel_west.add(this.CheckBox);
/* 100 */       FlowLayout CheckBoxLayout = new FlowLayout();
/* 101 */       CheckBoxLayout.setAlignOnBaseline(true);
/* 102 */       this.CheckBox.setText("CheckBox");
/* 103 */       this.CheckBox.setLayout(null);
/*     */ 
/* 106 */       this.filterPanel = new JPanel();
/* 107 */       this.filterPanel.setBorder(BorderFactory.createTitledBorder("捕获过滤器"));
/* 108 */       this.filterPanel.setAlignmentX(0.0F);
/* 109 */       FlowLayout filterPanelLayout = new FlowLayout();
/* 110 */       filterPanelLayout.setAlignment(0);
/* 111 */       filterPanelLayout.setAlignOnBaseline(true);
/* 112 */       this.jPanel_west.add(this.filterPanel);
/* 113 */       this.filterPanel.setLayout(filterPanelLayout);
/*     */ 
/* 115 */       this.filterField = new JTextField(20);
/* 116 */       this.filterField.setText("port 9066");
/* 117 */       this.filterPanel.add(this.filterField);
/*     */ 
/* 122 */       this.jPanel_east = new JPanel();
/* 123 */       this.jPanel_east.setLayout(new BoxLayout(this.jPanel_east, 1));
/* 124 */       getContentPane().add(this.jPanel_east);
/*     */ 
/* 127 */       this.caplenPanel = new JPanel();
/* 128 */       this.caplenPanel.setBorder(BorderFactory.createTitledBorder("最长字长"));
/* 129 */       this.caplenPanel.setAlignmentX(0.0F);
/* 130 */       this.jPanel_east.add(this.caplenPanel);
/* 131 */       this.caplenPanel.setLayout(new BoxLayout(this.caplenPanel, 1));
/*     */ 
/* 135 */       this.caplenTextField = new JTextField(20);
/* 136 */       this.caplenPanel.add(this.caplenTextField);
/* 137 */       this.caplenTextField.setText("1514");
/* 138 */       this.caplenTextField.setEnabled(false);
/*     */ 
/* 141 */       this.wholeRadioButton = new JRadioButton();
/* 142 */       FlowLayout userRadioButtonLayout = new FlowLayout();
/* 143 */       userRadioButtonLayout.setAlignOnBaseline(true);
/* 144 */       this.caplenPanel.add(this.wholeRadioButton);
/* 145 */       this.wholeRadioButton.setText(" wholeRadioButto");
/* 146 */       this.wholeRadioButton.setSelected(true);
/*     */ 
/* 148 */       this.wholeRadioButton.addActionListener(this);
/*     */ 
/* 151 */       this.headRadioButton = new JRadioButton();
/* 152 */       this.caplenPanel.add(this.headRadioButton);
/* 153 */       this.headRadioButton.setText("headRadioButton");
/*     */ 
/* 155 */       this.headRadioButton.addActionListener(this);
/*     */ 
/* 158 */       this.userRadioButton = new JRadioButton();
/* 159 */       this.caplenPanel.add(this.userRadioButton);
/* 160 */       this.userRadioButton.setText("userRadioButton");
/*     */ 
/* 162 */       this.userRadioButton.addActionListener(this);
/*     */ 
/* 164 */       ButtonGroup group = new ButtonGroup();
/* 165 */       group.add(this.wholeRadioButton);
/* 166 */       this.wholeRadioButton.setActionCommand("Whole");
/* 167 */       group.add(this.headRadioButton);
/* 168 */       this.headRadioButton.setActionCommand("Head");
/* 169 */       group.add(this.userRadioButton);
/* 170 */       this.userRadioButton.setActionCommand("user");
/*     */ 
/* 172 */       JLabel iplabel = new JLabel("转发ip");
/* 173 */       JLabel portlabel = new JLabel("转发端口");
/* 174 */       this.caplenPanel.add(iplabel);
/* 175 */       this.caplenPanel.add(this.ipField);
/* 176 */       this.caplenPanel.add(portlabel);
/* 177 */       this.caplenPanel.add(this.portField);
/*     */ 
/* 181 */       this.buttonPanel = new JPanel(new FlowLayout(2));
/*     */ 
/* 183 */       this.jPanel_east.add(this.buttonPanel);
/*     */ 
/* 186 */       this.okButton = new JButton();
/* 187 */       this.buttonPanel.add(this.okButton);
/* 188 */       FlowLayout cancelButtonLayout = new FlowLayout();
/* 189 */       cancelButtonLayout.setAlignOnBaseline(true);
/* 190 */       this.okButton.setText("开始");
/*     */ 
/* 192 */       this.okButton.setActionCommand("ok");
/* 193 */       this.okButton.addActionListener(this);
/*     */ 
/* 196 */       this.cancelButton = new JButton();
/* 197 */       this.buttonPanel.add(this.cancelButton);
/* 198 */       this.cancelButton.setText("取消");
/*     */ 
/* 200 */       this.cancelButton.setActionCommand("cancel");
/* 201 */       this.cancelButton.addActionListener(this);
/*     */ 
/* 206 */       getContentPane().setLayout(new BoxLayout(getContentPane(), 0));
/*     */ 
/* 208 */       getContentPane().add(this.jPanel_west);
/*     */ 
/* 210 */       getContentPane().add(this.jPanel_east);
/*     */ 
/* 212 */       pack();
/*     */     }
/*     */     catch (Exception e) {
/* 215 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/* 219 */   public void actionPerformed(ActionEvent evt) { String cmd = evt.getActionCommand();
/* 220 */     String ipStr = this.ipField.getText();
/* 221 */     if ((ipStr == null) || (ipStr.equals(""))) {
/* 222 */       JOptionPane.showMessageDialog(this, "转发ip不能为空，请重新输入！");
/* 223 */       return;
/*     */     }
/* 225 */     zfip = ipStr.trim();
/* 226 */     String portStr = this.portField.getText();
/* 227 */     if ((portStr == null) || (portStr.equals(""))) {
/* 228 */       JOptionPane.showMessageDialog(this, "转发端口不能为空，请重新输入！");
/* 229 */       return;
/*     */     }
/* 231 */     portStr = portStr.trim();
/* 232 */     zfport = Integer.parseInt(portStr);
/* 233 */     if (cmd.equals("Whole")) {
/* 234 */       this.caplenTextField.setText("1514");
/* 235 */       this.caplenTextField.setEnabled(false);
/* 236 */     } else if (cmd.equals("Head")) {
/* 237 */       this.caplenTextField.setText("68");
/* 238 */       this.caplenTextField.setEnabled(false);
/* 239 */     } else if (cmd.equals("user")) {
/* 240 */       this.caplenTextField.setText("");
/* 241 */       this.caplenTextField.setEnabled(true);
/* 242 */       this.caplenTextField.requestFocus();
/* 243 */     } else if (cmd.equals("ok")) {
/*     */       try {
/* 245 */         int caplen = Integer.parseInt(this.caplenTextField.getText());
/* 246 */         if ((caplen < 68) || (caplen > 1514)) {
/* 247 */           JOptionPane.showMessageDialog(null, "捕获长度必须介于 68 和 1514之间");
/* 248 */           return;
/*     */         }
/*     */ 
/* 251 */         jpcap = JpcapCaptor.openDevice(this.devices[this.netJComboBox.getSelectedIndex()], caplen, 
/* 252 */           this.CheckBox.isSelected(), 50);
/*     */ 
/* 254 */         if ((this.filterField.getText() != null) && (this.filterField.getText().length() > 0))
/* 255 */           jpcap.setFilter(this.filterField.getText(), true);
/*     */       }
/*     */       catch (NumberFormatException e)
/*     */       {
/* 259 */         JOptionPane.showMessageDialog(null, "捕获长度必须是正整数");
/*     */       } catch (IOException e) {
/* 261 */         JOptionPane.showMessageDialog(null, e.toString());
/* 262 */         jpcap = null;
/*     */       } finally {
/* 264 */         dispose();
/*     */       }
/*     */     }
/* 267 */     else if (cmd.equals("cancel")) {
/* 268 */       dispose();
/*     */     } }
/*     */ 
/*     */   public static JpcapCaptor getJpcap(JFrame parent)
/*     */   {
/* 273 */     new Jcapturedialog(parent).setVisible(true);
/* 274 */     return jpcap;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\jcapture3.jar
 * Qualified Name:     netcap.Jcapturedialog
 * JD-Core Version:    0.6.2
 */