import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        File logFile = new File("src/logs/NormalLogs.txt");
        File outputFile = new File("src/logs/Segmented.txt");

        try {
            readAndWrite(logFile, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 一行一行读取文件，解决读取中文字符时出现乱码
     * 流的关闭顺序：先打开的后关，后打开的先关，否则有可能出现java.io.IOException: Stream closed异常
     */
    private static void readFile(File inputFile) throws IOException {
        FileInputStream fis = new FileInputStream(inputFile);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);

        String line;
        String[] arrs = null;
        while ((line = br.readLine()) != null) {
//            arrs=line.split(",");
//            System.out.println(arrs[0] + " : " + arrs[1] + " : " + arrs[2]);
            System.out.println(line);
        }

        br.close();
        isr.close();
        fis.close();
    }


    /**
     * 一行一行写入文件，解决写入中文字符时出现乱码
     * 流的关闭顺序：先打开的后关，后打开的先关，否则有可能出现java.io.IOException: Stream closed异常
     */
    private static void writeFile(String aSentence, File outputFile) throws IOException {
        // 写入中文字符时解决中文乱码问题
        FileOutputStream fos = new FileOutputStream(outputFile);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        // 写文件
        bw.write(aSentence + "\t\n");

        bw.close();
        osw.close();
        fos.close();
    }

    /**
     * 读取文件，并每读一行就把数据写入到新文件
     * @param inputFile 读取的文件
     * @param outputFile 写入的文件
     */
    private static void readAndWrite(File inputFile, File outputFile) throws IOException {

        FileInputStream fis = new FileInputStream(inputFile);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);

        FileOutputStream fos = new FileOutputStream(outputFile);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);

        String line;
        String[] arrs = null;
        while ((line = br.readLine()) != null) {
            /* * * * * * * * * * * * * * * * * * * * * * * * * * * *
             *      在这里写正则，分割读出来的每一行日志数据            *
             * * * * * * * * * * * * * * * * * * * * * * * * * * * */
//            arrs=line.split(",");
//            System.out.println(arrs[0] + " : " + arrs[1] + " : " + arrs[2]);
            // 写文件
            bw.write(line + "\t\n");
        }

        br.close();
        isr.close();
        fis.close();

        bw.close();
        osw.close();
        fos.close();
    }

}
