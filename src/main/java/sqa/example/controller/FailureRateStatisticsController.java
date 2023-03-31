package sqa.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import sqa.example.repository.KyHocRepository;
import sqa.example.repository.LopHocPhanRepository;
import sqa.example.repository.MonHocRepository;
import sqa.example.repository.NamHocKyHocRepository;
import sqa.example.repository.NamHocRepository;
import sqa.example.repository.NganhRepository;
import sqa.example.repository.NienKhoaNganhNamHocKyHocMonHocRepository;
import sqa.example.repository.NienKhoaNganhNamHocKyHocRepository;
import sqa.example.repository.NienKhoaNganhRepository;
import sqa.example.repository.NienKhoaRepository;

/* Test http.
http://localhost:8080/api/v1/thong-ke-truot/nam-hocs/2017-2018/ky-hocs/hoc ky 1/nien-khoas/d19/nganhs/cong nghe thong tin/mon-hocs/nhap mon cong nghe phan mem/danh-sach
Mau tra ve
{
    "0": {
        "so hoc vien"			: "20",
        "ty le truot mon"		: "0.15",
        "ten giao vien"			: "nguyen phuong ly4",
        "ma giao vien"			: "gvbcvt313",
        "ten phong hoc"			: "101-a2",
        "thoi gian bat dau"		: "08:00:00.0"
    },
    "1": {
        "so hoc vien"			: "20",
        "ty le truot mon"		: "0.1",
        "ten giao vien"			: "nguyen phuong ly4",
        "ma giao vien"			: "gvbcvt313",
        "ten phong hoc"			: "101-a3",
        "thoi gian bat dau"		: "08:00:00.0"
    }
}

*/

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/thong-ke-truot")
public class FailureRateStatisticsController
{
	private final LopHocPhanRepository lopHocPhanRepository;
	private final NganhRepository nganhRepository;
	private final NienKhoaRepository nienKhoaRepository;
	private final NienKhoaNganhNamHocKyHocMonHocRepository nienKhoaNganhNamHocKyHocMonHocRepository;
	private final NienKhoaNganhNamHocKyHocRepository nienKhoaNganhNamHocKyHocRepository;
	private final NienKhoaNganhRepository nienKhoaNganhRepository;
	private final NamHocRepository namHocRepository;
	private final KyHocRepository kyHocRepository;
	private final NamHocKyHocRepository namHocKyHocRepository;
	private final MonHocRepository monHocRepository;

	@GetMapping("nam-hocs")
    public ResponseEntity<List<NamHoc>> getAllNamhoc() {
        return ResponseEntity.ok(namHocRepository.findAll());
    }
	
	@GetMapping("nam-hocs/{nam-hoc-name}/ky-hocs")
    public ResponseEntity<List<NamHocKyHoc>> getAllKyHocOfNamHoc(@PathVariable(value = "nam-hoc-name") String nam_hoc_name) 
	{
        int nam_hoc_id = namHocRepository.getIdNamHocByName((nam_hoc_name)).getId();
        return ResponseEntity.ok(namHocKyHocRepository.getNamHocKyHoc(nam_hoc_id));
    }
	
    @GetMapping("nam-hocs/{nam-hoc-name}/ky-hocs/{ky-hoc-name}/nien-khoas")
    public ResponseEntity<List<NienKhoa>> getAllNienKhoaOfNamHocKiHoc(@PathVariable(value = "nam-hoc-name") String nam_hoc_name ,
																	  @PathVariable(value = "ky-hoc-name") String ky_hoc_name) 
	{
		int namHoc_id = namHocRepository.getIdNamHocByName((nam_hoc_name)).getId();
        int kyHoc_id  = kyHocRepository.getKyHocByName(ky_hoc_name).getId();
        int namHocKyHoc_id = namHocKyHocRepository.getNamHocKyHoc(namHoc_id, kyHoc_id).getId();

        var nienKhoaNganhNamHocKiHocList = nienKhoaNganhNamHocKyHocRepository.getListNienKhoaNganhNamHocKyHoc(namHocKyHoc_id);
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
		int namHoc_id = namHocRepository.getIdNamHocByName((nam_hoc_name)).getId();
        int kyHoc_id  = kyHocRepository.getKyHocByName(ky_hoc_name).getId();
        int namHocKyHoc_id = namHocKyHocRepository.getNamHocKyHoc(namHoc_id, kyHoc_id).getId();

        var nienKhoaNganhNamHocKiHocList = nienKhoaNganhNamHocKyHocRepository.getListNienKhoaNganhNamHocKyHoc(namHocKyHoc_id);
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
		int namHoc_id = namHocRepository.getIdNamHocByName((nam_hoc_name)).getId();
        int kyHoc_id  = kyHocRepository.getKyHocByName(ky_hoc_name).getId();
        int namHocKyHoc_id = namHocKyHocRepository.getNamHocKyHoc(namHoc_id, kyHoc_id).getId();
        int nienKhoa_id = nienKhoaRepository.getNienKhoaByName(nien_khoa_name).getId();
        int nganh_id = nganhRepository.getNganhByTen(nganh_name).getId();
		int nienKhoaNganh_id = nienKhoaNganhRepository.getNienKhoaNganhByNienKhoaAndNganh(nienKhoa_id, nganh_id).getId();
		int nienKhoaNganhNamHocKiHoc_id = nienKhoaNganhNamHocKyHocRepository.getNienKhoaNganhNamHocKyHoc(namHocKyHoc_id, nienKhoaNganh_id).getId();
		
		var nkmhList = nienKhoaNganhNamHocKyHocMonHocRepository.getNienKhoaNganhNamHocKyHocMonHoc(nienKhoaNganhNamHocKiHoc_id);
		var monHocSet = new HashSet<MonHoc>();
		for (var nkmh : nkmhList)
			monHocSet.add(nkmh.getMonHoc());
		
        return ResponseEntity.ok(monHocSet.stream().toList());
    }
	
	@GetMapping("nam-hocs/{nam-hoc-name}/ky-hocs/{ky-hoc-name}/nien-khoas/{nien-khoa-name}/nganhs/{nganh-name}/mon-hocs/{mon-hoc-name}/danh-sach")
    public ResponseEntity<List<Map>> getFailureRateOfMonHoc(@PathVariable(value = "nam-hoc-name") String nam_hoc_name ,
																   @PathVariable(value = "ky-hoc-name") String ky_hoc_name,
																   @PathVariable(value = "nien-khoa-name") String nien_khoa_name,
																   @PathVariable(value = "nganh-name") String nganh_name,
																   @PathVariable(value = "mon-hoc-name") String mon_hoc_name) 
	{
		int namHoc_id = namHocRepository.getIdNamHocByName((nam_hoc_name)).getId();
        int kyHoc_id  = kyHocRepository.getKyHocByName(ky_hoc_name).getId();
        int namHocKyHoc_id = namHocKyHocRepository.getNamHocKyHoc(namHoc_id, kyHoc_id).getId();
        int nienKhoa_id = nienKhoaRepository.getNienKhoaByName(nien_khoa_name).getId();
        int nganh_id = nganhRepository.getNganhByTen(nganh_name).getId();
		int nienKhoaNganh_id = nienKhoaNganhRepository.getNienKhoaNganhByNienKhoaAndNganh(nienKhoa_id, nganh_id).getId();
		int nienKhoaNganhNamHocKiHoc_id = nienKhoaNganhNamHocKyHocRepository.getNienKhoaNganhNamHocKyHoc(namHocKyHoc_id, nienKhoaNganh_id).getId();
		int monHoc_id = monHocRepository.getMonHocByName(mon_hoc_name).getId();
		int nienKhoaNganhNamHocKiHocMonHoc_id = nienKhoaNganhNamHocKyHocMonHocRepository.getNienKhoaNganhNamHocKyHocMonHoc(nienKhoaNganhNamHocKiHoc_id, monHoc_id).getId();
		
		var LHPList = lopHocPhanRepository.getLopHocPhan(nienKhoaNganhNamHocKiHocMonHoc_id);
        return ResponseEntity.ok(getFailureRateMapOfLopHocPhanList(LHPList));
    }
   
   
	////////////////////////////////////////////////////////////////////////////////////////////////
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
