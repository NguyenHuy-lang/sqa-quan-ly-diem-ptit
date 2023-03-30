package sqa.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sqa.example.model.*;
import sqa.example.repository.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/thang-diem/")
public class ThangDiemController {
    private final ThangDiemRepository thangDiemRepository;

    @GetMapping("get-all")
    public ResponseEntity<List<ThangDiem>> getAll() {
        return ResponseEntity.ok(thangDiemRepository.findAll());
    }
	
	@GetMapping("get-theo-id/{id}")
    public ResponseEntity<ThangDiem> getById(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(thangDiemRepository.getById(id));
    }	

    @PostMapping(
			  "tao-moi"
			+ "/from/{from}"
			+ "/to/{to}"
			+ "/diem_chu/{dc}"
			+ "/diem_he_4/{d4}")
    public ResponseEntity<ThangDiem> create(@PathVariable(value = "from") Double from,
										    @PathVariable(value = "to") Double to,
                                            @PathVariable(value = "dc") String dc,
                                            @PathVariable(value = "d4") Double d4) 
	{
		var thangDiem = new ThangDiem();
		thangDiem.setFrom(from);
		thangDiem.setTo(to);
		thangDiem.setDiemChu(dc);
		thangDiem.setDiemHe4(d4);
		thangDiemRepository.save(thangDiem);
        
		return ResponseEntity.ok(thangDiem);
    }
	
    @PutMapping(
			  "cap-nhat-theo-id"
			+ "/{id}"
			+ "/from/{from}"
			+ "/to/{to}"
			+ "/diem_chu/{dc}"
			+ "/diem_he_4/{d4}")
	public ResponseEntity<ThangDiem> updateThangDiem(@PathVariable(value = "id") Integer id,
													 @PathVariable(value = "from") Double from,
													 @PathVariable(value = "to") Double to,
													 @PathVariable(value = "dc") String dc,
													 @PathVariable(value = "d4") Double d4)
	{		
		var thangDiem = thangDiemRepository.getById(id);
		if (thangDiem == null)
			return ResponseEntity.ok(null);
		
		thangDiem.setFrom(from);
		thangDiem.setTo(to);
		thangDiem.setDiemChu(dc);
		thangDiem.setDiemHe4(d4);
		thangDiemRepository.save(thangDiem);
   
		return ResponseEntity.ok(thangDiem);
    }

    @DeleteMapping("xoa-theo-id/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable(value = "id") Integer id) {
		try
		{
			thangDiemRepository.deleteById(id);
		}
		catch (Exception E) 
		{
			return ResponseEntity.ok(false);
		}
        return ResponseEntity.ok(true);
    }
}
