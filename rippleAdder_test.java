class rippleAdder_test  {
  public static void runTests()  {
    System.out.println("---------------------------------------");
    Longword a = new Longword();
    Longword b = new Longword();
    a.set(10);
    b.set(5);
    System.out.println("10 + 5 = " + rippleAdder.add(a,b).getSigned());
    System.out.println("10 - 5 = " + rippleAdder.subtract(a,b).getSigned());
    Longword c = new Longword();
    Longword d = new Longword();
    c.set(-10);
    d.set(5);
    System.out.println("-10 + 5 = " + rippleAdder.add(c,d).getSigned());
    System.out.println("-10 - 5 = " + rippleAdder.subtract(c,d).getSigned());
    Longword e = new Longword();
    Longword f = new Longword();
    e.set(10);
    f.set(-5);
    System.out.println("10 + -5 = " + rippleAdder.add(e,f).getSigned());
    System.out.println("10 - -5 = " + rippleAdder.subtract(e,f).getSigned());
    Longword g = new Longword();
    Longword h = new Longword();
    g.set(-10);
    h.set(-5);
    System.out.println("-10 + -5 = " + rippleAdder.add(g,h).getSigned());
    System.out.println("-10 - -5 = " + rippleAdder.subtract(g,h).getSigned());
  }
}