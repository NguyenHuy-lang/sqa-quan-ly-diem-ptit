package sqa.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import sqa.example.model.NienKhoaNganhNamHocKyHoc;
import sqa.example.repository.NienKhoaNganhNamHocKyHocRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NienKhoaNganhNamHocKyHocService {
    private final NienKhoaNganhNamHocKyHocRepository nienKhoaNganhNamHocKyHocRepository;

    NienKhoaNganhNamHocKyHoc getNienKhoaNganhNamHocKyHoc(Integer nam_hoc_ky_hoc_id,
                                                         Integer nien_khoa_nganh_id
    ){
        return nienKhoaNganhNamHocKyHocRepository.getNienKhoaNganhNamHocKyHoc(nam_hoc_ky_hoc_id, nien_khoa_nganh_id);
    }
    public List<NienKhoaNganhNamHocKyHoc> findAllByNienKhoaNganhId(Integer nienKhoaNganhId) {
        return nienKhoaNganhNamHocKyHocRepository.findAllByNienKhoaNganhId(nienKhoaNganhId);
    }
}
