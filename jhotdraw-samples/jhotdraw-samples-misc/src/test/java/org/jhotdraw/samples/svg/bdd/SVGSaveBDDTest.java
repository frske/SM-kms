package org.jhotdraw.samples.svg.bdd;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;

public class SVGSaveBDDTest extends ScenarioTest<GivenSvgSave, WhenSvgSave, ThenSvgSave> {

    @Test
    public void save_svg_with_missing_file_extension() throws Exception {
        given().an_svg_drawing_is_open()
                .and().the_svg_save_chooser_is_available()
                .and().the_user_enters_a_filename_named("drawing");

        when().the_user_selects_the_filename_in_the_swing_save_chooser();

        then().the_saved_file_path_points_to("drawing.svg");
    }

    @Test
    public void preserve_existing_svg_extension() throws Exception {
        given().an_svg_drawing_is_open()
                .and().the_svg_save_chooser_is_available()
                .and().the_user_enters_a_filename_named("drawing.svg");

        when().the_user_selects_the_filename_in_the_swing_save_chooser();

        then().the_original_filename_is_preserved();
    }

    @Test
    public void preserve_supported_compressed_svg_extension() throws Exception {
        given().an_svg_drawing_is_open()
                .and().the_svg_save_chooser_is_available()
                .and().the_user_enters_a_filename_named("drawing.svgz");

        when().the_user_selects_the_filename_in_the_swing_save_chooser();

        then().the_original_filename_is_preserved();
    }

    @Test
    public void saved_svg_is_well_formed_xml() throws Exception {
        given().an_svg_drawing_is_open();

        when().the_drawing_is_saved_as_svg()
                .and().the_saved_svg_is_parsed();

        then().the_xml_declaration_appears_before_the_svg_content()
                .and().the_svg_contains_only_one_xml_declaration()
                .and().the_svg_document_can_be_parsed()
                .and().the_svg_root_element_is_present();
    }

    @Test
    public void saved_svg_reflects_the_current_drawing_state() throws Exception {
        given().an_svg_drawing_is_open();

        when().the_drawing_is_saved_as_svg();

        then().the_saved_svg_contains_drawing_content();
    }
}
