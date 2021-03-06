package com.izforge.izpack.compiler;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.InputStream;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;
import org.hamcrest.collection.IsCollectionContaining;
import org.hamcrest.core.IsNot;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.izforge.izpack.compiler.container.TestCompilerContainer;
import com.izforge.izpack.core.container.AbstractContainer;
import com.izforge.izpack.matcher.ZipMatcher;
import com.izforge.izpack.test.Container;
import com.izforge.izpack.test.InstallFile;
import com.izforge.izpack.test.junit.PicoRunner;

/**
 * Test for an Izpack compilation
 */
@RunWith(PicoRunner.class)
@Container(TestCompilerContainer.class)
public class CompilerConfigSamplesTest
{
    private JarFile jar;
    private CompilerConfig compilerConfig;
    private AbstractContainer testContainer;

    public CompilerConfigSamplesTest(TestCompilerContainer container, CompilerConfig compilerConfig)
    {
        this.testContainer = container;
        this.compilerConfig = compilerConfig;
    }

    @Test
    @InstallFile("samples/izpack.xml")
    public void installerShouldContainInstallerClassResourcesAndImages() throws Exception
    {
        compilerConfig.executeCompiler();
        jar = testContainer.getComponent(JarFile.class);
        assertThat((ZipFile)jar, ZipMatcher.isZipContainingFiles(
                "com/izforge/izpack/panels/checkedhello/CheckedHelloPanel.class",
                "resources/vars",
                "com/izforge/izpack/img/JFrameIcon.png"));
    }

    @Test
    @InstallFile("samples/silverpeas/silverpeas.xml")
    public void installerShouldMergeProcessPanelCorrectly() throws Exception
    {
        compilerConfig.executeCompiler();
        jar = testContainer.getComponent(JarFile.class);
        assertThat((ZipFile)jar, ZipMatcher.isZipMatching(IsNot.not(IsCollectionContaining.hasItems(
                "com/izforge/izpack/panels/process/VariableCondition.class",
                "com/sora/panel/VimPanel.class",
                "resource/32/help-browser.png"))));
    }

    @Test
    @InstallFile("samples/silverpeas/silverpeas.xml")
    public void installerShouldConfigureSplashScreenCorrectly() throws Exception
    {
        compilerConfig.executeCompiler();
        jar = testContainer.getComponent(JarFile.class);
        assertThat((ZipFile)jar, ZipMatcher.isZipMatching(IsCollectionContaining.hasItems(
                "META-INF/Vim_splash.png")));
        ZipEntry entry = jar.getEntry("META-INF/MANIFEST.MF");
        InputStream content = jar.getInputStream(entry);
        try
        {
            List<String> list = IOUtils.readLines(content);
            assertThat(list, IsCollectionContaining.hasItem("SplashScreen-Image: META-INF/Vim_splash.png"));
        }
        finally
        {
            content.close();
        }

    }


}