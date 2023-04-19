package sqa.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sqa.example.model.*;

import java.util.*;
import sqa.example.service.MonHocService;
import sqa.example.service.NganhService;
import sqa.example.service.NienKhoaNganhNamHocKyHocMonHocService;
import sqa.example.service.NienKhoaNganhNamHocKyHocService;
import sqa.example.service.NienKhoaNganhService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cau-hinh-mon-hoc/")
public class MonHocConfigurationController {
	private final NganhService nganhService;
	private final NienKhoaNganhService nienKhoaNganhService;
	private final NienKhoaNganhNamHocKyHocService nienKhoaNganhNamHocKyHocService;
	private final NienKhoaNganhNamHocKyHocMonHocService nienKhoaNganhNamHocKyHocMonHocService;
    private final MonHocService monHocService;

	@GetMapping("/nganhs")
    public ResponseEntity<List<Nganh>> getAllNganh() {
        return ResponseEntity.ok(nganhService.findAll());
    }

    @GetMapping("/nganhs/{nganhId}/mon-hocs")
    public ResponseEntity<List<MonHoc>> getAllNganhMonHoc(
			@PathVariable("nganhId") Integer nganhId) {
		var listNienKhoaNganh = nienKhoaNganhService.findAllByNganhId(nganhId);
	
		HashSet<NienKhoaNganhNamHocKyHoc> setNienKhoaNganhNamHocKyHoc = new HashSet<>();
		for (var nienKhoaNganh : listNienKhoaNganh) {
			var list = nienKhoaNganhNamHocKyHocService.findAllByNienKhoaNganhId(nienKhoaNganh.getId());
			for (var listElement : list)
				setNienKhoaNganhNamHocKyHoc.add(listElement);
		}
		
		HashSet<MonHoc> setMonHoc = new HashSet<>();
		for (var nienKhoaNganhNamHocKyHoc : setNienKhoaNganhNamHocKyHoc) {
			var list = nienKhoaNganhNamHocKyHocMonHocService.findAllByNienKhoaNganhNamHocKyHocId(nienKhoaNganhNamHocKyHoc.getId());
			for (var listElement : list)
				setMonHoc.add(listElement.getMonHoc());
		}
		
        return ResponseEntity.ok(setMonHoc.stream().toList());
    }
	
	@GetMapping("/nganhs/{nganhId}/mon-hocs/{monHocId}")
    public ResponseEntity<MonHoc> get(
			@PathVariable("nganhId") Integer nganhId,
			@PathVariable("monHocId") Integer monHocId) {
        return ResponseEntity.ok(monHocService.findById(monHocId));
    }

	@PutMapping("/nganhs/{nganhId}/mon-hocs")
    public ResponseEntity<MonHoc> update(@RequestBody MonHoc monHoc) {
		return ResponseEntity.ok(monHocService.update(monHoc)); 
    }
}
