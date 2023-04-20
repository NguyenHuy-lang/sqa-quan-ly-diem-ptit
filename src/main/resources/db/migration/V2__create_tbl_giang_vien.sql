CREATE TABLE tbl_giao_vien(
    ma_giao_vien varchar(20) NOT NULL,
    nguoi_dung_id INT NOT NULL,
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    CONSTRAINT fk_giao_vien_nguoi_dung foreign key (nguoi_dung_id)
    REFERENCES tbl_nguoi_dung(id) ON DELETE CASCADE ON UPDATE CASCADE
)