package sqa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sqa.example.model.ThangDiem;

@Repository
public interface ThangDiemRepository extends JpaRepository<ThangDiem, Integer> {
}
