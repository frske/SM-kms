package org.jhotdraw.samples.svg.bdd;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import java.io.File;
import java.net.URI;
import org.w3c.dom.Document;

import static org.assertj.core.api.Assertions.assertThat;

public class ThenSvgSave extends Stage<ThenSvgSave> {

    @ExpectedScenarioState
    File saveDirectory;

    @ExpectedScenarioState
    File selectedFile;

    @ExpectedScenarioState
    URI selectedURI;

    @ExpectedScenarioState
    String svgOutput;

    @ExpectedScenarioState
    Document parsedDocument;

    public ThenSvgSave the_saved_file_path_points_to(String filename) {
        assertThat(selectedURI).isEqualTo(new File(saveDirectory, filename).toURI());
        return self();
    }

    public ThenSvgSave the_original_filename_is_preserved() {
        assertThat(selectedURI).isEqualTo(selectedFile.toURI());
        return self();
    }

    public ThenSvgSave the_xml_declaration_appears_before_the_svg_content() {
        assertThat(svgOutput).startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        assertThat(svgOutput.indexOf("<?xml")).isLessThan(svgOutput.indexOf("<svg"));
        return self();
    }

    public ThenSvgSave the_svg_contains_only_one_xml_declaration() {
        assertThat(countOccurrences(svgOutput, "<?xml")).isEqualTo(1);
        return self();
    }

    public ThenSvgSave the_svg_document_can_be_parsed() {
        assertThat(parsedDocument).isNotNull();
        return self();
    }

    public ThenSvgSave the_svg_root_element_is_present() {
        assertThat(parsedDocument.getDocumentElement().getNodeName()).isEqualTo("svg");
        return self();
    }

    public ThenSvgSave the_saved_svg_contains_drawing_content() {
        assertThat(svgOutput).contains("<svg");
        return self();
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
