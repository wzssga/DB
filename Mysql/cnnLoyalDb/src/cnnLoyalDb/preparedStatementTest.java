package cnnLoyalDb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.print.DocFlavor.STRING;

import com.mysql.jdbc.PreparedStatement;

public class preparedStatementTest {
	public static void main(String[] args){
		Connection conn = getConnection();
		String path ="D:\\法条库";  
		String sql = new String("insert into test(lname,ltime,lcategory,ltext) values(?,?,?,?);");
		try {
			PreparedStatement stmt = (PreparedStatement)conn.prepareStatement(sql);
			insertDB(path,stmt);
		} catch (Exception e) {
			
		}
	    
		
	}

	public static void insertDB(String path,PreparedStatement stmt) {
		
			try{

			
			
			// statement用来执行SQL语句
		    
		    
	        File tempFile =new File(path.trim());  	          
	        String fileName = tempFile.getName();  	        
	        if (!tempFile.exists()) {
	        	System.out.println(path + " not exists");
	        	return;
	        }
	        System.out.println("fileName = " + fileName);
	        //读取文件中的文件
	        File fa[] = tempFile.listFiles();
	        for (int i = 0; i < fa.length; i++) {
	            File fs = fa[i];
	            if (fs.isDirectory()){         
	                //System.out.println(fs.getName() +"[目录]");
	            	path = fs.getAbsolutePath();
	            	insertDB(path,stmt);
	            	
	            }else {
	                //System.out.println(fs.getName()+"[不是目录]");
	                //读取名字
	                String sqlName = "";
	                String sqlTime = "";
	                String[] mes= getMessage(fs.getName());  
	                if (mes.length<2) {
	    				sqlName = mes[0];
	    				sqlTime = "null";
	    			}
	                else {
	                	sqlName = mes[0];
	    				sqlTime = mes[1];
					}
	                String txtString = "";
	                txtString = txtString(fs);
	                //String sql = "insert into loyal(lname,ltime) values(";
	                
	                
	               /* System.out.println("sqlName----"+sqlName);
	                System.out.println("sqlTime----"+sqlTime);
	                System.out.println("txtstring----"+txtString);
	                System.out.println("catagory----"+fileName);
	                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");*/
	                
	                stmt.setString(1,sqlName);  
	                stmt.setString(2,sqlTime);
	                stmt.setString(3,fileName);
	                stmt.setString(4,txtString);
	                stmt.executeUpdate();  
	                //System.out.println("##############################################");
	               // sql.append(insertValueString);    
	            }	            
	        }
	        
/*	        if (sql.length()!=sqlLength) {
	        	sql.replace(sql.lastIndexOf(","), sql.lastIndexOf(",")+1, ";");
		        //System.out.println(sql.toString());
	            stmt.executeUpdate(sql.toString());
			}
	        */
			
		 }
		 catch(Exception e){}
		

	}

	private static String txtString(File file){
		// TODO Auto-generated method stub
		StringBuilder result = new StringBuilder();
		try{
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), "utf-8");
            BufferedReader br = new BufferedReader(read);//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
            read.close();
        }catch(Exception e){
            e.printStackTrace();
        }
		//System.out.println(result.toString());
        return result.toString();
    }
		

	private static Connection getConnection() {
		Connection conn = null;	
		try{ // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw  
			
		    Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
			// 连接数据库
			String url = "jdbc:mysql://localhost:3306/loyaldb?"+"user=root&password=root&useUnicode=true&characterEncoding=UTF8&useSSL=true";
			conn = DriverManager.getConnection(url);						
		    if(!conn.isClosed())
			System.out.println("Succeeded connecting to the Database!");//jdbc连接成功。
			
					
		}catch(Exception e){
			
		}
		
		return conn;
	}

	private static String[] getMessage(String message) {
			String[] mes = message.split("-[(]");
			for (int i = 0; i < mes.length; i++) {
				mes[i] = mes[i].replace(")", "").replace(".txt", "").replace("-颁布", "").replace("?", "").replace("月", "-").replace("日", "-");
			}
			if (mes.length>0&&(mes[0].startsWith("《")&&mes[0].endsWith("》"))) {
				mes[0]=mes[0].substring(1, mes[0].length()-1);
			}
			if (mes.length>2) {
				mes[1].replace("颁布时间：", "");
			}
			//System.out.println(mes[0]);
			return mes;
	}
		

   
}
