package ilya.lesnikov.ui.tests;

import ilya.lesnikov.ui.pages.setup.FirstStartPage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SetUpServerTest extends BaseUiTest {
    @Test
    @Tag("setup")
    public void setUpTeamCityServerTest() {
        FirstStartPage.open()
                .setUpFirstStart();
    }
}
