package model;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReportManager {
	public boolean add(Report report){
		ReportDAO repDAO = new ReportDAO();
		//IDの割り振りをここに記述する

		int id=0;
		try {
			id = repDAO.searchNoUserID();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		report.setReportID(id);

		if(id>0){
			boolean flag = repDAO.add(report);
			if(flag){
				System.out.println("レポートは追加されました");
				return true;
			}else{
				System.out.println("レポートの追加に失敗しました");
				return false;
			}
		}else{
			System.out.println("reportIDの設定に失敗しました");
			return false;
		}
	}

	public boolean remove(int reportedUserID){
		ReportDAO repDAO = new ReportDAO();

		boolean flag;

		flag = repDAO.delete(reportedUserID);
		if(flag){
			System.out.println("レポートは削除されました");
			return true;
		}else{
			System.out.println("レポートの削除に失敗しました");
			return false;
		}

	}

	public ArrayList<Report> getReportList(){
		ReportDAO repDAO = new ReportDAO();
		ArrayList<Report> repList = new ArrayList<Report>();

		repList = repDAO.returnReports();

		return repList;
	}

}
