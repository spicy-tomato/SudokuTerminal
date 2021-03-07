package sudokuMvc.Model;

public class Choice {

    public int row;
    public int column;
    public int value;

    public Choice(String row, String column, String value) throws NumberFormatException {
        _setChoice(row, column, value);
    }

    private void _setChoice(String rowStr, String columnStr, String valueStr) throws NumberFormatException {
        row = _tryParseInt(rowStr);
        column = _tryParseInt(columnStr);
        value = _tryParseInt(valueStr);
    }

    private int _tryParseInt(String str) throws NumberFormatException {
        try {
            return Integer.parseInt(str);

        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ký tự vừa nhập không phải số!");
        }
    }
}
