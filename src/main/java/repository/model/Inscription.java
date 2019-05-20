package repository.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wpisy")
public class Inscription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "INT(11) PRIMARY KEY AUTO_INCREMENT COMMENT 'ID tabeli wpisy'")
	private Integer id;

	@Column(name = "date_time", columnDefinition = "TIMESTAMP NOT NULL COMMENT 'Data wpisu'")
	private Date date;
	
	@Column(name = "title", columnDefinition = "varchar(100) NOT NULL COMMENT 'Tytu³ wpisu'")
	private String title;
	
	@Column(name = "content", columnDefinition = "TEXT COMMENT 'Zawartoœæ wpisu'")
	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
