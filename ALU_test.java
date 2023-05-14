public class ALU_test  {
  public static void runTests()  {
    doOpTests();
  }

  //method to test doOp 
  public static void doOpTests()  {
    bit[] and = {new bit(true), new bit(false), new bit(false), new bit(false)}; //1000
    Longword test1 = new Longword();
    Longword test2 = new Longword();
    test1.set(504823);
    test2.set(23591205);
    System.out.println("---------------------------------------------------------------------------");
    System.out.println("test1: " + test1);
    System.out.println("test2: " + test2);
    System.out.println("and of test1 and test2: " + ALU.doOp(and,test1,test2));
    bit[] or = {new bit(true), new bit(false), new bit(false), new bit(true)}; //1001
    System.out.println("or of test1 and test2: " + ALU.doOp(or,test1,test2));
    bit[] xor = {new bit(true), new bit(false), new bit(true), new bit(false)}; //1010
    System.out.println("xor of test1 and test2: " + ALU.doOp(xor,test1,test2));
    bit[] not = {new bit(true), new bit(false), new bit(true), new bit(true)}; //1011
    System.out.println("not of test1: " + ALU.doOp(not,test1,test2));
    bit[] left = {new bit(true), new bit(true), new bit(false), new bit(false)}; //1100
    Longword test3 = new Longword();
    Longword test4 = new Longword();
    Longword test5 = new Longword();
    test3.set(15);
    test4.set(3);
    test5.set(1);
    System.out.println("test3: " + test3);
    System.out.println("test4: " + test4);
    System.out.println("left shift of test3 by 3: " + ALU.doOp(left,test3,test4));
    bit[] right = {new bit(true), new bit(true), new bit(false), new bit(true)}; //1101
    System.out.println("right shift of test4 by 1: " + ALU.doOp(right,test4,test5));
    Longword test6 = new Longword();
    Longword test7 = new Longword();
    test6.set(10);
    test7.set(5);
    bit[] multiply = {new bit(false), new bit(true), new bit(true), new bit(true)}; //0111
    System.out.println("10 x 5 = " + ALU.doOp(multiply,test6,test7).getSigned());
    bit[] add = {new bit(true), new bit(true), new bit(true), new bit(false)}; //1110
    System.out.println("10 + 5 = " + ALU.doOp(add,test6,test7).getSigned());
    bit[] subtract = {new bit(true), new bit(true), new bit(true), new bit(true)}; //1111
    System.out.println("10 - 5 = " + ALU.doOp(subtract,test6,test7).getSigned());
  }
}