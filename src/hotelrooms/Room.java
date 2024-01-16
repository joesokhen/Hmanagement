package hotelrooms;

import customers.Customer;

// created an abstract class "Room" which all room types will be related to by polymorphism
public abstract class Room {

    protected int roomNumber;
    protected String roomType;
    protected String roomInfo;
    protected Customer customer;

    public Room(int roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomInfo = roomType + " " + roomNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getFullName() {
        return roomInfo;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        if (customer != null) {
            return customer.getFullName() + "\n";
        } else {
            return roomType + "\t\t| Room " + roomNumber + "\t| Free\n";
        }
    }
}

class Suite extends Room {

    public Suite(int roomNumber) {
        super(roomNumber, "Suite");
    }

    @Override
    public String getFullName() {
        if (customer != null) {
            return customer.getFullName();
        } else {
            return "Not Occupied";
        }
    }
}

class Queen extends Room {

    public Queen(int roomNumber) {
        super(roomNumber, "Queen");
    }

    @Override
    public String getFullName() {
        if (customer != null) {
            return customer.getFullName();
        } else {
            return "Not Occupied";
        }
    }
}

class Single extends Room {

    public Single(int roomNumber) {
        super(roomNumber, "Single");
    }

    @Override
    public String getFullName() {
        if (customer != null) {
            return customer.getFullName();
        } else {
            return "Not Occupied";
        }
    }
}
