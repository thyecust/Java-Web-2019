package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.*;

public class UserInfoDAO {
	private BaseDAO dao = new BaseDAO();

	/**
	 * 创建注册用户
	 * 
	 * @param userName
	 *            用户名
	 * @param passWord
	 *            密码
	 * @param sex
	 *            性别
	 * @param userFace
	 *            头像图片名
	 * @return boolean 返回true表示注册成功
	 */
	public boolean checkReg(String userName, String passWord, boolean sex,
			String userFace) {
		String sql = "insert into userInfo(uName,uPassWord,uSex,uFace) values(?,?,?,?)";
		int result = -1;
		try {
			result = dao.executeUpdate(sql, new Object[] { userName, passWord,
					sex, userFace });
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeStatement();
			dao.closeConnection();
		}
		return result > 0 ? true : false;
	}
	public boolean checkAddUser(String userName, String passWord, boolean sex,
			String userFace,Integer userType) {
		String sql = "insert into userInfo(uName,uPassWord,uSex,uFace,uType) values(?,?,?,?,?)";
		int result = -1;
		try {
			result = dao.executeUpdate(sql, new Object[] { userName, passWord,
					sex, userFace,userType });
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeStatement();
			dao.closeConnection();
		}
		return result > 0 ? true : false;
	}

	// 测试
	public UserInfo getUserInfoByID(int uid) {
		UserInfo user = new UserInfo();
		String sql = "select * from userInfo where uId=?";
		ResultSet rs = null;
		try {
			rs = dao.executeQuery(sql, new Object[] { uid });
			if (rs != null && rs.next()) {
				user.setUid(rs.getInt("uId"));
				user.setUname(rs.getString("uName"));
				user.setUpassword(rs.getString("uPassWord"));
				user.setUsex(rs.getBoolean("uSex"));
				user.setUface(rs.getString("uFace"));
				user.setUregtime(rs.getDate("uRegTime"));
				user.setUtype(rs.getInt("uType"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return user;
	}
	
	/**
	 * 检查用户登录名和密码
	 * 
	 * @param userName
	 *            用户名
	 * @param passWord
	 *            密码
	 * @return boolean 返回true表示用户合法
	 */
	public boolean checkLogin(String userName, String passWord) {
		String sql = "select * from userInfo where uName=? and uPassWord=? ";
		ResultSet rs = null;
		try {
			rs = dao.executeQuery(sql, new Object[] { userName, passWord, });
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			return (rs != null && rs.next()) ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return false;
	}

	/**
	 * 根据用户名，获得用户信息
	 * 
	 * @param userName
	 *            用户名
	 * @return UserInfo 返回一个对象
	 */
	public UserInfo getUserInfo(String userName) {
		UserInfo user = new UserInfo();
		String sql = "select * from userInfo where uName=?";
		ResultSet rs = null;
		try {
			rs = dao.executeQuery(sql, new Object[] { userName });
			if (rs != null && rs.next()) {

				user.setUid(rs.getInt("uId"));
				user.setUname(rs.getString("uName"));
				user.setUpassword(rs.getString("uPassWord"));
				user.setUsex(rs.getBoolean("uSex"));
				user.setUface(rs.getString("uFace"));
				user.setUregtime(rs.getDate("uRegTime"));
				user.setUtype(rs.getInt("uType"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return user;
	}

	/**
	 * 根据用户类型id，获得名称
	 * 
	 * @param id
	 *            用户类型编号
	 * @return 返回用户类型名称
	 */
	public String getUserTypeNameById(Integer id) {
		switch (id) {
		case 0:
			return "会员";
		case 1:
			return "版主";
		case 2:
			return "管理员";
		}
		return "";
	}

	/**
	 * 根据布尔值获得性别名称
	 * 
	 * @param sex
	 *            布尔值 true：男 false：女
	 * @return 返回性别名
	 */
	public String getSexName(Boolean sex) {
		return sex ? "男" : "女";
	}
	public String getUserTypeName(Integer uType) {
		if(uType==2) {
			return "管理员";
		}else {
			return "普通用户";
		}
	}
	public Integer CountTopic(int uid) {
		String sql="select * from topicInfo where tUId=?";
		Integer count=0;
		try {
			ResultSet rs = dao.executeQuery(sql,new Object[] { uid });
			while (rs != null && rs.next()) {
				count++;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();		//关闭结果集
			dao.closeStatement();		//关闭处理对象
			dao.closeConnection();		//关闭连接对象
		}
		return count;
	}
	public Integer CountReply(int uid) {
		String sql="select * from replyInfo where rUId=?";
		Integer count=0;
		try {
			ResultSet rs = dao.executeQuery(sql,new Object[] { uid });
			while (rs != null && rs.next()) {
				count++;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();		//关闭结果集
			dao.closeStatement();		//关闭处理对象
			dao.closeConnection();		//关闭连接对象
		}
		return count;
	}
	public Integer CountSection(int uid) {
		String sql="select * from sectionInfo where sMasterId=?";
		Integer count=0;
		try {
			ResultSet rs = dao.executeQuery(sql,new Object[] { uid });
			while (rs != null && rs.next()) {
				count++;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();		//关闭结果集
			dao.closeStatement();		//关闭处理对象
			dao.closeConnection();		//关闭连接对象
		}
		return count;
	}
	public List<UserInfo> getUserById() {
		List<UserInfo> list = new ArrayList<UserInfo>();//版块列表
		String sql = "select * from userInfo";//根据用户编号查询用户信息 
		try {
			ResultSet rs = dao.executeQuery(sql);//执行查询
			while (rs != null && rs.next()) {		//如果查找到记录
				UserInfo user = new UserInfo();//用户信息对象
				user.setUid(rs.getInt("uId"));		//设置用户编号
				user.setUpassword(rs.getString("uPassWord"));//设置用户密码
				user.setUname(rs.getString("uName"));	//设置用户名
				user.setUsex(rs.getBoolean("uSex"));//设置性别
				user.setUface(rs.getString("uFace"));//设置头像
				user.setUregtime(rs.getDate("uRegTime"));
				user.setUtype(rs.getInt("uType"));
				list.add(user);//添加版块对象
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();		//关闭结果集
			dao.closeStatement();		//关闭处理对象
			dao.closeConnection();		//关闭连接对象
		}
		return list;					//返回版块列表
	}
	public boolean delUser(Integer Uid) {
		String sql1="delete from userInfo where uId=?";
		Integer result=0;
		try {
			result=dao.executeUpdate(sql1,new Object[] { Uid });
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();//关闭结果集
			dao.closeStatement();//关闭处理对象
			dao.closeConnection();//关闭连接对象
		}
		return result > 0 ? true : false;
	}
}
