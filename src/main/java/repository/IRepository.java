package repository;

import main.Bean;

public interface IRepository {
	public void save(Long date, String title, String content);
	public void remove(Long date);
	public Bean find(Long date);
}
