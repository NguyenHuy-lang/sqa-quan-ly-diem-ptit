package sqa.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import sqa.example.model.LopHocPhan;
import sqa.example.repository.LopHocPhanRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LopHocPhanService {
    private final LopHocPhanRepository lopHocPhanRepository;
    public List<LopHocPhan> getLopHocPhan(Integer nien_khoa_nganh_nam_hoc_ky_hoc_mon_hoc_id) {
        return lopHocPhanRepository.getLopHocPhan(nien_khoa_nganh_nam_hoc_ky_hoc_mon_hoc_id);
    }
}
