/*
 * IzPack - Copyright 2001-2008 Julien Ponge, All Rights Reserved.
 * 
 * http://izpack.org/
 * http://izpack.codehaus.org/
 * 
 * Copyright 2004 Klaus Bartz
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.izforge.izpack.event;

import java.io.File;

import com.izforge.izpack.api.data.AutomatedInstallData;
import com.izforge.izpack.api.data.Pack;
import com.izforge.izpack.api.data.PackFile;
import com.izforge.izpack.api.event.InstallerListener;
import com.izforge.izpack.api.event.ProgressNotifiers;
import com.izforge.izpack.api.handler.AbstractUIProgressHandler;
import com.izforge.izpack.api.resource.Messages;
import com.izforge.izpack.api.resource.Resources;
import com.izforge.izpack.util.helper.SpecHelper;

/**
 * <p>
 * This class implements all methods of interface InstallerListener, but do not do anything. It can
 * be used as base class to save implementation of unneeded methods.
 * </p>
 * <p>
 * Additional there are some common helper methods which are used from the base class SpecHelper.
 * </p>
 *
 * @author Klaus Bartz
 */
public class SimpleInstallerListener implements InstallerListener
{

    /**
     * The name of the XML file that specifies the panel langpack
     */
    protected static final String LANG_FILE_NAME = "CustomActionsLang.xml";

    /**
     * The messages.
     */
    private Messages messages;

    private AutomatedInstallData installdata = null;

    private SpecHelper specHelper = null;

    /**
     * The resources.
     */
    private final Resources resources;

    /**
     * The progress notifiers.
     */
    private final ProgressNotifiers notifiers;


    /**
     * Constructs a <tt>SimpleInstallerListener</tt>.
     *
     * @param resources the resources
     */
    public SimpleInstallerListener(Resources resources)
    {
        this(resources, null, false);
    }

    /**
     * Constructs a <tt>SimpleInstallerListener</tt>.
     *
     * @param resources the resources
     * @param notifiers the progress notifiers. May be {@code null}
     */
    public SimpleInstallerListener(Resources resources, ProgressNotifiers notifiers)
    {
        this(resources, notifiers, false);
    }

    /**
     * Constructs a <tt>SimpleInstallerListener</tt>.
     *
     * @param resources     the resources
     * @param useSpecHelper if <tt>true</tt> a specification helper will be created
     */
    public SimpleInstallerListener(Resources resources, boolean useSpecHelper)
    {
        this(resources, null, useSpecHelper);
    }

    /**
     * Constructs a <tt>SimpleInstallerListener</tt>.
     *
     * @param resources     the resources
     * @param notifiers     the progress notifiers. May be {@code null}
     * @param useSpecHelper if <tt>true</tt> a specification helper will be created
     */
    public SimpleInstallerListener(Resources resources, ProgressNotifiers notifiers, boolean useSpecHelper)
    {
        super();
        if (useSpecHelper)
        {
            setSpecHelper(new SpecHelper(resources));
        }
        this.resources = resources;
        this.notifiers = notifiers;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.izforge.izpack.compiler.InstallerListener#handleFile(java.io.File,
     * com.izforge.izpack.PackFile)
     */

    public void afterFile(File file, PackFile pf) throws Exception
    {
        // Do nothing
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.izforge.izpack.compiler.InstallerListener#handleDir(java.io.File,
     * com.izforge.izpack.PackFile)
     */

    public void afterDir(File dir, PackFile pf) throws Exception
    {
        // Do nothing
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.izforge.izpack.compiler.InstallerListener#afterPacks(com.izforge.izpack.installer.AutomatedInstallData,
     * com.izforge.izpack.api.handler.AbstractUIProgressHandler)
     */

    public void afterPacks(AutomatedInstallData idata, AbstractUIProgressHandler handler)
            throws Exception
    {

        // Do nothing
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.izforge.izpack.compiler.InstallerListener#afterPack(com.izforge.izpack.Pack, int,
     * com.izforge.izpack.api.handler.AbstractUIProgressHandler)
     */

    public void afterPack(Pack pack, Integer i, AbstractUIProgressHandler handler) throws Exception
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.izforge.izpack.compiler.InstallerListener#beforePacks(com.izforge.izpack.installer.AutomatedInstallData,
     * int, com.izforge.izpack.api.handler.AbstractUIProgressHandler)
     */

    public void beforePacks(AutomatedInstallData idata, Integer npacks,
                            AbstractUIProgressHandler handler) throws Exception
    {
        if (installdata == null)
        {
            installdata = idata;
        }
        if (messages == null)
        {
            messages = idata.getMessages();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.izforge.izpack.compiler.InstallerListener#beforePack(com.izforge.izpack.Pack, int,
     * com.izforge.izpack.api.handler.AbstractUIProgressHandler)
     */

    public void beforePack(Pack pack, Integer i, AbstractUIProgressHandler handler)
            throws Exception
    {
        // Do nothing
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.izforge.izpack.installer.InstallerListener#isFileListener()
     */

    public boolean isFileListener()
    {
        // For default no.
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.izforge.izpack.installer.InstallerListener#beforeFile(java.io.File,
     * com.izforge.izpack.PackFile)
     */

    public void beforeFile(File file, PackFile pf) throws Exception
    {
        // Do nothing
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.izforge.izpack.installer.InstallerListener#beforeDir(java.io.File,
     * com.izforge.izpack.PackFile)
     */

    public void beforeDir(File dir, PackFile pf) throws Exception
    {
        // Do nothing
    }

    public void afterInstallerInitialization(AutomatedInstallData data) throws Exception
    {
        this.installdata = data;
    }

    /**
     * Returns current specification helper.
     *
     * @return current specification helper
     */
    public SpecHelper getSpecHelper()
    {
        return specHelper;
    }

    /**
     * Sets the given specification helper to the current used helper.
     *
     * @param helper specification helper which should be used
     */
    public void setSpecHelper(SpecHelper helper)
    {
        specHelper = helper;
    }

    /**
     * Returns the current installdata object.
     *
     * @return current installdata object
     */
    public AutomatedInstallData getInstalldata()
    {
        return installdata;
    }

    /**
     * Sets the installdata object.
     *
     * @param data installdata object which should be set to current
     */
    public void setInstalldata(AutomatedInstallData data)
    {
        installdata = data;
    }

    /**
     * Returns the progress notifiers.
     *
     * @return the progress notifiers. May be {@code null}
     */
    protected ProgressNotifiers getNotifiers()
    {
        return notifiers;
    }

    /**
     * Returns the progress bar caller id of this object.
     *
     * @return the progress bar caller id of this object, or {@code 0} if this is not registered
     */
    protected int getProgressBarCallerId()
    {
        return notifiers != null ? notifiers.indexOf(this) + 1 : 0;
    }

    /**
     * Sets this object as progress bar caller.
     */
    protected void setProgressBarCaller()
    {
        if (notifiers != null)
        {
            notifiers.addNotifier(this);
        }
    }

    /**
     * Returns whether this object should inform the progress bar or not.
     *
     * @return whether this object should inform the progress bar or not
     */
    protected boolean informProgressBar()
    {
        return notifiers != null && notifiers.notifyProgress();
    }

    /**
     * Returns the language dependant message from the resource CustomActionsLang.xml or the common
     * language pack for the given id. If no string will be found, the id returns.
     *
     * @param id string id for which the message should be resolved
     * @return the related language dependant message
     */
    protected String getMsg(String id)
    {
        return (messages != null) ? messages.get(id) : id;
    }

    /**
     * Returns the resources.
     *
     * @return the resources
     */
    protected Resources getResources()
    {
        return resources;
    }
}
