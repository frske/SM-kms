package org.jhotdraw.samples.svg.io;

import java.io.ByteArrayOutputStream;
import org.jhotdraw.draw.DefaultDrawing;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SVGOutputFormatTest {

    @Test
    public void writeStartsDocumentWithXMLDeclaration() throws Exception {
        String svg = writeEmptyDrawing();

        assert svg.length() > 0 : "SVG output must not be empty";
        assertTrue(svg.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
    }

    @Test
    public void writeProducesOnlyOneXMLDeclaration() throws Exception {
        String svg = writeEmptyDrawing();

        assert svg.length() > 0 : "SVG output must not be empty";
        assertEquals(1, countOccurrences(svg, "<?xml"));
    }

    private String writeEmptyDrawing() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        new SVGOutputFormat().write(out, new DefaultDrawing());

        String svg = out.toString("UTF-8");
        assert svg.contains("<svg") : "SVG output must contain the root svg element";
        return svg;
    }

    private int countOccurrences(String value, String token) {
        int count = 0;
        int index = 0;
        while ((index = value.indexOf(token, index)) != -1) {
            count++;
            index += token.length();
        }
        return count;
    }
}
