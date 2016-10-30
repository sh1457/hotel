////IMPORTS////

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Hotel {

    //INSTANCE VARIABLES//

    Room[][] room;
    BufferedReader br;


    //NON-STATIC METHODS//

    public void disp() {
        for (int i=7;i>=0;i--) {
            for (int j=0;j<=7;j++) {
                System.out.print(this.room[i][j].getID()+" ");
            }
        System.out.println();
        }
    }

    public boolean isFull() {
        int c=0;
        for (int i=7;i>=0;i--) {
            for (int j=0;j<=7;j++) {
                if (this.room[i][j].checkAvail()) {
                    c++;
                }
            }
        }
        if (c==64) {
            return true;
        }
        return false; 
    }

    public boolean checkCustList(int verf,Customer[] c)throws Exception {
        for (int i=0;i<c.length;i++) {
            if (c[i].getVerif()==verf) {
                return true;
            }
        }
        return false;
    }

    public void newCust(Customer[] c, String nam, int ind)throws Exception {
        int nOR;
        System.out.print("\n\nHow many rooms would you like : ");
        nOR=Integer.parseInt(this.br.readLine());
        c[ind]=new Customer(nam, nOR, this);
    }

    public void valuedCust(Customer[] cu, int in)throws Exception {
        boolean reEnter;
        int tries=3;
        do {
            reEnter=false;
            System.out.println("Enter you Unique Identification Provided at Booking : ");
            int vCode=Integer.parseInt(this.br.readLine());
            if (cu[0]!=null) {
                if (this.checkCustList(vCode, cu)) {
                    int cid=0;
                    for (int j=0;j<cu.length;j++) {
                        if (cu[j].getVerif()==(vCode)) {
                            cid=j;
                            break;
                        }
                    }
                    this.subMenu(cu, cid);
                }
            } else {
                if (tries>0) {
                    System.out.print("Please Enter Correct Code!\t");
                    System.out.print("Number of Tries left : "+tries+"\n\n");
                    reEnter=true;
                    continue;
                }
                tries--;
                System.out.println("No records exists\n");
                System.out.println("\nWould you like to create a new Booking?");
                System.out.println("\n1) Yes");
                System.out.println("\n2) No");
                int ch;
                do {
                    ch=Integer.parseInt(this.br.readLine());
                }while (ch<1 || ch>2);
                if (ch==1) {
                    String naM=inputName();
                    this.newCust(cu, naM, in);
                }
            }
        }while(reEnter);
    }

    public String inputName()throws IOException {
        String n;
        System.out.print("\n\nEnter Name : ");
        n=this.br.readLine();
        return n;
    }
 
    public void subMenu(Customer[] cus, int custID)throws Exception {
        System.out.println("Welcome back, "+cus[custID].getName());
        System.out.println("\n\t\tCustomer Details\n\n"+cus[custID]);
        System.out.println("\n\n1) Add Rooms");
        System.out.println("\n2) Remove Rooms\n\n");
        int ch2;
        do {
            ch2=Integer.parseInt(this.br.readLine());
        }while(ch2<1 && ch2>2);
        switch (ch2) {
            case 1:
                System.out.print("Enter no of Rooms to add : ");
                int no=Integer.parseInt(this.br.readLine());
                cus[custID].owned=cus[custID].reAddRooms(no);
            break;
            case 2:
                cus[custID].owned=cus[custID].removeRooms();
            break;
        }
    }

    ////STATIC METHODS////

    public static void main(String[] args)throws Exception {
        Hotel hotel=new Hotel();
        Customer[] cust=new Customer[64];
        for (int i=0;i<cust.length;i++) {
            System.out.println("\tMain Menu\n");
            System.out.println("1) New Customer \n");
            System.out.println("2) Existing Customer \n");
            System.out.println("3) Exit \n");
            int ch1;
            String nam;
            do {
                System.out.print("Enter choice : ");
                ch1=Integer.parseInt(hotel.br.readLine());
            }while((ch1<1) || (ch1>3));
            switch(ch1) {
                case 1:
                    nam=hotel.inputName();
                    hotel.newCust(cust, nam, i);
                break;
                case 2:
                    nam=hotel.inputName();
                    hotel.valuedCust(cust, i);
                break;
                case 3:
                    System.out.println("\n\nTHANK YOU AND HAVE A NICE DAY!");
                    System.exit(0);
                break;
            }
            if (hotel.isFull()) {
                System.out.println("SORRY FOR THE INCONVINIENCE, HOTEL IS FULL!");
                System.exit(0);
            }
        }
    }

    /////CONSTRUCTORS/////

    public Hotel() {
        this.room=new Room[8][8];
        this.br=new BufferedReader(new InputStreamReader(System.in));
        String number=null;
        int no=0;
        byte roomType=0;
            for (int i=0;i<=7;i++) {
                for (int j=0;j<=7;j++) {
                    no=(i+1)*100+(j+1);
                    number=String.valueOf(no);
                    if (j<4) {
                        roomType=1;
                        room[i][j]=new Room(number, roomType);
                    }else if ((j>3)&&(j<6)) {
                        roomType=2;
                        room[i][j]=new Room(number, roomType);
                    }else {
                        roomType=3;
                        room[i][j]=new Room(number, roomType);
                    }
                }
            }
        System.out.println("\n\t\t\t\tWELCOME TO TAJ HOTEL!\n\n\n");
    }
}
