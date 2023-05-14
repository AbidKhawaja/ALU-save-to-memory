//Class to test assembler
public class assembler_test {

    public static void runTests() throws Exception {
        System.out.println("-----------------------------------------------------------------------------------------------");
        mathTests();
        functionTests();
        shiftTests();
        remainderTests();
        newOpsTests();
    }

    //Testing math functions 
    public static void mathTests() throws Exception {

        String[] temp = { "add r1 r9 r7 subtract r2 r5 r4 multiply r2 r6 r12" };
        String[] test = assembler.assemble(temp);
        int count = 1;
        System.out.println(temp[0]);
        for (String a : test) {
            if (a == null)
                break;
            System.out.println(count + ": " + a);
            count++;
        }
        System.out.println("----------");
        String[] temp2 = { "subtract r5 r9 r11 add r5 r2 r4 multiply r2 r2 r14 subtract r8 r9 r10" };
        String[] test2 = assembler.assemble(temp2);
        int count2 = 1;
        System.out.println(temp2[0]);
        for (String a : test2) {
            if (a == null)
                break;
            System.out.println(count2 + ": " + a);
            count2++;
        }
        System.out.println("----------");
    }

    //Testing and or xor not
    public static void functionTests() throws Exception  {
        String[] temp = { "and r2 r5 r4 or r8 r7 r9 xor r7 r11 r14 not r12 r14 r6" };
        String[] test = assembler.assemble(temp);
        int count = 1;
        System.out.println(temp[0]);
        for (String a : test) {
            if (a == null)
                break;
            System.out.println(count + ": " + a);
            count++;
        }
        System.out.println("----------");
        String[] temp2 = { "or r5 r6 r3 not r1 r11 r14 xor r4 r9 r10 and r4 r3 r5 not r9 r6 r8" };
        String[] test2 = assembler.assemble(temp2);
        int count2 = 1;
        System.out.println(temp2[0]);
        for (String a : test2) {
            if (a == null)
                break;
            System.out.println(count2 + ": " + a);
            count2++;
        }
        System.out.println("----------");
    }

    //Testing shifting
    public static void shiftTests() throws Exception  {
        String[] temp = { "leftshift r1 r4 r2 rightshift r7 r10 r15" };
        String[] test = assembler.assemble(temp);
        int count = 1;
        System.out.println(temp[0]);
        for (String a : test) {
            if (a == null)
                break;
            System.out.println(count + ": " + a);
            count++;
        }
        System.out.println("----------");
        String[] temp2 = { "rightshift r9 r10 r14 leftshift r8 r10 r4 rightshift r9 r10 r14" };
        String[] test2 = assembler.assemble(temp2);
        int count2 = 1;
        System.out.println(temp2[0]);
        for (String a : test2) {
            if (a == null)
                break;
            System.out.println(count2 + ": " + a);
            count2++;
        }
        System.out.println("----------");
    }

    //Testing interrupt halt and move
    public static void remainderTests()  throws Exception{
        String[] temp = { "move r15 17 move r10 60 interrupt 50 halt" };
        String[] test = assembler.assemble(temp);
        int count = 1;
        System.out.println(temp[0]);
        for (String a : test) {
            if (a == null)
                break;
            System.out.println(count + ": " + a);
            count++;
        }
        System.out.println("----------");
        String[] temp2 = { "move r14 70 move r15 90 interrupt 100 halt" };
        String[] test2 = assembler.assemble(temp2);
        int count2 = 1;
        System.out.println(temp2[0]);
        for (String a : test2) {
            if (a == null)
                break;
            System.out.println(count2 + ": " + a);
            count2++;
        }
        System.out.println("----------");
    }

    //Testing new ops
    public static void newOpsTests()  throws Exception  {
        String[] temp = { "move R15 10 move r14 12 move r13 1 add r15 r13 r15 interrupt 0 compare r15 r14 branch > -8 interrupt 0 jump 2" };
        String[] test = assembler.assemble(temp);
        int count = 1;
        System.out.println(temp[0]);
        for (String a : test) {
            if (a == null)
                break;
            System.out.println(count + ": " + a);
            count++;
        }
    }

}
