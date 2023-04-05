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

	public List<NienKhoaNganhNamHocKyHoc> getListNienKhoaNganhNamHocKyHoc(Integer namHocKyHocId) {
		return nienKhoaNganhNamHocKyHocRepository.getListNienKhoaNganhNamHocKyHoc(namHocKyHocId);
	}
	
	public NienKhoaNganhNamHocKyHoc getNienKhoaNganhNamHocKyHoc(Integer namHocKyHocId, Integer nienKhoaNganhId) {
		return nienKhoaNganhNamHocKyHocRepository.getNienKhoaNganhNamHocKyHoc(namHocKyHocId, nienKhoaNganhId);
	}

}
