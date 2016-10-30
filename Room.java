class Room {

    ////CLASS CONSTANTS////

    final double SINGLE_ROOM_PRICE=500.00;
    final double DOUBLE_ROOM_PRICE=1000.00;
    final double ROYALE_ROOM_PRICE=2000.00;

    //INSTANCE VARIABLES//

    private String ID, type;
    private double price;
    private boolean avail;

    //NON-STATIC METHODS//

        ////SET METHODS////

    public void setID(String RNo) {
        this.ID=RNo;
    }
    public void setPrice(byte RType) {
        switch (RType) {
            case 1:
                this.type="Single Room";
                this.price=SINGLE_ROOM_PRICE;
            break;
            case 2:
                this.type="Double Room";
                this.price=DOUBLE_ROOM_PRICE;
            break;
            case 3:
                this.type="Royale Room";
                this.price=ROYALE_ROOM_PRICE;
            break;
            default:
            System.out.println("\nNO SUCH ROOM AVAILABLE!\n");
        }
    }
    public void bookRoom() {
        this.avail=true;
    }

    public void setAvail(boolean sa) {
        this.avail=sa;
    }

        ////GET METHODS////

    public String getID() {
        return this.ID;
    }
    public String getRoomType() {
        return this.type;
    }
    public double getPrice() {
        return this.price;
    }
    public boolean checkAvail() {
        return this.avail;
    }

    ////OTHER METHODS////

    public String toString() {
        String tmp="Room Type : "+this.type;
        tmp+="\nRoom No. : "+this.ID;
        tmp+="\nPrice : Rs."+this.price;
        tmp+="\nBooked : "+this.avail;
        return tmp;
    }

    /////CONSTRUCTORS/////

    public Room(String RID, byte rType) {
        this.avail=false;
        this.setPrice(rType);
        this.setID(RID);
    }
}
