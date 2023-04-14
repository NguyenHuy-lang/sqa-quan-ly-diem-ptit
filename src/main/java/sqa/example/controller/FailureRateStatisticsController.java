package sqa.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
import sqa.example.model.MonHoc;
import sqa.example.model.NamHoc;
import sqa.example.model.NamHocKyHoc;
import sqa.example.model.Nganh;
import sqa.example.model.NienKhoa;
import sqa.example.model.NienKhoaNganh;
import sqa.example.repository.NganhRepository;
import sqa.example.service.KyHocService;
import sqa.example.service.LopHocPhanService;
import sqa.example.service.MonHocService;
import sqa.example.service.NamHocKyHocService;
import sqa.example.service.NamHocService;
import sqa.example.service.NienKhoaNganhNamHocKyHocMonHocService;
import sqa.example.service.NienKhoaNganhNamHocKyHocService;
import sqa.example.service.NienKhoaNganhService;
import sqa.example.service.NienKhoaService;
import sqa.example.service.ThangDiemService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/thong-ke-truot")
public class FailureRateStatisticsController
{	
	private final NamHocService namHocService;
	private final KyHocService kyHocService;
	private final NamHocKyHocService namHocKyHocService;
	private final NienKhoaService nienKhoaService;
	private final NganhRepository nganhService;
	private final NienKhoaNganhService nienKhoaNganhService;
	private final NienKhoaNganhNamHocKyHocService nienKhoaNganhNamHocKyHocService;
	private final MonHocService monHocService;
	private final NienKhoaNganhNamHocKyHocMonHocService nienKhoaNganhNamHocKyHocMonHocService;
	private final LopHocPhanService lopHocPhanService;
	private final ThangDiemService thangDiemService;
	
	@GetMapping("nam-hocs")
    public ResponseEntity<List<NamHoc>> getAllNamhoc() 
	{
        return ResponseEntity.ok(namHocService.getAll());
    }
	
	@GetMapping("nam-hocs/{nam-hoc-name}/ky-hocs")
    public ResponseEntity<List<NamHocKyHoc>> getAllKyHocOfNamHoc(@PathVariable(value = "nam-hoc-name") String nam_hoc_name) 
	{
        int namHocId = namHocService.getIdNamHocByName((nam_hoc_name));
        return ResponseEntity.ok(namHocKyHocService.getNamHocKyHoc(namHocId));
    }
	
    @GetMapping("nam-hocs/{nam-hoc-name}/ky-hocs/{ky-hoc-name}/nien-khoas")
    public ResponseEntity<List<NienKhoa>> getAllNienKhoaOfNamHocKiHoc(@PathVariable(value = "nam-hoc-name") String nam_hoc_name ,
																	  @PathVariable(value = "ky-hoc-name") String ky_hoc_name) 
	{
		int namHocId = namHocService.getIdNamHocByName((nam_hoc_name));
        int kyHocId  = kyHocService.getIdKyHocByNameKyHoc(ky_hoc_name);
        int namHocKyHocId = namHocKyHocService.getNamHocKyHoc(namHocId, kyHocId).getId();

        var nienKhoaNganhNamHocKiHocList = nienKhoaNganhNamHocKyHocService.getListNienKhoaNganhNamHocKyHoc(namHocKyHocId);
        var nienKhoaSet = new HashSet<NienKhoa>();
        for (var nienKhoaNganhNamHocKiHoc : nienKhoaNganhNamHocKiHocList) 
			nienKhoaSet.add(nienKhoaNganhNamHocKiHoc.getNienKhoaNganh().getNienKhoa());
		
        return ResponseEntity.ok(nienKhoaSet.stream().toList());
    }
	
	@GetMapping("nam-hocs/{nam-hoc-name}/ky-hocs/{ky-hoc-name}/nien-khoas/{nien-khoa-name}/nganhs")
    public ResponseEntity<List<Nganh>> getAllNganhOfNienKhoaNamHocKiHoc(@PathVariable(value = "nam-hoc-name") String nam_hoc_name,
																		@PathVariable(value = "ky-hoc-name") String ky_hoc_name,
																		@PathVariable(value = "nien-khoa-name") String nien_khoa_name) 
	{
		int namHocId = namHocService.getIdNamHocByName((nam_hoc_name));
        int kyHocId  = kyHocService.getIdKyHocByNameKyHoc(ky_hoc_name);
        int namHocKyHocId = namHocKyHocService.getNamHocKyHoc(namHocId, kyHocId).getId();

        var nienKhoaNganhNamHocKiHocList = nienKhoaNganhNamHocKyHocService.getListNienKhoaNganhNamHocKyHoc(namHocKyHocId);
		var nienKhoaNganhSet = new HashSet<NienKhoaNganh>();
        for (var nienKhoaNganhNamHocKiHoc : nienKhoaNganhNamHocKiHocList) 
			nienKhoaNganhSet.add(nienKhoaNganhNamHocKiHoc.getNienKhoaNganh());
		
		var nganhSet = new HashSet<Nganh>();
		for (var nienKhoaNganh : nienKhoaNganhSet) 
			nganhSet.add(nienKhoaNganh.getNganh());
		
        return ResponseEntity.ok(nganhSet.stream().toList());
    }
	
	@GetMapping("nam-hocs/{nam-hoc-name}/ky-hocs/{ky-hoc-name}/nien-khoas/{nien-khoa-name}/nganhs/{nganh-name}/mon-hocs")
    public ResponseEntity<List<MonHoc>> getMonHocOfNienKhoaNganhNamHocKiHoc(@PathVariable(value = "nam-hoc-name") String nam_hoc_name ,
																			@PathVariable(value = "ky-hoc-name") String ky_hoc_name,
																			@PathVariable(value = "nien-khoa-name") String nien_khoa_name,
																			@PathVariable(value = "nganh-name") String nganh_name) 
	{
		int namHocId = namHocService.getIdNamHocByName((nam_hoc_name));
        int kyHocId  = kyHocService.getIdKyHocByNameKyHoc(ky_hoc_name);
        int namHocKyHocId = namHocKyHocService.getNamHocKyHoc(namHocId, kyHocId).getId();
        int nienKhoaId = nienKhoaService.getIdNienKhoaByName(nien_khoa_name);
        int nganhId = nganhService.getNganhByTen(nganh_name).getId();
		int nienKhoaNganhId = nienKhoaNganhService.getByNienKhoaIdAndNganhId(nienKhoaId, nganhId).getId();
		int nienKhoaNganhNamHocKiHocId = nienKhoaNganhNamHocKyHocService.getNienKhoaNganhNamHocKyHoc(namHocKyHocId, nienKhoaNganhId).getId();
		
		var nkmhList = nienKhoaNganhNamHocKyHocMonHocService.getNienKhoaNganhNamHocKyHocMonHoc(nienKhoaNganhNamHocKiHocId);
		var monHocSet = new HashSet<MonHoc>();
		for (var nkmh : nkmhList)
			monHocSet.add(nkmh.getMonHoc());
		
        return ResponseEntity.ok(monHocSet.stream().toList());
    }
	
	@GetMapping("nam-hocs/{nam-hoc-name}/ky-hocs/{ky-hoc-name}/nien-khoas/{nien-khoa-name}/nganhs/{nganh-name}/mon-hocs/{mon-hoc-name}/lop-hoc-phans")
    public ResponseEntity<List<Map>> getFailureRateOfMonHoc(@PathVariable(value = "nam-hoc-name") String nam_hoc_name ,
															@PathVariable(value = "ky-hoc-name") String ky_hoc_name,
															@PathVariable(value = "nien-khoa-name") String nien_khoa_name,
															@PathVariable(value = "nganh-name") String nganh_name,
															@PathVariable(value = "mon-hoc-name") String mon_hoc_name) 
	{
		int namHocId = namHocService.getIdNamHocByName((nam_hoc_name));
        int kyHocId  = kyHocService.getIdKyHocByNameKyHoc(ky_hoc_name);
        int namHocKyHocId = namHocKyHocService.getNamHocKyHoc(namHocId, kyHocId).getId();
        int nienKhoaId = nienKhoaService.getIdNienKhoaByName(nien_khoa_name);
        int nganhId = nganhService.getNganhByTen(nganh_name).getId();
		int nienKhoaNganhId = nienKhoaNganhService.getByNienKhoaIdAndNganhId(nienKhoaId, nganhId).getId();
		int nienKhoaNganhNamHocKiHocId = nienKhoaNganhNamHocKyHocService.getNienKhoaNganhNamHocKyHoc(namHocKyHocId, nienKhoaNganhId).getId();
		int monHocId = monHocService.getIdMonHocByName(mon_hoc_name);
		int nienKhoaNganhNamHocKiHocMonHoc_id = nienKhoaNganhNamHocKyHocMonHocService.getNienKhoaNganhNamHocKyHocMonHoc(nienKhoaNganhNamHocKiHocId, monHocId).getId();
		
		var LHPList = lopHocPhanService.getLopHocPhan(nienKhoaNganhNamHocKiHocMonHoc_id);
        return ResponseEntity.ok(getFailureRateMapOfLopHocPhanList(LHPList));
    }
	
	@GetMapping("nam-hocs/{nam-hoc-name}/ky-hocs/{ky-hoc-name}/nien-khoas/{nien-khoa-name}/nganhs/{nganh-name}/mon-hocs/{mon-hoc-name}/lop-hoc-phans/{lop-hoc-phan-id}")
    public ResponseEntity<List<Map>> getFailureRateOfLopHocPhan(@PathVariable(value = "nam-hoc-name") String nam_hoc_name,
																@PathVariable(value = "ky-hoc-name") String ky_hoc_name,
																@PathVariable(value = "nien-khoa-name") String nien_khoa_name,
																@PathVariable(value = "nganh-name") String nganh_name,
																@PathVariable(value = "mon-hoc-name") String mon_hoc_name,
																@PathVariable(value = "lop-hoc-phan-id") Integer lop_hoc_phan_id) 
	{
		var LHP = lopHocPhanService.get(lop_hoc_phan_id);
		var listKetQua = LHP.getListKetQua();
		
		double pointLimit = getPointLimit();
		var result = new ArrayList<Map>();
		for (var ketQua : listKetQua)
		{
			var sinhVien = ketQua.getSinhVien();
			var nguoiDung = sinhVien.getNguoiDung();
			
			var map = new HashMap<String, String>();
			map.put("ten", nguoiDung.getName());
			map.put("msv", sinhVien.getMaSinhVien());
			map.put("cc", String.valueOf(ketQua.getDiemCC()));
			map.put("th", String.valueOf(ketQua.getDiemTH()));
			map.put("kt", String.valueOf(ketQua.getDiemKT()));
			map.put("bt", String.valueOf(ketQua.getDiemBT()));
			map.put("ck", String.valueOf(ketQua.getDiemCuoiKy()));
			
			double tong = getDiemTongKet(LHP, ketQua);
			map.put("tong", String.valueOf(tong));
			map.put("ket qua", tong < pointLimit ? "TRUOT" : "QUA");
			result.add(map);
		}
		
        return ResponseEntity.ok(result);
    }
   
   
	////////////////////////////////////////////////////////////////////////////////////////////////	
	private double getPointLimit() {
		var thangDiemD = thangDiemService.getByName("D");
		var limit = thangDiemD.getFrom();
		return limit;
	}
	
	private List<Map> getFailureRateMapOfLopHocPhanList(List<LopHocPhan> listLHP)
	{
		var result = new ArrayList<Map>();
		for (var LHP : listLHP)
			result.add(getFailureRateMapOfLopHocPhan(LHP));
		return result;
	}

	private Map<String, String> getFailureRateMapOfLopHocPhan(LopHocPhan LHP)
	{
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

	public double getFailureRateOfLopHocPhan(LopHocPhan LHP)
	{
		var listKQ = LHP.getListKetQua();
		if (listKQ.isEmpty())
			return 0;

		double pointLimit = getPointLimit();
		
		var count = 0.0;
		for (var KQ : listKQ)
			count += getDiemTongKet(LHP, KQ) < pointLimit ? 1 : 0;

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
