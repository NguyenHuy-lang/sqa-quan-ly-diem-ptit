CREATE TABLE tbl_sinh_vien(
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    ma_sinh_vien varchar(150) NOT NULL,
    nien_khoa_nganh_id INT,
    nguoi_dung_id INT NOT NULL,
    CONSTRAINT fk_sinh_vien_nguoi_dung FOREIGN KEY(nguoi_dung_id)
    REFERENCES tbl_nguoi_dung(id) ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT fk_sinh_vien_nien_khoa_nganh FOREIGN KEY(nien_khoa_nganh_id)
    REFERENCES tbl_nien_khoa_nganh(id) ON DELETE CASCADE ON UPDATE CASCADE

);