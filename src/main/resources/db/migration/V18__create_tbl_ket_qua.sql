CREATE TABLE tbl_ket_qua(
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    diem_CC DECIMAL(10, 5),
    diem_TH DECIMAL(10, 5),
    diem_KT DECIMAL(10, 5),
    diem_BT DECIMAL(10, 5),
    diem_cuoi_ky DECIMAL(10, 5),
    sinh_vien_id INT,
    lop_hoc_phan_id INT,

    CONSTRAINT fk_ket_qua_sinh_vien FOREIGN KEY(sinh_vien_id)
    REFERENCES tbl_sinh_vien(id) ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT fk_ket_qua_lop_hoc_phan FOREIGN KEY(lop_hoc_phan_id)
    REFERENCES tbl_lop_hoc_phan(id) ON DELETE CASCADE ON UPDATE CASCADE

);