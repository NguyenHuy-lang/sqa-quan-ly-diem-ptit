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

    @GetMapping("/thang-diems")
    public ResponseEntity<List<ThangDiem>> all() 
	{
        return ResponseEntity.ok(thangDiemRepository.findAll());
    }
	
	@GetMapping("/thang-diems/{id}")
    public ResponseEntity<ThangDiem> one(@PathVariable("id") Integer id) 
	{
        return ResponseEntity.ok(thangDiemRepository.findById(id).get());
    }

	@PostMapping("/thang-diems")
	public ResponseEntity<ThangDiem> create(@RequestBody ThangDiem thangDiem) 
	{
		if (thangDiemRepository.getThangDiemByName(thangDiem.getDiemChu()) != null)
			return ResponseEntity.ok(null);
		
		return ResponseEntity.ok(thangDiemRepository.save(thangDiem));
	}
	
	@PutMapping("/thang-diems")
    public ResponseEntity<ThangDiem> update(@RequestBody ThangDiem thangDiem) 
	{
		return thangDiemRepository.findById(thangDiem.getId()).map(mh -> {
			return ResponseEntity.ok(thangDiemRepository.save(thangDiem));
		})
		.orElseGet(() -> {
			return ResponseEntity.ok(null);
		});
    }
	
	@DeleteMapping("/thang-diems/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) 
	{
		var thangDiem = thangDiemRepository.findById(id).get();
		if (thangDiem == null)
			return ResponseEntity.ok(false);
		
		thangDiemRepository.delete(thangDiem);
		return ResponseEntity.ok(true);
	}
}
