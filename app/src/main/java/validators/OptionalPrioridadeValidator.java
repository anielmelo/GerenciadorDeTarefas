package validators;

public class OptionalPrioridadeValidator implements Validator<String> {

    @Override
    public boolean validate(String data) {
        if (data.equals("")) return true;
        
        return (data.equals("1") || data.equals("2") || data.equals("3"));
    }
    
}
