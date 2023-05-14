import java.util.Objects;

//Assembler class
public class assembler {

    // assemble function to convert string array of tokens into opcodes
    public static String[] assemble(String[] input) throws Exception {
        return lex(input);
    }

    // Lexical analyzer/parsers
    public static String[] lex(String[] command) throws Exception {
        String[] tokens = new String[1000];
        int count = 0;
        for (int i = 0; i < command.length; i++) {
            String[] split = command[i].split(" ");
            for (int x = 0; x < split.length; x++) {
                tokens[count] = split[x];
                count++;
            }
        }
        for (int i = 0; i < tokens.length; i++) {
            if (Objects.isNull(tokens[i]))
                break;
        }
        String[] output = new String[1000];
        count = 0;
        for (int i = 0; i < tokens.length; i++) {
            int countSave = count;
            String[] temp = operator(tokens, i);
            if (Objects.isNull(tokens[i]))
                break;
            for (int x = 0; x < temp.length; x++) {
                output[count] = temp[x];
                count++;
            }
            i += count - countSave - 1;
            if (Objects.isNull(tokens[i + 1]))
                break;
        }
        return output;
    }

    // Function to convert from decimal to binary
    public static String decToBinary(int n, int len) {
        int temp = n;
        if (n < 0) {
            n = n * -1;
        }
        String output = "";
        for (int i = len - 1; i >= 0; i--) {
            int k = n >> i;
            if ((k & 1) > 0) {
                output += "1";
            } else {
                output += "0";
            }
        }
        // if number is negative call twosComp
        if (temp < 0) {
            output = twosComp(new StringBuffer(output));
        }
        return output;
    }

    // Function to get twos compliment for negative numbers
    public static String twosComp(StringBuffer input) {
        int i;
        for (i = input.length() - 1; i >= 0; i--)
            if (input.charAt(i) == '1')
                break;
        if (i == -1)
            return "1" + input;
        for (int j = i - 1; j >= 0; j--) {
            if (input.charAt(j) == '1')
                input.replace(j, j + 1, "0");
            else
                input.replace(j, j + 1, "1");
        }
        return input.toString();
    }

    //Function for stack operations 
    private static String[] stackOps(String[] command, int index)  throws Exception{
        String answer[] = new String[2];
        if (command[index].toLowerCase().equals("call")) {
            answer[0] = "011010";
            answer[1] = decToBinary(Integer.parseInt(command[index+1]), 10);
        }
        if (command[index].toLowerCase().equals("push")) {
            answer[0] = "011000000000";
            String x = "";
            // for loop to get register value excluding the r
            for (int i = 1; i < command[index + 1].length(); i++) {
                x += command[index + 1].charAt(i);
            }
            // convert register value from string to int then to binary and store in answer
            // array
            int reg = Integer.parseInt(x);
            if (reg < 0 || reg > 15) {
                throw new Exception("register out of bounds (push)");
            }
            answer[1] = decToBinary(reg, 4);
        }
        if (command[index].toLowerCase().equals("pop")) {
            answer[0] = "011001000000";
            String x = "";
            // for loop to get register value excluding the r
            for (int i = 1; i < command[index + 1].length(); i++) {
                x += command[index + 1].charAt(i);
            }
            // convert register value from string to int then to binary and store in answer
            // array
            int reg = Integer.parseInt(x);
            if (reg < 0 || reg > 15) {
                throw new Exception("register out of bounds (pop)");
            }
            answer[1] = decToBinary(reg, 4);
        }
        if (command[index].toLowerCase().equals("return")) {
            return new String[] { "0110110000000000" };
        }
        return answer;
    }

    // Function for mathematical operations
    private static String[] normalOps(String[] command, int index) throws Exception {
        String answer[] = new String[4];
        // gets opcode for corresponding function and sets to first index of answer
        // array
        if (command[index].toLowerCase().equals("leftshift")) {
            answer[0] = "1100";
        }
        if (command[index].toLowerCase().equals("rightshift")) {
            answer[0] = "1101";
        }
        if (command[index].toLowerCase().equals("add")) {
            answer[0] = "1110";
        }
        if (command[index].toLowerCase().equals("subtract")) {
            answer[0] = "1111";
        }
        if (command[index].toLowerCase().equals("multiply")) {
            answer[0] = "0111";
        }
        if (command[index].toLowerCase().equals("and")) {
            answer[0] = "1000";
        }
        if (command[index].toLowerCase().equals("or")) {
            answer[0] = "1001";
        }
        if (command[index].toLowerCase().equals("xor")) {
            answer[0] = "1010";
        }
        if (command[index].toLowerCase().equals("not")) {
            answer[0] = "1011";
        }
        // iterates through next 3 indexes of command array to get all registers
        for (int i = 1; i < 4; i++) {
            String x = "";
            for (int j = 1; j < command[index + i].length(); j++) {
                x += command[index + i].charAt(j);
            }
            // converts strings from command array to int and then to binary and saves in
            // answer[1], answer[2] and answer[3]
            int reg = Integer.parseInt(x);

            if (reg < 0 || reg > 15) {
                throw new Exception("register out of bounds");
            }

            answer[i] = decToBinary(reg, 4);
        }
        return answer;
    }

    // Function for move operation
    private static String[] move(String[] command, int index) throws Exception {
        // create answer array of 3 because last index is a byte instead of 2 nibbles
        String[] answer = new String[3];
        // stores move opcode in answer array
        answer[0] = "0001";
        String x = "";
        // for loop to get register value excluding the r
        for (int i = 1; i < command[index + 1].length(); i++) {
            x += command[index + 1].charAt(i);
        }
        // convert register value from string to int then to binary and store in answer
        // array
        int reg = Integer.parseInt(x);
        if (reg < 0 || reg > 15) {
            throw new Exception("register out of bounds");
        }
        answer[1] = decToBinary(reg, 4);
        // convert 8 bit number being moved from string to int then to binary and store
        // in answer array
        int y = Integer.parseInt(command[index + 2]);
        answer[2] = decToBinary(y, 8);
        return answer;
    }

    // Function for halt function
    private static String[] halt() {
        String[] answer = new String[1];
        // convert 0 into a 16 bit binary number and store in answer array
        answer[0] = decToBinary(0, 16);
        return answer;
    }

    // Function for interrupt function
    private static String[] interrupt(String[] command, int index) {
        String[] answer = new String[2];
        // store interrupt opcode in answer array
        answer[0] = "0010";
        // convert second index of command to int then to binary and store in answer
        // array
        int x = Integer.parseInt(command[index + 1]);
        answer[1] = decToBinary(x, 12);
        return answer;
    }

    // Function to jump
    private static String[] jump(String[] command, int index) {
        String[] answer = new String[2];
        // store opcode
        answer[0] = "0011";
        int x = Integer.parseInt(command[index + 1]);
        answer[1] = decToBinary(x, 12);
        return answer;
    }

    private static String[] compare(String[] command, int index) throws Exception {
        String[] answer = new String[3];
        // stores opcode and 4 0s as first index answer array
        answer[0] = "01000000";
        String x = "";
        // for loop to get register value excluding the r
        for (int i = 1; i < command[index + 1].length(); i++) {
            x += command[index + 1].charAt(i);
        }
        // convert first register value from string to int then to binary and store in
        // answer array
        int reg = Integer.parseInt(x);
        if (reg < 0 || reg > 15) {
            throw new Exception("register out of bounds");
        }
        answer[1] = decToBinary(reg, 4);
        String y = "";
        // for loop to get register value excluding the r
        for (int i = 1; i < command[index + 2].length(); i++) {
            y += command[index + 2].charAt(i);
        }
        // convert second register value from string to int then to binary and store in
        // answer array
        int reg2 = Integer.parseInt(y);
        if (reg2 < 0 || reg2 > 15) {
            throw new Exception("register out of bounds");
        }
        answer[2] = decToBinary(reg2, 4);
        return answer;
    }

    // branch function
    private static String[] branch(String[] command, int index) throws Exception {
        String[] answer = new String[3];
        // stores opcode in first index of answer array
        answer[0] = "0101";
        // stores CC as second index in answer array based on sign
        if (command[index + 1].equals("==")) {
            answer[1] = "00";
        } else if (command[index + 1].equals(">")) {
            answer[1] = "10";
        } else if (command[index + 1].equals(">=")) {
            answer[1] = "11";
        } else if (command[index + 1].equals("!=")) {
            answer[1] = "01";
        }
        // Stores the binary of the number of memory address
        int x = Integer.parseInt(command[index + 2]);
        answer[2] = decToBinary(x, 10);
        return answer;
    }

    // Function to call functions based on string input
    private static String[] newOps(String[] command, int index) throws Exception {
        String answer[] = new String[4];
        if (command[index].toLowerCase().equals("jump")) {
            answer = new String[2];
            answer = jump(command, index);
        }
        if (command[index].toLowerCase().equals("branch")) {
            answer = new String[3];
            answer = branch(command, index);
        }
        if (command[index].toLowerCase().equals("compare")) {
            answer = new String[3];
            answer = compare(command, index);
        }
        return answer;
    }

    // Function for interrupt, halt and move
    private static String[] other(String[] command, int index) throws Exception {
        String answer[] = new String[4];
        // if user input interrupt, call interrupt function
        if (command[index].toLowerCase().equals("interrupt")) {
            answer = new String[2];
            answer = interrupt(command, index);
        }
        // if user input halt, call halt function
        if (command[index].toLowerCase().equals("halt")) {
            answer = new String[1];
            answer = halt();
        }
        // if user input move, call move function
        if (command[index].toLowerCase().equals("move")) {
            answer = new String[3];
            answer = move(command, index);
        }
        return answer;
    }

    // Function to call functions corresponding to user input
    private static String[] operator(String[] command, int index) throws Exception {
        switch (command[index].toLowerCase()) {
            case "and":
            case "or":
            case "xor":
            case "not":
            case "leftshift":
            case "rightshift":
            case "add":
            case "subtract":
            case "multiply":
                return normalOps(command, index);
            case "push":
            case "pop":
            case "return":
            case "call":
                return stackOps(command, index);
            case "halt":
            case "move":
            case "interrupt":
                return other(command, index);
            case "jump":
            case "branch":
            case "compare":
                return newOps(command, index);
        }
        return new String[1];
    }

}
