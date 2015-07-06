package model;

import java.sql.SQLException;

public class UserManager {

	UserDAO userDAO = new UserDAO();

	public int login(String mailAddress, String password) throws SQLException{
		//メールアドレスとパスワードが対応しているかどうか調べる
		//対応していない時0を返す
		//対応している時1を返す
		//対応している上に管理者である場合は2を返す
		User user = userDAO.searchUser(mailAddress);
		if(user==null){
			return 0;
		}
		if(user.getPassword()!=password){
			return 0;
		}

		if(user.isManager()){
			return 2;
		}else{
			return 0;
		}
	}

	public boolean check(String mailAddress) throws SQLException{
		//同じメールアドレスがデータベースに存在していないか調べる
		//存在している時true,存在していない時false
		if(userDAO.searchUser(mailAddress)==null){
			return false;
		}else{
			return true;
		}
	}

	public void updateUser(User user) throws SQLException{
		//ユーザーの情報を更新する
		//IDは書き換えない
		userDAO.updateUser(user);
	}

	public void createUserData(User user) throws SQLException{
		//新しいユーザーをデータベースに保存する
		//UserIDはここで割り振る
		//引数のUserIDは0
		//エラーが起きたときのsearchNoUserIDは0を返す
		boolean flag;
		int id =userDAO.searchNoUserID();
		if(id>0){
			flag = userDAO.addUser(user);
		}
	}

}
