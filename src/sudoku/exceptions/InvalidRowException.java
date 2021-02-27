package sudoku.exceptions;

public class InvalidRowException extends Exception {
    private final int minValue;
    private final int maxValue;

    public InvalidRowException(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public String getMessage() {
        return String.format("Hàng nhập vào không hợp lệ! (Hàng cần lớn hơn %d và nhỏ hơn %d)", minValue, maxValue);
    }
}
