package com.izforge.izpack.installer.gui;

/**
 * Listener for {@link IzPanels} events.
 *
 * @author Tim Anderson
 */
public interface IzPanelsListener
{
    /**
     * Invoked to switch panels.
     *
     * @param newPanel the panel to switch to
     * @param oldPanel the panel to switch from, or {@code null} if there was no prior panel
     */
    void switchPanel(IzPanelView newPanel, IzPanelView oldPanel);

    /**
     * Determines if the next panel may be navigated to.
     *
     * @param enable if {@code true}, enable navigation, otherwise disable it
     */
    void setNextEnabled(boolean enable);

    /**
     * Determines if the previous panel may be navigated to.
     *
     * @param enable if {@code true}, enable navigation, otherwise disable it
     */
    void setPreviousEnabled(boolean enable);
}