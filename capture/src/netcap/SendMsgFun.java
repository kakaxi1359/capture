/*    */ package netcap;
/*    */ 
/*    */ import java.io.BufferedReader;
import java.io.IOException;
/*    */ import java.io.InputStream;
import java.io.InputStreamReader;
/*    */ import java.io.OutputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.net.DatagramPacket;
/*    */ import java.net.DatagramSocket;
/*    */ import java.net.InetAddress;
import java.net.Socket;
/*    */ 
/*    */ public class SendMsgFun
/*    */ {
/*    */   public static Socket socket;
/*    */   public static Socket socket2;
/*    */ 
/*    */   public static void sendByUdp(byte[] messageDest)
/*    */   {
/*    */     try
/*    */     {
/* 18 */       String hostDest = Jcapturedialog.zfip;
/* 19 */       int portDest = Jcapturedialog.zfport;
/*    */ 
/* 25 */       InetAddress addressDest = InetAddress.getByName(hostDest);
/*    */ 
/* 28 */       DatagramPacket packetDest = new DatagramPacket(messageDest, messageDest.length, 
/* 29 */         addressDest, portDest);
/*    */ 
/* 31 */       DatagramSocket dsocketDest = new DatagramSocket();
/* 32 */       dsocketDest.send(packetDest);
/* 33 */       System.out.println("send: " + new String(messageDest));
/* 34 */       dsocketDest.close();
/*    */     } catch (Exception e) {
/* 36 */       System.err.println(e);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void sendByTcp(byte[] messageDest)
/*    */   {
/*    */     try
/*    */     {
/* 45 */       if (socket == null) {
/* 46 */         socket = new Socket(Jcapturedialog.zfip, Jcapturedialog.zfport);
/*    */       }
/* 48 */       InputStream in = socket.getInputStream();
/* 49 */       OutputStream out = socket.getOutputStream();
/* 50 */       out.write(messageDest);
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 54 */       if (socket != null) {
/*    */         try {
/* 56 */           socket.close();
/*    */         }
/*    */         catch (IOException e1) {
/* 59 */           e1.printStackTrace();
/*    */         }
/*    */       }
/* 62 */       socket = null;
/* 63 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void sendByTcp(byte[] messageDest, String zfip, int zfport)
/*    */   {
/*    */     try
/*    */     {
/* 71 */       if (socket2 == null) {
/* 72 */         socket2 = new Socket(zfip, zfport);
/*    */       }
/* 74 */       InputStream in = socket2.getInputStream();
/* 75 */       OutputStream out = socket2.getOutputStream();
/* 76 */       out.write(messageDest);


/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 80 */       if (socket2 != null) {
/*    */         try {
/* 82 */           socket2.close();
/*    */         }
/*    */         catch (IOException e1) {
/* 85 */           e1.printStackTrace();
/*    */         }
/*    */       }
/* 88 */       socket2 = null;
/* 89 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\jcapture3.jar
 * Qualified Name:     netcap.SendMsgFun
 * JD-Core Version:    0.6.2
 */