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
        String firstName, lastName, phoneNumber;
        // Check each field a time instead of bulk checking, this eliminate the
        // hasstle to input all info from scratch in case of a user mistake.        
        do {
            firstName = JOptionPane.showInputDialog("Enter Customer First Name: ");
            if (firstName == null) {
                JOptionPane.showMessageDialog(null, "Operation aborted. Returning to the menu.", "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        } while (firstName.isEmpty() || firstName.equals(Integer.toString(JOptionPane.OK_OPTION)));

        do {
            lastName = JOptionPane.showInputDialog("Enter Customer Last Name: ");
            if (lastName == null) {
                JOptionPane.showMessageDialog(null, "Operation aborted. Returning to the menu.", "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        } while (lastName.isEmpty() || lastName.equals(Integer.toString(JOptionPane.OK_OPTION)));

        do {
            phoneNumber = JOptionPane.showInputDialog("Enter Customer Phone Number: ");
            if (phoneNumber == null) {
                JOptionPane.showMessageDialog(null, "Operation aborted. Returning to the menu.", "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        } while (phoneNumber.isEmpty() || phoneNumber.equals(Integer.toString(JOptionPane.OK_OPTION)));

        // Now, create the reservation by calling the method
        hotel.createReservation(roomType, firstName, lastName, phoneNumber);
    }

    public static void handleMaxRoomsReached(HotelRooms hotel, String roomType) {
        int userChoice, newNumberOfRooms;

        userChoice = JOptionPane.showConfirmDialog(null,
                "All " + roomType + " rooms are occupied. Do you want to increase the number of " + roomType + " rooms?",
                "Room Occupancy", JOptionPane.YES_NO_OPTION);

        if (userChoice == JOptionPane.YES_OPTION) {
            try {
                String input = JOptionPane.showInputDialog(
                        null,
                        "Enter the new number of " + roomType + " rooms:",
                        "Room Occupancy",
                        JOptionPane.PLAIN_MESSAGE
                );

                // Check if the input is null or empty & return to the menu
                if (input == null || input.isEmpty()) {
                    return;
                }

                newNumberOfRooms = Integer.parseInt(input);

                // Validate the new number of rooms
                switch (roomType) {
                    case "Suite":
                        if (newNumberOfRooms > hotel.getNumberOfSuites()) {
                            hotel.increaseNumberOfRooms("Suite", newNumberOfRooms);
                            getReservationInput(hotel, roomType);
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to increase number of " + roomType + " rooms.");
                        }
                        break;

                    case "Queen":
                        if (newNumberOfRooms > hotel.getNumberOfQueens()) {
                            hotel.increaseNumberOfRooms("Queen", newNumberOfRooms);
                            getReservationInput(hotel, roomType);
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to increase number of " + roomType + " rooms.");
                        }
                        break;

                    case "Single":
                        if (newNumberOfRooms > hotel.getNumberOfSingles()) {
                            hotel.increaseNumberOfRooms("Single", newNumberOfRooms);
                            getReservationInput(hotel, roomType);
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to increase number of " + roomType + " rooms.");
                        }
                        break;

                    default:
                        break;
                }
                // Validate this field contains only numbers.            
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}