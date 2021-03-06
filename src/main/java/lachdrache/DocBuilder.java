package lachdrache;

import com.googlecode.jatl.Html;
import com.googlecode.jatl.SimpleIndenter;

import java.io.StringWriter;

class DocBuilder {
    public static final SimpleIndenter INDENT_SAME_LINE = new SimpleIndenter("\n", "\t", null, null);

    private final StringWriter sw = new StringWriter();
    private final Html html = new Html(sw);

    public DocBuilder() {
        html.indent(INDENT_SAME_LINE);
    }

    void firstSection() {
        html.
                h1().text("JATL").end()
                .p().text("Java Anti-Template Language").end();
    }

    void secondSection() {
        html.h1().text("Double Brace Initialization").end();
        html.p().text("One of the Java idioms").end();
    }

    public String getDoc() {
        html.body();

        firstSection();
        secondSection();

        html.endAll();

        return get();
    }

    public String get() {
        html.done();
        return sw.toString();
    }

}
