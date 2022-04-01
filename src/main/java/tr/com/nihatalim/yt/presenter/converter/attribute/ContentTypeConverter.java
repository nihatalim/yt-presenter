package tr.com.nihatalim.yt.presenter.converter.attribute;

import tr.com.nihatalim.yt.core.enums.ContentType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ContentTypeConverter implements AttributeConverter<ContentType, String> {

    @Override
    public String convertToDatabaseColumn(ContentType contentType) {
        return contentType.getValue();
    }

    @Override
    public ContentType convertToEntityAttribute(String s) {
        return ContentType.fromString(s);
    }
}
