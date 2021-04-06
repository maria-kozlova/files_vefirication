package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import net.lingala.zip4j.exception.ZipException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static utils.Files.*;

public class FileVerificationTests {

    String expectedData = "This is my Test File";


    @Test
    void verifyTxtFile() throws IOException {

        String txtFilePath = "src/test/resources/files/test.txt";
        String actualData = readTextFromPath(txtFilePath);
        assertThat(actualData, containsString(expectedData));
    }

    @Test
    void verifyPdfFile() throws IOException {

        String pdfFilePath = "src/test/resources/files/test.pdf";
        PDF pdf = getPdf(pdfFilePath);
        assertThat(pdf, PDF.containsText(expectedData));
    }

    @Test
    void verifyXlsFile() throws IOException {
        String xlsFilePath = "src/test/resources/files/test.xls";
        XLS xls = getXls(xlsFilePath);
        String actualData = xls.excel.getSheetAt(0).getRow(0).getCell(0).toString();
        assertThat(actualData, containsString(expectedData));
    }

    @Test
    void verifyZipFile() throws IOException, ZipException {
        String zipFilePath = "src/test/resources/files/test.zip";
        String unZipFolderPath = "src/test/resources/files/unzip";
        String unzipTxtFilePath = "src/test/resources/files/unzip/test copy.txt";
        String zipPassword = "";

        unZip(zipFilePath, unZipFolderPath, zipPassword);
        String actualData = readTextFromPath(unzipTxtFilePath);
        assertThat(actualData, containsString(expectedData));

    }
}
