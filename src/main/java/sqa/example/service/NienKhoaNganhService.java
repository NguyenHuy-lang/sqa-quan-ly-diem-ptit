package sqa.example.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqa.example.model.NienKhoaNganh;
import sqa.example.repository.NienKhoaNganhRepository;

@Service
@RequiredArgsConstructor
public class NienKhoaNganhService {
    private final NienKhoaNganhRepository nienKhoaNganhRepository;
	
	public List<NienKhoaNganh> findAllByNganhId(Integer nganhId) {
		return nienKhoaNganhRepository.findAllByNganhId(nganhId);
	}
	
	public NienKhoaNganh findByNienKhoaAndNganhId(Integer nienKhoaID, Integer nganhId) {
		return nienKhoaNganhRepository.findByNienKhoaAndNganhId(nienKhoaID, nganhId);
	}
}
