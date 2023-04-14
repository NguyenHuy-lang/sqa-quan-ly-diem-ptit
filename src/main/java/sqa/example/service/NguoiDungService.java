package sqa.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqa.example.model.NguoiDung;
import sqa.example.repository.NguoiDungRepository;

@Service
@RequiredArgsConstructor
public class NguoiDungService {
	private final NguoiDungRepository nguoiDungRepository;

	public NguoiDung get(Integer id) 
	{
        return nguoiDungRepository.findById(id).get();
    }
}
