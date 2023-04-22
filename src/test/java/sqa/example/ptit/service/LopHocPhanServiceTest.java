package sqa.example.ptit.service;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import sqa.example.model.ThangDiem;
import sqa.example.ptit.Dummy;
import sqa.example.repository.ThangDiemRepository;
import sqa.example.service.ThangDiemService;
import sqa.example.service.LopHocPhanServiceUtils;

@SpringBootTest
public class LopHocPhanServiceTest 
{
	@Mock private ThangDiemRepository thangDiemRepository;
	@InjectMocks private ThangDiemService thangDiemService;
	private Dummy dummy = new Dummy();
	
	@BeforeEach
    public void setup() {
		var dummyLimitPoint = new ThangDiem(1, 4.0, 5.0, "", 1.0);
		when(thangDiemRepository.findByName("D")).thenReturn(dummyLimitPoint);
	}
	
	@Test
	public void utils_getFailureRate() {
		var pointLimit = thangDiemService.getPointLimit();
		var realResult = LopHocPhanServiceUtils.getFailureRate(dummy.lopHocPhan, pointLimit);
		var expectedResult = 0.0;
		
		assertEquals(realResult, expectedResult);
	}
	
	@Test
	public void utils_getFailureRate_emptyList() {
		var pointLimit = thangDiemService.getPointLimit();
		var realResult = LopHocPhanServiceUtils.getFailureRate(dummy.lopHocPhan_empty, pointLimit);
		var expectedResult = 0.0;
		
		assertEquals(realResult, expectedResult);
	}

	@Test
	public void utils_getDiemTongKet() {
		var realResult = LopHocPhanServiceUtils.getDiemTongKet(dummy.lopHocPhan, dummy.ketQuaList.get(0));
		var expectedResult = 9.0;
		
		assertEquals(realResult, expectedResult);
	}
	
	@Test
	public void utils_getJsonFailureRate() {
		Map<String, String> result = LopHocPhanServiceUtils.getJsonFailureRate(dummy.lopHocPhan, 4.0);
		
		assertEquals(result.get("id"),					String.valueOf(dummy.lopHocPhan.getId()));
		assertEquals(result.get("ma giao vien"),		dummy.lopHocPhan.getGiaoVien().getMaGiaoVien());
		assertEquals(result.get("ten giao vien"),		dummy.lopHocPhan.getGiaoVien().getNguoiDung().getName());
		assertEquals(result.get("ten phong hoc"),		dummy.lopHocPhan.getPhongHoc().getTen());
		assertEquals(result.get("so hoc vien"),			String.valueOf(dummy.lopHocPhan.getListKetQua().size()));
		assertEquals(result.get("ty le truot mon"),		String.valueOf(LopHocPhanServiceUtils.getFailureRate(dummy.lopHocPhan, thangDiemService.getPointLimit())));
	}
	
	@Test
	public void utils_getJsonFailureRateList() {
		List<Map> result = LopHocPhanServiceUtils.getJsonFailureRateList(dummy.lopHocPhanList, 4.0);
		
		assertEquals(result.size(), dummy.lopHocPhanList.size());
	}
}
