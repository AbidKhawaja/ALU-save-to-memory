public class cpu_test2 {
  public static void runTests() {
    System.out
        .println("-----------------------------------------------------------------------------------------------");
    branchAndCompareNotEqualsTest();
    System.out
        .println("-----------------------------------------------------------------------------------------------");
    branchAndCompareEqualsTest();
    System.out
        .println("-----------------------------------------------------------------------------------------------");
    branchAndCompareGreaterThanTest();
    System.out
        .println("-----------------------------------------------------------------------------------------------");
    branchAndCompareGreaterEqualThanTest();
  }

  // Function to test compare and branch !=
  public static void branchAndCompareNotEqualsTest() {
    computer cpu = new computer();
    String[] value = {
        "0001111100001010",
        "0001111000001100",
        "0001110100000001",
        "1110111111011111",
        "0010000000000000",
        "0100000011111110",
        "0101011111111000",
        "0010000000000000",
    };
    // Function will continue to increment by 1 until the circumstance is not true
    // => until register 15 = register 14
    cpu.preload(value);
    cpu.run();
  }

  // Function to test compare and branch ==
  public static void branchAndCompareEqualsTest() {
    computer cpu = new computer();
    String[] value = {
        "0001111100001010",
        "0001111000001100",
        "0001110100000001",
        "1110111111011111",
        "0100000011111110",
        "0101001111111000",
        "0010000000000000",
    };
    // Function will only run once because the circumstance is not proven; meaning
    // register 14 and 15 are not equal so it will not branch.
    cpu.preload(value);
    cpu.run();
  }

  // Function to test compare and branch > and jump
  public static void branchAndCompareGreaterThanTest() {
    computer cpu = new computer();
    String[] value = {
        "0011000000000100",
        "0000000000000000",
        "0001111100001110",
        "0001111000001100",
        "0001110100000001",
        "1110111111011111",
        "0010000000000000",
        "0100000011111110",
        "0101101111111000",
        "0010000000000000",
    };
    cpu.preload(value);
    cpu.run();
  }

  // Function to test compare and branch > and jump
  public static void branchAndCompareGreaterEqualThanTest() {
    computer cpu = new computer();
    String[] value = {
        "0011000000000100",
        "0000000000000000",
        "0001000100000101",
        "0001001000000101",
        "0100000000010010",
        "0101000000000100",
        "0000000000000000",
        "0010000000000000",
        "0001111100001001",
        "0001111000001110",
        "0001110100000001",
        "1110110011011100",
        "1110111111011111",
        "0010000000000000",
        "0100000011101111",
        "0101111111110110",
        "0010000000000000",
    };
    // Will continue until register 15 is greater than or equal to register 14
    cpu.preload(value);
    cpu.run();
  }
}
