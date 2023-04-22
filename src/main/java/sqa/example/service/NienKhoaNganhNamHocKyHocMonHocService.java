package sqa.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqa.example.model.NienKhoaNganhNamHocKyHocMonHoc;
import sqa.example.repository.NienKhoaNganhNamHocKyHocMonHocRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class NienKhoaNganhNamHocKyHocMonHocService {
    private final NienKhoaNganhNamHocKyHocMonHocRepository nienKhoaNganhNamHocKyHocMonHocRepository;

    public NienKhoaNganhNamHocKyHocMonHoc findByNienKhoaNganhNamHocKyHocAndMonHocId(Integer nien_khoa_nganh_nam_hoc_ky_hoc_id, Integer mon_hoc_id){
        return nienKhoaNganhNamHocKyHocMonHocRepository.findByNienKhoaNganhNamHocKyHocAndMonHocId(nien_khoa_nganh_nam_hoc_ky_hoc_id, mon_hoc_id);
    }
	
    public List<NienKhoaNganhNamHocKyHocMonHoc> findAllByNienKhoaNganhNamHocKyHocId(Integer nien_khoa_nganh_nam_hoc_ky_hoc_id) {
        return nienKhoaNganhNamHocKyHocMonHocRepository.findAllByNienKhoaNganhNamHocKyHocId(nien_khoa_nganh_nam_hoc_ky_hoc_id);
    }
}
