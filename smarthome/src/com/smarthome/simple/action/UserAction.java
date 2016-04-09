package com.smarthome.simple.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;

import com.smarthome.base.Authority;
import com.smarthome.base.BaseAction;
import com.smarthome.base.PeriodSum;
import com.smarthome.simple.entity.ResetPwd;
import com.smarthome.simple.entity.Restrecord;
import com.smarthome.simple.entity.User;
import com.smarthome.simple.query.UserQuery;
import com.smarthome.simple.services.RestrecordServices;
import com.smarthome.simple.services.ServiceException;
import com.smarthome.simple.services.UserServices;
import com.smarthome.util.Constans;
import com.smarthome.util.DateUtil;
import com.smarthome.util.FileUtils;
import com.smarthome.util.JSONSerializer;
import com.smarthome.util.MD5Util;
import com.smarthome.util.OwnUtil;
import com.smarthome.util.SendEmail;
import com.smarthome.util.StringUtils;

public class UserAction extends BaseAction<User, UserQuery> {
	private static final long serialVersionUID = 1L;

	@Resource
	protected UserServices userService;
	@Resource
	protected RestrecordServices restrecordServices; 

	private String message = "";

	/**
	 * 分页列出用户
	 * @return
	 */
	@Authority(privilege="userList",module ="user")
	public String userList() {
		page = userService.getCurrentPage(query, this.pageNum, this.numPerPage);
		createPage(page);
		if (!OwnUtil.objectIsEmpty(page) && page.size() > 0
				&& page.getPageNum() > page.getTotalPage())
			return "direct_userList";
		else
			return "userList";
	}

	/**
	 * 登陆
	 * 
	 * @return
	 */
	public String login() {
		try {
			String returnStr = this.userService.login(getQuery());
			if (StringUtils.isEmpty(returnStr)) {
				return "login_failed";
			} else
				return returnStr;
		} catch (ServiceException e) {
			message = e.getMessage();
			return "login_failed";
		}
	}

	/**
	 * 注销登录
	 * 
	 * @return
	 */
	public String logout() {
		User user = (User) request.getSession().getAttribute("user");
		try {
			if (userService.loginOut(user)) {
				request.getSession().removeAttribute("user");
				request.getSession().invalidate();
				return "logout_sucess";
			} else
				return INPUT;
		} catch (ServiceException e) {
			return INPUT;
		}

	}

	/**
	 * 校验用户名、手机是否存在
	 */
	public void userNameIsExist() {
		User user = userService.findByPhoneAndUserName(model);
		try {
			if (!OwnUtil.objectIsEmpty(user)) {
				getResponse().getWriter()
						.write(JSONSerializer.serialize(false));
			} else {
				getResponse().getWriter().write(JSONSerializer.serialize(true));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 新用户注册
	 * 
	 * @return
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 */
	public String registerUser() throws ParseException,
			UnsupportedEncodingException {
		// 用户名
		if (OwnUtil.stringIsEmpty(model.getUserName())) {
			message = "用户名不能为空";
			return "register_failed";
		}
		// 邮箱
		if (OwnUtil.stringIsEmpty(model.getEmail())) {
			message = "邮箱不能为空";
			return "register_failed";
		}
		// 密码
		if (OwnUtil.stringIsEmpty(model.getPwd())) {
			message = "密码不能为空";
			return "register_failed";
		}

		model.setStatus((short) 1);// 用户状态
		model.setRegisterTime(DateUtil.getCurrTimestamp());// 注册时间
		model.setPwd(MD5Util.encode2hex(model.getPwd()));
		Date birthday = DateUtil.strToDate(model.getBirthday(), "yyyy-MM-dd");
		int age = DateUtil.getAge(birthday);
		model.setAge(age);
		short period = PeriodSum.getPeriod(birthday, age);
		query.setPeroid(period);
		model.setPeriod(period);// 后台自动生成人生阶段，方便日后计算客户生活作息规律表
		model.setLogoImage("dist/img/user2-160x160.jpg");
		userService.save(model);
		try {
			Restrecord rest = new Restrecord();
			rest.setUserId(model.getId());
			restrecordServices.saveRecord(rest,query);
		} catch (ServiceException e) {
			message = e.getMessage();
			return "register_failed";			
		}
		message = "注册成功,请输入你的帐号密码登录吧。";
		message = URLEncoder.encode(message, "utf-8");
		return "login_redirect";
	}

	/**
	 * 后台添加用户
	 * 
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 */
	public void registerBUser() throws ParseException,
			UnsupportedEncodingException {
		message="true";
		model.setStatus((short) 1);// 用户状态
		model.setRegisterTime(DateUtil.getCurrTimestamp());// 注册时间
		model.setPwd(MD5Util.encode2hex(model.getPwd()));
		// model.setAdminId(query.getUid());
		Date birthday = DateUtil.strToDate(model.getBirthday(), "yyyy-MM-dd");
		int age = DateUtil.getAge(birthday);
		model.setAge(age);
		model.setAdminId(getUserId());

		model.setPeriod(PeriodSum.getPeriod(birthday, age));// 后台自动生成人生阶段，方便日后计算客户生活作息规律表
		model.setLogoImage("dist/img/user2-160x160.jpg");
		
		userService.save(model);
		try {
			Restrecord rest = new Restrecord();
			rest.setUserId(model.getId());
			restrecordServices.saveRecord(rest,query);
		} catch (ServiceException e) {
			message = e.getMessage();
		}		
		
		try {
			getResponse().getWriter().write(JSONSerializer.serialize(message));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过id去获取唯一user
	 */
	public void getUser() {
		User user = userService.get(query.getId());
		try {
			if (!OwnUtil.objectIsEmpty(user)) {
				getResponse().getWriter().write(
						JSONSerializer.serialize(user).toString());
			} else
				getResponse().getWriter().write("false");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 更新用户
	 */
	public void updateBUser() {
		User user = userService.get(query.getId());

		if (!OwnUtil.stringIsEmpty(query.getConfirmpwd())) {
			user.setPwd(MD5Util.encode2hex(model.getPwd()));
		}
		if (!OwnUtil.stringIsEmpty(model.getEmail())
				&& !OwnUtil.stringIsEqual(model.getEmail(), user.getEmail())) {
			user.setEmail(model.getEmail());
		}
		if (!OwnUtil.stringIsEmpty(model.getPhone())
				&& !OwnUtil.stringIsEqual(model.getPhone(), user.getPhone())) {
			user.setPhone(model.getPhone());
		}
		if (!OwnUtil.stringIsEmpty(model.getBirthday())
				&& !OwnUtil.stringIsEqual(model.getBirthday(),
						user.getBirthday())) {
			user.setBirthday(model.getBirthday());
			Date birthday;
			try {
				birthday = DateUtil
						.strToDate(model.getBirthday(), "yyyy-MM-dd");
				int age = DateUtil.getAge(birthday);
				user.setAge(age);
				user.setPeriod(PeriodSum.getPeriod(birthday, age));// 后台自动生成人生阶段，方便日后计算客户生活作息规律表
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		user.setExchangeTime(DateUtil.getCurrDateStr());
		user.setAdminId(getUserId());
		userService.update(user);
		try {
			getResponse().getWriter().write("true");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新个人信息
	 */
	public void updateUser() {
		User user = userService.get(query.getId());
		String msg = "0";//1修改密码成功跳转登录页面  0 除密码以外其他信息修改成功 -1  修改密码失败原密码不正确
		if (!OwnUtil.stringIsEmpty(query.getConfirmpwd())
				&& !OwnUtil.stringIsEmpty(model.getPwd())) {
			String oldPwd = MD5Util.encode2hex(model.getPwd());
			if (OwnUtil.stringIsEqual(oldPwd, user.getPwd())) {
				user.setPwd(MD5Util.encode2hex(query.getConfirmpwd()));
				msg="1";
			} else {
					msg="-1";
			}

		}
		if (!OwnUtil.stringIsEmpty(model.getEmail())
				&& !OwnUtil.stringIsEqual(model.getEmail(), user.getEmail())) {
			if(!OwnUtil.stringIsEmpty(model.getPwd())){//修改邮箱时候必须需要输入旧密码
				String oldPwd = MD5Util.encode2hex(model.getPwd());
				if (OwnUtil.stringIsEqual(oldPwd, user.getPwd())) {
					user.setEmail(model.getEmail());
					msg="1";
				} else {
						msg="-1";
				}				
				
			}
		}
		if (!OwnUtil.stringIsEmpty(model.getPhone())
				&& !OwnUtil.stringIsEqual(model.getPhone(), user.getPhone())) {
			user.setPhone(model.getPhone());
		}
		if (!OwnUtil.stringIsEmpty(model.getBirthday())
				&& !OwnUtil.stringIsEqual(model.getBirthday(),
						user.getBirthday())) {
			user.setBirthday(model.getBirthday());
			Date birthday;
			try {
				birthday = DateUtil
						.strToDate(model.getBirthday(), "yyyy-MM-dd");
				int age = DateUtil.getAge(birthday);
				user.setAge(age);
				user.setPeriod(PeriodSum.getPeriod(birthday, age));// 后台自动生成人生阶段，方便日后计算客户生活作息规律表
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!OwnUtil.stringIsEmpty(model.getNote())
				&& !OwnUtil.stringIsEqual(model.getNote(),user.getNote())) {
			user.setNote(model.getNote());
		}
		if(msg!="-1"){
			userService.update(user);
		}
		if(msg=="0"){//不修改密码的时候才保存到session 修改密码就跳转到登录页面
			getSession().setAttribute("user", user);
		}
		
		try {
			getResponse().getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 后台删除用户
	 */
	public void deleteUser() {
		try {
			User user = userService.get(query.getId());
			if (OwnUtil.isAdmin(user)) {
				getResponse().getWriter().write("false");
			} else
				userService.deleteUser(user);
			getResponse().getWriter().write("true");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 后台更新用户状态
	 */
	public void forbiddenUser() {
		try {
			User user = userService.get(query.getId());
			user.setStatus(query.getStatus());
			if (OwnUtil.isAdmin(user)) {
				getResponse().getWriter().write("false");
			} else
				userService.update(user);
			getResponse().getWriter().write("true");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 发送邮件验证码
	 * 
	 * @throws IOException
	 */
	public void sendValidate() {
		User user = userService.findByEmail(query.getEmail());
		Boolean msg = false;

		if (!OwnUtil.objectIsEmpty(user)) {
			String code = RandomStringUtils.randomAlphanumeric(6);
			String validate = "您的验证码是:" + code;

			ResetPwd reset = new ResetPwd();
			reset.setEmailVer(code);
			reset.setUid(user.getId());
			reset.setRecordTime(DateUtil.getCurrDateStr());
			reset.setStatus((short) 1);

			userService.saveResetPwd(reset);

			SendEmail.send(query.getEmail(), validate, "SMARTHOMEC 密码找回邮件");

			msg = true;
		}

		try {
			getResponse().getWriter().write(JSONSerializer.serialize(msg));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 检查验证码是否正确
	 * 
	 * @return
	 */
	public String checkCode() {
		int status = userService.findByEmailAndValidate(query);
		if (status == 0) {
			message = "验证码已经超时,请重新获取。";
		} else if (status == 1) {
			model = userService.findByEmail(query.getEmail());
			query.setUserName(model.getUserName());
			query.setEmail(model.getEmail());
			return "resetpwd";
		} else {
			message = "验证码不正确或者不存在。";
		}

		return "failed_resetPwd";
	}

	/**
	 * 找回密码之设置新密码
	 * 
	 * @return
	 */
	public String updatePwd() {

		int status = userService.updatePwd(query);
		if (status == 1) {
			message = "修改密码成功，请使用新密码登录";
			return "login_redirect";
		} else {// 重新开始 找回密码
			message = "密码找回失败，请重新尝试。（原因:对话超时验证码失效等情况...）";
			return "failed_resetPwd";

		}
	}

	public void uploadPic() throws IOException {
		String extendstion = null;// 获取当前文件的后缀名
		int user_id = getUserId();
		boolean num = false;// 判断是否更新成功
		String realpath = getSession().getServletContext().getRealPath("dist/img/upload/");
		User user_old = (User) getSession().getAttribute("user");
		query.setOldFileDir(user_old.getLogoImage());
		user_old=null;
		String baseStr = query.getBase64();
		if (OwnUtil.stringIsEmpty(baseStr)) {
			getResponse().getWriter().write("文件不能为空");
			num = false;
		} else {
			extendstion = query.getBase64().substring(11, 14);
			if (extendstion.equalsIgnoreCase("png")
					|| extendstion.equalsIgnoreCase("jpeg")
					|| extendstion.equalsIgnoreCase("jpg")) {
				query.setTargetFileName(OwnUtil.generateFileName("."
						+ extendstion));
				query.setDir("dist/img/upload/" + user_id + "/"
						+ query.getTargetFileName());
				File parentDir = new File(realpath + "/" + user_id,
						query.getTargetFileName());
				// 先创建文件所在的目录
				parentDir.getParentFile().mkdirs();
				try {
					// 创建新文件
					parentDir.createNewFile();
				} catch (IOException e) {
					getResponse().getWriter().write("由于网络原因上传失败");
					e.printStackTrace();
				}
				num = OwnUtil.base64ToImage(query.getBase64().substring(22),
						parentDir);
				if (num) {
					query.setUid(getUserId());
					userService.uploadPic(query);
					FileUtils.deleteFile(getSession().getServletContext().getRealPath(query.getOldFileDir()));//上传成功之后删除原来的头像
					User user = (User) userService.get(getUserId());
					getSession().setAttribute("user", user);
					getResponse().getWriter().write("上传成功");
				}
			} else {
				getResponse().getWriter().write("仅支持JPEG,PNG格式的图片");
			}
		}

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}