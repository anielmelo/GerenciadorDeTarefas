package validators;

public class PrioridadeValidator implements Validator<String> {
    
    @Override
    public boolean validate(String data) {
        if (data.equals("")) return false;
        
        return (data.equals("1") || data.equals("2") || data.equals("3"));
    }
}
