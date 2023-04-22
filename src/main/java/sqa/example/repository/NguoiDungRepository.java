package sqa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sqa.example.model.NguoiDung;

import javax.websocket.server.PathParam;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    @Query(value = "SELECT * FROM tbl_nguoi_dung nd WHERE nd.email = :email and nd.so_dien_thoai = :soDienThoai", nativeQuery = true)
    NguoiDung checkLogin(@PathParam("email") String email, @Param("soDienThoai") String soDienThoai);

}
