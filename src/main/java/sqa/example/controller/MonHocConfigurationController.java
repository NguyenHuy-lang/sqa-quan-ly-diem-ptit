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

    @GetMapping("/mon-hocs")
    public ResponseEntity<List<MonHoc>> all() 
	{
        return ResponseEntity.ok(monHocRepository.findAll());
    }
	
	@GetMapping("/mon-hocs/{id}")
    public ResponseEntity<MonHoc> one(@PathVariable("id") Integer id) 
	{
        return ResponseEntity.ok(monHocRepository.findById(id).get());
    }

	@PostMapping("/mon-hocs")
	public ResponseEntity<MonHoc> create(@RequestBody MonHoc monHoc) 
	{
		if (monHocRepository.getMonHocByName(monHoc.getTen()) != null)
			return ResponseEntity.ok(null);
		
		if (isInvalid(monHoc))
			return ResponseEntity.ok(null);
	
		return ResponseEntity.ok(monHocRepository.save(monHoc));
	}
	
	@PutMapping("/mon-hocs")
    public ResponseEntity<MonHoc> update(@RequestBody MonHoc monHoc) 
	{
		if (isInvalid(monHoc))
			return ResponseEntity.ok(null);
		
		return monHocRepository.findById(monHoc.getId()).map(mh -> {
			return ResponseEntity.ok(monHocRepository.save(monHoc));
		})
		.orElseGet(() -> {
			return ResponseEntity.ok(null);
		});
    }
	
	@DeleteMapping("/mon-hocs/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) 
	{
		var monHoc = monHocRepository.findById(id).get();
		if (monHoc == null)
			return ResponseEntity.ok(false);
		
		monHocRepository.delete(monHoc);
		return ResponseEntity.ok(true);
	}
	
	private boolean isInvalid(MonHoc monHoc)
	{
		boolean isInvalid = false;
		isInvalid |= monHoc.getTyLeDiemCC() < 0 || monHoc.getTyLeDiemTH() < 0 
				  || monHoc.getTyLeDiemKT() < 0 || monHoc.getTyLeDiemBT() < 0 || monHoc.getTyLeDiemCuoiKy() < 0;
		isInvalid |= monHoc.getTyLeDiemCC() > 1 || monHoc.getTyLeDiemTH() > 1 
				  || monHoc.getTyLeDiemKT() > 1 || monHoc.getTyLeDiemBT() > 1 || monHoc.getTyLeDiemCuoiKy() > 1;
		isInvalid |= monHoc.getTyLeDiemCC() + monHoc.getTyLeDiemTH() + monHoc.getTyLeDiemKT() + monHoc.getTyLeDiemBT() + monHoc.getTyLeDiemCuoiKy() != 1;
		return isInvalid;
	}
}
