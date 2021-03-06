package com.izforge.izpack.installer.unpacker;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.izforge.izpack.util.IoHelper;
import com.izforge.izpack.util.os.FileQueue;


/**
 * Tests the {@link DefaultFileUnpacker} class.
 *
 * @author Tim Anderson
 */
public class DefaultFileUnpackerTest extends AbstractFileUnpackerTest
{

    /**
     * Creates a pack file stream.
     *
     * @param source the source
     * @return a new stream
     * @throws IOException for any I/O error
     */
    @Override
    protected ObjectInputStream createPackStream(File source) throws IOException
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream objectOut = new ObjectOutputStream(out);
        IoHelper.copyStream(new FileInputStream(source), objectOut);
        objectOut.close();
        return new ObjectInputStream(new ByteArrayInputStream(out.toByteArray()));
    }

    /**
     * Helper to create an unpacker.
     *
     * @param sourceDir the source directory
     * @param queue     the file queue. May be {@code null}
     * @return a new unpacker
     */
    protected FileUnpacker createUnpacker(File sourceDir, FileQueue queue)
    {
        return new DefaultFileUnpacker(getCancellable(), queue);
    }

}
