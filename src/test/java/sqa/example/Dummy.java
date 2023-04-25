package sqa.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import sqa.example.model.GiaoVien;
import sqa.example.model.KetQua;
import sqa.example.model.KyHoc;
import sqa.example.model.LopHocPhan;
import sqa.example.model.MonHoc;
import sqa.example.model.NamHoc;
import sqa.example.model.NamHocKyHoc;
import sqa.example.model.Nganh;
import sqa.example.model.NguoiDung;
import sqa.example.model.NienKhoa;
import sqa.example.model.NienKhoaNganh;
import sqa.example.model.NienKhoaNganhNamHocKyHoc;
import sqa.example.model.NienKhoaNganhNamHocKyHocMonHoc;
import sqa.example.model.PhongHoc;
import sqa.example.model.SinhVien;
import sqa.example.model.ThangDiem;

public class Dummy
{
    public final NamHoc namHoc																= new NamHoc(1, "2019");
    public final KyHoc kyHoc																= new KyHoc(1, "2019");
    public final NienKhoa nienKhoa															= new NienKhoa(1, "2019");
    public final Nganh nganh																= new Nganh(1, "CNTT");
    public final MonHoc monHoc																= new MonHoc(1, "Mon Hoc A", 0.2, 0.2, 0.2, 0.2, 0.2);
    public final ThangDiem thangDiem														= new ThangDiem(1, 0.0, 3.9, "D", 1.0);
    public final NamHocKyHoc namHocKyHoc													= new NamHocKyHoc(1, namHoc, kyHoc);
    public final NienKhoaNganh nienKhoaNganh												= new NienKhoaNganh(1, nganh, nienKhoa);
    public final NienKhoaNganhNamHocKyHoc nienKhoaNganhNamHocKyHoc							= new NienKhoaNganhNamHocKyHoc(1, namHocKyHoc, nienKhoaNganh);
    public final NienKhoaNganhNamHocKyHocMonHoc nienKhoaNganhNamHocKyHocMonHoc				= new NienKhoaNganhNamHocKyHocMonHoc(1, monHoc, nienKhoaNganhNamHocKyHoc);

    public final PhongHoc phongHoc															= new PhongHoc(1, "101A");
    public final NguoiDung nguoiDungGV														= new NguoiDung(1, "", "", "", "", "", null);
    public final NguoiDung nguoiDungSV														= new NguoiDung(1, "", "", "", "", "", null);
    public final GiaoVien giaoVien															= new GiaoVien(1, "", nguoiDungGV);

    public final SinhVien sinhVien															= new SinhVien(1, nguoiDungSV, "", nienKhoaNganh);
    public final KetQua ketQua																= new KetQua(1, 9.0, 9.0, 9.0, 9.0, 9.0, 0.0, 0.0, "", sinhVien, null, null);

    public final List<KetQua> ketQuaList													= Arrays.asList(ketQua);
    public final LopHocPhan lopHocPhan														= new LopHocPhan(1, new Date(), new Date(), nienKhoaNganhNamHocKyHocMonHoc, phongHoc, ketQuaList, giaoVien);
    public final LopHocPhan lopHocPhan_empty												= new LopHocPhan(2, new Date(), new Date(), nienKhoaNganhNamHocKyHocMonHoc, phongHoc, new ArrayList<KetQua>(), giaoVien);

    public final List<NamHoc> namHocList													= Arrays.asList(namHoc);
    public final List<NamHocKyHoc> namHocKyHocList											= Arrays.asList(namHocKyHoc);
    public final List<NienKhoaNganh> nienKhoaNganhList										= Arrays.asList(nienKhoaNganh);
    public final List<NienKhoaNganhNamHocKyHoc> nienKhoaNganhNamHocKyHocList				= Arrays.asList(nienKhoaNganhNamHocKyHoc);
    public final List<NienKhoaNganhNamHocKyHocMonHoc> nienKhoaNganhNamHocKyHocMonHocList	= Arrays.asList(nienKhoaNganhNamHocKyHocMonHoc);
    public final List<LopHocPhan> lopHocPhanList											= Arrays.asList(lopHocPhan);
}