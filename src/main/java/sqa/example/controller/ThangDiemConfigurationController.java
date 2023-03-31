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
public class ThangDiemConfigurationController {
    private final ThangDiemRepository thangDiemRepository;

	// http://localhost:8080/api/v1/cau-hinh/thang-diems
    @GetMapping("thang-diems")
    public ResponseEntity<List<ThangDiem>> getAll() 
	{
        return ResponseEntity.ok(thangDiemRepository.findAll());
    }
	
	// http://localhost:8080/api/v1/cau-hinh/thang-diems/A+
	@GetMapping("thang-diems/{thang-diem-name}")
    public ResponseEntity<ThangDiem> getByName(@PathVariable(value = "thang-diem-name") String thang_diem_name) 
	{
        return ResponseEntity.ok(thangDiemRepository.getThangDiemByName(thang_diem_name));
    }
	
	// http://localhost:8080/api/v1/cau-hinh/thang-diems/G/them-moi?from=0.4&to=0.2&diemHe4=0.222
	@PostMapping("thang-diems/{thang-diem-name}/them-moi")
	public ResponseEntity<ThangDiem> create(@PathVariable(value = "thang-diem-name") String thang_diem_name,
											@RequestParam(value = "from", required = true) Double from,
											@RequestParam(value = "to", required = true) Double to,
											@RequestParam(value = "diemHe4", required = true) Double diem_he_4) 
	{
		ThangDiem thangDiem = new ThangDiem();
		if (thangDiemRepository.getThangDiemByName(thang_diem_name) != null)
			return ResponseEntity.ok(null);
		
		thangDiem.setDiemChu(thang_diem_name);
		thangDiem.setFrom(from);
		thangDiem.setTo(to);
		thangDiem.setDiemHe4(diem_he_4);
		
		return ResponseEntity.ok(thangDiemRepository.save(thangDiem));
	}	
	
	// http://localhost:8080/api/v1/cau-hinh/thang-diems/B+/chinh-sua?from=0.0&to=3.9&diemHe4=0.1
	@PutMapping("thang-diems/{thang-diem-name}/chinh-sua")
    public ResponseEntity<ThangDiem> update(@PathVariable(value = "thang-diem-name") String thang_diem_name,
											@RequestParam(value = "from", required = true) Double from,
											@RequestParam(value = "to", required = true) Double to,
											@RequestParam(value = "diemHe4", required = true) Double diem_he_4) 
	{
        var thangDiem = thangDiemRepository.getThangDiemByName(thang_diem_name);	
		if (thangDiem == null)
			return ResponseEntity.ok(null);
		
		thangDiem.setFrom(from);
		thangDiem.setTo(to);
		thangDiem.setDiemHe4(diem_he_4);
		thangDiemRepository.save(thangDiem);
		return ResponseEntity.ok(thangDiem);
    }
	
	// http://localhost:8080/api/v1/cau-hinh/thang-diems/G/xoa
	@DeleteMapping("thang-diems/{thang-diem-name}/xoa")
	public ResponseEntity<Boolean> delete(@PathVariable(value = "thang-diem-name") String thang_diem_name) 
	{
		var thangDiem = thangDiemRepository.getThangDiemByName(thang_diem_name);
		if (thangDiem == null)
			return ResponseEntity.ok(false);
		
		thangDiemRepository.delete(thangDiem);
		return ResponseEntity.ok(true);
	}
}
