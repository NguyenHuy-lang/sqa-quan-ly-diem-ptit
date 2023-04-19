package sqa.example.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqa.example.model.NienKhoaNganhNamHocKyHoc;
import sqa.example.repository.NienKhoaNganhNamHocKyHocRepository;

@Service
@RequiredArgsConstructor
public class NienKhoaNganhNamHocKyHocService {
    private final NienKhoaNganhNamHocKyHocRepository nienKhoaNganhNamHocKyHocRepository;

	public List<NienKhoaNganhNamHocKyHoc> findAllByNamHocKyHocId(Integer namHocKyHocId) {
		return nienKhoaNganhNamHocKyHocRepository.findAllByNamHocKyHocId(namHocKyHocId);
	}
	
	public List<NienKhoaNganhNamHocKyHoc> findAllByNienKhoaNganhId(Integer nienKhoaNganhId) {
		return nienKhoaNganhNamHocKyHocRepository.findAllByNienKhoaNganhId(nienKhoaNganhId);
	}
	
	public NienKhoaNganhNamHocKyHoc findByNienKhoaNganhAndNamHocKyHocId(Integer nienKhoaNganhId, Integer namHocKyHocId) {
		return nienKhoaNganhNamHocKyHocRepository.findByNienKhoaNganhAndNamHocKyHocId(nienKhoaNganhId, namHocKyHocId);
	}

}
