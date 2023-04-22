package sqa.example.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqa.example.model.ThangDiem;
import sqa.example.repository.ThangDiemRepository;

@Service
@RequiredArgsConstructor
public class ThangDiemService {
	private final ThangDiemRepository thangDiemRepository;
	
	public List<ThangDiem> findAll() {
		return thangDiemRepository.findAll();
	}
	
	public ThangDiem findById(Integer id) {
		var result = thangDiemRepository.findById(id);
        return result.isPresent() ? result.get() : null;
    }
	
	public ThangDiem findByName(String name) {
		return thangDiemRepository.findByName(name);
	}
	
	public ThangDiem create(ThangDiem entity) {		
		if (ThangDiemServiceUtils.isInvalid(entity))
			return null;
		
		return thangDiemRepository.save(entity);
	}
	
	public ThangDiem update(ThangDiem entity) {
		if (ThangDiemServiceUtils.isInvalid(entity))
			return null;

		var exist = findById(entity.getId()) != null;
		return exist ? thangDiemRepository.save(entity) : null;
    }
	
	public boolean delete(Integer id) {
		var entity = thangDiemRepository.findById(id).get();
		if (entity == null)
			return false;
		
		thangDiemRepository.delete(entity);
		return true;
	}
	
	public double getPointLimit() {
		var thangDiemD = findByName("D");
		var limit = thangDiemD.getFrom();
		return limit;
	}
}
