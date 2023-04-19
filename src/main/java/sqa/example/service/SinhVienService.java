package sqa.example.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqa.example.model.ThangDiem;
import sqa.example.repository.ThangDiemRepository;

@Service
@RequiredArgsConstructor
public class ThangDiemService 
{
	private final ThangDiemRepository thangDiemRepository;
	
	public List<ThangDiem> findAll() {
		return thangDiemRepository.findAll();
	}
	
	public ThangDiem findById(Integer id) {
        return thangDiemRepository.findById(id).get();
    }
	
	public ThangDiem findByName(String name) {
		return thangDiemRepository.findByName(name);
	}
	
	public ThangDiem create(ThangDiem entity) {		
		return thangDiemRepository.save(entity);
	}
	
	public ThangDiem update(ThangDiem thangDiem) {
		return thangDiemRepository.findById(thangDiem.getId()).map(mh -> thangDiemRepository.save(thangDiem)).orElseGet(() -> null);
    }
	
	public boolean delete(Integer id) {
		var entity = thangDiemRepository.findById(id).get();
		if (entity == null)
			return false;
		
		thangDiemRepository.delete(entity);
		return true;
	}
}
