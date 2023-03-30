package sqa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sqa.example.model.NguoiDung;
@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
}
