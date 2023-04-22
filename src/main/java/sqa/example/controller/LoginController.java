package sqa.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sqa.example.model.GiaoVien;
import sqa.example.model.NguoiDung;
import sqa.example.model.SinhVien;
import sqa.example.repository.GiaoVienRepository;
import sqa.example.repository.NguoiDungRepository;
import sqa.example.repository.SinhVienRepository;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/v1/login")
@CrossOrigin
public class LoginController {
    @Autowired
    HttpSession session;
    @Autowired
    private NguoiDungRepository nguoiDungRepository;
    @Autowired
    private SinhVienRepository sinhVienRepository;
    @Autowired
    private GiaoVienRepository giaoVienRepository;
    @PostMapping
    ResponseEntity<Object> checkLogin(@RequestBody NguoiDung nguoiDung) {
        nguoiDung = nguoiDungRepository.checkLogin(nguoiDung.getEmail(), nguoiDung.getSoDienThoai());
        SinhVien sinhVien = sinhVienRepository.getSinhVienByNguoiDungId(nguoiDung.getId());
        GiaoVien giaoVien = giaoVienRepository.getGiaoVienByNguoiDungId(nguoiDung.getId());

        if (sinhVien != null) {
            session.setAttribute("sinhVien", sinhVien);
            return ResponseEntity.ok(sinhVien);
        } else if(giaoVien != null) {
            session.setAttribute("giaoVien", giaoVien);
            return ResponseEntity.ok(giaoVien);
        }
        return ResponseEntity.notFound().build();
    }

}
