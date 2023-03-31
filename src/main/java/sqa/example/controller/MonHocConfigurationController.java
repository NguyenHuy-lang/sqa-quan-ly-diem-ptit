package sqa.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sqa.example.model.*;
import sqa.example.repository.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cau-hinh/")
public class MonHocConfigurationController {
    private final MonHocRepository monHocRepository;

	// http://localhost:8080/api/v1/cau-hinh/mon-hocs
    @GetMapping("mon-hocs")
    public ResponseEntity<List<MonHoc>> getAll() 
	{
        return ResponseEntity.ok(monHocRepository.findAll());
    }
	
	// http://localhost:8080/api/v1/cau-hinh/mon-hocs/nhap mon cong nghe phan mem
	@GetMapping("mon-hocs/{mon-hoc-name}")
    public ResponseEntity<MonHoc> getByName(@PathVariable(value = "mon-hoc-name") String mon_hoc_name) 
	{
        return ResponseEntity.ok(monHocRepository.getMonHocByName(mon_hoc_name));
    }

	// http://localhost:8080/api/v1/cau-hinh/mon-hocs/mon hoc moi da den/them-moi?cc=0.2&th=0.2&kt=0.2&bt=0.2&ck=0.2
	// Cac thong so diem thanh phan phai hop le, neu khong mon hoc se khong duoc them va tra ve ket qua rong.
	@PostMapping("mon-hocs/{mon-hoc-name}/them-moi")
	public ResponseEntity<MonHoc> create(@PathVariable(value = "mon-hoc-name") String mon_hoc_name,
										 @RequestParam(value = "cc", required = true) Double cc,
									     @RequestParam(value = "th", required = true) Double th,
										 @RequestParam(value = "kt", required = true) Double kt,
										 @RequestParam(value = "bt", required = true) Double bt,
										 @RequestParam(value = "ck", required = true) Double ck) 
	{
		if (monHocRepository.getMonHocByName(mon_hoc_name) != null)
			return ResponseEntity.ok(null);
		
		MonHoc monHoc = new MonHoc();
		if (isInvalid(cc, th, kt, bt, ck))
			return ResponseEntity.ok(null);
		
		monHoc.setTen(mon_hoc_name);
		monHoc.setTyLeDiemCC(cc);
		monHoc.setTyLeDiemTH(th);
		monHoc.setTyLeDiemKT(kt);
		monHoc.setTyLeDiemBT(bt);
		monHoc.setTyLeDiemCuoiKy(ck);
		
		return ResponseEntity.ok(monHocRepository.save(monHoc));
	}
	
	// http://localhost:8080/api/v1/cau-hinh/mon-hocs/nhap mon cong nghe phan mem/chinh-sua?cc=0.2&th=0.2&kt=0.2&bt=0.2&ck=0.2
	// Cac thong so diem thanh phan phai hop le, neu khong mon hoc se khong duoc sua va tra ve ket qua rong.
	@PutMapping("mon-hocs/{mon-hoc-name}/chinh-sua")
    public ResponseEntity<MonHoc> updateByName(@PathVariable(value = "mon-hoc-name") String mon_hoc_name,
											   @RequestParam(value = "cc", required = true) Double cc,
											   @RequestParam(value = "th", required = true) Double th,
											   @RequestParam(value = "kt", required = true) Double kt,
											   @RequestParam(value = "bt", required = true) Double bt,
											   @RequestParam(value = "ck", required = true) Double ck) 
	{
        var monHoc = monHocRepository.getMonHocByName(mon_hoc_name);	
		if (monHoc == null || isInvalid(cc, th, kt, bt, ck))
			return ResponseEntity.ok(null);
		
		monHoc.setTyLeDiemCC(cc);
		monHoc.setTyLeDiemTH(th);
		monHoc.setTyLeDiemKT(kt);
		monHoc.setTyLeDiemBT(bt);
		monHoc.setTyLeDiemCuoiKy(ck);
		monHocRepository.save(monHoc);
		return ResponseEntity.ok(monHoc);
    }
	
	// http://localhost:8080/api/v1/cau-hinh/mon-hocs/mon hoc moi da den/xoa
	@DeleteMapping("mon-hocs/{mon-hoc-name}/xoa")
	public ResponseEntity<Boolean> delete(@PathVariable(value = "mon-hoc-name") String mon_hoc_name) 
	{
		var monHoc = monHocRepository.getMonHocByName(mon_hoc_name);
		if (monHoc == null)
			return ResponseEntity.ok(false);
		
		monHocRepository.delete(monHoc);
		return ResponseEntity.ok(true);
	}
	
	private boolean isInvalid(Double cc, Double th, Double kt, Double bt, Double ck)
	{
		boolean isInvalid = false;
		isInvalid |= cc < 0 || th < 0 || kt < 0 || bt < 0;
		isInvalid |= cc > 1 || th > 1 || kt > 1 || bt > 1;
		isInvalid |= cc + th + kt + bt + ck != 1;
		return isInvalid;
	}
}
