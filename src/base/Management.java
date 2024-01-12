package base;

import hotelrooms.HotelRooms;
import javax.swing.JOptionPane;

public class Management {

    public static String selectRoomType() {
        int roomTypeSelected;
        String roomType;
        do {
            roomTypeSelected = JOptionPane.showOptionDialog(
                    null,
                    "Select Room Type:",
                    "Room Selection",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Suite", "Queen", "Single"},
                    "Queen"
            );
        } while (roomTypeSelected < 0 || roomTypeSelected > 2);

        switch (roomTypeSelected) {
            case 0:
                roomType = "Suite";
                break;

            case 1:
                roomType = "Queen";
                break;

            case 2:
                roomType = "Single";
                break;

            default:
                roomType = null;
                break;
        }
        return roomType;
    }

    public static void getReservationInput(HotelRooms hotel, String roomType) {
        String firstName = JOptionPane.showInputDialog("Enter Customer First Name: ");
        String lastName = JOptionPane.showInputDialog("Enter Customer Last Name: ");
        String phoneNumber = JOptionPane.showInputDialog("Enter Customer Phone Number: ");

        // Now, create the reservation by calling the method
        hotel.createReservation(roomType, firstName, lastName, phoneNumber);
    }

    public static void handleMaxRoomsReached(HotelRooms hotel, String roomType) {
        int userChoice = JOptionPane.showConfirmDialog(null,
                "All " + roomType + " rooms are occupied. Do you want to increase the number of " + roomType + " rooms?",
                "Room Occupancy", JOptionPane.YES_NO_OPTION);

        if (userChoice == JOptionPane.YES_OPTION) {
            int newNumberOfRooms = Integer.parseInt(JOptionPane.showInputDialog(
                    null, "Enter the new number of " + roomType + " rooms:", "Room Occupancy", JOptionPane.PLAIN_MESSAGE));

            // Validate the new number of rooms
            switch (roomType) {
                case "Suite":
                    if (newNumberOfRooms > hotel.getNumberOfSuites()) {
                        hotel.increaseNumberOfRooms("Suite", newNumberOfRooms);
                        getReservationInput(hotel, roomType);
                    } else {
                        JOptionPane.showMessageDialog(null, "Increasing number of " + roomType + " rooms failed, all " + roomType + " rooms are occupied.");
                    }
                    break;

                case "Queen":
                    if (newNumberOfRooms > hotel.getNumberOfQueens()) {
                        hotel.increaseNumberOfRooms("Queen", newNumberOfRooms);
                        getReservationInput(hotel, roomType);
                    } else {
                        JOptionPane.showMessageDialog(null, "Increasing number of " + roomType + " rooms failed, all " + roomType + " rooms are occupied.");
                    }
                    break;

                case "Single":
                    if (newNumberOfRooms > hotel.getNumberOfSingles()) {
                        hotel.increaseNumberOfRooms("Single", newNumberOfRooms);
                        getReservationInput(hotel, roomType);
                    } else {
                        JOptionPane.showMessageDialog(null, "Increasing number of " + roomType + " rooms failed, all " + roomType + " rooms are occupied.");
                    }
                    break;

                default:
                    break;
            }
        }
    }
}
