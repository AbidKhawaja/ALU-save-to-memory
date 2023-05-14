public class bit  {

  //boolean variable to hold t/f value
  boolean value;

  //bit constructor to initialize a bit with false value
  public bit()  {
    value = false;
  }

  //bit constructor to initialize a bit with a specific boolean value
  public bit(boolean value)  {
    this.value = value;
  }
  
  //sets the value of the bit
  void set(boolean value)  {
    this.value = value;
  }

  //if bit is true, make it false
  //if bit is false, make it true
  void toggle()  {
    if (value)  {
      value = false;
    }
    else  {
      value = true;
    }
  }

  //sets bit value to true
  void set()  {
    value = true;
  }

  //sets bit value to false
  void clear()  {
    value = false;
  }

  //returns current boolean value
  boolean getValue()  {
    return value;
  }

  //performs AND operation on bits and returns result
  public bit and(bit other)  {
    bit answer = new bit();
    if(value == false)  {
      return answer;
    }
    if(other.getValue() == false)  {
      return answer;
    }
    answer.toggle();
    return answer;
  }
  
  //performs OR operation on bits and returns result
  public bit or(bit other)  {
    bit answer = new bit();
    answer.toggle();
    if(value)  {
      return answer;
    }
    if (other.getValue())  {
      return answer;
    }
    answer.toggle();
    return answer;
  }

  //performs XOR operation on bits and returns result
  public bit xor(bit other)  {
    bit answer = new bit();
    if(and(other).getValue())  {
      return answer;
    }
    return or(other);
  }

  //performs NOT operation on bits and returns result
  public bit not() {
    if(value == false)  {
      set();
    }
    else  {
      clear();
    }
    return this;
  }

  @Override
  //returns boolean value as string
  public String toString()
  {
    if(value)  {
      return "t";
    }
    return "f";
  }
  
}
