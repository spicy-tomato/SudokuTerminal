package sudokuMvc.Model;

public class MutableCell extends Cell {

    public MutableCell(int value) {
        super(value, true);
    }

    @Override
    public void setValue(Choice choice) {
        value = choice.value;
    }

    @Override
    public String toString() {
        if (value != 0) {
            return String.format("[%d] ", value);
        }

        return "[-] ";
    }
}
