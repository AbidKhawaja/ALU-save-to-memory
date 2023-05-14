public class Longword {

  // 32 bit array longword variable
  public bit[] longword = new bit[32];

  // Constructor for longword
  public Longword() {
    for (int i = 0; i < 32; i++) {
      longword[i] = new bit();
    }
  }

  public Longword(int i) {
    set(i);
  }

  // Constructor to set a longword for string value
  public Longword(String value) {
    for (int i = 0; i < value.length(); i++) {
      if (Character.isDigit(value.charAt(i))) {
        if (value.charAt(i) == '1') {
          longword[i] = new bit(true);
        } else {
          longword[i] = new bit(false);
        }
      } else {
        if (value.charAt(i) == 't') {
          longword[i] = new bit(true);
        } else {
          longword[i] = new bit(false);
        }
      }
    }
    for (int i = value.length(); i < 32; i++) {
      longword[i] = new bit(false);
    }
  }

  // Function to return bit at index
  public bit getBit(int i) {
    return longword[i];
  }

  // Function to set bit value at index
  public void setBit(int i, bit value) {
    longword[i] = value;
  }

  // Function to perform AND operation on 2 longwords
  public Longword and(Longword other) {
    Longword temp = new Longword();
    for (int i = 0; i < 32; i++) {
      temp.longword[i] = longword[i].and(other.getBit(i));
    }
    return temp;
  }

  // Function to perform OR operation on 2 longwords
  public Longword or(Longword other) {
    Longword temp = new Longword();
    for (int i = 0; i < 32; i++) {
      temp.longword[i] = longword[i].or(other.getBit(i));
    }
    return temp;
  }

  // Function to perform XOR operation on 2 longwords
  public Longword xor(Longword other) {
    Longword temp = new Longword();
    for (int i = 0; i < 32; i++) {
      temp.longword[i] = longword[i].xor(other.getBit(i));
    }
    return temp;
  }

  // Function to perform NOT operation on a longword
  public Longword not() {
    Longword temp = new Longword();
    for (int i = 0; i < 32; i++) {
      temp.longword[i] = longword[i].not();
    }
    return temp;
  }

  // Function to shift the longword to the right amount of times
  public Longword rightShift(int amount) {
    Longword temp = new Longword();
    // sets the added bits to false
    for (int i = 0; i < amount; i++) {
      // moves the inner bits to the right
      for (int j = 31; j > amount - 1; j--) {
        temp.setBit(j, longword[j - amount]);
      }
      temp.setBit(i, new bit());
    }
    return temp;
  }

  // Function to shift the longword to the left amount of times
  public Longword leftShift(int amount) {
    Longword temp = new Longword();
    // moves the inner bits to the left
    for (int i = 0; i < 32 - amount; i++) {
      // sets the added bits to false
      for (int j = 32 - amount; j < 32; j++) {
        temp.setBit(j, new bit());
      }
      temp.setBit(i, longword[i + amount]);
    }
    return temp;
  }

  // toString to return longword as a string
  public String toString() {
    String str = new String();
    for (int i = 0; i < 32; i++) {
      str += longword[i] + ", ";
    }
    return str;
  }

  // Function to get unsigned value of longword
  public long getUnsigned() {
    long value = 0, temp = 1;
    for (int i = 31; i >= 0; i--) {
      if (longword[i].getValue()) {
        value += temp;
      }
      temp = temp * 2;
    }
    return value;
  }

  // Function to get signed value of longword
  public int getSigned() {
    int sum = 0;
    int factor = 1;
    int x;
    for (int i = 31; i >= 0; i--) {
      if (longword[i].getValue() == true) {
        x = 1;
      } else {
        x = 0;
      }
      sum += x * factor;
      factor = factor * 2;
    }
    return sum;
  }

  // Copies value of the bits from one longword to other longword
  public void copy(Longword other) {
    for (int i = 0; i < 32; i++) {
      longword[i] = other.getBit(i);
    }
  }

  // Uses desired value to create a longword of value
  public void set(int value) {
    int count = 31;
    boolean flag = false;
    if (value < 0) {
      flag = true;
      value = value * -1;
    }
    for (int i = 31; i >= 0; i--) {
      if (value % 2 == 1) {
        longword[count] = new bit(true);
      } else {
        longword[count] = new bit(false);
      }
      value /= 2;
      count--;
    }
    if (flag == true) {
      for (int i = 0; i < 32; i++) {
        longword[i] = longword[i].not();
      }
      Longword result = new Longword();
      result.setBit(31, new bit(true));
      result = rippleAdder.add(this, result);
      longword[0] = new bit(true);
    }
  }

}