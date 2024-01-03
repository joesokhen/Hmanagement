package hotelrooms;

import customers.Customer;
import javax.swing.JOptionPane;

public class HotelRooms {
    int numberOfRooms;
    Customer[] rooms;

    public HotelRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
        this.rooms = new Customer[numberOfRooms];
    }

    public void displayRooms() {
        System.out.println("Rooms:");

        for (int i = 0; i < numberOfRooms; i++) {
            if (rooms[i] == null) {
                System.out.println("Room " + (i + 1) + ": Free");
            } else {
                System.out.println("Room " + (i + 1) + ": Occupied by " + rooms[i].getFullName());
            }
        }
    }

    public void createReservation(String firstName, String lastName, String phoneNumber) {
        // Find the 1st available free room(rooms[i] = null) and fill it w/ the reservation
        for (int i = 0; i < numberOfRooms; i++) {
            if (rooms[i] == null) {
                rooms[i] = new Customer(firstName, lastName, phoneNumber, i + 1);
                JOptionPane.showMessageDialog(null,
                        "Reservation created for " + firstName + " " + lastName + " in Room " + (i + 1));
                return;
            }
        }
    }

    // Method to increase the number of rooms
    public void increaseNumberOfRooms(int newNumberOfRooms) {
        Customer[] newRooms = new Customer[newNumberOfRooms];
        System.arraycopy(rooms, 0, newRooms, 0, numberOfRooms);
        numberOfRooms = newNumberOfRooms;
        rooms = newRooms;
        JOptionPane.showMessageDialog(null, "Number of rooms increased to " + newNumberOfRooms);
    }

    public boolean isMaxRoomsReached() {
        boolean allRoomsOccupied = true;
        for (int i = 0; i < numberOfRooms; i++) {
            if (rooms[i] == null) {
                allRoomsOccupied = false;
                break;
            }
        }
        return allRoomsOccupied;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void checkOut(int roomNumber) {
        if (roomNumber >= 1 && roomNumber <= numberOfRooms) {
            if (rooms[roomNumber - 1] != null) {
                System.out.println("Checking out " + rooms[roomNumber - 1].getFullName()
                        + " from Room " + roomNumber);
                rooms[roomNumber - 1] = null;
            } else {
                System.out.println("Room " + roomNumber + " is already free.");
            }
        } else {
            System.out.println("Invalid room number.");
        }
    }
}
