import java.util.ArrayList;
import java.util.List;

import com.parth.airplaneSeater.AirplaneService;

public class app {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String[][]> totalAirplaneSeats = new ArrayList<String[][]>();
		totalAirplaneSeats.add(new String [2][3]);
		totalAirplaneSeats.add(new String [3][4]);
		totalAirplaneSeats.add(new String [3][2]);
		totalAirplaneSeats.add(new String [4][3]);
		AirplaneService air = new AirplaneService();
		
		totalAirplaneSeats = air.initializeSeats(totalAirplaneSeats);
		totalAirplaneSeats = air.fillSeats(totalAirplaneSeats);
		air.printSeatingArrangement(totalAirplaneSeats);
	}
	
	
}
