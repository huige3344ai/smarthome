package com.smarthome.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author hy
 *
 */
public class UploadPics {
	/*日志实现*/
	private static final Logger log = LoggerFactory.getLogger(UploadPics.class);

	/**
	 * 用于图片上传
	 * @return
	 */
	public static String uploadImage(File[] files,String[] imgFileName,String[] imgContentType,String userNum,String imageType){
		String realPath = "/upload/images/"+userNum;//相对路径
		String savePath = ServletActionContext.getServletContext().getRealPath(realPath);//获取服务器绝对路径
		String picPath = null;
        //System.out.println("部署的路径："+savePath);//保存的绝对路径
        int index = 0;
        for(File file :files){
        	if(filterTypes(imgContentType)){
            	try {
            		String fileName = imageType+"_"+imgFileName[index];//保存的文件的名称
            		File fileDirectory = new File(savePath);
            		if(!fileDirectory.exists()&&fileDirectory.isDirectory()){//判断文件夹路径是否存在
            			fileDirectory.mkdir();
            		}
					FileUtils.copyFile(file, new File(savePath+File.separator+fileName));//创建保存图片
					picPath = realPath+"/"+fileName;
            	} catch (IOException e) {
					e.printStackTrace();
					log.error("上传失败");
					return null;
				}

        	}
        	index++;
        }
		return picPath;
	}

	public  static String getPicPath(File[] files,String[] imgFileName,String[] imgContentType,String userNum,String imageType){
		String realPath = "upload/images/"+userNum;//相对路径
		String picPath = null;
		 int index = 0;
	        for(@SuppressWarnings("unused") File file :files){
	        	if(filterTypes(imgContentType)){
            		String fileName = imageType+"_"+imgFileName[index];//保存的文件的名称
					picPath = realPath+"/"+fileName;
	        	}
	        	index++;
	        }
		return picPath;
		
		
	}
	/**
	 * 判断图片格式是否正确
	 * @param types
	 * @return
	 */
	public static boolean filterTypes(String[] types){
		String[] imgTypes = {"image/png","image/jpeg","image/gif"};//支持的图片格式
		boolean flag = true;
		for(String type : types){
			setMessage("type:"+type);
			for(String trueType:imgTypes){
				setMessage("trueType:"+trueType);
				if(!trueType.equals(type)){
					flag = false;
					setMessage("仅支持 JPG/PEG/GIF");
				}else{
	                flag = true;
	                break;
	            }
			}
		}
		return flag;
	}
	
	/**
	 * 返还提示信息
	 * @param string
	 */
	private static void setMessage(String string) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html"); 
			response.setCharacterEncoding("utf-8");//返回的字符编码是类型。 
			PrintWriter out = response.getWriter();
			out.print(string);
			//System.out.println("setMessage:"+string);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
