CREATE TABLE tbl_nguoi_dung (
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    gioi_tinh varchar(20) NOT NULL,
    ten varchar(50) NOT NULL,
    dia_chi varchar(50) NOT NULL,
    email varchar(50) NOT NULL UNIQUE,
    so_dien_thoai varchar(50),
    ngay_sinh date
    )