import base.Management;
import hotelrooms.HotelRooms;

import javax.swing.*;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        JOptionPane.showMessageDialog(null,"welcome to the hotel management system!","startup",JOptionPane.INFORMATION_MESSAGE);

        // 2 suites, 3 queens, and 4 singles
        HotelRooms hotel = new HotelRooms(2, 3, 4);
        int choice;
        String menu = "Menu\n\n1. Display rooms\n2. Create Rooms\n3. Check Out\n4.Exit\nEnter Your Choice: ";

        do {
            choice= Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (choice) {
                case 1:
                    hotel.displayAllRooms();                  
                    break;

                case 2:
                    String roomType = Management.selectRoomType();
                    // Check if the maximum number of rooms is reached 
                    // for the selected room type.
                    if (!hotel.isMaxRoomsReached(roomType)) {
                        Management.getReservationInput(hotel, roomType);
                    } else {
                        Management.handleMaxRoomsReached(hotel, roomType);
                    }
                    break;

                case 3:
                    hotel.checkOut();
                    break;

                case 0:
                    JOptionPane.showMessageDialog(null,"Exiting the Hotel Management System. Goodbye!","exit",JOptionPane.INFORMATION_MESSAGE);
                    break;

                default:
                    JOptionPane.showMessageDialog(null,"Invalid choice. Please try again.","error",JOptionPane.ERROR_MESSAGE);
            }
        } while (choice
                != 0);
    }
}
