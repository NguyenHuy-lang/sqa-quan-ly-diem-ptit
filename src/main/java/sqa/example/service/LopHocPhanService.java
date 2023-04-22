package sqa.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqa.example.model.LopHocPhan;
import sqa.example.repository.LopHocPhanRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LopHocPhanService {
    private final LopHocPhanRepository lopHocPhanRepository;
    
	public LopHocPhan findById(Integer id){
		return lopHocPhanRepository.findById(id).get();
	}
	
	public List<LopHocPhan> findAllByNienKhoaNganhNamHocKyHocMonHocId(Integer nien_khoa_nganh_nam_hoc_ky_hoc_mon_hoc_id) {
        return lopHocPhanRepository.findAllByNienKhoaNganhNamHocKyHocMonHocId(nien_khoa_nganh_nam_hoc_ky_hoc_mon_hoc_id);
	}
}
