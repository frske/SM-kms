package org.jhotdraw.samples.svg.bdd;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.jhotdraw.api.gui.URIChooser;
import org.jhotdraw.gui.JFileURIChooser;
import org.jhotdraw.samples.svg.SVGApplicationModel;
import org.jhotdraw.samples.svg.SVGView;

import static org.assertj.core.api.Assertions.assertThat;

public class GivenSvgSave extends Stage<GivenSvgSave> {

    @ProvidedScenarioState
    File saveDirectory;

    @ProvidedScenarioState
    File selectedFile;

    @ProvidedScenarioState
    JFileURIChooser saveChooser;

    public GivenSvgSave an_svg_drawing_is_open() throws IOException {
        saveDirectory = Files.createTempDirectory("jhotdraw-svg-bdd").toFile();
        return self();
    }

    public GivenSvgSave the_svg_save_chooser_is_available() {
        SVGApplicationModel model = new SVGApplicationModel();
        URIChooser chooser = model.createSaveChooser(null, new SVGView());

        assertThat(chooser).isInstanceOf(JFileURIChooser.class);
        saveChooser = (JFileURIChooser) chooser;
        return self();
    }

    public GivenSvgSave the_user_enters_a_filename_named(String filename) {
        selectedFile = new File(saveDirectory, filename);
        return self();
    }
}
