package com.smarthome.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 图片上传
 * 
 * @author andy.gao
 * */
public class ImageFileUpload {
	/** 日志对象 */
	private static final Logger logger = Logger
			.getLogger(ImageFileUpload.class);
	/** 实现类日志对象 */
	protected final Logger log = Logger.getLogger(getClass());

	public static String UploadImage(HttpServletRequest request, String filePath) {
		String imageName = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		String path[] = new String[9];
		boolean flag = false;
		try {
			// boolean isUpload = ServletFileUpload.isMultipartContent(request);
			// String PHOTO=null;
			DiskFileItem item = null;
			// request.getSession().getServletContext().getRealPath("/")
			String fileSavePath = request.getSession().getServletContext()
					.getRealPath("/")
					+ filePath;     
			System.out.println(fileSavePath + "--------");
			logger.debug("[ImageFileUpload.save]save:" + filePath);
			// 如果目录不存在，则创建文件目录
			FileUtils.makeDirectory(fileSavePath);
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(10);//如果从没有调用该方法设置此临界值，将会采用系统默认值10KB。
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(2 * 1024 * 1024);//设计最大的字节数
			try {
				@SuppressWarnings("unchecked")
				List<DiskFileItem> DiskFileItems = upload.parseRequest(request);
				for (int i = 0; i < DiskFileItems.size(); i++) {
					item = DiskFileItems.get(i);
					String key = item.getFieldName();//取得input标签的name属性值
					String itemStr = StrUtil.nullToStr(item.getString("UTF-8"));
					if (!"".equals(key)) {
						System.out.println(key + "--------*****");
						map.put(key, itemStr);
					}
					if (item.isFormField()) {//判断这个元素type是否为text，FCKediter也返回true
						String value = item.getString("UTF-8");
						request.setAttribute(key, value);
					} else {//type是file，上传的文件
						for (int j = 0; j < 9; j++) {
							if (("img").equals(key)
									|| ("image" + j).equals(key)
									|| ("file" + j).equals(key)
									|| ("img" + j).equals(key)) {
								if (!"0".equals(itemStr)) {
									imageName = IDGenerator.getDefaultUUID();
									item.write(new File(fileSavePath + "\\"
											+ imageName + ".jpg"));
									path[j] = filePath + "/" + imageName
											+ ".jpg";
									flag = true;
								}
								// System.out.println("编号："+j+"地址"+path[j]);
								// p = p + "," + request.getContextPath()
								// + "/consult/" + recordFileName + ".jpg";

							}
						}
					}
				}
				System.out.println(imageName + "****************");
			} catch (FileUploadException e) {
				path = null;
				return null;
			}

		} catch (Exception e) {
			path = null;
			return null;
		}
		StringBuilder sb = new StringBuilder("");
		if (path != null && flag) {
			for (String str : path) {
				if (str != null) {
					System.out.println(str + "****---+++");
					sb.append(str);
					sb.append(",");
				}
			}
			sb.setLength(sb.length() - 1);
		}
		return sb.toString();

	}
}
