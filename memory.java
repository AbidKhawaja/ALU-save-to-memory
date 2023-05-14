public class memory  {

  //global array of bits
  public bit[] mem = new bit[8192];
  //memory constructor to set all bits to false
  public memory()  {
    for (int i = 0; i <= 8191; i++)  {
      mem[i] = new bit(false);
    }
  }
  //Function to read from a given address
  public Longword read(Longword address)  {
    Longword result = new Longword();
    //goes through the 32 bits at and after the address and sets those 32 bits into longword result
    for (long i = address.getUnsigned()*8, count = 0; i < 8192 && count < 32; i++, count++)  {
      result.setBit((int)count,mem[(int)i]);
    }
    return result;
  }

  //Function to write a longword at a given address
  public void write(Longword address, Longword value)  {
    int count = 0;
    //goes through the 32 bits at and after the address writing in the the bits from value
    for (int i = (int)address.getUnsigned()*8; i < (int)(address.getUnsigned()*8)+32; i++)  {
      mem[i] = value.getBit(count);
      count++;
    }
  }
}