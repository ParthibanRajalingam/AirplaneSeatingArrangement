package com.parth.airplaneSeater.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.parth.airplaneSeater.AirplaneService;

import junit.framework.Assert;

import static org.junit.Assert.assertEquals;  
import static org.mockito.Mockito.mock;  
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;  

@RunWith(MockitoJUnitRunner.class)
public class AirplaneServiceTest {
	   @Mock
	   AirplaneService airTestService;
	
	@Test
	 public void testusing_Mocks() {
		AirplaneService service = mock(AirplaneService.class);
		List<String[][]> totalAirplaneSeats = new ArrayList<String[][]>();
		totalAirplaneSeats.add(new String [2][3]);
		totalAirplaneSeats.add(new String [3][4]);
		List<String[][]> filledAirplaneSeats = new ArrayList<String[][]>();
		String [][] bunch1 = {{ "19    ","25     ","1   "},
							 {  "21    ","29     ","7   "}};
		String [][] bunch2 = {{ "2     ","26     ","27  ","3   "},
							 { 	"8     ","30     ","31  ","9   "	},
							 { 	"13    ","33     ","34  ","14  "	},};
		filledAirplaneSeats.add(bunch1);
		filledAirplaneSeats.add(bunch2);
		when(airTestService.fillSeats(totalAirplaneSeats)).thenReturn(filledAirplaneSeats);
		Assert.assertEquals(airTestService.fillSeats(totalAirplaneSeats), filledAirplaneSeats);
	}
	

}
