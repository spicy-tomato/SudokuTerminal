package sudokuMvc.exceptions;

public class InvalidValueException extends Exception {

    private final int minValue;
    private final int maxValue;
    private final int value;

    public InvalidValueException(int minValue, int maxValue, int value) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = value;
    }

    @Override
    public String getMessage() {
        return String.format("Giá trị nhập vào không hợp lệ! (Giá trị cần lớn hơn %d và nhỏ hơn %d)", minValue, maxValue);
    }
}
