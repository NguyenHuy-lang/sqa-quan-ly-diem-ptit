package sqa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sqa.example.model.NienKhoa;

@Repository
public interface NienKhoaRepository extends JpaRepository<NienKhoa, Integer> {}
