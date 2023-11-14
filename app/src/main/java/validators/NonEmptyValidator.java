package validators;

public class NonEmptyValidator implements Validator<String> {
    
    @Override
    public boolean validate(String data) {
        return !data.equals("");	// RETORNA TRUE CASO O DADO NÃO SEJA VAZIO
    }
}
