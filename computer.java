import java.util.Stack;

//computer class 
public class computer {
  // initialize global variables and longword
  public bit halt = new bit(false);
  private memory memory = new memory();
  Longword PC = new Longword();
  Longword currentInstruction = new Longword();
  Longword[] register = new Longword[16];
  Longword op1 = new Longword();
  Longword op2 = new Longword();
  Longword result = new Longword();
  Stack<Longword> stack = new Stack<Longword>();
  Longword SP = new Longword(1020);
  private bit first = new bit(false);
  private bit second = new bit(false);

  // constructor to create registers
  public computer() {
    for (int i = 0; i < 16; i++) {
      register[i] = new Longword();
    }
  }

  // run method to run all other methods if status value is true
  public void run() {
    while (!halt.getValue()) {
      fetch();
      decode();
      execute();
      if (!halt.getValue()) {
        store();
      }
    }
  }

  // preload function to take in opcodes and do operations based on preloaded
  // longwords
  public void preload(String[] value) {
    for (int i = 0, x = 0; i < value.length; i++, x += 2) {
      memory.write(new Longword(x), new Longword(value[i]));
    }
  }

  // fetch method to read a longword from PC and store it as currentInstruction
  public void fetch() {
    currentInstruction = memory.read(PC);
    Longword temp = new Longword();
    temp.set(2);
    PC = rippleAdder.add(PC, temp);
  }

  // decode method to copy longwords from register into op1 and op2 after masking
  public void decode() {
    // opcodes
    bit[] code = new bit[4];
    for (int i = 0; i < 4; i++) {
      code[i] = currentInstruction.getBit(i);
    }
    // if halt continue
    if (code[0].getValue() == false && code[1].getValue() == false && code[2].getValue() == false
        && code[3].getValue() == false) {
      return;
    }
    // if stack
    if (code[0].getValue() == false && code[1].getValue() == true && code[2].getValue() == true
        && code[3].getValue() == false) {
      return;
    }
    // fill register to fix null pointer error
    // Longword(-268435456) is 32 bit longword of all 1
    if (register[(int) currentInstruction.and(new Longword(-1).rightShift(4)).rightShift(24)
        .getUnsigned()] == null) {
      register[(int) currentInstruction.and(new Longword(-1).rightShift(4)).rightShift(24)
          .getUnsigned()] = new Longword();
    }
    op1.copy(
        register[(int) currentInstruction.and(new Longword(-1).rightShift(4)).rightShift(24).getUnsigned()]);
    if (register[(int) currentInstruction.and(new Longword(-1).rightShift(8)).rightShift(20)
        .getUnsigned()] == null) {
      register[(int) currentInstruction.and(new Longword(-1).rightShift(8)).rightShift(20)
          .getUnsigned()] = new Longword();
    }
    op2.copy(
        register[(int) currentInstruction.and(new Longword(-1).rightShift(8)).rightShift(20).getUnsigned()]);
  }

  // execute method to pass opcode op1 and op2 into the ALU doOp and save that
  // longword as result
  public void execute() {
    // opcodes
    bit[] code = new bit[4];
    for (int i = 0; i < 4; i++) {
      code[i] = currentInstruction.getBit(i);
    }
    // if opcode is halt, set halt to true (0000)
    if (code[0].getValue() == false && code[1].getValue() == false && code[2].getValue() == false
        && code[3].getValue() == false) {
      halt = new bit(true);
    }
    // Move function (0001)
    else if (code[0].getValue() == false && code[1].getValue() == false && code[2].getValue() == false
        && code[3].getValue() == true) {
      result = currentInstruction.leftShift(8).rightShift(24);
      // if the first index is 1 and number should be negative, start padding and
      // setting all previous numbers to 1
      if (result.getBit(24).getValue() == true) {
        for (int i = 0; i < 24; i++) {
          result.setBit(i, new bit(true));
        }
      }
    }
    // (0010) if opcode is this, print longwords from memory and registers
    else if (code[0].getValue() == false && code[1].getValue() == false && code[2].getValue() == true
        && code[3].getValue() == false) {
      if (currentInstruction.getBit(15).getValue()) {
        for (int i = 0; i < 256; i++) {
          Longword current = memory.read(new Longword(i));
          System.out.println("Longword #" + i + ": " + current);
        }
      } else {
        for (int i = 0; i < 16; i++) {
          System.out.println("Register " + i + ": " + register[i].getUnsigned());
        }
      }
    }
    // (0110) if stack opcode
    else if (code[0].getValue() == false && code[1].getValue() == true && code[2].getValue() == true
        && code[3].getValue() == false) {
      // Push
      if (currentInstruction.leftShift(4).rightShift(24).getUnsigned() == 0) {

        memory.write(SP, register[(int) currentInstruction.leftShift(12).rightShift(28).getUnsigned()]);
        SP = rippleAdder.add(SP, new Longword(4));
      }
      // Call
      else if (currentInstruction.leftShift(4).rightShift(26).getUnsigned() == 2) {
        memory.write(SP, PC);
        SP = rippleAdder.add(SP, new Longword(4));
        PC = currentInstruction.leftShift(6).rightShift(22);
      }
      // Return
      else if (currentInstruction.leftShift(4).rightShift(26).getUnsigned() == 3) {
        SP = rippleAdder.add(new Longword(-4), SP);
        PC = memory.read(SP);
      }
      // Pop
      else {
        SP = rippleAdder.add(new Longword(-4), SP);
        register[(int) currentInstruction.leftShift(12).rightShift(28).getUnsigned()] = memory.read(SP);
      }
    }
    // (0100) if opcode is this, do compare
    else if (code[0].getValue() == false && code[1].getValue() == true && code[2].getValue() == false
        && code[3].getValue() == false && currentInstruction.leftShift(4).rightShift(28).getSigned() == 0) {
      if (register[currentInstruction.leftShift(8).rightShift(28).getSigned()].getUnsigned()
          - register[currentInstruction.leftShift(12).rightShift(28).getSigned()].getUnsigned() == 0) {
        first = new bit(false);
        second = new bit(false);
      } else if (register[currentInstruction.leftShift(8).rightShift(28).getSigned()].getUnsigned()
          - register[currentInstruction.leftShift(12).rightShift(28).getSigned()].getUnsigned() > 0) {
        first = new bit(false);
        second = new bit(true);
      } else { // less than
        first = new bit(true);
        second = new bit(true);
      }
    }
    // (0101) if opcode is this, do branch
    else if (code[0].getValue() == false && code[1].getValue() == true && code[2].getValue() == false
        && code[3].getValue() == true) {
      if (currentInstruction.getBit(4).getValue() == true) {
        if (currentInstruction.getBit(5).getValue() == false) { // 10 greater than
          if (first.equals(new bit(false)) && second.equals(new bit(true))) {
            first = new bit(true);
            second = new bit(false);
          }
        } else { // 11 greater than or equal to
          if (first.getValue() == false && second.getValue() == true
              || first.getValue() == false && second.getValue() == false) {
            first = new bit(true);
            second = new bit(false);
          }
        }
      }
      if (currentInstruction.getBit(4).getValue() == false) {
        if (currentInstruction.getBit(5).getValue() == false) { // 00 equal
          if (first.getValue() == false && second.getValue() == false) {
            first = new bit(true);
            second = new bit(false);
          }
        } else { // 01 not equal
          if ((first.getValue() == false && second.getValue() == true)
              || (first.getValue() == true && second.getValue() == true)) {
            first = new bit(true);
            second = new bit(false);
          }
        }
      }
    }
    // if not 0001 0000 0010 0011, call to do alu operation calls
    else {
      result = ALU.doOp(code, op1, op2);
    }
  }

  // store method to copy the register in the result longword
  public void store() {
    // opcodes
    bit[] code = new bit[4];
    for (int i = 0; i < 4; i++) {
      code[i] = currentInstruction.getBit(i);
    }
    register[(int) currentInstruction.and(new Longword(-268435456).rightShift(0)).rightShift(28)
        .getUnsigned()] = result;
    // if halt, continue (0000)
    if (code[0].getValue() == false && code[1].getValue() == false && code[2].getValue() == true
        && code[3].getValue() == false) {
      return;
    }
    // if opcode is 0001 get the number register to store from bits 4-7
    else if (code[0].getValue() == false && code[1].getValue() == false && code[2].getValue() == false
        && code[3].getValue() == true) {
      register[(int) currentInstruction.leftShift(4).rightShift(28).getUnsigned()] = result;
    }
    // if opcode is 0011, JUMP
    else if (code[0].getValue() == false && code[1].getValue() == false && code[2].getValue() == true
        && code[3].getValue() == true) {
      PC = new Longword((int) currentInstruction.leftShift(4).rightShift(20).getUnsigned());
    }
    // if opcode is 0100, nothing
    else if (code[0].getValue() == false && code[1].getValue() == true && code[2].getValue() == false
        && code[3].getValue() == false) {
      ;
    }
    //do nothing if call pop push return
    else if (code[0].getValue() == false && code[1].getValue() == true && code[2].getValue() == true
    && code[3].getValue() == false)  {
      return;
    }
    // if opcode is 0101, branch
    else if (code[0].getValue() == false && code[1].getValue() == true && code[2].getValue() == false
        && code[3].getValue() == true) {
      if (first.getValue() == true && second.getValue() == false) {
        String temp = currentInstruction.leftShift(6).rightShift(22).toString();
        temp = temp.replaceAll(" ", "");
        temp = temp.replaceAll(",", "");
        temp = temp.replaceAll("t", "1");
        temp = temp.replaceAll("f", "0");
        temp = temp.substring(22, 32);
        if (temp.charAt(0) == '1') {
          for (int i = 0; i < 22; i++) {
            temp = "1" + temp;
          }
        }
        int temp2 = (int) Long.parseLong(temp, 2);
        PC = new Longword(PC.getSigned() + temp2);
      }
    }
    // if not 0000 or 0001
    // else get the register index to store from bits 12-15
    else {
      register[(int) currentInstruction.leftShift(12).rightShift(28).getUnsigned()] = result;
    }
  }
}