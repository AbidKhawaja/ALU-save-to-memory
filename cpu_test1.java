class cpu_test1  {
  public static void runTests()  {
    System.out.println("-----------------------------------------------------------------------------------------------");
    moveTest();
    System.out.println("-----------------------------------------------------------------------------------------------");
    ALUtest();
  }

  //Function to test move
  public static void moveTest()  {
    computer cpu = new computer();
    String[] value = {"0001111110000001" , "0010000000000001" , "0010000000000000"};
    cpu.preload(value);
    cpu.run();
  }

  //Function to test ALU adding 
  public static void ALUtest()  {
    computer cpu = new computer();
    String[] value = {"0001111000000001" , "0001111100000100" , "1110111111101101", "0010000000000001" , "0010000000000000"};
    cpu.preload(value);
    cpu.run();
  }
  
}