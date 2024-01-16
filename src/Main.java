
import base.Management;
import hotelrooms.HotelRooms;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        JOptionPane.showMessageDialog(null, "Welcome To The Hotel Management System!", "Startup", JOptionPane.INFORMATION_MESSAGE);

        // 2 suites, 3 queens, and 4 singles
        HotelRooms hotel = new HotelRooms(2, 3, 4);
        int choice;
        String[] menu = {"Display Rooms", "Create Reservation", "Check Out", "Exit"};

        do {
            choice = JOptionPane.showOptionDialog(null,
                    "Welcome to the Hotel Management System!", "Main Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, menu, menu[0]);
            if (choice == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Exiting the Hotel Management System. Goodbye!", "Exit", JOptionPane.INFORMATION_MESSAGE);
                break; // Exit the loop if the user closes the dialog
            }

            switch (choice) {
                case 0:
                    hotel.displayAllRooms();
                    break;
                case 1:
                    String roomType = Management.selectRoomType();
                    if (!hotel.isMaxRoomsReached(roomType)) {
                        Management.getReservationInput(hotel, roomType);
                    } else {
                        Management.handleMaxRoomsReached(hotel, roomType);
                    }
                    break;
                case 2:
                    hotel.checkOut();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Exiting the Hotel Management System. Goodbye!", "Exit", JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } while (choice != 3); // Continue until "Exit" is chosen
    }
}
