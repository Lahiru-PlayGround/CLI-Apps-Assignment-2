import java.util.Arrays;
import java.util.Scanner;

class Assignment5A{
    private static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "Welcome to Smart Banking";
        final String CREATE_NEW_ACCOUNT = "Open New Account";
        final String DEPOSITS = "Deposit Money";
        final String WITHDROWAL = "Withdraw Money";
        final String TRANSFER = "Transfer Money";
        final String CHECK_ACCOUNT_BALANCE = "Check Account Balance";
        final String DELETE_ACCOUNT = "Drop Existing Account";

        final String ERROR_MSG = String.format("%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

        String[][] account= new String[0][];
        

        String screen = DASHBOARD;

        do{
            final String APP_TITLE = String.format("%s%s%s",COLOR_BLUE_BOLD, screen, RESET);

            System.out.println(CLEAR);
            System.out.println("\t" + APP_TITLE + "\n");
            switch(screen){
                case DASHBOARD:
                    System.out.println("\t[1]. Create New Account");
                    System.out.println("\t[2]. Deposit Money");
                    System.out.println("\t[3]. Withdraw Money");
                    System.out.println("\t[4]. Transfer Money");
                    System.out.println("\t[5]. Check Account Balance");
                    System.out.println("\t[6]. Drop Existing Account");
                    System.out.println("\t[7]. Exit\n");
                    System.out.print("\tEnter an option to continue: ");
                    int option = SCANNER.nextInt();
                    SCANNER.nextLine();

                    switch (option){
                        case 1: screen = CREATE_NEW_ACCOUNT; break;
                        case 2: screen = DEPOSITS; break;
                        case 3: screen = WITHDROWAL; break;
                        case 4: screen = TRANSFER; break;
                        case 5: screen = CHECK_ACCOUNT_BALANCE ; break;
                        case 6: screen = DELETE_ACCOUNT; break;
                        case 7: System.out.println(CLEAR); System.exit(0);
                        default: continue;
                    }
                    break;

                case CREATE_NEW_ACCOUNT:

                    // ID Validation
                    String[][] temp = new String[account.length+1][3];

                    String accountIdName =String.format("SDB%05d ", (account.length + 1));
                    for(int i = 0;i<account.length;i++){
                        temp[i][0]=account[i][0];
                    }
                    temp[temp.length-1][0]=accountIdName;
                    System.out.println("New Account ID : "+accountIdName);

                    // Name Validation

                    String name;                 
                    loop:
                    do{
                        System.out.print("Enter Customer Name : ");
                        name = SCANNER.nextLine().strip();

                        if(name.isBlank()){
                            System.out.printf(ERROR_MSG,"Name Cant be Empty");
                            continue;
                        } 
                        for (int i = 0; i < name.length(); i++) {
                            if (!(Character.isLetter(name.charAt(i)) || 
                                Character.isSpaceChar(name.charAt(i))) ) {
                                System.out.printf(ERROR_MSG, "Invalid Customer name");
                                continue loop;
                            }
                        }
                        break;

                    }while(true);

                    for(int i = 0;i<account.length;i++){
                        temp[i][1]=account[i][1];
                    }
                    temp[temp.length-1][1]=name;

                    //Initial Deposit
                    boolean valid;
                    String initialDeposit;
                    do{
                        valid =true;
                        System.out.print("Enter your Initial Deposit : ");
                        initialDeposit = SCANNER.nextLine();

                        if(Integer.valueOf(initialDeposit)<5000){
                            System.out.printf(ERROR_MSG,"Insufficient Ammount");
                            valid=false;
                        }

                    }while(!valid);

                    for(int i =0;i<account.length;i++){
                        temp[i][2]=account[i][2];
                    }
                    temp[temp.length-1] [2]= initialDeposit;
                    account=temp;
                    System.out.println();
                    System.out.printf(SUCCESS_MSG, String.format("Account Number %s: %s has been saved successfully", accountIdName, name));
                    System.out.print("Do you want to continue adding (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;
                
                default: System.exit(0);
                
            }
        }while(true);  
    }
}