package customers;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private int roomNumber;

    public Customer(String firstName, String lastName, String phoneNumber, int roomNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.roomNumber = roomNumber;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "Customer{"
                + "firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", phoneNumber='" + phoneNumber + '\''
                + ", roomNumber=" + roomNumber
                + '}';
    }
}
