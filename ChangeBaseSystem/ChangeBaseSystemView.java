package package2;

class ChangeBaseSystemView {

    int getInput() {
        return Utility.checkInputInteger("Enter the base number input (1 for base 2, 2 for base 10, 3 for base 16)");
    }

    int getOutput() {
        return Utility.checkInputInteger("Enter the base number output (1 for base 2, 2 for base 10, 3 for base 16)");
    }

    String getValue(int baseInput) {
        String input = null;
        
        switch(baseInput) {
            case 1: // Binary
                input = Utility.checkInputString("Enter the binary number", "^[01]+$", 
                        "Invalid binary number. Only 0 and 1 are allowed");
                break;
                
            case 2: // Decimal
                input = Utility.checkInputString("Enter the decimal number", "^[0-9]+$", 
                        "Invalid decimal number. Only digits 0-9 are allowed");
                break;
            
            case 3: // Hexadecimal
                input = Utility.checkInputString("Enter the hexadecimal number", "^[0-9A-Fa-f]+$", 
                        "Invalid hexadecimal number. Only digits 0-9 and letters A-F are allowed");
                break;
        }
        
        return input;
    }
    
    void displayResult(String value, int baseInput, int baseOutput, String result) {
        System.out.println("\n========== CONVERSION RESULT ==========");
        System.out.println("Input: " + value + " (base " + getBaseName(baseInput) + ")");
        System.out.println("Output: " + result + " (base " + getBaseName(baseOutput) + ")");
        System.out.println("=======================================\n");
    }
    
    private String getBaseName(int baseCode) {
        switch(baseCode) {
            case 1: return "Binary (2)";
            case 2: return "Decimal (10)";
            case 3: return "Hexadecimal (16)";
            default: return "Unknown";
        }
    }
}