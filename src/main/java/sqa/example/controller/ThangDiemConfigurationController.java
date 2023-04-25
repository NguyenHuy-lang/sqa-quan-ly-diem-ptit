package sqa.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sqa.example.model.*;

import java.util.*;
import org.springframework.http.HttpStatus;
import sqa.example.service.ThangDiemService;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cau-hinh-thang-diem")
@CrossOrigin
public class ThangDiemConfigurationController {
    @Autowired
    HttpSession session;
    private final ThangDiemService thangDiemService;

    @GetMapping("/thang-diems")
    public ResponseEntity<List<ThangDiem>> findAll() {
        if(!checkRole()) {
            return ResponseEntity.notFound().build();
        }
        var result = thangDiemService.findAll();
        return new ResponseEntity(result, result != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @GetMapping("/thang-diems/{id}")
    public ResponseEntity<ThangDiem> findById(@PathVariable("id") Integer id) {
        if(!checkRole()) {
            return ResponseEntity.notFound().build();
        }
        var result = thangDiemService.findById(id);
        return new ResponseEntity(result, result != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @PutMapping("/thang-diems")
    public ResponseEntity<ThangDiem> update(@RequestBody ThangDiem thangDiem) {
        if(!checkRole()) {
            return ResponseEntity.notFound().build();
        }
        var result = thangDiemService.update(thangDiem);
        return new ResponseEntity(result, result != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
    public boolean checkRole() {
        GiaoVien giaoVien = (GiaoVien) session.getAttribute("GiaoVien");
        if(giaoVien == null) return true;
        return true;
    }
}