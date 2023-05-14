class multiplier  {

  //method to multiply 2 longwords
  public static Longword multiply (Longword a, Longword b)  {
    Longword temp = new Longword();
    for (int i = 0; i <= 31; i++)  {
      if (b.getBit(i).getValue() == true)  {
        temp = rippleAdder.add(a.leftShift(31-i),temp);
      }
    }
    return temp;
  }
}