public class multiplier_test  {
  public static void runTests()  {
    multiplierTest();
  }

  //method to test multiplier
  public static void multiplierTest()  {
    Longword a = new Longword();
    Longword b = new Longword();
    a.set(10);
    b.set(5);
    System.out.println("Multiplying 10 x 5 = " + multiplier.multiply(a,b).getSigned());
    Longword c = new Longword();
    Longword d = new Longword();
    c.set(-10);
    d.set(5);
    System.out.println("Multiplying -10 x 5 = " + multiplier.multiply(c,d).getSigned());
    Longword e = new Longword();
    Longword f = new Longword();
    e.set(10);
    f.set(-5);
    System.out.println("Multiplying 10 x -5 = " + multiplier.multiply(e,f).getSigned());
  }
}