class rippleAdder  {

  //function to add 2 longwords and return a new one
  public static Longword add(Longword a, Longword b)  {
    bit carry = new bit();
    Longword result = new Longword();
    int count = 31;
    //while loop to go through all bits in longword
    while (count >= 0){
      bit temp1 = a.getBit(count).xor(b.getBit(count)).xor(carry);
      bit temp2 = a.getBit(count).and(b.getBit(count));
      bit temp3 = carry.and(a.getBit(count).or(b.getBit(count)));
      result.setBit(count, temp1);
      carry = temp2.or(temp3);
      count--;
    }
    return result;
  }

  //function to subtract 2 longwords and return a new one
  public static Longword subtract(Longword a, Longword b)  {
    Longword result = new Longword();
    result.setBit(31, new bit(true));
    b = add(b.not(),result);
    return add(a,b);
  }
}