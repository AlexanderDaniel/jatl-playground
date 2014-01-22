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
            endAll();
            done();
        }};
        assertEquals("\n<html>\n</html>", htmlWriter.toString());
    }

    @Test
    public void justBody() throws Exception {
        StringWriter htmlWriter = new StringWriter();
        new Html(htmlWriter) {{
            body();
            endAll();
            done();
        }};
        assertEquals("\n<body/>", htmlWriter.toString());
    }

    @Test
    public void justParagraph() throws Exception {
        StringWriter htmlWriter = new StringWriter();
        new Html(htmlWriter) {{
            p();
            endAll();
            done();
        }};
        assertEquals("\n<p/>", htmlWriter.toString());
    }

    @Test
    public void simpleDocument() throws Exception {
        StringBuilder expected = new StringBuilder();
        expected.append("\n<h1>JATL\n</h1>");
        expected.append("\n<h1>Java Anti-Template Language\n</h1>");
        expected.append("\n<h2>https://code.google.com/p/jatl/\n</h2>");
        expected.append("\n<p>Is an extremely lightweight efficient Java library\n</p>");

        StringWriter htmlWriter = new StringWriter();
        new Html(htmlWriter) {{
            h1().text("JATL").end();
            h1().text("Java Anti-Template Language").end();
            h2().text("https://code.google.com/p/jatl/").end();
            p().text("Is an extremely lightweight efficient Java library").end();
            endAll();
            done();
        }};
        assertEquals(expected.toString(), htmlWriter.toString());
    }

    @Test
    public void testIndenter() throws Exception {
        StringWriter htmlWriter = new StringWriter();
        new Html(htmlWriter) {{
            indent(indentOff);
            body();
                p().text("How does it look like?").end();
            endAll();
            done();
        }};
        assertEquals("<body><p>How does it look like?</p></body>", htmlWriter.toString());
    }

}
