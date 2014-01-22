package lachdrache;

import com.googlecode.jatl.Html;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class JatlTest {

    @Test
    public void justHtml() throws Exception {
        StringWriter htmlWriter = new StringWriter();
        new Html(htmlWriter) {{
            html();
            done();
        }};
        assertEquals("\n<html>\n</html>", htmlWriter.toString());
    }

    @Test
    public void justBody() throws Exception {
        StringWriter htmlWriter = new StringWriter();
        new Html(htmlWriter) {{
            body();
            done();
        }};
        assertEquals("\n<body/>", htmlWriter.toString());
    }

    @Test
    public void justParagraph() throws Exception {
        StringWriter htmlWriter = new StringWriter();
        new Html(htmlWriter) {{
            p();
            done();
        }};
        assertEquals("\n<p/>", htmlWriter.toString());
    }

    @Test
    public void simpleDocument() throws Exception {
        StringBuilder expected = new StringBuilder();
        expected.append("\n<h1>JATL\n</h1>");
        expected.append("\n<h1>Java Anti-Template Language\n</h1>");

        StringWriter htmlWriter = new StringWriter();
        new Html(htmlWriter) {{
            h1().text("JATL").end();
            h1().text("Java Anti-Template Language").end();
            done();
        }};
        assertEquals(expected.toString(), htmlWriter.toString());
    }


}
