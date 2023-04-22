package sqa.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import sqa.example.model.LopHocPhan;

import java.util.List;
import java.util.Map;
import sqa.example.model.KetQua;

public class LopHocPhanServiceUtils {
	public static double getFailureRate(LopHocPhan LHP, double pointLimit) {
		var listKQ = LHP.getListKetQua();
		if (listKQ.isEmpty())
			return 0;
		
		var count = 0.0;
		for (var KQ : listKQ)
			count += getDiemTongKet(LHP, KQ) < pointLimit ? 1 : 0;

		return count/listKQ.size();
	}

	public static double getDiemTongKet(LopHocPhan LHP, KetQua KQ) {
		var monHoc = LHP.getNienKhoaNganhNamHocKyHocMonHoc().getMonHoc();
		double result = monHoc.getTyLeDiemCC()*KQ.getDiemCC()
					  + monHoc.getTyLeDiemTH()*KQ.getDiemTH()
					  + monHoc.getTyLeDiemKT()*KQ.getDiemKT()
					  + monHoc.getTyLeDiemBT()*KQ.getDiemBT()
					  + monHoc.getTyLeDiemCuoiKy()*KQ.getDiemCuoiKy();
		result = Math.max(result, 0.0);
		result = Math.min(result, 10.0);
		return result;
	}
	
	public static Map<String, String> getJsonFailureRate(LopHocPhan LHP, double pointLimit) {
		var result = new HashMap<String, String>();
		result.put("id",				String.valueOf(LHP.getId()));
		result.put("ma giao vien",		LHP.getGiaoVien().getMaGiaoVien());
		result.put("ten giao vien",		LHP.getGiaoVien().getNguoiDung().getName());
		result.put("thoi gian bat dau", LHP.getBatDau().toString().split(" ")[1]);
		result.put("ten phong hoc",		LHP.getPhongHoc().getTen());
		result.put("so hoc vien",		String.valueOf(LHP.getListKetQua().size()));
		result.put("ty le truot mon",	String.valueOf(getFailureRate(LHP, pointLimit)));
		return result;
	}
	
	public static List<Map> getJsonFailureRateList(List<LopHocPhan> listLHP, double pointLimit) {
		var result = new ArrayList<Map>();
		for (var LHP : listLHP)
			result.add(getJsonFailureRate(LHP, pointLimit));
		return result;
	}
}
