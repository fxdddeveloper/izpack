package com.izforge.izpack.installer.console;

import com.izforge.izpack.api.data.AutomatedInstallData;
import com.izforge.izpack.installer.data.UninstallDataWriter;
import com.izforge.izpack.installer.requirement.RequirementsChecker;
import com.izforge.izpack.test.util.TestConsole;
import com.izforge.izpack.util.Housekeeper;

/**
 * Test implementation of the {@link ConsoleInstaller}.
 * <p/>
 * This supports running the installer against a script of input commands.
 *
 * @author Tim Anderson
 */
public class TestConsoleInstaller extends ConsoleInstaller
{

    /**
     * Constructs a <tt>TestConsoleInstaller</tt>
     *
     * @param panels       the panels
     * @param installData  the installation date
     * @param requirements the installation requirements
     * @param writer       the uninstallation data writer
     * @param console      the console
     * @param housekeeper  the house-keeper
     * @throws Exception for any error
     */
    public TestConsoleInstaller(ConsolePanels panels, AutomatedInstallData installData,
                                RequirementsChecker requirements, UninstallDataWriter writer,
                                TestConsole console, Housekeeper housekeeper)
            throws Exception
    {
        super(panels, installData, requirements, writer, console, housekeeper);
    }

    /**
     * Returns the console.
     *
     * @return the console
     */
    public TestConsole getConsole()
    {
        return (TestConsole) super.getConsole();
    }

}
