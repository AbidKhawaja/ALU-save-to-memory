class ALU  {

  //function to do operation based on bit given
  public static Longword doOp(bit[] operation, Longword a, Longword b)  {
    Longword result = new Longword();
    if (operation[0].getValue() == true)  { //if 1XXX
      if(operation[1].getValue() == true)  { //if 11XX
        if (operation[2].getValue() == true)  { //if 111X
          if (operation[3].getValue() == true)  { //if 1111
            result = rippleAdder.subtract(a,b);
            return result;
          }
          else  { //if 1110
            result = rippleAdder.add(a,b);
            return result;
          }
        }
        else  { //if 110X
          if (operation[3].getValue() == true)  { //if 1101
            Longword temp = new Longword();
            //lowest 5 only
            for (int i = 31; i > 26; i--)  {
              temp.setBit(i,b.getBit(i));
            }
            result = a.rightShift(temp.getSigned());
            return result;
          }
          else  { //if 1100
            Longword temp = new Longword();
            //lowest 5 only
            for (int i = 31; i > 26; i--)  { 
              temp.setBit(i,b.getBit(i));
            }
            result = a.leftShift(temp.getSigned());
            return result;
          }
        }
      }
      else  { //if 10XX
        if (operation[2].getValue() == true)  { //if 101X
          if (operation[3].getValue() == true)  { //if 1011
            result = a.not();
            return result;
          }
          else  { //if 1010
            result = a.xor(b);
            return result;
          }
        }
        else  { //if 100X
          if (operation[3].getValue() == true)  { //if 1001
            result = a.or(b);
            return result;
          }
          else  { //if 1000
            result = a.and(b);
            return result;
          }
        }
      }
    }
    else  { //if 0XXX
      result = multiplier.multiply(a,b);
      return result;
    }
  }
}