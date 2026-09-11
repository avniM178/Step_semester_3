class BookInventory {

    private final int copiesTotal;
    private int copiesAvailable;

    public BookInventory(int copiesTotal) {

        if (copiesTotal <= 0) {
            throw new IllegalArgumentException(
                    "copiesTotal must be positive"
            );
        }

        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    public void checkOut() {

        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    public void checkIn() {

        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }
}


public class BookCopyCirculationGuardMain {

    public static void main(String[] args) {

        BookInventory inventory =
                new BookInventory(3);

        System.out.println(
                "Initial copies available: "
                + inventory.getCopiesAvailable()
        );


       
        inventory.checkOut();
        inventory.checkOut();
        inventory.checkOut();

        System.out.println(
                "After 3 checkouts: "
                + inventory.getCopiesAvailable()
        );


        
        inventory.checkOut();

        System.out.println(
                "After 4th checkout attempt: "
                + inventory.getCopiesAvailable()
        );


        
        inventory.checkIn();
        inventory.checkIn();
        inventory.checkIn();

        System.out.println(
                "After 3 check-ins: "
                + inventory.getCopiesAvailable()
        );


      
        inventory.checkIn();

        System.out.println(
                "After 4th check-in attempt: "
                + inventory.getCopiesAvailable()
        );
    }
}