package sqa.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqa.example.model.MonHoc;
import sqa.example.repository.MonHocRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonHocService {
    private final MonHocRepository entityRepository;

    private final MonHocRepository monHocRepository;
    public Integer getIdMonHocByName(String name) {
        return monHocRepository.getMonHocByName(name).getId();
    }
    public List<MonHoc> findAll() {
        return entityRepository.findAll();
    }

    public MonHoc findById(Integer id) {
        var result = entityRepository.findById(id);
        return result.isPresent() ? result.get() : null;
    }

    public MonHoc create(MonHoc entity) {
        if (isInvalid(entity))
            return null;
        return entityRepository.save(entity);
    }

    public MonHoc update(MonHoc entity)
    {
        if (isInvalid(entity))
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

    private boolean isInvalid(MonHoc entity) {
        boolean isInvalid = false;
        isInvalid |= entity.getTyLeDiemCC() < 0 || entity.getTyLeDiemTH() < 0
                || entity.getTyLeDiemKT() < 0 || entity.getTyLeDiemBT() < 0 || entity.getTyLeDiemCuoiKy() < 0;
        isInvalid |= entity.getTyLeDiemCC() > 1 || entity.getTyLeDiemTH() > 1
                || entity.getTyLeDiemKT() > 1 || entity.getTyLeDiemBT() > 1 || entity.getTyLeDiemCuoiKy() > 1;
        isInvalid |= entity.getTyLeDiemCC() + entity.getTyLeDiemTH() + entity.getTyLeDiemKT() + entity.getTyLeDiemBT() + entity.getTyLeDiemCuoiKy() != 1;
        return isInvalid;
    }
}
