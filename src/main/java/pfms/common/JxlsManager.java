package pfms.common;

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import skyline.common.PropertyManager;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Map;

/**
 * EXCEL���.
 * User: zhanrui
 * Date: 11-9-29
 * Time: ����2:48
 * To change this template use File | Settings | File Templates.
 */
public class JxlsManager {
    private static final Logger logger = LoggerFactory.getLogger(JxlsManager.class);

     public String exportList(String filename,Map beansMap,String strFileName) {
        try {
            String reportPath = PropertyManager.getProperty("prj_root_dir");
            String templateFileName = reportPath + "/report/"+strFileName;
            outputExcel(beansMap, templateFileName, filename);
        } catch (Exception e) {
            logger.error("���������", e);
            throw new RuntimeException("���������", e);
        }
        return "true";
    }

    private void outputExcel(Map beansMap, String templateFileName, String excelFilename) throws IOException {
        ServletOutputStream os = null;
        InputStream is = null;
        try {
            XLSTransformer transformer = new XLSTransformer();
            is = new BufferedInputStream(new FileInputStream(templateFileName));
            HSSFWorkbook wb = (HSSFWorkbook) transformer.transformXLS(is, beansMap);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            os = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(excelFilename, "UTF-8"));
            response.setContentType("application/msexcel");
            wb.write(os);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                os.flush();
                os.close();
            }
            if (is != null) {
                is.close();
            }
        }
    }
    private BufferedImage getImg(String imgPathpara, String imgNamePara) {
        try {
            return ImageIO.read(new File(imgPathpara +File.separator+ imgNamePara));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
