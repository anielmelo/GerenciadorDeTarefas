package validators;

public class IndexValidator implements Validator<Integer> {

    private int max;
    private int min;

    public IndexValidator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean validate(Integer data) {
        return data <= max && data >= min;
    }
    
}
