import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

import java.io.File;
import java.io.IOException;
import java.util.function.BiFunction;
import java.util.function.Predicate;


public class CopyFileAndReplacePackageName {
    private final Logger logger = Logger.getLogger(CopyFileAndReplacePackageName.class);

    public CopyFileAndReplacePackageName() {
        try {
            // 初始化log
            Appender appender = new FileAppender(new SimpleLayout(), "CopyFileAndReplacePackageName.log");
            logger.addAppender(appender);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        CopyFileAndReplacePackageName copyFileAndReplacePackageName = new CopyFileAndReplacePackageName();

        String oldPath = "/home/worldline/IdeaProjects/bistoury-agent-common";
        String newPath = "/home/worldline/Downloads/bistoury-agent-common";

        String oldPackageName = "qunar.tc.bistoury.agent.common";
        String newPackageName = "com.worldline.uuu";


        copyFileAndReplacePackageName.replacePackageName(oldPath, newPath, oldPackageName, newPackageName);
    }



    public void replacePackageName(String oldPath, String newPath, String oldPackageName, String newPacketName)
            throws IOException {
        processDir(oldPath, newPath, ignoreTempFiles, (text, file) -> StringUtils.replace(text, oldPackageName, newPacketName));
    }

    //lambda表达式，判断文件是否需要处理
    static Predicate<File> ignoreTempFiles = new Predicate<File>() {
        @Override
        public boolean test(File file) {
            return (!file.getName().endsWith(".svn")) && (!file.getName().equals("target")) && (!file.getName().endsWith(".idea"));
        }
    };

     //lambda表达式，替换后返回新文本
    private String processFile(File file, BiFunction<String, File, String> processor) throws IOException {
        String text = FileUtils.readFileToString(file, "UTF-8");
        text = processor.apply(text, file);
        return text;
    }


    //判断路径是否为空
    private void processDir(String oldPath, String newPath, Predicate<File> predicate, BiFunction<String, File, String> processor) throws IOException {

        // 如果输入的文件目录为空，则直接退出
        if (oldPath == null || oldPath.isEmpty() || newPath == null || newPath.isEmpty()) {
            logger.error("文件路径错误");
            System.exit(0);
        }
        File oldFile = new File(oldPath);
        // 如果输入的原文件目录不存在，直接退出
        if (!oldFile.exists()) {
            logger.error("文件夹不存在");
            System.exit(0);
        }

        File fileTemp = new File(oldPath);
        _processDir(fileTemp, newPath, predicate, processor);
    }
    //递归读文件，替换，写文件
    private void _processDir(File oldDir, String newPath, Predicate<File> predicate, BiFunction<String, File, String> processor) throws IOException {

        File[] listFiles = oldDir.listFiles(); //list之前检查是否为空
        if (listFiles == null) {
            // 路径下没有文件
            return;
        }
        for (File f : listFiles) {
            if (f.isDirectory()) {
                if (predicate.test(f))
                    _processDir(f, newPath + "/" + f.getName(), predicate, processor);
            } else {
                if (predicate.test(f)) {

                    String text = processFile(f, processor);

                    FileUtils.write(new File(newPath + "/" + f.getName()), text, "UTF-8");

                }
            }
        }

    }
}
