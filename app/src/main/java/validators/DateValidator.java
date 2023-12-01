package validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateValidator implements Validator<String> {
 
    @Override
    public boolean validate(String data) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.parse(data);

            Date date = dateFormat.parse(data);
            LocalDate dataInformada = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            
            LocalDate dataAtual = LocalDate.now();
            if (dataAtual.isAfter(dataInformada)) {
                return false;
            }
            
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
