package sqa.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqa.example.model.NamHocKyHoc;
import sqa.example.repository.NamHocKyHocRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NamHocKyHocService {
    private final NamHocKyHocRepository namHocKyHocRepository;
    
	public NamHocKyHoc findByNamHocAndKyHocId(Integer nam_hoc_id, Integer ky_hoc_id){
        return namHocKyHocRepository.findByNamHocAndKyHocId(nam_hoc_id, ky_hoc_id);
    }
	
    public List<NamHocKyHoc> findAllByNamHocId(Integer nam_hoc_id){
        return namHocKyHocRepository.findAllByNamHocId(nam_hoc_id);
    }
}
