package com.smarthome.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

//import sun.dc.path.PathException;


/**
 * 
 * 
 * FileUtils.java Create on 2011-12-1 ����10:51:22
 *  
 * Copyright (c) 2011 by ie580. All rights reserved.
 *  
 * @author zzm
 */
public class FileUtils {
	
	
	/**
	 * ������
	 */
	public static void rename(String filePath,String toFilePath) {
		File file = new File(filePath);
		if(file.exists())
			file.renameTo(new File(toFilePath));
	}
	
	/**
	 * makeDirectory("c:/1/2/3/4/5/6");
	 * һ��һ���Ľ���Ŀ¼
	 * @param path ���ָ�ʽ��"c:/1/2/3/4/5/6", "1/2/3/4/5/6", "/1/2/3/4/5/6"
	 */
	public static String makeDirectory(String path) {
		File f = new File(path);
		if(f.isDirectory() ){
			return "success: �Ѿ��������Ŀ¼";
		} else {
			if( f.mkdirs() ){
				return "success: �ɹ��������Ŀ¼";
			} else {
				return "error: ����Ŀ¼ʧ��, ·��:["+path+"]";
			}
		}
	}
	
	/**
	 * �����ļ�
	 * @param sourceFile Դ�ļ�
	 * @param targetFile ��չ�ļ�
	 * @return
	 */
	public static boolean copyFile(String sourceFile, String targetFile){
		File source = new File(sourceFile);
		File target = new File(targetFile);
		try {
			target.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(source.exists() && target.exists())
			return copyFile( source,  target);
		return false;	
	}
	
	/**
	 * �����ļ�
	 * @param sourceFile Դ�ļ�
	 * @param targetFile ��չ�ļ�
	 * @param isRewrite �Ƿ���д�ļ�
	 * @return
	 */
	public static boolean copyFile(String sourceFile, String targetFile,boolean isRewrite ){
		File target = new File(targetFile);
		if(!isRewrite && target.exists()){
			return true;
		}
		File source = new File(sourceFile);
		try {
			target.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(source.exists() && target.exists())
			return copyFile( source,  target);
		return false;	
	}
	
	
	/** 
	 * �����ļ�
	 * @param sourceFile Դ�ļ�
	 * @param targetFile ��չ�ļ� 
	 * @throws IOException
	 */
	public static boolean copyFile(File sourceFile, File targetFile){
		// New file input stream and buffer
		FileInputStream input= null;
		BufferedInputStream inBuff = null;
		FileOutputStream output = null;
		BufferedOutputStream outBuff= null;
		
		try {
			input = new FileInputStream(sourceFile);
			inBuff = new BufferedInputStream(input);
			// New file output stream and buffer
			output = new FileOutputStream(targetFile);
			outBuff = new BufferedOutputStream(output);
			
			// Buffer byte array
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			outBuff.flush();
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}finally{
			try {
				// Close stream 
				if(null!=inBuff){
					inBuff.close();
				}
				if(null!=output){
					output.close();
				}
				if(null!=outBuff){
					outBuff.close();
				}
				if(null!=input){
					input.close();
				}
			} catch (IOException e) {
			}
		}
		
		return true;
	}
	
	/**
	 * ɾ���ļ�
	 * 
	 * @param file
	 */
	public static void deleteFile(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteFile(files[i]);
				}
			}
			file.delete();
		}
	} 

	/**
	 * Delete file
	 * 
	 * @param file
	 */
	public static void deleteFile(String filePath) {
		File file=new File(filePath);
		deleteFile(file);
	} 
	
	/**
	 * ����Ϣд���ļ�
	 * @param fileName
	 * @param newStr
	 * @throws IOException
	 */
    public static void writeFile(String fileName,String content) throws IOException{
    	
       RandomAccessFile raf = null;
        try {
        	raf = new RandomAccessFile(new File(fileName), "rw");
        	raf.seek(raf.length());
        	raf.write(content.getBytes("UTF-8"));
        } catch (IOException e) {
        } finally {
            if (raf != null) {
                try {
                	raf.close();
                } catch (IOException e) {
                }
            }
        }
        
    }
    
    /**
     * Convert byte to Kbyte
     * @param fileByte
     * @return
     */
    public static float ConvertToKb(long fileByte){
    	
    	return (fileByte /1024f);
    }
    
    /**
     * ��ȡ�ļ�����
     * @param fileName
     * @return file content
     */
    public static String readFileContent(String file){
    	
    	StringBuffer content=new StringBuffer();
    	FileReader fr=null;
    	BufferedReader br=null;
    	
    	try {
			fr=new FileReader(file);
			br=new BufferedReader(fr);
			String str=null;
			while((str=br.readLine())!=null){
				content.append(str+"\n");
			}
			
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}finally{
			try {
				//Close Stream
				if(null!=br){
				   br.close();
				}
				if(null!=fr){
				   fr.close();
				}
			} catch (IOException e) {
			}
		}
    	
    	return content.toString();
    }
    
    public static String getFilesize(int size){
    	String rt_size = "";
		if (size >= 1024 && size < 1024 * 1024) {
			rt_size = size / 1024 + "KB";
		} else if (size >= 1024 * 1024
				&& size < 1024 * 1024 * 1024) {
			rt_size = (size / 1024) / 1024 + "MB";
		} else {
			rt_size = size + "�ֽ�";
		}
		return rt_size;
    }
    
    /** 
     * ѹ���ļ�,�ļ��� 
     * @param srcFileString Ҫѹ�����ļ�/�ļ�������(·��  + �ļ��� : path/filename) 
     * @param zipFileString ָ��ѹ����Ŀ�ĺ�����(·��  + �ļ���: path/filename.zip) 
     * @throws Exception 
     */  
    public static void ZipFolder(String srcFileString, String zipFileString)throws Exception {  
        //����Zip��  
        ZipOutputStream outZip = new ZipOutputStream(new FileOutputStream(new File(zipFileString)));  
        //��Ҫ������ļ�  
        File file = new File(srcFileString);  
        //ѹ��  
        ZipFiles(file.getParent()+File.separator, file.getName(), outZip);  
        //���,�ر�  
        outZip.finish();  
        outZip.close();  
    }  
      
    /** 
     * ѹ���ļ� 
     * @param folderString  
     * @param fileString 
     * @param zipOutputSteam 
     * @throws Exception 
     */  
    private static void ZipFiles(String folderString, String fileString, ZipOutputStream zipOutputSteam)throws Exception{  
        if(zipOutputSteam == null)  
            return;  
          
        File file = new File(folderString+fileString);  
        //�ж��ǲ����ļ�  
        if (file.isFile()) {  
  
            ZipEntry zipEntry =  new ZipEntry(fileString);  
            FileInputStream inputStream = new FileInputStream(file);  
            zipOutputSteam.putNextEntry(zipEntry);  
              
            int len;  
            byte[] buffer = new byte[4096];      
            while((len=inputStream.read(buffer)) != -1)  
            {  
                zipOutputSteam.write(buffer, 0, len);  
            }            
            zipOutputSteam.closeEntry();  
        }  
        else {  
            //�ļ��еķ�ʽ,��ȡ�ļ����µ����ļ�  
            String fileList[] = file.list();  
            //���û�����ļ�, ����ӽ�ȥ����  
            if (fileList.length <= 0) {  
                ZipEntry zipEntry =  new ZipEntry(fileString+File.separator);  
                zipOutputSteam.putNextEntry(zipEntry);  
                zipOutputSteam.closeEntry();                  
            }            
            //��������ļ�, �������ļ�  
            for (int i = 0; i < fileList.length; i++) {  
                ZipFiles(folderString, fileString+File.separator+fileList[i], zipOutputSteam);  
            }  
        }  
    } 
    /**
     * ��ȡ�ļ�
     * @throws IOException 
     */
    public static String ReadFile() throws IOException{
    	 File file=new File("/MainPhoto");
         StringBuffer sb=new StringBuffer();
         if(!file.exists()||file.isDirectory()){
        	 FileInputStream fis=new FileInputStream(file);
             byte[] buf = new byte[1024];
             while((fis.read(buf))!=-1){
                 sb.append(new String(buf));    
                 buf=new byte[1024];//������ɣ�������ϴζ�ȡ������ظ�
             } 
         }
         return sb.toString();
    }
    
    
	//	ɾ���ļ��м�֮�������ļ�(��)
	public static void delWFileList(String path) 
	{
//		FileUtils t = new FileUtils(); 
		delFolder(path); 
	}

	//ɾ���ļ��� 
	//param folderPath �ļ���������·�� 
	public static void delFolder(String folderPath) 
	{ 
		 try { 
		          delAllFile(folderPath); // ɾ���������������� 
		          String filePath = folderPath; 
		          filePath = filePath.toString(); 
		          java.io.File myFilePath = new java.io.File(filePath); 
		          myFilePath.delete(); // ɾ����ļ��� 
		  } catch (Exception e) 
		  { 
			  e.printStackTrace(); 
		  } 
	}
	//ɾ��ָ���ļ����������ļ� 
	//param path �ļ���������·�� 
	public static boolean delAllFile(String path) 
	{ 
	 boolean flag = false; 
	 File file = new File(path); 
	 if (!file.exists()) { return flag; } 
	 if (!file.isDirectory()) { return flag; } 
	 String[] tempList = file.list(); 
	 File temp = null; for (int i = 0; i < tempList.length; i++) 
	 { 
	     if (path.endsWith(File.separator)) { 
	         temp = new File(path + tempList[i]); 
	     } else { 
	         temp = new File(path + File.separator + tempList[i]); 
	     } 
	     if (temp.isFile()) 
	     { 
	         temp.delete(); 
	     } 
	     if (temp.isDirectory()) 
	     { 
	         delAllFile(path + "/" + tempList[i]);// ��ɾ���ļ���������ļ� 
	         delFolder(path + "/" + tempList[i]);// ��ɾ����ļ��� 
	         flag = true; 
	     } 
	} 
	return flag; 
	}
	}
    
