////IMPORTS////

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Customer {

    //INSTANCE VARIABLES//

    Room[] owned;
    BufferedReader br;
    Hotel hotel;
    private String name;
    private double total;
    private int verif;

    //NON-STATIC METHODS//

    public void setName(String n) {
        this.name=n;
    }

    public String getName() {
        return this.name;
    }

    public void setVerif() {
        int v=(int) Math.random()*100;
        String ver=null;
        String tmp=null;
        int rev=0;
        while (v<100 || v>999) {
            if (v>999 && rev<v) {
                v=v-rev;
            }
            if (v<100) {
                v=v+rev;
            }
            ver=Integer.toString(v);
            for (int i=ver.length();i>0;i--) {
                tmp+=ver.charAt(i);
            }
            rev=Integer.parseInt(tmp);
        }
        this.verif=v;
    }

    public int getVerif() {
        return this.verif;
    }

    public double getTotal() {
        return this.total;
    }

    public Room[] addRooms(int noOfRooms)throws Exception {
        Room[] fin=new Room[noOfRooms];
        for (int i=0;i<noOfRooms;i++) {
            fin[i]=this.getRoom();    
        }
        return fin;
    }

    public Room[] reAddRooms(int noOfRs)throws Exception {
        int ind=this.owned.length+noOfRs;
        Room[] fin=new Room[ind];
        Room[] rom=this.addRooms(noOfRs);
        for (int i=0;i<ind;i++) {
            if (i<this.owned.length) {
                fin[i]=this.owned[i];
            } else {
                int j=0;
                fin[i]=rom[j];
                j++;
            }
        }
        return fin;
    }

    public Room[] removeRooms()throws Exception {
        Room[] fin=new Room[this.owned.length-1];
        int index=-1;
        boolean check=false;
        for (int x=0;x<this.owned.length;x++) {
            System.out.println("The Booked Rooms:\n");
            for (int i=0;i<this.owned.length;i++) {
                if (this.owned[i].checkAvail()) {
                    System.out.println(this.owned[i]);
                }
            }
            System.out.println("\t\tEnter 000 to exit!");
            System.out.print("Enter a room number that you would like to remove : ");
            do {
                String n=this.br.readLine();
                if (n.equals("000")) {
                    break;
                }
                for (int j=0;j<this.owned.length;j++) {
                    if (this.owned[j].getID().equals(n)) {
                        index=j;
                        check=true;
                    }
                }
            }while (check);
        }
        if (check) {
            for (int k=0;k<this.owned.length;k++) {
                if (k!=index) {
                    fin[k]=this.owned[k];
                }
            }
            this.total-=this.owned[index].getPrice();
            this.owned[index].setAvail(false);
        }
        return fin;
    }
                         
    public Room getRoom()throws Exception {
        String key, rooms;
        rooms="\t";
        int rno;
        int ch;
        boolean fail=false;
        boolean noAvail=false;
     top:
        do {
            System.out.println("\n\n\n\t\tType of Rooms available -\n\n");
            System.out.println("1) Single Room : Rs."+hotel.room[0][0].SINGLE_ROOM_PRICE);
            System.out.println("2) Double Room : Rs."+hotel.room[0][0].DOUBLE_ROOM_PRICE);
            System.out.println("3) Royale Room : Rs."+hotel.room[0][0].ROYALE_ROOM_PRICE);
            do {
                System.out.print("\nEnter choice : ");
                ch=Integer.parseInt(this.br.readLine());
            }while (ch<1 || ch>3);
            System.out.println("\n\n\n");
            if (ch==1) {
                key="Single Room";    
            } else if (ch==2) {
                key="Double Room";
            } else {
                key="Royale Room";
            }
            for (int i=7;i>=0;i--) {
                for (int j=0;j<=7;j++) {
                    if (hotel.room[i][j].getRoomType().equals(key)) {
                        if (!hotel.room[i][j].checkAvail()) {
                            rooms+=hotel.room[i][j].getID()+"\t";
                        } else {
                            rooms+="xXx\t";
                        }
                    }
                }
            rooms+="\n\t";
            }
            System.out.println(rooms);
            System.out.println("\tEnter 000 to go back");
            do {
                System.out.print("Enter Room No : ");
                rno=Integer.parseInt(this.br.readLine());
                if (rno==000) {
                    continue top;
                }
                for (int i=7;i>=0;i--) {
                    for (int j=0;j<=7;j++) {
                        if (hotel.room[i][j].getRoomType().equals(key)) {
                            if (hotel.room[i][j].getID().equals(String.valueOf(rno))) {
                                if (!hotel.room[i][j].checkAvail()) {
                                    hotel.room[i][j].bookRoom();
                                    this.total+=hotel.room[i][j].getPrice();
                                    return hotel.room[i][j];
                                }   
                            }
                        } else {
                            fail=true;
                        }
                    }
                }
    
            }while(fail);
        continue top;
        }while(false);
        return null;
    }

    public String toString() {
        String temp="Name : "+this.getName()+"\n\nBooked Rooms :\n\n";
        for (int i=0;i<owned.length;i++) {
            temp+=owned[i]+"\n\n";
        }
        temp+="\n\nTotal Amount Payable : Rs."+this.getTotal();
        return temp;
    }

    public void printBill() {
        System.out.println("\t\tBill\n");
        System.out.println(this);
    }

    /////CONSTRUCTORS/////

    public Customer(String nam, int nORoom, Hotel h)throws Exception {
        this.br=new BufferedReader(new InputStreamReader(System.in));
        this.hotel=h;                          
        this.setName(nam);
        this.setVerif();
        this.owned=this.addRooms(nORoom);
        this.printBill();
    }
}
