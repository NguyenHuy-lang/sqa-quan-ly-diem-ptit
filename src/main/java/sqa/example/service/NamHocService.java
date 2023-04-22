package sqa.example.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqa.example.model.NamHoc;
import sqa.example.repository.NamHocRepository;

@Service
@RequiredArgsConstructor
public class NamHocService {
    private final NamHocRepository namHocRepository;
	
    public NamHoc findById(Integer id) {
        return namHocRepository.findById(id).get();
    }
	
	public List<NamHoc> findAll() {
		return namHocRepository.findAll();
	}
}
