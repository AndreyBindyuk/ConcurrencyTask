package exclusivelock;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;

public class FileLockExample1 {
    public static void main(String[] args) throws IOException,
            InterruptedException {
        String tmpdir = System.getProperty("java.io.tmpdir");
        File file = new File(tmpdir, "testLocking.txt");
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
