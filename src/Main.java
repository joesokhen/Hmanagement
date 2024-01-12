import base.Management;
import hotelrooms.HotelRooms;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println(
                "Welcome to the HManagement System!");

        // 2 suites, 3 queens, and 4 singles
        HotelRooms hotel = new HotelRooms(2, 3, 4);
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
                    System.out.println("Exiting the Hotel Management System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice
                != 0);
    }
}
