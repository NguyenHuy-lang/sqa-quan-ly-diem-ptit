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
	
    public Integer getIdNamHocByName(String name) {
        return namHocRepository.getIdNamHocByName(name).getId();
    }
	
	public List<NamHoc> getAll() {
		return namHocRepository.findAll();
	}
}
