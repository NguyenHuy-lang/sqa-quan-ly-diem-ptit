package sqa.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sqa.example.model.KetQua;
import sqa.example.model.LopHocPhan;
import sqa.example.repository.LopHocPhanRepository;
import sqa.example.repository.NamHocKyHocRepository;
import sqa.example.repository.NienKhoaNganhNamHocKyHocMonHocRepository;
import sqa.example.repository.NienKhoaNganhNamHocKyHocRepository;
import sqa.example.repository.NienKhoaNganhRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/thong-ke")
public class FailureRateStatisticsController
{
	private final LopHocPhanRepository lopHocPhanRepository;
	private final NienKhoaNganhNamHocKyHocMonHocRepository NKNNHKHMHRepository;
	private final NienKhoaNganhNamHocKyHocRepository NKNNHKHRepository;
	private final NienKhoaNganhRepository nienKhoaNganhRepository;
	private final NamHocKyHocRepository namHocKyHocRepository;

	@GetMapping("ti-le-truot-theo-lop-hoc-phan-id/{id}")
	public ResponseEntity<Map<String, String>> getFailureRateByLopHocPhanId(@PathVariable(value = "id") Integer id)
	{
		var LHP = lopHocPhanRepository.getLopHocPhanById(id);
		var result = getFailureRateMapOfLopHocPhan(LHP);
		return ResponseEntity.ok(result);
	}

	@GetMapping("ti-le-truot-theo-NKNNHKHMH-id/{nien-khoa-id}/{nam-hoc-id}/{ki-hoc-id}/{mon-hoc-id}")
	public ResponseEntity<Map<String, Map>> getFailureRateByNKNNHKHMHId(
			@PathVariable(value = "nien-khoa-id") Integer nien_khoa_id,
			@PathVariable(value = "nganh-id") Integer nganh_id,
			@PathVariable(value = "nam-hoc-id") Integer nam_hoc_id,
			@PathVariable(value = "ky-hoc-id") Integer ky_hoc_id,
			@PathVariable(value = "mon-hoc-id") Integer mon_hoc_id)
	{
		var NKN_id	= nienKhoaNganhRepository.getNienKhoaNganhByNienKhoaAndNganh(nien_khoa_id, nganh_id).getId();
		var NHKH_id = namHocKyHocRepository.getNamHocKyHoc(nam_hoc_id, ky_hoc_id).getId();
		var NKNNHKH_id = NKNNHKHRepository.getNienKhoaNganhNamHocKyHoc(NHKH_id, NKN_id).getId();
		var NKNNHKHMH_id = NKNNHKHMHRepository.getNienKhoaNganhNamHocKyHocMonHoc(NKNNHKH_id, mon_hoc_id).getId();
		var listLHP = lopHocPhanRepository.getLopHocPhan(NKNNHKHMH_id);
		var result = getFailureRateMapOfListLopHocPhan(listLHP);

		return ResponseEntity.ok(result);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	private Map<String, Map> getFailureRateMapOfListLopHocPhan(List<LopHocPhan> listLHP)
	{
		var count = 0;
		Map result = new HashMap<String, Map>();
		for (var LHP : listLHP)
			result.put("LHP-"+String.valueOf(count++), getFailureRateMapOfLopHocPhan(LHP));
		return result;
	}

	private Map<String, String> getFailureRateMapOfLopHocPhan(LopHocPhan LHP)
	{
		var result = new HashMap<String, String>();
		result.put("maGiaoVien", LHP.getGiaoVien().getMaGiaoVien());
		result.put("tenGiaoVien", LHP.getGiaoVien().getNguoiDung().getName());
		result.put("thoiGianHoc", LHP.getBatDau().toString());
		result.put("tenPhongHoc", LHP.getPhongHoc().getTen());
		result.put("tyLeTruot", String.valueOf(getFailureRateOfLopHocPhan(LHP)));
		return result;
	}

	public double getFailureRateOfLopHocPhan(LopHocPhan LHP)
	{
		var listKQ = LHP.getListKetQua();

		var count = 0.0;
		for (var KQ : listKQ)
			count += getDiemTongKet(LHP, KQ)<4.0 ? 1 : 0;

		return count/listKQ.size();
	}

	private double getDiemTongKet(LopHocPhan LHP, KetQua KQ)
	{
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

}


/*
	@GetMapping("ti-le-truot-theo-ten-mon-hoc/{mon-hoc-name}")
	public ResponseEntity<Map<String, Map>> getFailureRateByMonHocName(@PathVariable(value = "mon-hoc-name") String mon_hoc_name)
	{
		Integer mon_hoc_id = monHocRepository.getMonHocByName(mon_hoc_name).getId();
		var listNKNNHKHMH = NKNNHKHMHRepository.getNienKhoaNganhNamHocKyHocMonHocByMonHoc(mon_hoc_id);
		Map result = new HashMap<String, Map>();
		for (var NKNNHKHMH : listNKNNHKHMH)
			result.put(NKNNHKHMH.getNienKhoaNganhNamHocKyHoc().toString(), getFailureRateMapOfNKNNHKHMH(NKNNHKHMH));

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	private Map<String, Map> getFailureRateMapOfNKNNHKHMH(NienKhoaNganhNamHocKyHocMonHoc NKNNHKHMH)
	{
		var listLHP = lopHocPhanRepository.getLopHocPhan(NKNNHKHMH.getId());
		return getFailureRateMapOfListLopHocPhan(listLHP);
	}
*/
