import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ByteStream {

    private static Pattern getPattern() {
        return Pattern.compile("byte|short|int|long|char|float|double|boolean|if|else|switch|" +
                "case|default|while|do|break|continue|for|try|catch|finally|throw|throws|private|protected|" +
                "public|import|package|class|interface|extends|implements|static|final|void|abstract|native|" +
                "new|return|this|super|synchronized|volatile|const|goto|instanceof|enum|assert|transient|strictfp");
    }

    static void keywordsSearcher(String fileName) {
        try {

            BufferedInputStream inFile = new BufferedInputStream(new FileInputStream(fileName));
            int readedBytes;
            StringBuffer stringsFromTestFile = new StringBuffer();

            while ((readedBytes = inFile.read()) != -1) {
                stringsFromTestFile.append((char) readedBytes);
            }
            inFile.close();

            Matcher m = getPattern().matcher(stringsFromTestFile);
            FileOutputStream outFile = new FileOutputStream("keyWords.txt");
            String stringFromBuffer;
            int count = 0;

            while (m.find()) {
                stringFromBuffer = stringsFromTestFile.substring(m.start(), m.end()) + " ";
                outFile.write(stringFromBuffer.getBytes());
                count++;
            }

            String words = "Keywords found:" + count;
            outFile.write(words.getBytes());
            outFile.close();
            System.out.println("Keywords found: " + count);
        } catch (FileNotFoundException fileNotFound) {
            System.out.println("File not found");
            fileNotFound.printStackTrace();
        } catch (IOException ioException) {
            System.out.println("IOException");
            ioException.printStackTrace();
        }
    }
}

