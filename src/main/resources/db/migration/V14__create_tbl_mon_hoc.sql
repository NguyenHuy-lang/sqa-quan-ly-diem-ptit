CREATE TABLE tbl_mon_hoc(
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    ten varchar(100) NOT NULL,
    ty_le_diem_cc DECIMAL(5, 5),
    ty_le_diem_th DECIMAL(5, 5),
    ty_le_diem_kt DECIMAL(5, 5),
    ty_le_diem_bt DECIMAL(5, 5),
    ty_le_diem_cuoi_ky DECIMAL(5, 5)
);