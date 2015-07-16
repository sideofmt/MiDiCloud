package model;

import java.sql.SQLException;
import java.util.List;

public class UserManager {

	UserDAO userDAO = new UserDAO();

	public int login(String mailAddress, String password) throws SQLException{
		//メールアドレスとパスワードが対応しているかどうか調べる
		//対応していない時0を返す
		//対応している時1を返す
		//対応している上に管理者である場合は2を返す
		User user = userDAO.searchUser(mailAddress);
		System.out.println(user);
		if(user==null){
			System.out.println("ログインに失敗しました userがnull");
			return 0;
		}
		if(!user.getPassword().equals(password)){
			System.out.println("ログインに失敗しました password不一致");
			return 0;
		}

		if(user.isManager()){
			System.out.println("管理者としてログインしました");
			return 2;
		}else{
			System.out.println("一般ユーザーとしてログインしました");
			return 1;
		}
	}

	public boolean check(String mailAddress) throws SQLException{
		//同じメールアドレスがデータベースに存在していないか調べる
		//存在している時true,存在していない時false
		if(userDAO.searchUser(mailAddress)==null){
			System.out.println("指定されたメールアドレスは存在しません");
			return false;
		}else{
			System.out.println("指定されたメールアドレスは存在します");
			return true;
		}
	}

	public void updateUser(User user) throws SQLException{
		//ユーザーの情報を更新する
		//IDは書き換えない
		userDAO.updateUser(user);
		System.out.println("ユーザーの情報を更新しました");
	}

	public boolean createUserData(User user) throws SQLException{
		//新しいユーザーをデータベースに保存する
		//UserIDはここで割り振る
		//引数のUserIDは0
		//エラーが起きたときのsearchNoUserIDは0を返す
		boolean flag;
		int id =userDAO.searchNoUserID();
		user.setUserID(id);
		if(id>0){
			flag = userDAO.addUser(user);
			if(flag){
				System.out.println("ユーザーをDBに追加しました");
				return true;
			}else{
				System.out.println("ユーザーの追加に失敗しました");
				return false;
			}
		}else{
			System.out.println("IDを指定できず、ユーザーの追加に失敗しました");
			return false;
		}
	}

	public User getUser(String mailAddress) throws SQLException{
		//メールアドレスからユーザーのデータを取得する
		//取得に成功した時はtrue
		//取得に失敗した時はfalse
		User user;
		user = userDAO.searchUser(mailAddress);
		if(user != null){
			System.out.println("メールアドレスからユーザーを取得しました");
		}else{
			System.out.println("メールアドレスからユーザーの取得に失敗しました");
		}
		return user;
	}

	public boolean deleteUser(int userID) throws SQLException{
		//ユーザーIDからユーザーを削除する
		//成功した時はtrueが返される
		//失敗した時はfalseが返される
		MidifileManager manager = new MidifileManager();
		manager.deleteU(userID);
		userDAO.deleteUser(userID);
		System.out.println("ユーザーを削除しました");
		return true;
	}

	public User returnUser(int userID) throws SQLException{
		//ユーザーIDからユーザーデータを呼び出す
		//呼び出しに失敗した時はnullを返す

		User user = null;
		user = userDAO.returnUser(userID);
		if(user != null){
			System.out.println("IDからユーザーを呼び出しました");
		}else{
			System.out.println("IDからユーザーの呼び出しに失敗しました");
		}
		return user;
	}

	public List<User> returnUserList() throws SQLException{
		//ユーザーデータのリストを呼び出す
		//呼び出しに失敗した時はnullを返す

		List<User> users = userDAO.getUserList();
		if(users != null){
			System.out.println("ユーザーリストを呼び出しました");
		}else{
			System.out.println("ユーザーリストの呼び出しに失敗しました");
		}
		return users;
	}

}
