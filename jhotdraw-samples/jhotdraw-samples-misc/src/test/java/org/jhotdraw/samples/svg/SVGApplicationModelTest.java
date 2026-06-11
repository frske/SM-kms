package org.jhotdraw.samples.svg;

import java.io.File;
import java.net.URI;
import org.jhotdraw.api.gui.URIChooser;
import org.jhotdraw.gui.JFileURIChooser;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SVGApplicationModelTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void saveChooserAddsSVGExtensionWhenFilenameHasNoExtension() {
        JFileURIChooser chooser = createSaveChooser();
        File selectedFile = new File(folder.getRoot(), "drawing");

        chooser.setSelectedFile(selectedFile);

        assert chooser.getSelectedURI() != null : "save chooser must return a URI for a selected file";
        assertEquals(new File(folder.getRoot(), "drawing.svg").toURI(), chooser.getSelectedURI());
    }

    @Test
    public void saveChooserKeepsExistingSVGExtension() {
        JFileURIChooser chooser = createSaveChooser();
        File selectedFile = new File(folder.getRoot(), "drawing.svg");

        chooser.setSelectedFile(selectedFile);

        URI selectedURI = chooser.getSelectedURI();

        assert selectedURI != null : "save chooser must return a URI for a selected SVG file";
        assertEquals(selectedFile.toURI(), selectedURI);
    }

    @Test
    public void saveChooserKeepsExistingSVGZExtension() {
        JFileURIChooser chooser = createSaveChooser();
        File selectedFile = new File(folder.getRoot(), "drawing.svgz");

        chooser.setSelectedFile(selectedFile);
        URI selectedURI = chooser.getSelectedURI();

        assert selectedURI != null : "save chooser must return a URI for a selected SVGZ file";
        assertEquals(selectedFile.toURI(), selectedURI);
    }

    @Test
    public void fileNamingStrategyAddsSVGExtensionWhenFilenameHasNoExtension() {
        File selectedFile = new File(folder.getRoot(), "drawing");

        URI selectedURI = SVGFileNamingStrategy.appendSVGExtension(selectedFile.toURI());

        assertEquals(new File(folder.getRoot(), "drawing.svg").toURI(), selectedURI);
    }

    @Test
    public void fileNamingStrategyKeepsSVGExtensionsCaseInsensitive() {
        File selectedFile = new File(folder.getRoot(), "drawing.SVG");

        URI selectedURI = SVGFileNamingStrategy.appendSVGExtension(selectedFile.toURI());

        assertEquals(selectedFile.toURI(), selectedURI);
    }

    @Test
    public void fileNamingStrategyKeepsSVGZExtensionsCaseInsensitive() {
        File selectedFile = new File(folder.getRoot(), "drawing.SVGZ");

        URI selectedURI = SVGFileNamingStrategy.appendSVGExtension(selectedFile.toURI());

        assertEquals(selectedFile.toURI(), selectedURI);
    }

    @Test
    public void fileNamingStrategyKeepsNullURI() {
        assertNull(SVGFileNamingStrategy.appendSVGExtension(null));
    }

    private JFileURIChooser createSaveChooser() {
        SVGApplicationModel model = new SVGApplicationModel();
        URIChooser chooser = model.createSaveChooser(null, new SVGView());

        assert chooser instanceof JFileURIChooser : "SVG save chooser must be backed by JFileURIChooser";
        return (JFileURIChooser) chooser;
    }
}
