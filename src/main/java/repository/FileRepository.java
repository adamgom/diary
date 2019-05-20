package repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Date;
import main.Bean;

public class FileRepository implements IRepository {
	private File dataDir;
	
	public FileRepository() {
		this.dataDir = new File(System.getProperty("pamietnik.engine.datefiles"));
		getFilenameList();
	}
	
	public long[] getFilenameList() {
		return Arrays.stream(dataDir.list()).mapToLong(Long::parseLong).toArray();
	}
	
	@Override
	public void save(Long date, String title, String content) {
		if (exist(date)) {
			remove(date);
		}
		ObjectOutputStream objectOutputStream = null;
		try {
			objectOutputStream = new ObjectOutputStream(new FileOutputStream(dataDir.getAbsolutePath() + "/" + dTS(date)));
			objectOutputStream.writeObject(new Bean(title, content, new Date(date)));
			objectOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				objectOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void remove(Long date) {
//		File file = new File(dataDir.getName() + "/" + dTS(date));
//		file.delete();
		new File(dataDir.getAbsolutePath() + "/" + dTS(date)).delete();
	}

	@Override
	public Bean find(Long date) {
		if (exist(date)) return loadBean(date);
		return null;
	}
	
	private boolean exist(Long date) {
		for (String file : dataDir.list()) {
			if (file.equals(dTS(date))) return true;
		}
		return false;
	}
	
	private Bean loadBean(Long date) {
		Bean bean = null;
		try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(dataDir.getAbsolutePath() + "/" + dTS(date)))) {
				bean = (Bean)objectInputStream.readObject();
//				bean = new Bean().readExternal(objectInputStream.readObject());
//				bean.setDate((Bean)objectInputStream.readObject().getClass().);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		return bean;
	}
	
	private String dTS(Long date) {
		return String.valueOf(date);
	}
}
