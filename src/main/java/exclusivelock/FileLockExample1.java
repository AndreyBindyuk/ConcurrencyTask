package exclusivelock;

import sun.security.action.GetPropertyAction;

import static java.security.AccessController.doPrivileged;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.nio.file.*;

public class FileLockExample1 {
    public static void main(String[] args) throws IOException,
            InterruptedException {
        Path tempDir = Paths.get(doPrivileged(new GetPropertyAction(
                "java.io.tmpdir")));
        File file = new File(tempDir.toAbsolutePath() + File.separator
                + "testLocking.txt");
        if (file.exists())
            file.delete();
        file.createNewFile();
        FileChannel fileChannel = new RandomAccessFile(file, "rw").getChannel();
        FileLock lock = fileChannel.lock();
        fileChannel
                .write(Charset.defaultCharset().encode(CharBuffer.wrap("a")));
        fileChannel.force(false);
        Thread.sleep(20000);
        lock.release();

    }
}
