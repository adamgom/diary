package main;

import java.io.ByteArrayInputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Bean implements Externalizable {
	private static final long serialVersionUID = 6529685098267757690L;
	private String title;
	private String content;
	private Date date;
	
	public Bean(String title, String content, Date date) {
		this.title = title;
		this.content = content;
		this.date = date;
	}
	
	 public Bean() {
		 this(null, null, null);
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		DateFormat dF = new SimpleDateFormat(Configuration.dFormatLong);
		
		JsonObjectBuilder inscription = Json.createObjectBuilder();
		inscription.add("title", title);
		inscription.add("content", content);
		inscription.add("date", dF.format(date));

		out.writeUTF(inscription.build().toString());
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		DateFormat dF = new SimpleDateFormat(Configuration.dFormatShort);
		
		JsonObject insctiption = Json.createReader(new ByteArrayInputStream(in.readUTF().getBytes())).readObject();
		try {
			title = insctiption.getString("title");
			content = insctiption.getString("content");
			date = dF.parse(insctiption.getString("date"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return content;
	}
	public void setText(String text) {
		this.content = text;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
