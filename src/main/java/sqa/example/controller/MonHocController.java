package sqa.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sqa.example.model.*;
import sqa.example.repository.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/mon-hoc/")
public class MonHocController {
    private final MonHocRepository monHocRepository;

    @GetMapping("get-all")
    public ResponseEntity<List<MonHoc>> getAll() {
        return ResponseEntity.ok(monHocRepository.findAll());
    }
	
	@GetMapping("get-theo-id/{id}")
    public ResponseEntity<MonHoc> getById(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(monHocRepository.getById(id));
    }
	
	@GetMapping("get-theo-ten/{name}")
    public ResponseEntity<MonHoc> getByName(@PathVariable(value = "name") String name) {
        return ResponseEntity.ok(monHocRepository.getMonHocByName(name));
    }

    @PostMapping(
			  "tao-moi"
			+ "/{name}"
			+ "/ty-le-diem-cc/{cc}"
			+ "/ty-le-diem-th/{th}"
			+ "/ty-le-diem-kt/{kt}"
			+ "/ty-le-diem-bt/{bt}"
			+ "/ty-le-diem-ck/{ck}")
    public ResponseEntity<MonHoc> create(@PathVariable(value = "name") String name,
										@PathVariable(value = "cc") Double cc,
                                        @PathVariable(value = "th") Double th,
                                        @PathVariable(value = "kt") Double kt,
                                        @PathVariable(value = "bt") Double bt,
                                        @PathVariable(value = "ck") Double ck) 
	{
		if (monHocRepository.getMonHocByName(name) != null)
			return ResponseEntity.ok(null);
		
		var monHoc = new MonHoc();
		monHoc.setTen(name);
		monHoc.setTyLeDiemCC(cc);
		monHoc.setTyLeDiemTH(th);
		monHoc.setTyLeDiemKT(kt);
		monHoc.setTyLeDiemBT(bt);
		monHoc.setTyLeDiemCuoiKy(ck);
		monHocRepository.save(monHoc);
        
		return ResponseEntity.ok(monHoc);
    }
	
    @PutMapping(
			  "cap-nhat-theo-ten"
			+ "/{name}"
			+ "/ty-le-diem-cc/{cc}"
			+ "/ty-le-diem-th/{th}"
			+ "/ty-le-diem-kt/{kt}"
			+ "/ty-le-diem-bt/{bt}"
			+ "/ty-le-diem-ck/{ck}")
    public ResponseEntity<MonHoc> updateByName(@PathVariable(value = "name") String name,
											   @PathVariable(value = "cc") Double cc,
											   @PathVariable(value = "th") Double th,
											   @PathVariable(value = "kt") Double kt,
											   @PathVariable(value = "bt") Double bt,
											   @PathVariable(value = "ck") Double ck) 
	{
        var monHoc = monHocRepository.getMonHocByName(name);		
		if (monHoc == null)
			return ResponseEntity.ok(null);
		
		return updateById(monHoc.getId(), cc, th, kt, bt, ck);
    }
	
	@PutMapping(
			  "cap-nhat-theo-id/{id}"
			+ "/ty-le-diem-cc/{cc}"
			+ "/ty-le-diem-th/{th}"
			+ "/ty-le-diem-kt/{kt}"
			+ "/ty-le-diem-bt/{bt}"
			+ "/ty-le-diem-ck/{ck}")
    public ResponseEntity<MonHoc> updateById(@PathVariable(value = "id") Integer id,
											 @PathVariable(value = "cc") Double cc,
											 @PathVariable(value = "th") Double th,
											 @PathVariable(value = "kt") Double kt,
											 @PathVariable(value = "bt") Double bt,
											 @PathVariable(value = "ck") Double ck) 
	{
        var monHoc = monHocRepository.getById(id);		
		if (monHoc == null)
			return ResponseEntity.ok(null);
		
		monHoc.setTyLeDiemCC(cc);
		monHoc.setTyLeDiemTH(th);
		monHoc.setTyLeDiemKT(kt);
		monHoc.setTyLeDiemBT(bt);
		monHoc.setTyLeDiemCuoiKy(ck);
		monHocRepository.save(monHoc);
		return ResponseEntity.ok(monHoc);
    }

    @DeleteMapping("xoa-theo-id/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable(value = "id") Integer id) {
		try
		{
			monHocRepository.deleteById(id);
		}
		catch (Exception E) 
		{
			return ResponseEntity.ok(false);
		}
        return ResponseEntity.ok(true);
    }
	
	@DeleteMapping("xoa-theo-ten/{name}")
    public ResponseEntity<Boolean> deleteByName(@PathVariable(value = "name") String name) {
		try
		{
			var monHoc = monHocRepository.getMonHocByName(name);
			monHocRepository.delete(monHoc);
		}
		catch (Exception E) 
		{
			return ResponseEntity.ok(false);
		}
        return ResponseEntity.ok(true);
    }
}
