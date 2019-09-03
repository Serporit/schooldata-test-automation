package utils;

import bo.ListCreationParameters;
import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.BeanAccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class YamlLoader {
    public static ListCreationParameters loadLcpFromFile(String path) {
        Yaml yaml = new Yaml();
        yaml.setBeanAccess(BeanAccess.FIELD);
        try {
            return yaml.load(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ListCreationParameters loadLcpFromFile(File file) {
        Yaml yaml = new Yaml();
        yaml.setBeanAccess(BeanAccess.FIELD);
        try {
            return yaml.load(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<File> readFilesFromDir(String directoryPath) { //"src/test/resources/listCreationConfigs"
        return (List<File>) FileUtils.listFiles(new File(directoryPath), new String[]{"yml"}, false);
    }

    public static List<ListCreationParameters> loadLcpListFromDir(String directoryPath){
        List<ListCreationParameters> lcpList = new ArrayList<>();
        List<File> files =readFilesFromDir(directoryPath);
        for (File file : files) {
            lcpList.add(loadLcpFromFile(file));
        }
        return lcpList;
    }

    public static Object[][] loadLcpArrayFromDir(String directoryPath){
        List<ListCreationParameters> lcpList = loadLcpListFromDir(directoryPath);
        Object[][] array = new Object[lcpList.size()][];
        for (int i = 0; i <lcpList.size(); i++) {
            array[i] = new Object[]{lcpList.get(i)};
        }
        return array;
    }


}
