package sqa.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sqa.example.model.*;

import java.util.*;
import org.springframework.http.HttpStatus;
import sqa.example.service.MonHocService;
import sqa.example.service.NganhService;
import sqa.example.service.NienKhoaNganhNamHocKyHocMonHocService;
import sqa.example.service.NienKhoaNganhNamHocKyHocService;
import sqa.example.service.NienKhoaNganhService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cau-hinh-mon-hoc")
public class MonHocConfigurationController {
	private final NganhService nganhService;
	private final NienKhoaNganhService nienKhoaNganhService;
	private final NienKhoaNganhNamHocKyHocService nienKhoaNganhNamHocKyHocService;
	private final NienKhoaNganhNamHocKyHocMonHocService nienKhoaNganhNamHocKyHocMonHocService;
    private final MonHocService monHocService;

	@GetMapping("/nganhs")
    public ResponseEntity<List<Nganh>> getAllNganh() {
		var result = nganhService.findAll();
		return new ResponseEntity(result, result != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @GetMapping("/nganhs/{nganhId}/mon-hocs")
    public ResponseEntity<List<MonHoc>> getAllMonHocByNganh(
			@PathVariable("nganhId") Integer nganhId) {
		var listNienKhoaNganh = nienKhoaNganhService.findAllByNganhId(nganhId);
		if (listNienKhoaNganh == null)
			return ResponseEntity.noContent().build();
		
		HashSet<NienKhoaNganhNamHocKyHoc> setNienKhoaNganhNamHocKyHoc = new HashSet<>();
		for (var nienKhoaNganh : listNienKhoaNganh) {
			var list = nienKhoaNganhNamHocKyHocService.findAllByNienKhoaNganhId(nienKhoaNganh.getId());
			setNienKhoaNganhNamHocKyHoc.addAll(list);
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
    public ResponseEntity<MonHoc> getMonHoc(
			@PathVariable("nganhId") Integer nganhId,
			@PathVariable("monHocId") Integer monHocId) {
		var result = monHocService.findById(monHocId);
		return new ResponseEntity(result, result != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

	@PutMapping("/nganhs/{nganhId}/mon-hocs")
    public ResponseEntity<MonHoc> updateMonHoc(@RequestBody MonHoc monHoc) {
		var result = monHocService.update(monHoc);
		return new ResponseEntity(result, result != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
