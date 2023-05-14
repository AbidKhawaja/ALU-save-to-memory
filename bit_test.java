public class bit_test  {
  //Method to run all test functions
  public static void runTests()  {
    createTest();
    setTest1();
    toggleTest();
    setTest2();
    clearTest();
    getValueTest();
    andTest();
    orTest();
    xorTest();
    notTest();
    stringTest();
  }

  //Function to test the constructor
  public static void createTest()  {
    bit test = new bit();
    //Tests creating bits with initial true/false values
    if (test.getValue() == false)  {
      System.out.println("Constructor pass");
    }
    else  {
      System.out.println("Constructor fail");
    }
    System.out.println();
  }

  //Function to test set(boolean) method
  public static void setTest1()  {
    bit test = new bit();
    test.set(true);
    //if set method works in setting test to true : pass
    if (test.getValue())  {
      System.out.println("set pass");
    }
    //if set method fails in setting test to true : fail
    else  {
      System.out.println("set fail");
    }
    bit test2 = new bit();
    test2.set(false);
    //if set method fails in setting test2 to false : fail
    if (test2.getValue())  {
      System.out.println("set fail");
    }
    //if set method works in setting test2 to false : pass
    else  {
      System.out.println("set pass");
    }
    System.out.println();
  }

  //Function to test toggle function
  public static void toggleTest()  {
    bit test = new bit();
    //uses toggle function to change test value from false to true
    test.toggle();
    //if successful
    if (test.getValue())  {
      System.out.println("toggle pass");
    }
    //if unsuccessful
    else  {
      System.out.println("toggle fail");
    }
    //uses toggle function to set test value to false
    test.toggle();
    //if unsuccessful
    if (test.getValue())  {
      System.out.println("toggle fail");
    }
    //if successful
    else  {
      System.out.println("toggle pass");
    }
    System.out.println();
  }
  
  //Function to test set() function
  public static void setTest2()  {
    //Creates new bit with value false
    bit test = new bit();
    //uses set to make test value true
    test.set();
    
    if (test.getValue())  {
      System.out.println("set successful : pass");
    }
    else  {
      System.out.println("set unsuccessful : fail");
    }
    System.out.println();
  }

  //Function to test clear() function
  public static void clearTest()  {
    bit test = new bit();
    //Use set to make value of test true
    test.set();
    test.clear();
    //if clear is successful
    if (test.getValue() == false)  {
      System.out.println("clear successful : pass");
    }
    //if clear is unsuccessful
    else  {
      System.out.println("clear unsuccessful : fail");
    }
    System.out.println();
  }

  //Function to test getValue() function
  public static void getValueTest()  {
    bit test = new bit();
    //sets test value to true
    test.set();
    //Uses getValue to check if it is true 
    if (test.getValue() == true)  {
      System.out.println("Value is true : pass");
    }
    else  {
      System.out.println("Value is false: fail");
    }
    //clears test value to false
    test.clear();
    //Uses getValue to check if it is false
    if (test.getValue() == false)  {
      System.out.println("Value is false : pass");
    }
    else  {
      System.out.println("Value is true: fail");
    }
    System.out.println();
  }

  //Function to test and function
  public static void andTest()  {
    bit test1 = new bit(); bit test2 = new bit();
    test1.set(false); test2.set(false);
    //checks to see if and of false and false is true
    if(test1.and(test2).getValue() == false)  {
      System.out.println("and of false and false is false : pass");
    }
    else  {
      System.out.println("and of false and false is true : fail");
    }
    test2.toggle();
    //checks to see if false and true is false
    if(test1.and(test2).getValue() == false)  {
      System.out.println("and of false and true is false : pass");
    }
    else  {
      System.out.println("and of false and true is true : fail");
    }
    test1.toggle(); test2.toggle();
    //checks to see if and of true and false is false
    if(test1.and(test2).getValue() == false)  {
      System.out.println("and of true and false is false : pass");
    }
    else  {
      System.out.println("and of true and false is true : fail");
    }
    test2.toggle();
    //checks to see if and of true and true is true
    if(test1.and(test2).getValue() == true)  {
      System.out.println("and of true and true is true : pass");
    }
    else  {
      System.out.println("and of true and true is false : fail");
    }
    System.out.println();
  }

  //Function to test or function
  public static void orTest()  {
    bit test1 = new bit(); bit test2 = new bit();
    test1.set(false); test2.set(false);
    //checks to see if or of false and false is false
    if(test1.or(test2).getValue() == false)  {
      System.out.println("or of false and false is false : pass");
    }
    else  {
      System.out.println("or of false and false is true : fail");
    }
    test2.toggle();
    //checks to see if or of false and true is true
    if(test1.or(test2).getValue() == true)  {
      System.out.println("or of false and true is true : pass");
    }
    else  {
      System.out.println("or of false and true is false : fail");
    }
    test1.toggle(); test2.toggle();
    //checks to see if or of true and false is true
    if(test1.or(test2).getValue() == true)  {
      System.out.println("or of true and false is true : pass");
    }
    else  {
      System.out.println("or of true and false is false : fail");
    }
    test2.toggle();
    //checks to see if or of true and true is true
    if(test1.or(test2).getValue() == true)  {
      System.out.println("or of true and true is true : pass");
    }
    else  {
      System.out.println("or of true and true is false : fail");
    }
    System.out.println();
  }

  //Function to test xor function
  public static void xorTest()  {
    bit test1 = new bit(); bit test2 = new bit();
    test1.set(false); test2.set(false);
    if(test1.xor(test2).getValue() == false)  {
      System.out.println("xor of false and false is false : pass");
    }
    else  {
      System.out.println("xor of false and false is true : fail");
    }
    test2.toggle();
    if(test1.xor(test2).getValue() == true)  {
      System.out.println("xor of false and true is true : pass");
    }
    else  {
      System.out.println("xor of false and true is false : fail");
    }
    test1.toggle(); test2.toggle();
    if(test1.xor(test2).getValue() == true)  {
      System.out.println("xor of true and false is true : pass");
    }
    else  {
      System.out.println("xor of true and false is false : fail");
    }
    test2.toggle();
    if(test1.xor(test2).getValue() == false)  {
      System.out.println("xor of true and true is false : pass");
    }
    else  {
      System.out.println("xor of true and true is true : fail");
    }
    System.out.println();
  }

  //Function to test not function
  public static void notTest()  {
    bit test1 = new bit();
    test1.set(false);
    //checks to see if the not of false is true
    if(test1.not().getValue() == true)  {
      System.out.println("the not of false is true : pass");
    }
    else  {
      System.out.println("the not of false is false : fail");
    }
    test1.toggle();
    //checks to see if the not of true is false
    if(test1.not().getValue() == true)  {
      System.out.println("the not of true is false : pass");
    }
    else  {
      System.out.println("the not of true is true : fail");
    }
    System.out.println();
  }

  //Function to test toString
  public static void stringTest()  {
    bit test1 = new bit();
    //checks to see if toString of false is f
    if(test1.toString().equals("f"))  {
      System.out.println("the toString to false is f : pass");
    }
    else  {
      System.out.println("the toString of false is t : fail");
    }
    test1.toggle();
    //checks to see if toString of true is t
    if(test1.toString().equals("t"))  {
      System.out.println("the toString to true is t : pass");
    }
    else  {
      System.out.println("the toString of true is f : fail");
    }
  }
}