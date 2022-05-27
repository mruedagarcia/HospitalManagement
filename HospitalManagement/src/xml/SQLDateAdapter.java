package xml;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import java.sql.Date;

public class SQLDateAdapter extends XmlAdapter<String, Date>{
 private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM--dd");
 
 @Override
 public String marshal (Date sqlDate) throws Exception{
	 return sqlDate.toLocalDate().format(formatter);
 }
 
 @Override
 public Date unmarshal (String string) throws Exception{
	 LocalDate localDate = LocalDate.parse(string,formatter);
	 return Date.valueOf(localDate);
 }


}
