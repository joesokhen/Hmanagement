package hotelrooms;

import customers.Customer;
import javax.swing.JOptionPane;

public class HotelRooms {

    private int numberOfSuites;
    private int numberOfQueens;
    private int numberOfSingles;
    public Room[] suiteRooms;
    public Room[] queenRooms;
    public Room[] singleRooms;
//    public STATIC String[] Lista =  {"Suite", "Queen", "Single"};

    public HotelRooms(int numberOfSuites, int numberOfQueens, int numberOfSingles) {
        this.numberOfSuites = numberOfSuites;
        this.numberOfQueens = numberOfQueens;
        this.numberOfSingles = numberOfSingles;
        this.suiteRooms = new Suite[numberOfSuites];
        this.queenRooms = new Queen[numberOfQueens];
        this.singleRooms = new Single[numberOfSingles];
    }

    public int getNumberOfSuites() {
        return numberOfSuites;
    }

    public int getNumberOfQueens() {
        return numberOfQueens;
    }

    public int getNumberOfSingles() {
        return numberOfSingles;
    }

    private int getOccupiedRoomCount(Room[] rooms) {
        int count = 0;
        for (Room room : rooms) {
            if (room != null) {
                count++;
            }
        }
        return count;
    }

    public boolean isMaxRoomsReached(String roomType) {
        switch (roomType) {
            case "Suite":
                return getOccupiedRoomCount(suiteRooms) >= numberOfSuites;
            case "Queen":
                return getOccupiedRoomCount(queenRooms) >= numberOfQueens;
            case "Single":
                return getOccupiedRoomCount(singleRooms) >= numberOfSingles;
            default:
                return true;  // Invalid room type
        }
    }

    private Room[] getRoomsByType(String roomType) {
        switch (roomType) {
            case "Suite":
                return suiteRooms;
            case "Queen":
                return queenRooms;
            case "Single":
                return singleRooms;
            default:
                return null;  // Invalid room type
        }
    }

    public void displayRooms(String roomType) {
        System.out.println(roomType + "s:");

        Room[] rooms = getRoomsByType(roomType);
        for (int i = 0; i < rooms.length; i++) {
            Room room = rooms[i];
            if (room == null) {
                System.out.println("\t" + roomType + " " + (i + 1) + ": Free");
            } else {
                System.out.println("\t" + roomType + " " + (i + 1) + ": " + room.getFullName());
            }
        }
    }

    public void displayAllRooms() {
        displayRooms("Suite");
        displayRooms("Queen");
        displayRooms("Single");
    }

    public void createReservation(String roomType, String firstName, String lastName, String phoneNumber) {
        Room[] rooms = getRoomsByType(roomType);

        if (rooms == null) {
            System.out.println("Invalid room type.");
            return;
        }

        if (isMaxRoomsReached(roomType)) {
            System.out.println("No available rooms for " + roomType + ".");
            return;
        }

        Customer customer = new Customer(firstName, lastName, phoneNumber);

        // Find the first available free room by searching (rooms[i] == null) 
        // and fill it with the new reservation.
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] == null) {
                Room room = reserveRoom(roomType, i + 1);
                room.setCustomer(customer); // Set the customer for the room
                rooms[i] = room;
                System.out.println("Reservation created for " + firstName + " " + lastName + " in " + roomType + " " + room.getRoomNumber());
                return;
            }
        }
    }

    private Room reserveRoom(String roomType, int roomNumber) {
        switch (roomType) {
            case "Suite":
                return new Suite(roomNumber);
            case "Queen":
                return new Queen(roomNumber);
            case "Single":
                return new Single(roomNumber);
            default:
                return null;  // Invalid room type
        }
    }

    public void checkOut() {
        // 1 dropdown menu, 3 items(room Types) in a list.
        String selectedRoomType = (String) JOptionPane.showInputDialog(null, "Select a room type:", "Check Out",
                JOptionPane.QUESTION_MESSAGE, null, new String[]{"Suite", "Queen", "Single"}, "Suite");

        if (selectedRoomType == null) {
            return; // User clicked Cancel
        }

        // choose the array based on the selected room type and list all occupied rooms 
        Room[] selectedType = null;

        switch (selectedRoomType) {
            case "Suite":
                selectedType = suiteRooms;
                break;
            case "Queen":
                selectedType = queenRooms;
                break;
            case "Single":
                selectedType = singleRooms;
                break;
        }

        // Create an array for the occupied room numbers for the selected room type
        Integer[] occupiedRoomNumbers = new Integer[selectedType.length];
        int count = 0;

        // Find the occupied room numbers for the selected room type
        for (int i = 0; i < selectedType.length; i++) {
            if (selectedType[i] != null) {
                occupiedRoomNumbers[count] = i + 1;
                count++;
            }
        }

        // Check if any rooms are occupied for the selected room type
        if (count == 0) {
            JOptionPane.showMessageDialog(null, "No rooms are currently occupied.");
            return;
        }

        // Create the dropdown menu for the occupied room numbers
        int selectedRoomNumber = (int) JOptionPane.showInputDialog(null, "Select a room to check out:", "Check Out",
                JOptionPane.QUESTION_MESSAGE, null, occupiedRoomNumbers, occupiedRoomNumbers[0]);

        if (selectedRoomNumber == -1) {
            return; // User clicked Cancel
        }
        // Check out the selected room
        int roomNumber = selectedRoomNumber;
        Room selectedRoom = selectedType[roomNumber - 1];

        if (selectedRoom == null) {
            JOptionPane.showMessageDialog(null, "Room " + roomNumber + " is already vacant.");
        } else {
            JOptionPane.showMessageDialog(null, "Checking out " + selectedRoom.getFullName() + " from " + selectedRoomType + " " + roomNumber);
            selectedType[roomNumber - 1] = null;
        }
    }

    public void increaseNumberOfRooms(String roomType, int newNumberOfRooms) {
        Room[] newRooms = new Room[newNumberOfRooms];
        Room[] rooms = null;

        if (roomType.equals("Suite")) {
            rooms = suiteRooms;
        } else if (roomType.equals("Queen")) {
            rooms = queenRooms;
        } else if (roomType.equals("Single")) {
            rooms = singleRooms;
        }

        if (rooms == null) {
            System.out.println("Invalid room type.");
            return;
        }

        int numRoomsToCopy = Math.min(rooms.length, newNumberOfRooms);
        System.arraycopy(rooms, 0, newRooms, 0, numRoomsToCopy);

        if (roomType.equals("Suite")) {
            suiteRooms = newRooms;
            numberOfSuites = newNumberOfRooms;
            System.out.println("Number of Suite rooms increased.");
        } else if (roomType.equals("Queen")) {
            queenRooms = newRooms;
            numberOfQueens = newNumberOfRooms;
            System.out.println("Number of Queen rooms increased.");
        } else if (roomType.equals("Single")) {
            singleRooms = newRooms;
            numberOfSingles = newNumberOfRooms;
            System.out.println("Number of Single rooms increased.");
        }
    }
}
