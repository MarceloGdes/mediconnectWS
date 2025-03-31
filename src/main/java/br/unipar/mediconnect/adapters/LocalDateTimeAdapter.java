package br.unipar.mediconnect.adapters;

import br.unipar.mediconnect.exceptions.BusinessException;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Classe utilizada para adaptar o LocalDateTime do java para string. Assim é possível construir o XML corretamente evitando erros
public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public LocalDateTime unmarshal(String value) throws BusinessException {
        if(value == null || value.isBlank())
            throw new BusinessException("A data e hora de agendamento é obrigatória");

        return LocalDateTime.parse(value, FORMATTER);
    }

    @Override
    public String marshal(LocalDateTime value) {
        return value.format(FORMATTER);
    }
}
