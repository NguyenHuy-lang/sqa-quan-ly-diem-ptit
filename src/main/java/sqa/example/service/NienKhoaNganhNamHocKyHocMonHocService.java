package sqa.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import sqa.example.model.NienKhoaNganhNamHocKyHocMonHoc;
import sqa.example.repository.NienKhoaNganhNamHocKyHocMonHocRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class NienKhoaNganhNamHocKyHocMonHocService {
    private final NienKhoaNganhNamHocKyHocMonHocRepository nienKhoaNganhNamHocKyHocMonHocRepository;

    List<NienKhoaNganhNamHocKyHocMonHoc> getNienKhoaNganhNamHocKyHocMonHoc
            (Integer nien_khoa_nganh_nam_hoc_ky_hoc_id){
        return nienKhoaNganhNamHocKyHocMonHocRepository.getNienKhoaNganhNamHocKyHocMonHoc(nien_khoa_nganh_nam_hoc_ky_hoc_id);
    }


    NienKhoaNganhNamHocKyHocMonHoc getNienKhoaNganhNamHocKyHocMonHoc
            (Integer nien_khoa_nganh_nam_hoc_ky_hoc_id,
             Integer mon_hoc_id){
        return nienKhoaNganhNamHocKyHocMonHocRepository.getNienKhoaNganhNamHocKyHocMonHoc(nien_khoa_nganh_nam_hoc_ky_hoc_id, mon_hoc_id);
    }
}
