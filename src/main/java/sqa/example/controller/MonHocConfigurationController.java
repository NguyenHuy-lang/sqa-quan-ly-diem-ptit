package sqa.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sqa.example.model.*;

import java.util.*;
import sqa.example.service.MonHocService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cau-hinh/")
public class MonHocConfigurationController {
    private final MonHocService service;

    @GetMapping("/mon-hocs")
    public ResponseEntity<List<MonHoc>> getAll() 
	{
        return ResponseEntity.ok(service.getAll());
    }
	
	@GetMapping("/mon-hocs/{id}")
    public ResponseEntity<MonHoc> get(@PathVariable("id") Integer id) 
	{
        return ResponseEntity.ok(service.get(id));
    }

	@PostMapping("/mon-hocs")
	public ResponseEntity<MonHoc> create(@RequestBody MonHoc monHoc) 
	{
		return ResponseEntity.ok(service.create(monHoc));
	}
	
	@PutMapping("/mon-hocs")
    public ResponseEntity<MonHoc> update(@RequestBody MonHoc monHoc) 
	{
		return ResponseEntity.ok(service.update(monHoc)); 
    }
	
	@DeleteMapping("/mon-hocs/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) 
	{
		return ResponseEntity.ok(service.delete(id)); 
	}
}
