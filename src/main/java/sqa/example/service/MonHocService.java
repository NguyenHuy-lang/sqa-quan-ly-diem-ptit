package sqa.example.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqa.example.model.MonHoc;
import sqa.example.repository.MonHocRepository;

@Service
@RequiredArgsConstructor
public class MonHocService {
    private final MonHocRepository entityRepository;
	
	public List<MonHoc> findAll() {
		return entityRepository.findAll();
	}
	
	public MonHoc findById(Integer id) {
		var result = entityRepository.findById(id);
        return result.isPresent() ? result.get() : null;
    }
	
	public MonHoc create(MonHoc entity) {
		if (MonHocServiceUtils.isInvalid(entity))
			return null;
		return entityRepository.save(entity);
	}
	
	public MonHoc update(MonHoc entity) 
	{
		if (MonHocServiceUtils.isInvalid(entity))
			return null;
		
		if (entityRepository.findById(entity.getId()) != null)
			return entityRepository.save(entity);
		return null;
    }
	
	public boolean delete(Integer id) {
		var entity = entityRepository.findById(id).get();
		if (entity == null)
			return false;
		
		entityRepository.delete(entity);
		return true;
	}
}
