import java.io.FileInputStream;
import java.io.FileOutputStream;

public class JavaIOOperations {

    public static void main(String[] args) {
        try {
            FileInputStream inputStream = new
                    FileInputStream("/home/mandeep/Documents/getAheadWithMe/LowLevelDesign/Concurrency/src/sampleInputFile.txt");
            int fileContent = 0;
            while((fileContent = inputStream.read()) != -1) {
                System.out.print((char)fileContent);
            }
            inputStream.close();

            //outputStream
            FileOutputStream outputStream = new
                    FileOutputStream("/home/mandeep/Documents/getAheadWithMe/LowLevelDesign/Concurrency/src/sampleOutputFile.txt");
            String outputData = "Subscribe to Channel - MsDeep Singh";
            byte[] outputBytes = outputData.getBytes();
            outputStream.write(outputBytes);
            outputStream.close();
        } catch (Exception e) {
            System.out.println("Exception encountered: " + e);
        }
    }
}
