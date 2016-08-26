/*    */ package netcap;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import jpcap.JpcapCaptor;
/*    */ import jpcap.PacketReceiver;
/*    */ import jpcap.packet.Packet;
/*    */ 
/*    */ public class Netcaptor
/*    */ {
/* 16 */   JpcapCaptor jpcap = null;
/*    */   JFrameMain frame;
/*    */   private Thread captureThread;
/* 62 */   private PacketReceiver handler = new PacketReceiver()
/*    */   {
/*    */     public void receivePacket(Packet packet) {
/* 65 */       Netcaptor.this.frame.dealPacket(packet);
/*    */     }
/* 62 */   };
/*    */ 
/*    */   public void setJFrame(JFrameMain frame)
/*    */   {
/* 20 */     this.frame = frame;
/*    */   }
/*    */ 
/*    */   public void capturePacketsFromDevice()
/*    */   {
/* 25 */     if (this.jpcap != null) {
/* 26 */       this.jpcap.close();
/*    */     }
/* 28 */     this.jpcap = Jcapturedialog.getJpcap(this.frame);
/*    */ 
/* 30 */     if (this.jpcap != null)
/* 31 */       startCaptureThread();
/*    */   }
/*    */ 
/*    */   private void startCaptureThread()
/*    */   {
/* 40 */     if (this.captureThread != null)
/* 41 */       return;
/* 42 */     this.captureThread = new Thread(new Runnable() {
/*    */       public void run() {
/* 44 */         while (Netcaptor.this.captureThread != null)
/* 45 */           Netcaptor.this.jpcap.processPacket(1, Netcaptor.this.handler);
/*    */       }
/*    */     });
/* 49 */     this.captureThread.setPriority(1);
/* 50 */     this.captureThread.start();
/*    */   }
/*    */ 
/*    */   void stopcaptureThread() {
/* 54 */     this.captureThread = null;
/*    */   }
/*    */ 
/*    */   public void stopCapture() {
/* 58 */     System.out.println(2);
/* 59 */     stopcaptureThread();
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\jcapture3.jar
 * Qualified Name:     netcap.Netcaptor
 * JD-Core Version:    0.6.2
 */