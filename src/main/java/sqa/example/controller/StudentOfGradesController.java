package sqa.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sqa.example.model.*;
import sqa.example.repository.*;
import sqa.example.service.KyHocService;
import sqa.example.service.MonHocService;
import sqa.example.service.NamHocService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/sinh-vien/")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
public class StudentOfGradesController {
    private final NamHocRepository namHocRepository;
    private final NguoiDungRepository nguoiDungRepository;
    private final NamHocService namHocService;
    private final KyHocService kyHocService;
    private final MonHocService monHocService;
    private final SinhVienRepository sinhVienRepository;
    private final NienKhoaNganhNamHocKyHocRepository nienKhoaNganhNamHocKyHocRepository;
    private final NienKhoaNganhNamHocKyHocMonHocRepository nienKhoaNganhNamHocKyHocMonHocRepository;
    private final NamHocKyHocRepository namHocKyHocRepository;
    private final LopHocPhanRepository lopHocPhanRepository;
    private final KetQuaRepository ketQuaRepository;
    private final ThangDiemRepository thangDiemRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentOfGradesController.class);
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
    @GetMapping("nam-hocs/{nam-hoc-name}/ky-hocs/{ky-hoc-name}/mon-hoc")
    public ResponseEntity<List<MonHoc>> getAllMonHocOfKyHoc(@PathVariable("nam-hoc-name") String nam_hoc_name,
                                                            @PathVariable("ky-hoc-name") String ky_hoc_name) {
        Integer nam_hoc_id = namHocService.getIdNamHocByName((nam_hoc_name));
        Integer ky_hoc_id = kyHocService.getIdKyHocByNameKyHoc(standardized(ky_hoc_name));


        List<NienKhoaNganhNamHocKyHocMonHoc> nienKhoaNganhNamHocKyHocMonHocList

                = getNienKhoaNganhNamHocKyHocMonHoc(nam_hoc_id, ky_hoc_id);
        List<MonHoc> monHocList = nienKhoaNganhNamHocKyHocMonHocList.stream().
                map(v -> v.getMonHoc()).collect(Collectors.toList());
        return ResponseEntity.ok().body(monHocList);
    }
    @GetMapping("nam-hocs/{nam-hoc-name}/ky-hocs/{ky-hoc-name}/mon-hoc/{mon-hoc-name}/ket-quas")
    public ResponseEntity<KetQua> getKetQuaOfMonHoc(@PathVariable("nam-hoc-name") String nam_hoc_name,
                                                    @PathVariable("ky-hoc-name") String ky_hoc_name,
                                                    @PathVariable("mon-hoc-name") String mon_hoc_name
    ) {

        Integer nam_hoc_id = namHocService.getIdNamHocByName((nam_hoc_name));
        Integer ky_hoc_id = kyHocService.getIdKyHocByNameKyHoc(standardized(ky_hoc_name));
        Integer mon_hoc_id = monHocService.getIdMonHocByName(standardized(mon_hoc_name));

        Integer id = getUserId();

        SinhVien sinhVien = sinhVienRepository.getById(id);
        NienKhoaNganh nienKhoaNganh = sinhVien.getNienKhoaNganh();
        Integer nien_khoa_nganh_id = nienKhoaNganh.getId();

        NamHocKyHoc namHocKyHoc = namHocKyHocRepository.getNamHocKyHoc(nam_hoc_id, ky_hoc_id);
        Integer nam_hoc_ky_hoc_id = namHocKyHoc.getId();

        NienKhoaNganhNamHocKyHoc nienKhoaNganhNamHocKyHoc =
                nienKhoaNganhNamHocKyHocRepository
                        .getNienKhoaNganhNamHocKyHoc(nam_hoc_ky_hoc_id, nien_khoa_nganh_id);
        Integer nien_khoa_nganh_nam_hoc_ky_hoc_id = nienKhoaNganhNamHocKyHoc.getId();
        NienKhoaNganhNamHocKyHocMonHoc nienKhoaNganhNamHocKyHocMonHoc = nienKhoaNganhNamHocKyHocMonHocRepository
                .getNienKhoaNganhNamHocKyHocMonHoc(nien_khoa_nganh_nam_hoc_ky_hoc_id, mon_hoc_id);
        Integer nien_khoa_nganh_nam_hoc_ky_hoc_mon_hoc_id = nienKhoaNganhNamHocKyHocMonHoc.getId();
        List<LopHocPhan> lopHocPhanList = lopHocPhanRepository.getLopHocPhan(nien_khoa_nganh_nam_hoc_ky_hoc_mon_hoc_id);
        for(LopHocPhan lopHocPhan : lopHocPhanList) {
            System.out.println(lopHocPhan);
            System.out.println(lopHocPhan.getListKetQua());
            KetQua ketQua = ketQuaRepository.getKetQuaOfSinhVien(getUserId(), lopHocPhan.getId());
            MonHoc monHoc = lopHocPhan.getNienKhoaNganhNamHocKyHocMonHoc().getMonHoc();
            setKetQua(ketQua, monHoc);
            if (ketQua != null) {
                ketQua.setNameSinhVien(ketQua.getSinhVien().getNguoiDung().getName());
                ketQua.setMaSinhVien(ketQua.getSinhVien().getMaSinhVien());
                return ResponseEntity.ok().body(ketQua);
            }
        }
        return ResponseEntity.notFound().build();

    }

    public Integer getUserId() {
        return 3;
    }
    public List<NienKhoaNganhNamHocKyHocMonHoc> getNienKhoaNganhNamHocKyHocMonHoc(Integer nam_hoc_id, Integer ky_hoc_id) {
        Integer id = getUserId();
        SinhVien sinhVien = sinhVienRepository.getById(id);
        NienKhoaNganh nienKhoaNganh = sinhVien.getNienKhoaNganh();
        Integer nien_khoa_nganh_id = nienKhoaNganh.getId();

        NamHocKyHoc namHocKyHoc = namHocKyHocRepository.getNamHocKyHoc(nam_hoc_id, ky_hoc_id);
        Integer nam_hoc_ky_hoc_id = namHocKyHoc.getId();

        NienKhoaNganhNamHocKyHoc nienKhoaNganhNamHocKyHoc =
                nienKhoaNganhNamHocKyHocRepository
                        .getNienKhoaNganhNamHocKyHoc(nam_hoc_ky_hoc_id, nien_khoa_nganh_id);
        Integer nien_khoa_nganh_nam_hoc_ky_hoc_id = nienKhoaNganhNamHocKyHoc.getId();
        List<NienKhoaNganhNamHocKyHocMonHoc> nienKhoaNganhNamHocKyHocMonHocList = nienKhoaNganhNamHocKyHocMonHocRepository
                .getNienKhoaNganhNamHocKyHocMonHoc(nien_khoa_nganh_nam_hoc_ky_hoc_id);
        return nienKhoaNganhNamHocKyHocMonHocList;
    }

    public void setKetQua(KetQua ketQua, MonHoc monHoc) {
        double diemHe10 =   ketQua.getDiemCC() * monHoc.getTyLeDiemCC() +
                            ketQua.getDiemBT() * monHoc.getTyLeDiemBT() +
                            ketQua.getDiemKT() * monHoc.getTyLeDiemKT() +
                            ketQua.getDiemTH() * monHoc.getTyLeDiemTH() +
                            ketQua.getDiemCuoiKy() * monHoc.getTyLeDiemCuoiKy();
        ketQua.setDiemHe10(diemHe10);
        List<ThangDiem> thangDiemList = thangDiemRepository.findAll();

        for (ThangDiem thangDiem : thangDiemList) {
            Double from = thangDiem.getFrom();
            Double to  = thangDiem.getTo();
            if (ketQua.getDiemHe10() >= from && ketQua.getDiemHe10() <= to) {
                ketQua.setDiemHe4(thangDiem.getDiemHe4());
                ketQua.setDiemChu(thangDiem.getDiemChu());
                break;
            }
        }
    }

    public String standardized(String input) {
        String[] parts = input.split("-");
        return String.join(" ", parts);
    }

}
