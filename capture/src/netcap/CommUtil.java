/*     */ package netcap;
/*     */ 
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class CommUtil
/*     */ {
/*     */   public static final String orderTime = "2015-03-01 00:00:00";
/*  24 */   public static Set fyxset = new HashSet();
/*     */ 
/*  26 */   public static List<String> xuancheList = new ArrayList();
/*  27 */   public static HashMap<String, String> xuancheMap = new HashMap();
/*  28 */   public static String currentScx = "";
/*  29 */   public static List<String> yjchehaoList = new ArrayList();
/*  30 */   public static int dxCarListLength = 0;
/*  31 */   public static int currentCarIndex = 0;
/*  32 */   public static boolean atXuanche = false;
/*  33 */   public static boolean atFache = false;
/*  34 */   public static boolean deleteFalg = false;
/*  35 */   public static boolean deleteFalgSd = false;
/*  36 */   public static HashMap<String, List<String>> recoveryCarMap = new HashMap();
/*  37 */   public static HashMap<String, String> scxxlfsMap = new HashMap();
/*  38 */   public static HashMap<String, String> scxjvliMap = new HashMap();
/*     */   public static int x;
/*     */   public static int y;
/*     */   public static int width;
/*     */   public static int height;
/*     */   public static int tBottomHeight;
/*     */   public static int x2;
/*     */   public static int y2;
/*     */   public static int width2;
/*     */   public static int height2;
/*     */   public static int x3;
/*     */   public static int y3;
/*     */   public static int width3;
/*     */   public static int height3;
/*  55 */   public static int cuchehisPanelIndex = 1;
/*  56 */   public static String dataDefaultPath = "D:/混凝土调度数据/";
/*  57 */   public static int pagesize = 500;
/*     */   public static int blankDialog_x;
/*     */   public static int blankDialog_y;
/*     */   public static int blankDialog_width;
/*     */   public static int blankDialog_height;
/*  63 */   public static HashMap<String, String> blankDialogTextMap = new HashMap();
/*  64 */   public static ArrayList<String> blankDialogList = new ArrayList();
/*     */   public static int blankDialog_x2;
/*     */   public static int blankDialog_y2;
/*     */   public static int blankDialog_width2;
/*     */   public static int blankDialog_height2;
/*  70 */   public static HashMap<String, String> blankDialogTextMap2 = new HashMap();
/*  71 */   public static ArrayList<String> blankDialogList2 = new ArrayList();
/*     */   public static int blankDialog_x3;
/*     */   public static int blankDialog_y3;
/*     */   public static int blankDialog_width3;
/*     */   public static int blankDialog_height3;
/*  77 */   public static HashMap<String, String> blankDialogTextMap3 = new HashMap();
/*  78 */   public static ArrayList<String> blankDialogList3 = new ArrayList();
/*     */   public static int title_x;
/*     */   public static int title_y;
/*     */   public static int title_width;
/*     */   public static int title_height;
/*  84 */   public static int tableNum = 2;
/*  85 */   public static boolean zxcoverBoolean = false;
/*     */   public static int tabletitle_x;
/*     */   public static int tabletitle_y;
/*     */   public static int tabletitle_width;
/*     */   public static int tabletitle_height;
/*  93 */   public static String username = "root";
/*  94 */   public static String password = "123456";
/*  95 */   public static String url = "jdbc:mysql://127.0.0.1:3306/hnt4?characterEncoding=utf-8";
/*  96 */   public static String driverClass = "com.mysql.jdbc.Driver";
/*  97 */   public static String jdbcip = "127.0.0.1";
/*  98 */   public static String jdbcport = "3306";
/*  99 */   public static String databasename = "hnt4";
/*     */ 
/*     */   public static String getYearMonth()
/*     */   {
/* 103 */     Date nowDate = new Date();
/*     */ 
/* 107 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
/* 108 */     String ymonth = format.format(nowDate);
/* 109 */     return ymonth;
/*     */   }
/*     */   public static List<String> differList(List<String> jvliArray2, List<String> jvliBoxString) {
/* 112 */     ArrayList jvliArray = new ArrayList();
/* 113 */     for (int m = 0; m < jvliArray2.size(); m++) {
/* 114 */       jvliArray.add((String)jvliArray2.get(m));
/*     */     }
/* 116 */     for (int i = 0; i < jvliArray.size(); i++) {
/* 117 */       String orgStr = (String)jvliArray.get(i);
/* 118 */       boolean containFlag = false;
/* 119 */       for (int j = 0; j < jvliBoxString.size(); j++) {
/* 120 */         if (orgStr.equals(jvliBoxString.get(j)))
/* 121 */           containFlag = true;
/*     */       }
/* 123 */       if (containFlag) {
/* 124 */         jvliArray.remove(orgStr);
/* 125 */         i--;
/*     */       }
/*     */     }
/* 128 */     return jvliArray;
/*     */   }
/*     */ 
/*     */   public static List<String> arrayToList(String[] array)
/*     */   {
/* 133 */     List list = new ArrayList();
/* 134 */     if (array != null)
/* 135 */       for (int i = 0; i < array.length; i++) {
/* 136 */         String a = array[i];
/* 137 */         list.add(a);
/*     */       }
/* 139 */     return list;
/*     */   }
/*     */ 
/*     */   public static List<String> arrayToListNotk(String[] array) {
/* 143 */     List list = new ArrayList();
/* 144 */     if (array != null)
/* 145 */       for (int i = 0; i < array.length; i++) {
/* 146 */         String a = array[i];
/* 147 */         if (!"".equals(a.trim()))
/* 148 */           list.add(a);
/*     */       }
/* 150 */     return list;
/*     */   }
/*     */ 
/*     */   public static List<String> arrayToList(Object[] array)
/*     */   {
/* 155 */     List list = new ArrayList();
/* 156 */     for (int i = 0; i < array.length; i++) {
/* 157 */       String a = (String)array[i];
/* 158 */       list.add(a);
/*     */     }
/* 160 */     return list;
/*     */   }
/*     */ 
/*     */   public static String ListToStr(List<String> array) {
/* 164 */     String c = "";
/* 165 */     for (int i = 0; i < array.size(); i++) {
/* 166 */       String m = (String)array.get(i);
/* 167 */       c = c + "," + m;
/*     */     }
/* 169 */     return c;
/*     */   }
/*     */ 
/*     */   public static String ArrayToStr(Object[] array) {
/* 173 */     String c = "";
/* 174 */     for (int i = 0; i < array.length; i++) {
/* 175 */       String m = (String)array[i];
/* 176 */       c = c + "," + m;
/*     */     }
/* 178 */     return c;
/*     */   }
/*     */ 
/*     */   public static Object[] getMergeArray(Object[] defaultValue, Object[] bl) {
/* 182 */     Object[] a = defaultValue;
/* 183 */     Object[] b = bl;
/* 184 */     Object[] c = new Object[a.length + b.length];
/* 185 */     System.arraycopy(a, 0, c, 0, a.length);
/* 186 */     System.arraycopy(b, 0, c, a.length, b.length);
/* 187 */     return c;
/*     */   }
/*     */ 
/*     */   public static boolean isEmpty(String str) {
/* 191 */     if ((str == null) || ("".equals(str)))
/* 192 */       return true;
/* 193 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\jcapture3.jar
 * Qualified Name:     netcap.CommUtil
 * JD-Core Version:    0.6.2
 */