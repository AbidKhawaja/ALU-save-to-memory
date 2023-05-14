public class cpu_test3 {
    public static void runTests() {
        System.out
                .println(
                        "----------------------------------------------------------");
        stackTest1();
        System.out
                .println(
                        "----------------------------------------------------------");
        stackTest2();
    }

    //Function to convert instructions to 16 bits 
    public static String[] convert(String[] str) {
        String answer = "";
        for (String token : str) {
            answer += token;
        }
        answer = answer.replaceAll("(.{" + "16" + "})", "$1\n").trim();
        return answer.split("\n");
    }

    // Function to test call and return
    public static void stackTest1() {
      try  {
        computer cpu = new computer();
        String[] value = {
          "call 6",
          "interrupt 0",
          "halt",
          "interrupt 1",
          "return",
        };
      cpu.preload(convert(assembler.assemble(value)));
      cpu.run();
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }

    // Function to test pop push call return
    public static void stackTest2() {
        try  {
          computer cpu = new computer();
          String[] value = {
            "move r2 4",
            "move r1 5",
            "pop r3",
            "pop r3",
            "push r1",
            "push r2",
            "move r2 0",
            "move r1 0",
            "call 22",
            "interrupt 0",
            "halt",
            "pop r15",
            "pop r1",
            "pop r2",
            "push r15",
            "add r1 r2 r3",
            "return",
          };
        cpu.preload(convert(assembler.assemble(value)));
        cpu.run();
        }
        catch(Exception e) {
          e.printStackTrace();
        }
      }
}
