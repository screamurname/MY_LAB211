package package2;

import java.math.BigInteger;

class ChangeBaseSystemModel {

    String ConvertBase(int baseInput, int baseOutput, String value) {
        // Chuyển đổi cơ số đầu vào về decimal trước
        BigInteger decimalValue;
        
        switch(baseInput) {
            case 1: // Binary
                decimalValue = convertToDecimal(value, 2);
                break;
            case 2: // Decimal
                decimalValue = new BigInteger(value);
                break;
            case 3: // Hexadecimal
                decimalValue = convertToDecimal(value, 16);
                break;
            default:
                throw new IllegalArgumentException("Invalid input base");
        }
        
        // Chuyển từ decimal sang cơ số đầu ra
        switch(baseOutput) {
            case 1: // To Binary
                return convertFromDecimal(decimalValue, 2);
            case 2: // To Decimal
                return decimalValue.toString();
            case 3: // To Hexadecimal
                return convertFromDecimal(decimalValue, 16).toUpperCase();
            default:
                throw new IllegalArgumentException("Invalid output base");
        }
    }

    private BigInteger convertToDecimal(String value, int base) {
        // Bảng chữ số
        String DIGITS = "0123456789ABCDEF";
        
        // Chuẩn hóa chữ hoa
        value = value.toUpperCase();
        
        // Kết quả ban đầu = 0
        BigInteger result = BigInteger.ZERO;
        BigInteger baseBig = BigInteger.valueOf(base);
        
        // Duyệt từ trái sang phải
        for(int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            int digit = DIGITS.indexOf(c);
            
            // Kiểm tra kí tự hợp lệ
            if (digit == -1 || digit >= base) {
                throw new IllegalArgumentException("Invalid character '" + c + "' for base " + base);
            }
            
            // Công thức: result = result * base + digit
            result = result.multiply(baseBig).add(BigInteger.valueOf(digit));
        }
        return result;
    }

    private String convertFromDecimal(BigInteger decimalValue, int base) {
        if (decimalValue.equals(BigInteger.ZERO)) {
            return "0";
        }
        
        // Bảng chữ số
        String DIGITS = "0123456789ABCDEF";
        StringBuilder result = new StringBuilder();
        BigInteger baseBig = BigInteger.valueOf(base);
        BigInteger zero = BigInteger.ZERO;
        
        // Chuyển đổi bằng cách chia liên tiếp
        while (decimalValue.compareTo(zero) > 0) {
            BigInteger[] divRem = decimalValue.divideAndRemainder(baseBig);
            int remainder = divRem[1].intValue();
            result.insert(0, DIGITS.charAt(remainder));
            decimalValue = divRem[0];
        }
        
        return result.toString();
    }
}