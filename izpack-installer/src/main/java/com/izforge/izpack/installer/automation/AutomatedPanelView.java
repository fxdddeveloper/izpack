package com.izforge.izpack.installer.automation;

import com.izforge.izpack.api.data.InstallData;
import com.izforge.izpack.api.data.Panel;
import com.izforge.izpack.api.exception.IzPackException;
import com.izforge.izpack.api.factory.ObjectFactory;
import com.izforge.izpack.api.handler.AbstractUIHandler;
import com.izforge.izpack.installer.panel.PanelView;


/**
 * Implementation of {@link PanelView} for {@link AutomatedPanelView}s.
 *
 * @author Tim Anderson
 */
public class AutomatedPanelView extends PanelView<PanelAutomation>
{
    /**
     * The handler.
     */
    private final AbstractUIHandler handler;


    /**
     * Constructs an {@code AutomatedPanelView}.
     *
     * @param panel       the panel
     * @param factory     the factory for creating the view
     * @param installData the installation data
     * @param handler     the handler
     */
    public AutomatedPanelView(Panel panel, ObjectFactory factory, InstallData installData,
                              AbstractUIHandler handler)
    {
        super(panel, PanelAutomation.class, factory, installData);
        this.handler = handler;
    }

    /**
     * Returns the PanelAutomation class corresponding to the panel's class name
     *
     * @return the corresponding {@link PanelAutomation} implementation class, or {@code null} if none is found
     */
    public Class<PanelAutomation> getViewClass()
    {
        Panel panel = getPanel();
        return getClass(panel.getClassName() + "AutomationHelper");
    }

    /**
     * Creates a new view.
     *
     * @param panel     the panel to create the view for
     * @param viewClass the view base class
     * @return the new view
     */
    @Override
    protected PanelAutomation createView(Panel panel, Class<PanelAutomation> viewClass)
    {
        Class<PanelAutomation> impl = getViewClass();
        if (impl == null)
        {
            throw new IzPackException("Automation implementation not found for panel: " + panel.getClassName());
        }
        return getFactory().create(impl);
    }

    /**
     * Returns a handler to prompt the user.
     *
     * @return the handler
     */
    @Override
    protected AbstractUIHandler getHandler()
    {
        return handler;
    }

    /**
     * Determines the behaviour when a warning is encountered during validation.
     *
     * @param message       the validation message. May be {@code null}
     * @param defaultAnswer the default response for warnings
     * @return {@code true} if the warning doesn't invalidate the panel; {@code false} if it does
     */
    @Override
    protected boolean isWarningValid(String message, boolean defaultAnswer)
    {
        if (defaultAnswer)
        {
            handler.emitNotification(message + " - ignoring");
        }
        else
        {
            handler.emitError(getMessage("data.validation.error.title"), message);
        }
        return defaultAnswer;
    }
}
