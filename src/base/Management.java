package base;

import hotelrooms.HotelRooms;

import javax.swing.JOptionPane;
import java.util.Scanner;

public class Management {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the HManagement System!");

        System.out.print("Enter the number of rooms in the hotel: ");
        int numberOfRooms = scanner.nextInt();

        HotelRooms hotel = new HotelRooms(numberOfRooms);

        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Display Rooms");
            System.out.println("2. Create Reservation");
            System.out.println("3. Check Out");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    hotel.displayRooms();
                    break;

                case 2:
                    if (!hotel.isMaxRoomsReached()) {
                        getReservationInput(hotel);
                    } else {
                        handleMaxRoomsReached(hotel);
                    }
                    break;

                case 3:
                    System.out.print("Enter room number to check out: ");
                    int checkOutRoomNumber = scanner.nextInt();
                    hotel.checkOut(checkOutRoomNumber);
                    break;

                case 0:
                    System.out.println("Exiting the Hotel Management System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private static void getReservationInput(HotelRooms hotel) {
        System.out.print("Enter customer first name: ");
        String firstName = scanner.next();

        System.out.print("Enter customer last name: ");
        String lastName = scanner.next();

        System.out.print("Enter customer phone number: ");
        String phoneNumber = scanner.next();

        // Now, create the reservation by calling the method
        hotel.createReservation(firstName, lastName, phoneNumber);
    }

    private static void handleMaxRoomsReached(HotelRooms hotel) {
        // Display a dialog asking the user if they want to increase the number of rooms
        int option = JOptionPane.showConfirmDialog(null,
                "All rooms are occupied. Do you want to increase the number of rooms?",
                "Room Occupancy", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            // User chose to increase the number of rooms
            int newNumberOfRooms = Integer.parseInt(JOptionPane.showInputDialog(
                    null, "Enter the new number of rooms:", "Room Occupancy", JOptionPane.PLAIN_MESSAGE));

            // Validate the new number of rooms
            if (newNumberOfRooms > hotel.getNumberOfRooms()) {
                hotel.increaseNumberOfRooms(newNumberOfRooms);
                getReservationInput(hotel);
            } else {
                // In case the user didn't increase the number of rooms
                JOptionPane.showMessageDialog(null, "Increasing number of rooms failed, all rooms are occupied.");
            }
        }
    }

}
