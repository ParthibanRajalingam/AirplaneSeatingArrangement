package com.parth.airplaneSeater;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AirplaneService {
	static final String[] seatsStart = { "W", "M", "A" };
	static final String[] seatsMid = { "A", "M", "A" };
	static final String[] seatsEnd = { "A", "M", "W" };
	int numberOfWindowSeats = 0;
	int numberOfMidSeats = 0;
	int numberOfAsileSeats = 0;
	int maxNumberOfRows = 0;
	Queue<Integer> windowTickets = new LinkedList<Integer>();
	Queue<Integer> asileTickets = new LinkedList<Integer>();
	Queue<Integer> midTickets = new LinkedList<Integer>();

	public List<String[][]> initializeSeats(List<String[][]> seats) {

		List<String[][]> initializedSeats = new ArrayList<String[][]>();
		for (int i = 0; i < seats.size(); i++) {
			String[][] bunchOfSeats = seats.get(i);
			maxNumberOfRows = (bunchOfSeats.length > maxNumberOfRows) ? bunchOfSeats.length : maxNumberOfRows;
			if (i == 0) {
				bunchOfSeats = initialzeBunchOfSeats(bunchOfSeats, seatsStart);
			} else if (i == seats.size() - 1) {
				bunchOfSeats = initialzeBunchOfSeats(bunchOfSeats, seatsEnd);
			} else {
				bunchOfSeats = initialzeBunchOfSeats(bunchOfSeats, seatsMid);
			}
			initializedSeats.add(bunchOfSeats);
		}
		fillQueues();
		return initializedSeats;
	}

	private String[][] initialzeBunchOfSeats(String[][] bunchOfSeats, String[] values) {

		for (int row = 0; row < bunchOfSeats.length; row++) {
			for (int col = 0; col < bunchOfSeats[row].length; col++) {
				String seatType = "";
				if (col == 0) {
					seatType = values[0];
				} else if (col == bunchOfSeats[row].length - 1) {
					seatType = values[2];
				} else {
					seatType = values[1];
				}
				bunchOfSeats[row][col] = seatType;
				if (seatType.equalsIgnoreCase("w")) {
					numberOfWindowSeats++;
				} else if (seatType.equalsIgnoreCase("a")) {
					numberOfAsileSeats++;
				} else {
					numberOfMidSeats++;
				}
			}
		}

		return bunchOfSeats;
	}

	private void fillQueues() {
		addSeatNumberToQueues(1, numberOfAsileSeats, asileTickets);
		addSeatNumberToQueues(numberOfAsileSeats + 1, numberOfAsileSeats + numberOfWindowSeats, windowTickets);
		addSeatNumberToQueues(numberOfAsileSeats + numberOfWindowSeats + 1,
				numberOfAsileSeats + numberOfWindowSeats + numberOfMidSeats, midTickets);
	}

	private void addSeatNumberToQueues(int start, int end, Queue<Integer> input) {
		for (int i = start; i <= end; i++) {
			input.add(i);
		}
	}

	public List<String[][]> fillSeats(List<String[][]> initializedSeats) {

		for (int row = 0; row < maxNumberOfRows; row++) {
			for (int i = 0; i < initializedSeats.size(); i++) {
				String[][] currentBunchOfSeats = initializedSeats.get(i);
				if (row < currentBunchOfSeats.length) {
					String[] currentRow = currentBunchOfSeats[row];
					fillRows(currentRow);
				}
			}
			}
		return initializedSeats;
	}

	private String[] fillRows(String[] row) {
		String[] filledRow = row;
		for (int i = 0; i < row.length; i++) {
			if (row[i].equalsIgnoreCase("a")) {
				filledRow[i] = asileTickets.poll() + "";
			} else if (row[i].equalsIgnoreCase("w")) {
				filledRow[i] = windowTickets.poll() + "";
			} else {
				filledRow[i] = midTickets.poll() + "";
			}
		}
		return filledRow;
	}
	
	public void printSeatingArrangement(List<String[][]> filledSeats) {
		for (int row = 0; row < maxNumberOfRows; row++) {
			for (int i = 0; i < filledSeats.size(); i++) {
				String[][] currentBunchOfSeats = filledSeats.get(i);
				if (row < currentBunchOfSeats.length ) {
					String[] currentRow = currentBunchOfSeats[row];
					for(String seat: currentRow) {
						
						System.out.print(getSeat(seat)+"  ");
					}
				}else {
					for(int space=0; space < currentBunchOfSeats[0].length;space++) {
						System.out.print("      ");
					}
				}
				System.out.print("   |   ");
			}
			System.out.println();
		}
	}
	
	//Method to pretty print seats. Appending 4 chars to seats.
	private String getSeat(String seat) {
		if(seat.length() == 1) {
			seat = "   "+seat;
		}else if(seat.length() == 2) {
			seat = "  "+seat;
		}else if(seat.length() == 3) {
			seat = " "+seat;
		}
		return seat;
	}
}
