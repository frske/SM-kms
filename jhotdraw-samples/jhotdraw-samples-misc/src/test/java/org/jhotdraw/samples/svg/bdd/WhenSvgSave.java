package org.jhotdraw.samples.svg.bdd;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URI;
import javax.xml.parsers.DocumentBuilderFactory;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiTask;
import org.jhotdraw.draw.DefaultDrawing;
import org.jhotdraw.gui.JFileURIChooser;
import org.jhotdraw.samples.svg.io.SVGOutputFormat;
import org.w3c.dom.Document;

public class WhenSvgSave extends Stage<WhenSvgSave> {

    @ExpectedScenarioState
    File selectedFile;

    @ExpectedScenarioState
    JFileURIChooser saveChooser;

    @ProvidedScenarioState
    URI selectedURI;

    @ProvidedScenarioState
    String svgOutput;

    @ProvidedScenarioState
    Document parsedDocument;

    public WhenSvgSave the_user_selects_the_filename_in_the_swing_save_chooser() {
        GuiActionRunner.execute(new GuiTask() {
            @Override
            protected void executeInEDT() {
                saveChooser.setSelectedFile(selectedFile);
            }
        });
        selectedURI = saveChooser.getSelectedURI();
        return self();
    }

    public WhenSvgSave the_drawing_is_saved_as_svg() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        new SVGOutputFormat().write(out, new DefaultDrawing());

        svgOutput = out.toString("UTF-8");
        return self();
    }

    public WhenSvgSave the_saved_svg_is_parsed() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        parsedDocument = factory.newDocumentBuilder().parse(new ByteArrayInputStream(svgOutput.getBytes("UTF-8")));
        return self();
    }
}
