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

public class CnnLoyalDb {
	public static void main(String[] args){
		Connection conn = getConnection();
		String path ="D:\\法条库";  
		insertDB(path,conn);
	}

	public static void insertDB(String path,Connection conn) {
		
			try{

			
			
			// statement����ִ��SQL���
		    Statement stmt = conn.createStatement();
		    StringBuffer sql = new StringBuffer("insert into loyal(lname,ltime,lcategory) values");
		    int sqlLength = sql.length();
			//stmt.executeUpdate(sql);		
			//String path ="D:\\������\\����Ա���ɷ���";  
	        File tempFile =new File(path.trim());  	          
	        String fileName = tempFile.getName();  	        
	        if (!tempFile.exists()) {
	        	System.out.println(path + " not exists");
	        	return;
	        }
	        System.out.println("fileName = " + fileName);
	        //��ȡ�ļ��е��ļ�
	        File fa[] = tempFile.listFiles();
	        for (int i = 0; i < fa.length; i++) {
	            File fs = fa[i];
	            if (fs.isDirectory()){         
	                //System.out.println(fs.getName() +"[Ŀ¼]");
	            	path = fs.getAbsolutePath();
	            	insertDB(path,conn);
	            	
	            }else {
	                //System.out.println(fs.getName()+"[����Ŀ¼]");
	                //��ȡ����
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
	                //String txtString = txtString(fs);
	                //String sql = "insert into loyal(lname,ltime) values(";
	                String insertValueString = "('"+sqlName+"',"+"'"+sqlTime+"',"+"'"+fileName+"'),";
	                sql.append(insertValueString);    
	            }	            
	        }
	        
	        if (sql.length()!=sqlLength) {
	        	sql.replace(sql.lastIndexOf(","), sql.lastIndexOf(",")+1, ";");
		        //System.out.println(sql.toString());
	            stmt.executeUpdate(sql.toString());
			}
	        
			
		 }
		 catch(Exception e){}
		

	}

	private static String txtString(File file){
		// TODO Auto-generated method stub
		StringBuilder result = new StringBuilder();
		try{
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), "utf-8");
            BufferedReader br = new BufferedReader(read);//����һ��BufferedReader������ȡ�ļ�
            String s = null;
            while((s = br.readLine())!=null){//ʹ��readLine������һ�ζ�һ��
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
		//System.out.println(result.toString());
        return result.toString();
    }
		

	private static Connection getConnection(){
		Connection conn = null;	
		try{ // ��ֹ�ļ��������ȡʧ�ܣ���catch��׽���󲢴�ӡ��Ҳ����throw  
			
		    Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
			// �������ݿ�
			String url = "jdbc:mysql://localhost:3306/loyaldb?"+"user=root&password=root&useUnicode=true&characterEncoding=UTF8&useSSL=true";
			conn = DriverManager.getConnection(url);						
		    if(!conn.isClosed())
			System.out.println("Succeeded connecting to the Database!");//jdbc���ӳɹ���
			
					
		}catch(Exception e){
			
		}
		
		return conn;
	}

	private static String[] getMessage(String message) {
			String[] mes = message.split("-[(]");
			for (int i = 0; i < mes.length; i++) {
				mes[i] = mes[i].replace(")", "").replace(".txt", "").replace("-�䲼", "").replace("?", "").replace("��", "-").replace("��", "-");
			}
			if (mes.length>0&&(mes[0].startsWith("��")&&mes[0].endsWith("��"))) {
				mes[0]=mes[0].substring(1, mes[0].length()-1);
			}
			if (mes.length>2) {
				mes[1].replace("�䲼ʱ�䣺", "");
			}
			return mes;
	}
		

   
}
