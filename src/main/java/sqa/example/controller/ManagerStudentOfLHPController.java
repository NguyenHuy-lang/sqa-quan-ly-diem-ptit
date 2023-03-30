package sqa.example.controller;

import lombok.RequiredArgsConstructor;
import org.apache.naming.TransactionRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sqa.example.model.*;
import sqa.example.repository.*;
import sqa.example.service.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin/")
public class ManagerStudentOfLHPController {
    private final NganhRepository nganhRepository;
    private final NamHocRepository namHocRepository;
    private final NienKhoaNganhRepository nienKhoaNganhRepository;
    private final NienKhoaService nienKhoaService;
    private final NguoiDungRepository nguoiDungRepository;
    private final NamHocService namHocService;
    private final NienKhoaRepository nienKhoaRepository;
    private final NganhService nganhService;
    private final KyHocService kyHocService;
    private final MonHocService monHocService;
    private final SinhVienRepository sinhVienRepository;
    private final NienKhoaNganhNamHocKyHocRepository nienKhoaNganhNamHocKyHocRepository;
    private final NienKhoaNganhNamHocKyHocMonHocRepository nienKhoaNganhNamHocKyHocMonHocRepository;
    private final NamHocKyHocRepository namHocKyHocRepository;
    private final LopHocPhanRepository lopHocPhanRepository;
    private final KetQuaRepository ketQuaRepository;
    private final ThangDiemRepository thangDiemRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerStudentOfLHPController.class);
    @GetMapping("nam-hocs")
    public ResponseEntity<List<NamHoc>> getAllNamhoc() {
        LOGGER.info("GET ALL NAM HOC");
        return ResponseEntity.ok(namHocRepository.findAll());
    }

    @GetMapping("nam-hocs/{nam-hoc-name}/ky-hocs")
    public ResponseEntity<List<NamHocKyHoc>> getAllKyHocOfNamHoc(@PathVariable(value = "nam-hoc-name") String nam_hoc_name) {
        LOGGER.info("GET ALL KY HOC OF NAM HOC");
        Integer nam_hoc_id = namHocService.getIdNamHocByName((nam_hoc_name));
        return ResponseEntity.ok(namHocKyHocRepository.getNamHocKyHoc(nam_hoc_id));
    }
    @GetMapping("nam-hocs/{nam-hoc-name}/ky-hocs/{ky-hocs-name}/nien-khoas")
    public ResponseEntity<List<NienKhoa>> getAllNienKhoa(@PathVariable(value = "nam-hoc-name") String nam_hoc_name ,
                                                         @PathVariable(value = "ky-hocs-name") String ky_hoc_names) {
        Integer ky_hoc_id = kyHocService.getIdKyHocByNameKyHoc(standardized(ky_hoc_names));
        Integer nam_hoc_id = namHocService.getIdNamHocByName((nam_hoc_name));
        NamHocKyHoc namHocKyHoc = namHocKyHocRepository.getNamHocKyHoc(nam_hoc_id, ky_hoc_id);
        Integer nam_hoc_ky_hoc_id = namHocKyHoc.getId();

        List<NienKhoaNganhNamHocKyHoc> nienKhoaNganhNamHocKyHocList =
                nienKhoaNganhNamHocKyHocRepository.
                        getListNienKhoaNganhNamHocKyHoc(nam_hoc_ky_hoc_id);
        Set<NienKhoa> nienKhoaList = new HashSet<>();
        for (NienKhoaNganhNamHocKyHoc nienKhoaNganhNamHocKyHoc
                : nienKhoaNganhNamHocKyHocList) {
            Integer nienKhoaNganhId = nienKhoaNganhNamHocKyHoc.getNienKhoaNganh().getId();
            NienKhoaNganh nienKhoaNganh = nienKhoaNganhRepository.getById(nienKhoaNganhId);
            Integer nienKhoaId = nienKhoaNganh.getNienKhoa().getId();
            NienKhoa nienKhoa = nienKhoaRepository.getById(nienKhoaId);
            nienKhoaList.add(nienKhoa);
        }
        System.out.println(nienKhoaList.stream().toList());
        return ResponseEntity.ok().body(nienKhoaList.stream().toList());
    }
    @GetMapping("nam-hocs/{nam-hoc-name}/ky-hocs/{ky-hocs-name}/nien-khoas/{nien-khoa-name}/nganh")
    public ResponseEntity<List<Nganh>> getAllNganh(@PathVariable(value = "nam-hoc-name") String nam_hoc_name,
                                                   @PathVariable(value = "ky-hocs-name") String ky_hoc_names,
                                                   @PathVariable(value = "nien-khoa-name") String nien_khoa_name) {
        Integer ky_hoc_id = kyHocService.getIdKyHocByNameKyHoc(standardized(ky_hoc_names));
        Integer nam_hoc_id = namHocService.getIdNamHocByName(nam_hoc_name);
        Integer nien_khoa_id = nienKhoaService.getIdNienKhoaByName(standardized(nien_khoa_name));
        NamHocKyHoc namHocKyHoc = namHocKyHocRepository.getNamHocKyHoc(nam_hoc_id, ky_hoc_id);
        Integer nam_hoc_ky_hoc_id = namHocKyHoc.getId();

        List<NienKhoaNganhNamHocKyHoc> nienKhoaNganhNamHocKyHocList =
                nienKhoaNganhNamHocKyHocRepository.
                        getListNienKhoaNganhNamHocKyHoc(nam_hoc_ky_hoc_id);
        List<Nganh> nganhList = new ArrayList<>();
        for (NienKhoaNganhNamHocKyHoc nienKhoaNganhNamHocKyHoc
                : nienKhoaNganhNamHocKyHocList) {
            NienKhoaNganh nienKhoaNganh = nienKhoaNganhNamHocKyHoc.getNienKhoaNganh();
            if (nienKhoaNganh.getNienKhoa().getId() == nien_khoa_id) {
                Nganh nganh = nienKhoaNganh.getNganh();
                nganhList.add(nganh);
            }
        }
        return ResponseEntity.ok().body(nganhList);
    }

    @GetMapping("nam-hocs/{nam-hoc-name}/ky-hocs/{ky-hocs-name}/nien-khoas/{nien-khoa-name}/nganh/{nganh-name}/mon-hocs")
    public ResponseEntity<List<MonHoc>> getListMonHoc(@PathVariable(value = "nam-hoc-name") String nam_hoc_name,
                                                      @PathVariable(value = "ky-hocs-name") String ky_hoc_names,
                                                      @PathVariable(value = "nien-khoa-name") String nien_khoa_name,
                                                      @PathVariable(value = "nganh-name") String nganh_name
    ) {
        Integer ky_hoc_id = kyHocService.getIdKyHocByNameKyHoc(standardized(ky_hoc_names));
        Integer nam_hoc_id = namHocService.getIdNamHocByName(nam_hoc_name);
        Integer nien_khoa_id = nienKhoaService.getIdNienKhoaByName(standardized(nien_khoa_name));
        Integer nganh_id = nganhService.getIdOfNganhByName(standardized(nganh_name));

        NienKhoaNganh nienKhoaNganh = nienKhoaNganhRepository.
                getNienKhoaNganhByNienKhoaAndNganh(nien_khoa_id, nganh_id);

        NamHocKyHoc namHocKyHoc = namHocKyHocRepository.getNamHocKyHoc(nam_hoc_id, ky_hoc_id);
        NienKhoaNganhNamHocKyHoc nienKhoaNganhNamHocKyHoc = nienKhoaNganhNamHocKyHocRepository.
                getNienKhoaNganhNamHocKyHoc(namHocKyHoc.getId(), nienKhoaNganh.getId());
        List<NienKhoaNganhNamHocKyHocMonHoc> nienKhoaNganhNamHocKyHocMonHocList =
                nienKhoaNganhNamHocKyHocMonHocRepository.
                getNienKhoaNganhNamHocKyHocMonHoc(nienKhoaNganhNamHocKyHoc.getId());
        List<MonHoc> monHocList = new ArrayList<>();
        for (NienKhoaNganhNamHocKyHocMonHoc nienKhoaNganhNamHocKyHocMonHoc : nienKhoaNganhNamHocKyHocMonHocList) {
            monHocList.add(nienKhoaNganhNamHocKyHocMonHoc.getMonHoc());
        }
        return ResponseEntity.ok().body(monHocList);
    }
    @GetMapping("nam-hocs/{nam-hoc-name}/ky-hocs/{ky-hocs-name}/nien-khoas/{nien-khoa-name}/nganh/{nganh-name}/mon-hocs/{mon-hoc-name}/lhps")
    ResponseEntity<List<LopHocPhan>> getAllLhpOfMonHoc(@PathVariable(value = "nam-hoc-name") String nam_hoc_name,
                                                       @PathVariable(value = "ky-hocs-name") String ky_hoc_names,
                                                       @PathVariable(value = "nien-khoa-name") String nien_khoa_name,
                                                       @PathVariable(value = "nganh-name") String nganh_name,
                                                       @PathVariable(value = "mon-hoc-name") String mon_hoc_name) {
        Integer ky_hoc_id = kyHocService.getIdKyHocByNameKyHoc(standardized(ky_hoc_names));
        Integer nam_hoc_id = namHocService.getIdNamHocByName(nam_hoc_name);
        Integer nien_khoa_id = nienKhoaService.getIdNienKhoaByName(standardized(nien_khoa_name));
        Integer nganh_id = nganhService.getIdOfNganhByName(standardized(nganh_name));
        Integer mon_hoc_id = monHocService.getIdMonHocByName(standardized(mon_hoc_name));

        NienKhoaNganh nienKhoaNganh = nienKhoaNganhRepository.
                getNienKhoaNganhByNienKhoaAndNganh(nien_khoa_id, nganh_id);

        NamHocKyHoc namHocKyHoc = namHocKyHocRepository.getNamHocKyHoc(nam_hoc_id, ky_hoc_id);
        NienKhoaNganhNamHocKyHoc nienKhoaNganhNamHocKyHoc = nienKhoaNganhNamHocKyHocRepository.
                getNienKhoaNganhNamHocKyHoc(namHocKyHoc.getId(), nienKhoaNganh.getId());
        NienKhoaNganhNamHocKyHocMonHoc nienKhoaNganhNamHocKyHocMonHoc = nienKhoaNganhNamHocKyHocMonHocRepository.
                getNienKhoaNganhNamHocKyHocMonHoc(nienKhoaNganhNamHocKyHoc.getId(), mon_hoc_id);

        List<LopHocPhan> lopHocPhanList = lopHocPhanRepository.getLopHocPhan(nienKhoaNganhNamHocKyHocMonHoc.getId());

        return ResponseEntity.ok().body(lopHocPhanList);
    }
    @GetMapping("nam-hocs/{nam-hoc-name}/ky-hocs/{ky-hocs-name}/nien-khoas/{nien-khoa-name}/nganh/{nganh-name}/mon-hocs/" +
            "{mon-hoc-name}/lhps/{lhp-id}/sinh-viens")
    ResponseEntity<List<SinhVien>> getAllSinhVienOfLhp(@PathVariable(value = "nam-hoc-name") String nam_hoc_name,
                                                       @PathVariable(value = "ky-hocs-name") String ky_hoc_names,
                                                       @PathVariable(value = "nien-khoa-name") String nien_khoa_name,
                                                       @PathVariable(value = "nganh-name") String nganh_name,
                                                       @PathVariable(value = "mon-hoc-name") String mon_hoc_name,
                                                       @PathVariable(value = "lhp-id") Integer lhp_id

    ) {
        LopHocPhan lopHocPhan = lopHocPhanRepository.getById(lhp_id);
        List<KetQua> ketQuaList = lopHocPhan.getListKetQua();
        List<SinhVien> sinhVienList = ketQuaList.stream().map(ketQua -> ketQua.getSinhVien()).collect(Collectors.toList());
        return ResponseEntity.ok().body(sinhVienList);
    }
    @GetMapping("nam-hocs/{nam-hoc-name}/ky-hocs/{ky-hocs-name}/nien-khoas/{nien-khoa-name}/nganh/{nganh-name}/mon-hocs/{mon-hoc-name}/lhps/{lhp-id}/ket-quas")
    ResponseEntity<List<KetQua>> getAllKetQuaSinhVienOfLhp(@PathVariable(value = "nam-hoc-name") String nam_hoc_name,
                                                       @PathVariable(value = "ky-hocs-name") String ky_hoc_names,
                                                       @PathVariable(value = "nien-khoa-name") String nien_khoa_name,
                                                       @PathVariable(value = "nganh-name") String nganh_name,
                                                       @PathVariable(value = "mon-hoc-name") String mon_hoc_name,
                                                       @PathVariable(value = "lhp-id") Integer lhp_id

    ) {
        LopHocPhan lopHocPhan = lopHocPhanRepository.getById(lhp_id);
        List<KetQua> ketQuaList = lopHocPhan.getListKetQua();
        return ResponseEntity.ok().body(ketQuaList);
    }
    @PostMapping("nam-hocs/{nam-hoc-name}/ky-hocs/{ky-hocs-name}/nien-khoas/{nien-khoa-name}/nganh/" +
            "{nganh-name}/mon-hocs/{mon-hoc-name}/lhps/{lhp-id}/sinh-viens")
    ResponseEntity<List<SinhVien>> addSinhVienIntoLhp(@PathVariable(value = "nam-hoc-name") String nam_hoc_name,
                                                @PathVariable(value = "ky-hocs-name") String ky_hoc_names,
                                                @PathVariable(value = "nien-khoa-name") String nien_khoa_name,
                                                @PathVariable(value = "nganh-name") String nganh_name,
                                                @PathVariable(value = "mon-hoc-name") String mon_hoc_name,
                                                @PathVariable(value = "lhp-id") Integer lhp_id,
                                                @RequestBody SinhVien sinhVien) {

        LopHocPhan lopHocPhan = lopHocPhanRepository.getById(lhp_id);
        List<KetQua> ketQuaList = lopHocPhan.getListKetQua();
        Nganh nganh = sinhVien.getNienKhoaNganh().getNganh();
        NienKhoa nienKhoa = sinhVien.getNienKhoaNganh().getNienKhoa();
        NienKhoaNganh nienKhoaNganh = new NienKhoaNganh();
        nienKhoaNganh.setNganh(nganhRepository.save(nganh));

        nienKhoaNganh.setNienKhoa(nienKhoaRepository.save(nienKhoa));
        nienKhoaNganh = nienKhoaNganhRepository.getNienKhoaNganhByNienKhoaIdAndNganhId(nienKhoa.getId(), nganh.getId());
        if (nienKhoaNganh != null) {
            sinhVien.setNienKhoaNganh(nienKhoaNganh);
        } else {
            sinhVien.setNienKhoaNganh(nienKhoaNganhRepository.save(nienKhoaNganh));
        }
        sinhVien.setNguoiDung(nguoiDungRepository.save(sinhVien.getNguoiDung()));


        sinhVien = sinhVienRepository.save(sinhVien);
        KetQua ketQua = new KetQua();
        ketQua.setSinhVien(sinhVien);
        KetQua ketQua1 = ketQuaRepository.save(ketQua);
        ketQuaList.add(ketQua1);
        lopHocPhan.setListKetQua(ketQuaList);
        lopHocPhanRepository.save(lopHocPhan);
        List<SinhVien> sinhVienList = lopHocPhan.getListKetQua().stream().map(kq -> kq.getSinhVien()).collect(Collectors.toList());
        return ResponseEntity.ok().body(sinhVienList);
    }

    @PutMapping("nam-hocs/{nam-hoc-name}/ky-hocs/{ky-hocs-name}/nien-khoas/{nien-khoa-name}/nganh/" +
            "{nganh-name}/mon-hocs/{mon-hoc-name}/lhps/{lhp-id}/sinh-viens/{sinh-vien-id}")
    ResponseEntity<SinhVien> updateSinhVienIntoLhp(@PathVariable(value = "nam-hoc-name") String nam_hoc_name,
                                                      @PathVariable(value = "ky-hocs-name") String ky_hoc_names,
                                                      @PathVariable(value = "nien-khoa-name") String nien_khoa_name,
                                                      @PathVariable(value = "nganh-name") String nganh_name,
                                                      @PathVariable(value = "mon-hoc-name") String mon_hoc_name,
                                                      @PathVariable(value = "lhp-id") Integer lhp_id,
                                                      @PathVariable(value = "sinh-vien-id") Integer sinh_vien_id,
                                                      @RequestBody SinhVien sinhVien) {
        SinhVien sinhVienOdd = sinhVienRepository.getReferenceById(sinh_vien_id);
        sinhVienOdd.setMaSinhVien(sinhVien.getMaSinhVien());
        sinhVienOdd.getNguoiDung().setName(sinhVien.getNguoiDung().getName());
        sinhVienOdd.getNguoiDung().setEmail(sinhVien.getNguoiDung().getEmail());
        sinhVienOdd.getNguoiDung().setGioiTinh(sinhVien.getNguoiDung().getGioiTinh());

        sinhVienOdd.getNguoiDung().setNgaySinh(sinhVien.getNguoiDung().getNgaySinh());
        sinhVienOdd.getNguoiDung().setSoDienThoai(sinhVien.getNguoiDung().getSoDienThoai());
        sinhVienOdd.setNienKhoaNganh(sinhVien.getNienKhoaNganh());
        sinhVienOdd = sinhVienRepository.save(sinhVienOdd);
        return ResponseEntity.ok().body(sinhVienOdd);
    }

    @DeleteMapping("nam-hocs/{nam-hoc-name}/ky-hocs/{ky-hocs-name}/nien-khoas/{nien-khoa-name}/nganh/" +
            "{nganh-name}/mon-hocs/{mon-hoc-name}/lhps/{lhp-id}/sinh-viens/{sinh-vien-id}")
    ResponseEntity<List<SinhVien>> deleteSinhVienIntoLhp(@PathVariable(value = "nam-hoc-name") String nam_hoc_name,
                                                         @PathVariable(value = "ky-hocs-name") String ky_hoc_names,
                                                         @PathVariable(value = "nien-khoa-name") String nien_khoa_name,
                                                         @PathVariable(value = "nganh-name") String nganh_name,
                                                         @PathVariable(value = "mon-hoc-name") String mon_hoc_name,
                                                         @PathVariable(value = "lhp-id") Integer lhp_id,
                                                         @PathVariable(value = "sinh-vien-id") Integer sinh_vien_id) {
        sinhVienRepository.deleteById(sinh_vien_id);
        LopHocPhan lopHocPhan = lopHocPhanRepository.getById(lhp_id);
        List<SinhVien> sinhVienList = lopHocPhan.getListKetQua().stream().map(kq -> kq.getSinhVien()).collect(Collectors.toList());
        return ResponseEntity.ok().body(sinhVienList);
    }
    public String standardized(String input) {
        String[] parts = input.split("-");
        return String.join(" ", parts);
    }
}
