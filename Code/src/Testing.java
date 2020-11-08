import java.util.ArrayList;

public class Testing {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    static int testsPassed = 0;
    static int testsTotal = 0;

    public static void main(String[] args){
        Player player = new Player(new int[]{4,4,4,4});
        Player player2 = new Player(new int[]{4,4,4,1});
        Player player3 = new Player(new int[]{4,4,4});

        Test("are 4 equal cards considered a winner", player.IsWinner(), true);
        Test("are 3 equal cards and 1 different considered a winner", player2.IsWinner(), false);
        Test("player has 3 identical cards, did they win", player3.IsWinner(), false);

        //Test("are card values read correctly from the files",
                //CardGame.CardsFromFile("test.txt"), new ArrayList<int>(Array.asList(1,2,3,4,5)));

        TestOutcome();
    }

    /**
     * Tests a given condition and compares it to the expected value.
     * If the test fails the program will tell you which test has failed.
     *
     * @param testName - short description of the test
     * @param condition - the outcome of the function
     * @param expectedValue - the expected outcome of the function
     * @author 690034975
     */
    public static void Test(String testName, Object condition, Object expectedValue){
        if (condition == expectedValue){
            testsPassed++;
        } else {
            System.out.println(ANSI_RED+testName+" has failed.");
        }
        testsTotal++;
    }

    /**
     * Calculate the result of the tests and display which have failed.
     * @author 690034975
     */
    public static void TestOutcome(){
        Print(ANSI_GREEN+testsPassed+" tests passed.");
        if (testsPassed < testsTotal) {
            Print(ANSI_RED + (testsTotal - testsPassed) + " tests failed.");
        } else {
            Print(ANSI_GREEN+"All tests passed!");
        }
    }

    /**
     * short hand for System.out.println
     * @param value - String to print
     * @author 690034975
     */
    public static void Print(String value){
        System.out.println(value);
    }

    /**
     * short hand for System.out.println
     * @param value - boolean to print
     * @author 690034975
     */
    public static void Print(boolean value){
        System.out.println(value);
    }
}
