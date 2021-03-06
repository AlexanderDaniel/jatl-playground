package lachdrache;

import com.googlecode.jatl.Html;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.StringWriter;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.junit.Assert.assertEquals;

public class JatlTest {

    @BeforeClass
    public static void setUpClass() {
        XMLUnit.setIgnoreWhitespace(true);
    }

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

    @Test
    public void composeHtmlOutOfFunctions() throws Exception {
        StringBuilder expected = new StringBuilder();
        expected.append("\n");
        expected.append("<body>\n");
        expected.append("\t<h1>JATL</h1>\n");
        expected.append("\t<p>Java Anti-Template Language</p>\n");
        expected.append("\t<h1>Double Brace Initialization</h1>\n");
        expected.append("\t<p>One of the Java idioms</p></body>");
        assertEquals(expected.toString(), new DocBuilder().getDoc());
    }

    @Test
    public void testFirstSectionIndividually() throws Exception {
        DocBuilder docBuilder = new DocBuilder();
        docBuilder.firstSection();
        assertEquals("\n<h1>JATL</h1>\n<p>Java Anti-Template Language</p>", docBuilder.get());
    }

    /**
     * Maybe you get some HTML as string which you want to include in your document
     */
    @Test
    public void addInnerHtml() throws Exception {
        StringWriter htmlWriter = new StringWriter();
        new Html(htmlWriter) {{
            div().raw("<p>innerHtml</p>").end();
            done();
        }};
        assertEquals("\n<div><p>innerHtml</p>\n</div>", htmlWriter.toString());
    }

    @Test
    public void head() throws Exception {
        StringBuilder expected = new StringBuilder();
        expected.append("\n");
        expected.append("<head>\n");
        expected.append("\t<meta http-equiv=\"content-type\" content=\"application/xhtml+xml; charset=UTF-8\"/>\n");
        expected.append("\t<title>JATL</title>\n");
        expected.append("\t<link media=\"all\" rel=\"stylesheet\" type=\"text/css\" href=\"main.css\"/></head>");
        StringWriter htmlWriter = new StringWriter();
        new Html(htmlWriter) {{
            indent(indentSameLine);
            head();
            meta().httpEquiv("content-type").content("application/xhtml+xml; charset=UTF-8").end();
            title().text("JATL").end();
            link().media("all").rel("stylesheet").type("text/css").href("main.css").end();
            endAll();
            done();
        }};
        assertEquals(expected.toString(), htmlWriter.toString());
    }

    @Test
    public void testHtml() throws Exception {
        StringWriter sw = new StringWriter();
        Html html = new Html(sw);
        html.html();
        html.head().end();
        html.body().end();
        html.endAll();
        html.done();
        assertEquals("\n<html>\n\t<head>\n\t</head>\n\t<body/>\n</html>", sw.toString());
    }

    @Test
    public void brInParagraph() throws Exception {
        StringWriter sw = new StringWriter();
        new Html(sw) {{
            p().text("1st line").br().text("2nd line").end();
            done();
        }};
        assertXMLEqual("<p>1st line<br/>2nd line</p>", sw.toString());
    }

    @Test
    public void xhmtlWithNamespaceDecl() throws Exception {
        StringWriter sw = new StringWriter();
        new Html(sw) {{
            html().xmlns("http://www.w3.org/1999/xhtml");
            endAll();
            done();
        }};
        assertXMLEqual("<html xmlns=\"http://www.w3.org/1999/xhtml\"></html>", sw.toString());
    }


}
