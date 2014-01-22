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

}
