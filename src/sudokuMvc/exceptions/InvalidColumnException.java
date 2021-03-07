package sudokuMvc.exceptions;

public class InvalidColumnException extends Exception {
    private final int minValue;
    private final int maxValue;

    public InvalidColumnException(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public String getMessage() {
        return String.format("Cột nhập vào không hợp lệ! (Cột cần lớn hơn %d và nhỏ hơn %d)", minValue, maxValue);
    }
}

