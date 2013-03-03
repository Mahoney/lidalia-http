package uk.org.lidalia.http.api.headerfield;

import uk.org.lidalia.http.api.Text;

public interface HeaderFieldDefinition<HeaderFieldType extends HeaderField> {

    HeaderFieldName getName();

    HeaderFieldValue parseValue(Text value);
}
