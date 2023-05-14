public class memory_test  {
  //Function to call memoryTest()
  public static void runTests()  {
    memoryTest();
  }

  //Function to test memory
  public static void memoryTest()  {
    System.out.println("---------------------------------------------------------------------------");
    memory mem = new memory();
    Longword zero = new Longword();
    Longword fifteen = new Longword();
    zero.set(0);
    fifteen.set(15);
    //Writing fifteen to zero
    mem.write(zero, fifteen);
    //Reading zero and comparing to fifteen: true if success
    System.out.println("True if success: ");
    System.out.println(fifteen.getUnsigned() == mem.read(zero).getUnsigned());
    Longword five = new Longword();
    Longword ten = new Longword();
    five.set(5);
    ten.set(10);
    //Writing ten to five
    mem.write(five, ten);
    //Reading five and comparing to ten: true if success
    System.out.println("True if success: ");
    System.out.println(ten.getUnsigned() == mem.read(five).getUnsigned());
  }
}