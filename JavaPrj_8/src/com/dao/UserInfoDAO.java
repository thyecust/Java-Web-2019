package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.*;

public class UserInfoDAO {
	private BaseDAO dao = new BaseDAO();

	/**
	 * ����ע���û�
	 * 
	 * @param userName
	 *            �û���
	 * @param passWord
	 *            ����
	 * @param sex
	 *            �Ա�
	 * @param userFace
	 *            ͷ��ͼƬ��
	 * @return boolean ����true��ʾע��ɹ�
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

	// ����
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
	 * ����û���¼��������
	 * 
	 * @param userName
	 *            �û���
	 * @param passWord
	 *            ����
	 * @return boolean ����true��ʾ�û��Ϸ�
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
	 * �����û���������û���Ϣ
	 * 
	 * @param userName
	 *            �û���
	 * @return UserInfo ����һ������
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
	 * �����û�����id���������
	 * 
	 * @param id
	 *            �û����ͱ��
	 * @return �����û���������
	 */
	public String getUserTypeNameById(Integer id) {
		switch (id) {
		case 0:
			return "��Ա";
		case 1:
			return "����";
		case 2:
			return "����Ա";
		}
		return "";
	}

	/**
	 * ���ݲ���ֵ����Ա�����
	 * 
	 * @param sex
	 *            ����ֵ true���� false��Ů
	 * @return �����Ա���
	 */
	public String getSexName(Boolean sex) {
		return sex ? "��" : "Ů";
	}
	public String getUserTypeName(Integer uType) {
		if(uType==2) {
			return "����Ա";
		}else {
			return "��ͨ�û�";
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
			dao.closeResultSet();		//�رս����
			dao.closeStatement();		//�رմ������
			dao.closeConnection();		//�ر����Ӷ���
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
			dao.closeResultSet();		//�رս����
			dao.closeStatement();		//�رմ������
			dao.closeConnection();		//�ر����Ӷ���
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
			dao.closeResultSet();		//�رս����
			dao.closeStatement();		//�رմ������
			dao.closeConnection();		//�ر����Ӷ���
		}
		return count;
	}
	public List<UserInfo> getUserById() {
		List<UserInfo> list = new ArrayList<UserInfo>();//����б�
		String sql = "select * from userInfo";//�����û���Ų�ѯ�û���Ϣ 
		try {
			ResultSet rs = dao.executeQuery(sql);//ִ�в�ѯ
			while (rs != null && rs.next()) {		//������ҵ���¼
				UserInfo user = new UserInfo();//�û���Ϣ����
				user.setUid(rs.getInt("uId"));		//�����û����
				user.setUpassword(rs.getString("uPassWord"));//�����û�����
				user.setUname(rs.getString("uName"));	//�����û���
				user.setUsex(rs.getBoolean("uSex"));//�����Ա�
				user.setUface(rs.getString("uFace"));//����ͷ��
				user.setUregtime(rs.getDate("uRegTime"));
				user.setUtype(rs.getInt("uType"));
				list.add(user);//��Ӱ�����
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();		//�رս����
			dao.closeStatement();		//�رմ������
			dao.closeConnection();		//�ر����Ӷ���
		}
		return list;					//���ذ���б�
	}
	public boolean delUser(Integer Uid) {
		String sql1="delete from userInfo where uId=?";
		Integer result=0;
		try {
			result=dao.executeUpdate(sql1,new Object[] { Uid });
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();//�رս����
			dao.closeStatement();//�رմ������
			dao.closeConnection();//�ر����Ӷ���
		}
		return result > 0 ? true : false;
	}
}
