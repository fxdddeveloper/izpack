package com.izforge.izpack.compiler.merge.resolve;

import com.izforge.izpack.api.merge.Mergeable;
import com.izforge.izpack.compiler.container.TestResolveContainer;
import com.izforge.izpack.compiler.merge.panel.PanelMerge;
import com.izforge.izpack.matcher.DuplicateMatcher;
import com.izforge.izpack.matcher.MergeMatcher;
import com.izforge.izpack.matcher.ZipMatcher;
import com.izforge.izpack.test.Container;
import com.izforge.izpack.test.MergeUtils;
import com.izforge.izpack.test.junit.PicoRunner;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.zip.ZipFile;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for panel merge
 *
 * @author Anthonin Bonnefoy
 */
@RunWith(PicoRunner.class)
@Container(TestResolveContainer.class)
public class PanelMergeTest
{
    private PanelMerge panelMerge;

    private CompilerPathResolver pathResolver;

    public PanelMergeTest(CompilerPathResolver pathResolver)
    {
        this.pathResolver = pathResolver;
    }

    @Test
    public void testResolvePanelNameFromFile() throws Exception
    {
        panelMerge = pathResolver.getPanelMerge("HelloPanelTestClass");
        assertThat(panelMerge, MergeMatcher.isMergeableContainingFiles(
                "com/izforge/izpack/panels/hello/HelloPanelTestClass.class"));
    }

    @Test
    public void testResolvePanelWithCompleteNameFromFile() throws Exception
    {
        panelMerge = pathResolver.getPanelMerge("com.izforge.izpack.panels.hello.HelloPanelTestClass");
        assertThat(panelMerge, MergeMatcher.isMergeableContainingFiles(
                "com/izforge/izpack/panels/hello/HelloPanelTestClass.class"));
    }

    @Test
    public void testResolvePanelWithDependencies() throws Exception
    {
        panelMerge = pathResolver.getPanelMerge("com.izforge.izpack.panels.hello.HelloPanelTestWithDependenciesClass");
        assertThat(panelMerge, MergeMatcher.isMergeableContainingFiles(
                "com/izforge/izpack/panels/hello/HelloPanelTestWithDependenciesClass.class",
                "com/izforge/izpack/panels/depend/DependedClass.class"
        ));
    }

    @Test
    public void testGetClassNameFromPanelMergeWithFullClassGiven() throws Exception
    {
        panelMerge = pathResolver.getPanelMerge("com.izforge.izpack.panels.hello.HelloPanelTestClass");
        assertThat(panelMerge.getPanelClass().getName(), Is.is("com.izforge.izpack.panels.hello.HelloPanelTestClass"));
    }

    @Test
    public void testGetClassNameFromPanelMergeWithOnlyPanelName() throws Exception
    {
        panelMerge = pathResolver.getPanelMerge("HelloPanelTestClass");
        assertThat(panelMerge.getPanelClass().getName(), Is.is("com.izforge.izpack.panels.hello.HelloPanelTestClass"));
    }

    @Test
    public void testMergeDuplicatePanel() throws Exception
    {
        Mergeable mergeable = pathResolver.getPanelMerge("com.izforge.izpack.panels.hello.HelloPanelTestClass");
        File tempFile = MergeUtils.doDoubleMerge(mergeable);
        ZipFile tempZipFile = new ZipFile(tempFile);
        assertThat(tempZipFile, ZipMatcher.isZipMatching(
                DuplicateMatcher.isEntryUnique("com/izforge/izpack/panels/hello/HelloPanelTestClass.class")
        ));
    }
}
