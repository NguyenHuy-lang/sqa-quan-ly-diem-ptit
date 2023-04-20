package sqa.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sqa.example.model.KetQua;
import sqa.example.model.LopHocPhan;
import sqa.example.model.MonHoc;
import sqa.example.model.NamHoc;
import sqa.example.model.NamHocKyHoc;
import sqa.example.model.Nganh;
import sqa.example.model.NienKhoa;
import sqa.example.model.NienKhoaNganh;
import sqa.example.service.LopHocPhanService;
import sqa.example.service.NamHocKyHocService;
import sqa.example.service.NamHocService;
import sqa.example.service.NienKhoaNganhNamHocKyHocMonHocService;
import sqa.example.service.NienKhoaNganhNamHocKyHocService;
import sqa.example.service.NienKhoaNganhService;
import sqa.example.service.ThangDiemService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/thong-ke-truot")
public class FailureRateStatisticsController {	
	private final NamHocService namHocService;
	private final NamHocKyHocService namHocKyHocService;
	private final NienKhoaNganhService nienKhoaNganhService;
	private final NienKhoaNganhNamHocKyHocService nienKhoaNganhNamHocKyHocService;
	private final NienKhoaNganhNamHocKyHocMonHocService nienKhoaNganhNamHocKyHocMonHocService;
	private final LopHocPhanService lopHocPhanService;
	private final ThangDiemService thangDiemService;
	
	@GetMapping("nam-hocs")
    public ResponseEntity<List<NamHoc>> getAllNamHoc() {
		var result = namHocService.findAll();
		return new ResponseEntity(result, result != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
	
	@GetMapping("nam-hocs/{namHocId}/ky-hocs")
    public ResponseEntity<List<NamHocKyHoc>> getAllKyHocOfNamHoc(
			@PathVariable(value = "namHocId") Integer namHocId) {
		var result = namHocKyHocService.findAllByNamHocId(namHocId);
		return new ResponseEntity(result, result != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
	
    @GetMapping("nam-hocs/{namHocId}/ky-hocs/{kyHocId}/nien-khoas")
    public ResponseEntity<List<NienKhoa>> getAllNienKhoaOfNamHocKiHoc(
			@PathVariable(value = "namHocId") Integer namHocId,
			@PathVariable(value = "kyHocId") Integer kyHocId) {
        var namHocKyHoc = namHocKyHocService.findByNamHocAndKyHocId(namHocId, kyHocId);
		var nienKhoaNganhNamHocKiHocList = nienKhoaNganhNamHocKyHocService.findAllByNamHocKyHocId(namHocKyHoc.getId());
		var nienKhoaSet = new HashSet<NienKhoa>();
        for (var nienKhoaNganhNamHocKiHoc : nienKhoaNganhNamHocKiHocList) 
			nienKhoaSet.add(nienKhoaNganhNamHocKiHoc.getNienKhoaNganh().getNienKhoa());
		
        return ResponseEntity.ok(nienKhoaSet.stream().toList());
    }
	
	@GetMapping("nam-hocs/{namHocId}/ky-hocs/{kyHocId}/nien-khoas/{nienKhoaId}/nganhs")
    public ResponseEntity<List<Nganh>> getAllNganhOfNienKhoaNamHocKiHoc(
			@PathVariable(value = "namHocId") Integer namHocId,
			@PathVariable(value = "kyHocId") Integer kyHocId,
			@PathVariable(value = "nienKhoaId") Integer nienKhoaId) {
        var namHocKyHoc = namHocKyHocService.findByNamHocAndKyHocId(namHocId, kyHocId);
        var nienKhoaNganhNamHocKiHocList = nienKhoaNganhNamHocKyHocService.findAllByNamHocKyHocId(namHocKyHoc.getId());
		var nienKhoaNganhSet = new HashSet<NienKhoaNganh>();
		for (var nienKhoaNganhNamHocKiHoc : nienKhoaNganhNamHocKiHocList) 
			nienKhoaNganhSet.add(nienKhoaNganhNamHocKiHoc.getNienKhoaNganh());
		
		var nganhSet = new HashSet<Nganh>();
		for (var nienKhoaNganh : nienKhoaNganhSet) 
			nganhSet.add(nienKhoaNganh.getNganh());
		
        return ResponseEntity.ok(nganhSet.stream().toList());
    }
	
	@GetMapping("nam-hocs/{namHocId}/ky-hocs/{kyHocId}/nien-khoas/{nienKhoaId}/nganhs/{nganhId}/mon-hocs")
    public ResponseEntity<List<MonHoc>> getMonHocOfNienKhoaNganhNamHocKiHoc(
			@PathVariable(value = "namHocId") Integer namHocId,					
			@PathVariable(value = "kyHocId") Integer kyHocId,						
			@PathVariable(value = "nienKhoaId") Integer nienKhoaId,					
			@PathVariable(value = "nganhId") Integer nganhId) {
        int namHocKyHocId = namHocKyHocService.findByNamHocAndKyHocId(namHocId, kyHocId).getId();
		int nienKhoaNganhId = nienKhoaNganhService.findByNienKhoaAndNganhId(nienKhoaId, nganhId).getId();
		int nienKhoaNganhNamHocKiHocId = nienKhoaNganhNamHocKyHocService.findByNienKhoaNganhAndNamHocKyHocId(nienKhoaNganhId, namHocKyHocId).getId();
		
		var nkmhList = nienKhoaNganhNamHocKyHocMonHocService.findAllByNienKhoaNganhNamHocKyHocId(nienKhoaNganhNamHocKiHocId);
		var monHocSet = new HashSet<MonHoc>();
		for (var nkmh : nkmhList)
			monHocSet.add(nkmh.getMonHoc());
		
        return ResponseEntity.ok(monHocSet.stream().toList());
    }
	
	@GetMapping("nam-hocs/{namHocId}/ky-hocs/{kyHocId}/nien-khoas/{nienKhoaId}/nganhs/{nganhId}/mon-hocs/{monHocId}/lop-hoc-phans")
    public ResponseEntity<List<Map>> getLopHocPhanOfNienKhoaNganhNamHocKyHocMonHoc(
			@PathVariable(value = "namHocId") Integer namHocId,					
			@PathVariable(value = "kyHocId") Integer kyHocId,						
			@PathVariable(value = "nienKhoaId") Integer nienKhoaId,					
			@PathVariable(value = "nganhId") Integer nganhId,
			@PathVariable(value = "monHocId") Integer monHocId)
	{
        int namHocKyHocId = namHocKyHocService.findByNamHocAndKyHocId(namHocId, kyHocId).getId();
		int nienKhoaNganhId = nienKhoaNganhService.findByNienKhoaAndNganhId(nienKhoaId, nganhId).getId();
		int nienKhoaNganhNamHocKiHocId = nienKhoaNganhNamHocKyHocService.findByNienKhoaNganhAndNamHocKyHocId(nienKhoaNganhId, namHocKyHocId).getId();
		int nienKhoaNganhNamHocKiHocMonHocId = nienKhoaNganhNamHocKyHocMonHocService.findByNienKhoaNganhNamHocKyHocAndMonHocId(nienKhoaNganhNamHocKiHocId, monHocId).getId();
		
		var LHPList = lopHocPhanService.findAllByNienKhoaNganhNamHocKyHocMonHocId(nienKhoaNganhNamHocKiHocMonHocId);
        return ResponseEntity.ok(getFailureRateMapOfLopHocPhanList(LHPList));
    }
	
	////////////////////////////////////////////////////////////////////////////////////////////////	
	private double getPointLimit() {
		var thangDiemD = thangDiemService.findByName("D");
		var limit = thangDiemD.getFrom();
		return limit;
	}
	
	private List<Map> getFailureRateMapOfLopHocPhanList(List<LopHocPhan> listLHP) {
		var result = new ArrayList<Map>();
		for (var LHP : listLHP)
			result.add(getFailureRateMapOfLopHocPhan(LHP));
		return result;
	}

	private Map<String, String> getFailureRateMapOfLopHocPhan(LopHocPhan LHP) {
		var result = new HashMap<String, String>();
		result.put("id", String.valueOf(LHP.getId()));
		result.put("ma giao vien", LHP.getGiaoVien().getMaGiaoVien());
		result.put("ten giao vien", LHP.getGiaoVien().getNguoiDung().getName());
		result.put("thoi gian bat dau", LHP.getBatDau().toString().split(" ")[1]);
		result.put("ten phong hoc", LHP.getPhongHoc().getTen());
		result.put("so hoc vien", String.valueOf(LHP.getListKetQua().size()));
		result.put("ty le truot mon", String.valueOf(getFailureRateOfLopHocPhan(LHP)));
		return result;
	}

	public double getFailureRateOfLopHocPhan(LopHocPhan LHP) {
		var listKQ = LHP.getListKetQua();
		if (listKQ.isEmpty())
			return 0;

		double pointLimit = getPointLimit();
		
		var count = 0.0;
		for (var KQ : listKQ)
			count += getDiemTongKet(LHP, KQ) < pointLimit ? 1 : 0;

		return count/listKQ.size();
	}

	private double getDiemTongKet(LopHocPhan LHP, KetQua KQ) {
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
