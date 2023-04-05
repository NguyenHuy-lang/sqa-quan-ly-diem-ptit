package sqa.example.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqa.example.model.KyHoc;
import sqa.example.repository.KyHocRepository;

@Service
@RequiredArgsConstructor
public class KyHocService 
{
    private final KyHocRepository kyHocRepository;
    
	public Integer getIdKyHocByNameKyHoc(String name) {
        KyHoc kyHoc = kyHocRepository.getKyHocByName(name);
        return kyHoc.getId();
    }
	
	public List<KyHoc> getAll() 
	{
		return kyHocRepository.findAll();
	}
	
	public KyHoc getByName(String name) 
	{
		return kyHocRepository.getKyHocByName(name);
	}
}
