package shizhichao_common_utils.DateUtil;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

import com.shizhichao.utils.DateUtil;

public class DateUtilTest {

	@Test
	public void test() throws ParseException {
		
		 Date parse = DateUtil.dateFormat.parse("2020-05-18 03:04:22");
		  
		  Date firstDateInMonth2 = DateUtil.getFirstDateInMonth(parse);
		  
		  
		  String format = DateUtil.dateTimeFormat.format(firstDateInMonth2);
		 
				
		  Date parse2 =DateUtil.dateFormat.parse("2020-05-18 03:04:22");
		  
		  Date lastDateInMonth = DateUtil.getLastDateInMonth(parse2);
		  
		  String format2 = DateUtil.dateTimeFormat.format(lastDateInMonth);
		
		  
		  String sql="select * from t_order where create_time>="
		  		+ format+" and create_time<= "+format2;
		  System.out.println(sql);
		
	}
	
	
	
}
