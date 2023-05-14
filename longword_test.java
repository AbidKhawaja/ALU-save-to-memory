class longword_test  {
  public static void runTests()  {
    getAndSetTest();
    System.out.println("-----------------------------------------------------------------------------------------------");
    getAndSetTest2();
    System.out.println("-----------------------------------------------------------------------------------------------");
    andOrXorNotTest();
    System.out.println("-----------------------------------------------------------------------------------------------");
    shiftTest();
    System.out.println("-----------------------------------------------------------------------------------------------");
    finalTests();
  }

  public static void getAndSetTest()  {
    Longword test1 = new Longword();
    System.out.println(test1);
    System.out.println("The bit value at index 6 is: " + test1.getBit(6));
    test1.setBit(6, new bit(true));
    System.out.println("setting bit value at 6 to true: ");
    System.out.println(test1);
    System.out.println("The bit value at index 6 is: " + test1.getBit(6));
  }

  public static void getAndSetTest2()  {
    Longword test2 = new Longword();
    for (int i = 0; i < 32; i++)  {
      test2.setBit(i,new bit(true));
    }
    System.out.println(test2);
    System.out.println("The bit value at index 31 is: " + test2.getBit(31));
    test2.setBit(31, new bit(false));
    System.out.println("setting bit value at 31 to false: ");
    System.out.println(test2);
    System.out.println("The bit value at index 31 is: " + test2.getBit(31));
  }

  public static void andOrXorNotTest()  {
    Longword test1 = new Longword();
    Longword test2 = new Longword();
    for (int i = 0; i < 32; i += 4)  {
      test1.setBit(i,new bit(true));
    }
    for (int i = 0; i < 32; i += 3)  {
      test2.setBit(i,new bit(true));
    }
    System.out.println("test1:");
    System.out.println(test1);
    System.out.println("test2:");
    System.out.println(test2);
    System.out.println("-----------------------------------------------------------------------------------------------");
    System.out.println("test1 AND test2:");
    System.out.println(test1.and(test2));
    System.out.println("test2 AND test1:");
    System.out.println(test2.and(test1));
    System.out.println("-----------------------------------------------------------------------------------------------");
    System.out.println("test1 OR test2:");
    System.out.println(test1.or(test2));
    System.out.println("test2 OR test1:");
    System.out.println(test2.or(test1));
    System.out.println("-----------------------------------------------------------------------------------------------");
    System.out.println("test1 XOR test2:");
    System.out.println(test1.xor(test2));
    System.out.println("test2 XOR test1:");
    System.out.println(test2.xor(test1));
    System.out.println("-----------------------------------------------------------------------------------------------");
    System.out.println("NOT test1");
    System.out.println(test1.not());
    System.out.println("NOT test2");
    System.out.println(test2.not());
  }

  public static void shiftTest()  {
    Longword test1 = new Longword();
    Longword test2 = new Longword();
    for (int i = 0; i < 32; i += 4)  {
      test1.setBit(i,new bit(true));
    }
    for (int i = 0; i < 32; i += 3)  {
      test2.setBit(i,new bit(true));
    }
    System.out.println("test1:");
    System.out.println(test1);
    System.out.println("test2:");
    System.out.println(test2);
    System.out.println("-----------------------------------------------------------------------------------------------");
    System.out.println("Right shift of 4 to test 1:");
    System.out.println(test1.rightShift(4));
    System.out.println("Left shift of 3 to test 2:");
    System.out.println(test2.leftShift(3));
  }

  public static void finalTests()  {
    Longword test1 = new Longword();
    Longword test2 = new Longword();
    for (int i = 0; i < 32; i += 4)  {
      test1.setBit(i,new bit(true));
    }
    for (int i = 0; i < 32; i += 3)  {
      test2.setBit(i,new bit(true));
    }
    System.out.println("test1:");
    System.out.println(test1);
    System.out.println("test2:");
    System.out.println(test2);
    System.out.println("signed value of test1: " + test1.getSigned());
    System.out.println("unsigned value of test1: " + test1.getUnsigned());
    System.out.println("signed value of test2: " + test2.getSigned());
    System.out.println("unsigned value of test2: " + test2.getUnsigned());
    System.out.println("-----------------------------------------------------------------------------------------------");
    System.out.println("test1:");
    System.out.println(test1);
    System.out.println("test2:");
    System.out.println(test2);
    Longword test3 = new Longword();
    Longword test4 = new Longword();
    System.out.println("copying test1 to test3:");
    test3.copy(test1);
    System.out.println("test1:");
    System.out.println(test1);
    System.out.println("test3:");
    System.out.println(test3);
    System.out.println("setting test4 value to 395840284: ");
    System.out.println("test4:");
    test4.set(395840284);
    System.out.println(test4);
  }
  
}